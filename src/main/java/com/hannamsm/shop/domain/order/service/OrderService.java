package com.hannamsm.shop.domain.order.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hannamsm.shop.domain.address.dao.AddressDao;
import com.hannamsm.shop.domain.address.exception.BillingAddressNotFoundException;
import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.domain.cart.dao.CartDao;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.domain.invoice.service.InvoiceService;
import com.hannamsm.shop.domain.invoice.vo.CreateNewInvoiceDto;
import com.hannamsm.shop.domain.order.dao.OrderDao;
import com.hannamsm.shop.domain.order.vo.NewOrderDto;
import com.hannamsm.shop.domain.order.vo.Order;
import com.hannamsm.shop.domain.order.vo.OrderDetailDto;
import com.hannamsm.shop.domain.order.vo.OrderDto;
import com.hannamsm.shop.domain.order.vo.OrderSearch;
import com.hannamsm.shop.domain.order.vo.PayNowOrderDto;
import com.hannamsm.shop.domain.payment.dao.PaymentDao;
import com.hannamsm.shop.domain.payment.exception.PaymentNotFoundException;
import com.hannamsm.shop.domain.payment.vo.Payment;
import com.hannamsm.shop.domain.payment.vo.PaymentSearch;
import com.hannamsm.shop.domain.pickup.dao.PickupTimeslotDao;
import com.hannamsm.shop.domain.pickup.exception.PickupNoSlotTimeException;
import com.hannamsm.shop.domain.pickup.service.PickupTimeslotService;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtDefaultSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslotDefault;
import com.hannamsm.shop.domain.pickup.vo.UpdatePickupReservation;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	CartDao cartDao;

	@Autowired
	AddressDao addressDao;

	@Autowired
	PaymentDao paymentDao;

	@Autowired
	InvoiceService invoiceService;

	@Autowired
	PickupTimeslotService pickupTimeslotService;

	@Autowired
	PickupTimeslotDao pickupTimeslotDao;

	public int findAllCount(OrderSearch orderSearch) throws Exception {
		return this.orderDao.findAllCount(orderSearch);
	}

	public List<OrderDto> findAll(OrderSearch orderSearch) throws Exception {
		return orderDao.findAll(orderSearch);
	}

	public int findByIdCount(OrderSearch orderSearch) throws Exception {
		return orderDao.findByIdCount(orderSearch);
	}

	public List<OrderDetailDto> findById(OrderSearch orderSearch) throws Exception {
		return orderDao.findById(orderSearch);
	}

	public NewOrderDto saveCartToOrder(NewOrderDto newOrderDto) throws Exception {

		//픽업 예약 가능한지 확인
		{
			PickupSlotTimeSearch pickupSlotTimeSearch = PickupSlotTimeSearch.builder()
					.storeId(newOrderDto.getStoreId())
					.slotDt(newOrderDto.getSlotDt())
					.slotTime(newOrderDto.getSlotTime())
					.build();

			int numberAvailable = pickupTimeslotDao.getNumberAvailable(pickupSlotTimeSearch);
			if(0 == numberAvailable) {
				//throw new Exception("픽업 예약이 불가능합니다!!!");
			}
		}

		//상품 확인 err[픽업 취소]

		//주문 저장
		{
			//주문번호 생성
			String orderId = orderDao.createOrderId(newOrderDto.getStoreId());

			//주문상세 저장
			newOrderDto.setOrderId(orderId);
			orderDao.createOrdersDetailFromCart(newOrderDto);

			orderDao.createOrdersFromCart(newOrderDto);
		}

//		//픽업 업데이트
//		{
//			SimpleDateFormat sfm = new SimpleDateFormat("E"); //영문 요일 ex)Tue
//			String dayWeek = sfm.format(new Date()).toUpperCase();
//
//			PickupSlogDtDefaultSearch pickupSlogDtDefaultSearch = PickupSlogDtDefaultSearch.builder()
//					.storeId(newOrderDto.getStoreId())
//					.slotDt(newOrderDto.getSlotDt())
//					.defaultSlotTime(newOrderDto.getSlotTime())
//					.defaultDayWeek(dayWeek)
//					.build();
//			// 픽업기본설정 조회
//			PickupTimeslotDefault pickupTimeslotDefault = pickupTimeslotDao.findBySlotDtDefaultByDayTime(pickupSlogDtDefaultSearch)
//					.orElseThrow();
//
//			UpdatePickupReservation updatePickupReservation = UpdatePickupReservation.builder()
//					.accountNo(newOrderDto.getAccountNo())
//					.storeId(newOrderDto.getStoreId())
//					.slotDt(newOrderDto.getSlotDt())
//					.slotTime(newOrderDto.getSlotTime())
//					.allocationQty(pickupTimeslotDefault.getAllocationQty())
//					.build();
//			pickupTimeslotDao.updatePickupReservation(updatePickupReservation);
//		}

		//장바구니 삭제
		{
			CartItemSearch cartItemSearch = CartItemSearch.builder()
					.accountNo(newOrderDto.getAccountNo())
					.storeId(newOrderDto.getStoreId())
					.build();

			cartDao.deleteAllForOrder(cartItemSearch);
			//저장정보 조회
		}

		return newOrderDto;
	}

	@Transactional(rollbackFor = {RuntimeException.class})
	public PayNowOrderDto savePayNowOrder(PayNowOrderDto payNowOrderDto) throws Exception {

		// 1) 픽업 예약 가능한지 확인
		{
			PickupSlotTimeSearch pickupSlotTimeSearch = PickupSlotTimeSearch.builder()
					.storeId(payNowOrderDto.getStoreId())
					.slotDt(payNowOrderDto.getSlotDt())
					.slotTime(payNowOrderDto.getSlotTime())
					.build();

			int numberAvailable = pickupTimeslotDao.getNumberAvailable(pickupSlotTimeSearch);
			if(0 == numberAvailable) {
				throw new PickupNoSlotTimeException();
			}
		}

		// 2) Billing Address 확인
		AccountAddress billingAddress = null;
		{
			AccountAddress accountAddress = AccountAddress.builder()
				.accountNo(payNowOrderDto.getAccountNo())
				.seq(Integer.parseInt(payNowOrderDto.getAddressId()))
				.build();
			billingAddress = this.addressDao.findById(accountAddress)
					.orElseThrow(() -> new BillingAddressNotFoundException());
		}

		// 3) Payment 확인
		Payment paymentDto = null;
		{
			PaymentSearch paymentSearch = PaymentSearch.builder()
					.accountNo(payNowOrderDto.getAccountNo())
					.paymentId(payNowOrderDto.getPaymentId())
					.build();
			paymentDto = this.paymentDao.findById(paymentSearch)
				.orElseThrow(() -> new PaymentNotFoundException());
		}

		// 4) 상품 확인
		{

		}

		// 5) 주문 상태 변경
		{
			Order updateOrder = Order.builder()
				.accountNo(payNowOrderDto.getAccountNo())
				.orderId(payNowOrderDto.getOrderId())
				.storeId(payNowOrderDto.getStoreId())
				.slotDt(payNowOrderDto.getSlotDt())
				.slotTime(payNowOrderDto.getSlotTime())
				.orderStatusCd("PAY")
				.lastModPerson(String.valueOf(payNowOrderDto.getAccountNo()))
				.build();
			//저장정보 조회
			this.orderDao.updateOrders(updateOrder);
		}

		// 6) 결제(대외계)
		{
			System.out.println("결제(대외계) ===> "+paymentDto.toString());
		}


		// 7) 인보이스 생성
		CreateNewInvoiceDto resultNewInvoiceDto = null;
		{
			CreateNewInvoiceDto createNewInvoiceDto = CreateNewInvoiceDto.builder()
					.accountNo(payNowOrderDto.getAccountNo())
					.orderId(payNowOrderDto.getOrderId())
					.storeId(payNowOrderDto.getStoreId())
					.customerContactNumber(payNowOrderDto.getCustomerContactNumber())
					.billingAddress(billingAddress.getFullAddress())
					.totalPayAmount(new BigDecimal(0))
					.build();
			resultNewInvoiceDto = this.invoiceService.createInvoice(createNewInvoiceDto);
		}

		// 8) 주문 상태 변경
		{
			Order updateOrder = Order.builder()
				.accountNo(payNowOrderDto.getAccountNo())
				.orderId(payNowOrderDto.getOrderId())
				.storeId(payNowOrderDto.getStoreId())
				.slotDt(payNowOrderDto.getSlotDt())
				.slotTime(payNowOrderDto.getSlotTime())
				.invoiceId(resultNewInvoiceDto.getInvoiceId())
				.orderStatusCd("PAID")
				.lastModPerson(String.valueOf(payNowOrderDto.getAccountNo()))
				.build();
			//저장정보 조회
			this.orderDao.updateOrders(updateOrder);
		}

		//픽업예약 업데이트
		{
			UpdatePickupReservation updatePickupReservation = UpdatePickupReservation.builder()
					.accountNo(payNowOrderDto.getAccountNo())
					.storeId(payNowOrderDto.getStoreId())
					.slotDt(payNowOrderDto.getSlotDt())
					.slotTime(payNowOrderDto.getSlotTime())
					.build();
			this.pickupTimeslotService.updatePickupReservation(updatePickupReservation);
		}

		return payNowOrderDto;
	}
}
