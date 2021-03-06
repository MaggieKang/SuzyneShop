package com.suzyne.shop.domain.order.service;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.suzyne.shop.domain.address.dao.AddressDao;
import com.suzyne.shop.domain.address.exception.BillingAddressNotFoundException;
import com.suzyne.shop.domain.address.vo.AccountAddress;
import com.suzyne.shop.domain.cart.dao.CartDao;
import com.suzyne.shop.domain.cart.vo.CartItemSearch;
import com.suzyne.shop.domain.invoice.service.InvoiceService;
import com.suzyne.shop.domain.invoice.vo.CreateNewInvoiceDto;
import com.suzyne.shop.domain.order.dao.OrderDao;
import com.suzyne.shop.domain.order.exception.OrderNotFoundException;
import com.suzyne.shop.domain.order.exception.OrderWrongStatusException;
import com.suzyne.shop.domain.order.vo.NewOrderDto;
import com.suzyne.shop.domain.order.vo.Order;
import com.suzyne.shop.domain.order.vo.OrderDetailDto;
import com.suzyne.shop.domain.order.vo.OrderDto;
import com.suzyne.shop.domain.order.vo.OrderSearch;
import com.suzyne.shop.domain.order.vo.OrderStatus;
import com.suzyne.shop.domain.order.vo.PayNowOrderDto;
import com.suzyne.shop.domain.payment.dao.PaymentDao;
import com.suzyne.shop.domain.payment.exception.PaymentNotFoundException;
import com.suzyne.shop.domain.payment.service.PaymentService;
import com.suzyne.shop.domain.payment.vo.CardPaymentResultVO;
import com.suzyne.shop.domain.payment.vo.ConvergeSaleTransctionVO;
import com.suzyne.shop.domain.payment.vo.Payment;
import com.suzyne.shop.domain.payment.vo.PaymentSearch;
import com.suzyne.shop.domain.pickup.dao.PickupTimeslotDao;
import com.suzyne.shop.domain.pickup.exception.PickupNoSlotTimeException;
import com.suzyne.shop.domain.pickup.service.PickupTimeslotService;
import com.suzyne.shop.domain.pickup.vo.PickupSlotTimeSearch;
import com.suzyne.shop.domain.pickup.vo.UpdatePickupReservation;

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
	PaymentService paymentService;

	@Autowired
	PickupTimeslotDao pickupTimeslotDao;

	@Autowired
    RestTemplate restTemplate;

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

		// 1) ?????? ?????? ???????????? ??????
		{
			PickupSlotTimeSearch pickupSlotTimeSearch = PickupSlotTimeSearch.builder()
					.storeId(newOrderDto.getStoreId())
					.slotDt(newOrderDto.getSlotDt())
					.slotTime(newOrderDto.getSlotTime())
					.build();

			int numberAvailable = pickupTimeslotDao.getNumberAvailable(pickupSlotTimeSearch);
			if(0 == numberAvailable) {
				//throw new Exception("?????? ????????? ??????????????????!!!");
			}
		}

		// 2) ?????? ??????
		{
			//???????????? ??????
			String orderId = orderDao.createOrderId(newOrderDto.getStoreId());

			//???????????? ??????
			newOrderDto.setOrderId(orderId);
			orderDao.createOrdersDetailFromCart(newOrderDto);

			orderDao.createOrdersFromCart(newOrderDto);
		}

		// 3) ???????????? ??????
		{
			CartItemSearch cartItemSearch = CartItemSearch.builder()
					.accountNo(newOrderDto.getAccountNo())
					.storeId(newOrderDto.getStoreId())
					.build();

			cartDao.deleteAllForOrder(cartItemSearch);
			//???????????? ??????
		}

		return newOrderDto;
	}

	@Transactional(rollbackFor = {RuntimeException.class})
	public PayNowOrderDto savePayNowOrder(PayNowOrderDto payNowOrderDto) throws Exception {

		// 0) ?????? ?????? ??????
		OrderDto orderDto = null;
		{
			OrderSearch orderSearch = OrderSearch.builder()
					.accountNo(payNowOrderDto.getAccountNo())
					.storeId(payNowOrderDto.getStoreId())
					.orderId(payNowOrderDto.getOrderId())
					.build();

			List<OrderDto> rOrderList= this.orderDao.findAll(orderSearch);
			if(null == rOrderList || rOrderList.isEmpty() || 0 == rOrderList.size()) {
				throw new OrderNotFoundException(orderSearch.getOrderId());
			}

			orderDto = rOrderList.get(0);

			if(!OrderStatus.ORDERED.toString().equals(orderDto.getOrderStatusCd())) {
				throw new OrderWrongStatusException(orderSearch.getOrderId());
			}

		}

		// 1) ?????? ?????? ???????????? ??????
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

		// 2) Billing Address ??????
		AccountAddress billingAddress = null;
		{
			AccountAddress accountAddress = AccountAddress.builder()
				.accountNo(payNowOrderDto.getAccountNo())
				.seq(Integer.parseInt(payNowOrderDto.getAddressId()))
				.build();
			billingAddress = this.addressDao.findById(accountAddress)
					.orElseThrow(() -> new BillingAddressNotFoundException());
		}

		// 3) Payment ??????
		Payment paymentDto = null;
		{
			PaymentSearch paymentSearch = PaymentSearch.builder()
					.accountNo(payNowOrderDto.getAccountNo())
					.paymentId(payNowOrderDto.getPaymentId())
					.build();
			paymentDto = this.paymentDao.findById(paymentSearch)
				.orElseThrow(() -> new PaymentNotFoundException());
		}

		// 4) ?????? ??????
		{

		}

		// 5) ?????? ?????? ??????
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
			//???????????? ??????
			this.orderDao.updateOrders(updateOrder);
		}

		// 7) ???????????? ??????
		CreateNewInvoiceDto resultNewInvoiceDto = null;
		{
			CreateNewInvoiceDto createNewInvoiceDto = CreateNewInvoiceDto.builder()
					.accountNo(payNowOrderDto.getAccountNo())
					.orderId(payNowOrderDto.getOrderId())
					.storeId(payNowOrderDto.getStoreId())
					.customerContactNumber(payNowOrderDto.getCustomerContactNumber())
					.billingAddress(billingAddress.getFullAddress())
					.totalPayAmount(orderDto.getGrandTotalPrice())
					.build();
			resultNewInvoiceDto = this.invoiceService.createInvoice(createNewInvoiceDto);
		}

		// 8)???????????? ????????????
		{
			UpdatePickupReservation updatePickupReservation = UpdatePickupReservation.builder()
					.accountNo(payNowOrderDto.getAccountNo())
					.storeId(payNowOrderDto.getStoreId())
					.slotDt(payNowOrderDto.getSlotDt())
					.slotTime(payNowOrderDto.getSlotTime())
					.build();
			this.pickupTimeslotService.updatePickupReservation(updatePickupReservation);
		}

		// 9) ?????? ?????? ??????
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
			//???????????? ??????
			this.orderDao.updateOrders(updateOrder);
		}



		// 10) ??????(?????????)
		{
			NumberFormat format = NumberFormat.getInstance();
			format.setMinimumIntegerDigits(2);
			format.setRoundingMode(RoundingMode.HALF_EVEN);

			ConvergeSaleTransctionVO convergeSaleTransctionVO = ConvergeSaleTransctionVO.builder()
					.sslInvoiceNumber(resultNewInvoiceDto.getInvoiceId())
					.sslCardNumber(paymentDto.getCardNo())
					.sslExpDate(paymentDto.getExpirationMonth()+paymentDto.getExpirationYear())
					.sslCvv2cvc2(paymentDto.getCardVerificationCode())
					.sslAmount(format.format(orderDto.getGrandTotalPrice()))
					.build();

			CardPaymentResultVO cardPaymentResultVO = CardPaymentResultVO.builder()
					.storeId(payNowOrderDto.getStoreId())
					.accountNo(payNowOrderDto.getAccountNo())
					.orderId(payNowOrderDto.getOrderId())
					.invoiceId(resultNewInvoiceDto.getInvoiceId())
					.build();
			this.paymentService.callConvergeForSaleTransction(convergeSaleTransctionVO, cardPaymentResultVO);
		}

		return payNowOrderDto;
	}
}
