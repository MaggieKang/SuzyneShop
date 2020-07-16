package com.hannamsm.shop.domain.account.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

public class AccountAlreadyMemberException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public AccountAlreadyMemberException() {
		super(ErrorCode.EMAIL_DUPLICATION);
	}
}
