package com.hannamsm.shop.domain.payment.exception;

import com.hannamsm.shop.global.exception.BusinessException;

public class PaymentSaleTransactionException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public PaymentSaleTransactionException() {
		super("CC0000", "Result is null!!!", "Result is null!!!");
	}

	public PaymentSaleTransactionException(String errorCode, String errorName, String errorMessage) {
		super(errorCode, errorName, errorMessage);
	}
}