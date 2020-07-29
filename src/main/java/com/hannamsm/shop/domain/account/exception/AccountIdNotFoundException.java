package com.hannamsm.shop.domain.account.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

public class AccountIdNotFoundException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public AccountIdNotFoundException() {
		super(ErrorCode.ID_NOT_FOUND);
	}
}