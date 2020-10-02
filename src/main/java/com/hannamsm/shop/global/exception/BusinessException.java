package com.hannamsm.shop.global.exception;

import java.util.ArrayList;
import java.util.List;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.error.FieldError;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private ErrorCode errorCode;
	private List<FieldError> errors;

	public BusinessException(String value, ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.errors = FieldError.of(value, value, errorCode.getMessage());
	}

	public BusinessException(List<FieldError> errors, ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.errors = errors;
	}

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
		this.errors = new ArrayList<>();
	}

	public BusinessException(String errorCode, String errorName, String errorMessage) {
		super(errorCode + ", " + errorName + ", " + errorMessage);
		this.errorCode = null;
		this.errors = null;
	}
}
