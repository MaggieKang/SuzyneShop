package com.hannamsm.shop.domain.order.dao;

import java.util.List;

import com.hannamsm.shop.domain.order.vo.Order;
import com.hannamsm.shop.domain.order.vo.OrderSearch;
import com.hannamsm.shop.global.mapper.SryposConnMapper;

@SryposConnMapper
public interface SryposOrderDao {
	public List<Order> findAll(OrderSearch orderSearch) throws Exception;
}
