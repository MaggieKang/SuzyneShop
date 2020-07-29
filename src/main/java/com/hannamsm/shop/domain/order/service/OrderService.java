package com.hannamsm.shop.domain.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.cart.dao.CartDao;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.domain.order.dao.OrderDao;
import com.hannamsm.shop.domain.order.vo.NewOrderDto;
import com.hannamsm.shop.domain.order.vo.Order;
import com.hannamsm.shop.domain.order.vo.OrderDetail;
import com.hannamsm.shop.domain.order.vo.OrderPickup;
import com.hannamsm.shop.domain.order.vo.OrderSearch;
import com.hannamsm.shop.domain.pickup.dao.PickupTimeslotDao;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;

	@Autowired
	CartDao cartDao;

	@Autowired
	PickupTimeslotDao pickupTimeslotDao;

	public int findAllCount(OrderSearch orderSearch) throws Exception {
		return this.orderDao.findAllCount(orderSearch);
	}

	public List<Order> findAll(OrderSearch orderSearch) throws Exception {
		return orderDao.findAll(orderSearch);
	}

	public int findByIdCount(OrderSearch orderSearch) throws Exception {
		return orderDao.findByIdCount(orderSearch);
	}

	public List<OrderDetail> findById(OrderSearch orderSearch) throws Exception {
		return orderDao.findById(orderSearch);
	}

	public NewOrderDto saveCartToOrder(NewOrderDto newOrderDto) throws Exception {

		PickupSlotTimeSearch pickupSlotTimeSearch = PickupSlotTimeSearch.builder()
				.storeId(newOrderDto.getStoreId())
				.slotDt(newOrderDto.getSlotDt())
				.slotTime(newOrderDto.getSlotTime())
				.build();

		//픽업 예약 가능한지 확인
		int numberAvailable = pickupTimeslotDao.getNumberAvailable(pickupSlotTimeSearch);
		if(0 == numberAvailable) {
			throw new Exception("픽업 예약이 불가능합니다!!!");
		}

		//상품 확인 err[픽업 취소]

		//주문번호 생성
		String orderId = orderDao.createOrderId();

		//주문상세 저장
		newOrderDto.setOrderId(orderId);
		orderDao.createOrdersDetailFromCart(newOrderDto);
		//주문 저장
		orderDao.createOrdersFromCart(newOrderDto);

		OrderPickup orderPickup = OrderPickup.builder()
				.accountNo(newOrderDto.getAccountNo())
				.orderId(newOrderDto.getOrderId())
				.storeId(newOrderDto.getStoreId())
				.slotDt(newOrderDto.getSlotDt())
				.slotTime(newOrderDto.getSlotTime())
				.pickupStoreId(newOrderDto.getStoreId())
				.orderPickupStatusCd("ORDERED")
				.regPerson(String.valueOf(newOrderDto.getAccountNo()))
				.lastModPerson(String.valueOf(newOrderDto.getAccountNo()))
				.build();

		//주문픽업 저장
		orderDao.createOrderPickup(orderPickup);

		//픽업 업데이트
		pickupTimeslotDao.updatePickReservation(pickupSlotTimeSearch);

		CartItemSearch cartItemSearch = CartItemSearch.builder()
				.accountNo(newOrderDto.getAccountNo())
				.storeId(newOrderDto.getStoreId())
				.build();

		//장바구니 삭제
		cartDao.deleteAllForOrder(cartItemSearch);
		//저장정보 조회

		return newOrderDto;
	}
}
