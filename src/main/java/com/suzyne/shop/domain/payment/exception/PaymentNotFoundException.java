package com.suzyne.shop.domain.payment.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class PaymentNotFoundException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public PaymentNotFoundException() {
		super(ErrorCode.PAYMENT_NOT_FOUND_ERROR);
	}
}