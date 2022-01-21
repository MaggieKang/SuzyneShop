package com.suzyne.shop.domain.address.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class BillingAddressNotFoundException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public BillingAddressNotFoundException() {
		super(ErrorCode.ADDRESS_BILLING_NOT_FOUND_ERROR);
	}
}