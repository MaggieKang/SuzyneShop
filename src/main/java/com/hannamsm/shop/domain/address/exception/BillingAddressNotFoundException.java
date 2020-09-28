package com.hannamsm.shop.domain.address.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

public class BillingAddressNotFoundException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public BillingAddressNotFoundException() {
		super(ErrorCode.ADDRESS_BILLING_NOT_FOUND_ERROR);
	}
}