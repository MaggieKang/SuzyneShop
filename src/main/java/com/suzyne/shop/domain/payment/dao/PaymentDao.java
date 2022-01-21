package com.suzyne.shop.domain.payment.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.suzyne.shop.domain.payment.vo.CardPaymentResultVO;
import com.suzyne.shop.domain.payment.vo.Payment;
import com.suzyne.shop.domain.payment.vo.PaymentDto;
import com.suzyne.shop.domain.payment.vo.PaymentSearch;
import com.suzyne.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface PaymentDao {
	public List<PaymentDto> find(PaymentSearch paymentSearch) throws Exception;

	public PaymentDto findByDefault(PaymentSearch paymentSearch) throws Exception;

	public Optional<Payment> findById(PaymentSearch paymentSearch) throws Exception;

	public int save(Payment payment) throws Exception;

	public int insert(Payment payment) throws Exception;

	public int delete(Payment payment) throws Exception;

	public int saveDefaultAllFalse(Payment payment) throws Exception;

	public int saveRequestCardTranjection(Payment payment) throws Exception;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int saveReqRequestCardTranjection(CardPaymentResultVO cardPaymentResultVO) throws Exception;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int saveResRequestCardTranjection(CardPaymentResultVO cardPaymentResultVO) throws Exception;
}
