package com.suzyne.shop.domain.payment.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class PaymentDeclinedException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public PaymentDeclinedException() {
		super(ErrorCode.PAYMENT_DECLINED_ERROR);
	}

	public PaymentDeclinedException(String errorMessage) {
		super("PI002", "Transaction declined!", errorMessage);
	}
}