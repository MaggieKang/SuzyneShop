package com.hannamsm.shop.domain.payment.exception;

import com.hannamsm.shop.global.exception.BusinessException;

public class PaymentSaleTransctionException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public PaymentSaleTransctionException() {
		super("CC0000", "Result is null!!!", "Result is null!!!");
	}

	public PaymentSaleTransctionException(String errorCode, String errorName, String errorMessage) {
		super(errorCode, errorName, errorMessage);
	}
}