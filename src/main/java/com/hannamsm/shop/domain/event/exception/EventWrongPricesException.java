package com.hannamsm.shop.domain.event.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.InvalidValueException;

public class EventWrongPricesException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public EventWrongPricesException() {
		super();
	}

	public EventWrongPricesException(String value) {
		super(value +" is wrong", ErrorCode.EVENT_WRONG_PRICES);
	}

	public EventWrongPricesException(String value, ErrorCode errorCode) {
		super(value +" is wrong", errorCode);
	}

}
