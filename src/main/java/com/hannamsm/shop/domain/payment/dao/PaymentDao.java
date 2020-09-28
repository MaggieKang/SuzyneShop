package com.hannamsm.shop.domain.payment.dao;

import java.util.List;
import java.util.Optional;

import com.hannamsm.shop.domain.payment.vo.Payment;
import com.hannamsm.shop.domain.payment.vo.PaymentDto;
import com.hannamsm.shop.domain.payment.vo.PaymentSearch;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface PaymentDao {
	public List<PaymentDto> find(PaymentSearch paymentSearch) throws Exception;

	public PaymentDto findByDefault(PaymentSearch paymentSearch) throws Exception;

	public Optional<Payment> findById(PaymentSearch paymentSearch) throws Exception;

	public int save(Payment payment) throws Exception;

	public int insert(Payment payment) throws Exception;

	public int delete(Payment payment) throws Exception;

	public int saveDefaultAllFalse(Payment payment) throws Exception;
}
