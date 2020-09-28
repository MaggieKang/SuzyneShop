package com.hannamsm.shop.domain.payment.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

public class PaymentNotFoundException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException() {
		super(ErrorCode.PAYMENT_NOT_FOUND_ERROR);
	}
}