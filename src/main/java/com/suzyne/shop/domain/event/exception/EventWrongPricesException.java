package com.suzyne.shop.domain.event.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.InvalidValueException;

public class EventWrongPricesException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public EventWrongPricesException() {
		super();
	}

	public EventWrongPricesException(String value) {
		super(value +" is wrong", ErrorCode.EVENT_WRONG_PRICES_ERROR);
	}

	public EventWrongPricesException(String value, ErrorCode errorCode) {
		super(value +" is wrong", errorCode);
	}

}
