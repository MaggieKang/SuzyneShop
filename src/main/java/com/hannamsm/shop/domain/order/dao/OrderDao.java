package com.hannamsm.shop.domain.order.dao;

import java.util.List;

import com.hannamsm.shop.domain.order.vo.NewOrderDto;
import com.hannamsm.shop.domain.order.vo.Order;
import com.hannamsm.shop.domain.order.vo.OrderDetail;
import com.hannamsm.shop.domain.order.vo.OrderPickup;
import com.hannamsm.shop.domain.order.vo.OrderSearch;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface OrderDao {
	public int findAllCount(OrderSearch orderSearch) throws Exception;

	public List<Order> findAll(OrderSearch orderSearch) throws Exception;

	public List<OrderDetail> findById(OrderSearch orderSearch) throws Exception;

	public int findByIdCount(OrderSearch orderSearch) throws Exception;

	public String createOrderId() throws Exception;

	public int createOrdersDetailFromCart(NewOrderDto newOrderDto) throws Exception;

	public int createOrdersFromCart(NewOrderDto newOrderDto) throws Exception;

	public int createOrderPickup(OrderPickup orderPickup) throws Exception;

}
