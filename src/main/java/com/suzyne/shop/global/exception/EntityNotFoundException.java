package com.suzyne.shop.global.exception;

import com.suzyne.shop.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message, ErrorCode.ENTITY_NOT_FOUND);
	}
}
