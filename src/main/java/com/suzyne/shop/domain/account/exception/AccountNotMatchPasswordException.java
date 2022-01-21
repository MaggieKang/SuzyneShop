package com.suzyne.shop.domain.account.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class AccountNotMatchPasswordException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public AccountNotMatchPasswordException() {
		super(ErrorCode.PASSWORD_NOT_MATCH);
	}
}