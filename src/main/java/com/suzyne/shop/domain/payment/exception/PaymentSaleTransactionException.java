package com.suzyne.shop.domain.payment.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class PaymentSaleTransactionException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public PaymentSaleTransactionException() {
		super(ErrorCode.PAYMENT_TRANSACTION_ERROR);
	}

	public PaymentSaleTransactionException(String errorMessage) {
		super(ErrorCode.PAYMENT_TRANSACTION_ERROR, errorMessage);
	}
}