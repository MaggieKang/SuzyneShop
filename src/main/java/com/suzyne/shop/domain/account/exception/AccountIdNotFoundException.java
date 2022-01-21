package com.suzyne.shop.domain.account.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class AccountIdNotFoundException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public AccountIdNotFoundException() {
		super(ErrorCode.ID_NOT_FOUND);
	}
}