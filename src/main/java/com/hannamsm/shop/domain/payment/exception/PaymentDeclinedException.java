package com.hannamsm.shop.domain.payment.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

public class PaymentDeclinedException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public PaymentDeclinedException() {
		super(ErrorCode.PAYMENT_DECLINED_ERROR);
	}

	public PaymentDeclinedException(String errorMessage) {
		super("PI002", "Transaction declined!", errorMessage);
	}
}