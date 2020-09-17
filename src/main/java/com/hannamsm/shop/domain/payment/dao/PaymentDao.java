package com.hannamsm.shop.domain.payment.dao;

import java.util.List;

import com.hannamsm.shop.domain.payment.vo.PaymentDto;
import com.hannamsm.shop.domain.payment.vo.PaymentSearch;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface PaymentDao {
	public List<PaymentDto> find(PaymentSearch paymentSearch) throws Exception;

	public PaymentDto findByDefault(PaymentSearch paymentSearch) throws Exception;
}
