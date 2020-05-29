package com.hannamsm.shop.domain.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.order.dao.OrderDao;
import com.hannamsm.shop.domain.order.vo.Order;
import com.hannamsm.shop.domain.order.vo.OrderDetail;
import com.hannamsm.shop.domain.order.vo.OrderSearch;

@Service
public class OrderService {

	@Autowired
	OrderDao orderDao;

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
}
