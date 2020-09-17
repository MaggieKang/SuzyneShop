package com.hannamsm.shop.domain.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.payment.dao.PaymentDao;
import com.hannamsm.shop.domain.payment.vo.PaymentDto;
import com.hannamsm.shop.domain.payment.vo.PaymentSearch;

@Service
public class PaymentService {

	@Autowired
	PaymentDao paymentDao;

	public List<PaymentDto> find(PaymentSearch paymentSearch) throws Exception {
		return paymentDao.find(paymentSearch);
	}

	public PaymentDto findByDefault(PaymentSearch paymentSearch) throws Exception {
		return paymentDao.findByDefault(paymentSearch);
	}
}