package com.hannamsm.shop.domain.event.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.InvalidValueException;

public class EventWrongEndEventException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public EventWrongEndEventException(String value) {
		super(value, ErrorCode.EVENT_END_EVENT_DATE_TIME_ERROR);
	}
}
