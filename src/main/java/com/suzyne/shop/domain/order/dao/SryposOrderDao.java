package com.suzyne.shop.domain.order.dao;

import java.util.List;

import com.suzyne.shop.domain.order.vo.Order;
import com.suzyne.shop.domain.order.vo.OrderSearch;
import com.suzyne.shop.global.mapper.SryposConnMapper;

@SryposConnMapper
public interface SryposOrderDao {
	public List<Order> findAll(OrderSearch orderSearch) throws Exception;
}
