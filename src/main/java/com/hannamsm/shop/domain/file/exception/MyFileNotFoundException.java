package com.hannamsm.shop.domain.file.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends BusinessException {
	private static final long serialVersionUID = 1L;

//	public MyFileNotFoundException(String message) {
//		super(message);
//	}
//
//	public MyFileNotFoundException(String message, Throwable cause) {
//		super(message, cause);
//	}

	public MyFileNotFoundException() {
		super(ErrorCode.FILE_NOT_FOUND_ERROR);
	}
}