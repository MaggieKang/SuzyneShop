package com.suzyne.shop.global.exception;

import com.suzyne.shop.global.error.ErrorCode;

public class InvalidValueException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public InvalidValueException() {
		super(ErrorCode.INVALID_INPUT_VALUE);
	}

	public InvalidValueException(String value) {
		super(value, ErrorCode.INVALID_INPUT_VALUE);
	}

	public InvalidValueException(String value, ErrorCode errorCode) {
		super(value, errorCode);
	}
}
