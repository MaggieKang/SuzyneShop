package com.suzyne.shop.domain.event.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.InvalidValueException;

public class EventWrongEndEventException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public EventWrongEndEventException(String value) {
		super(value, ErrorCode.EVENT_END_EVENT_DATE_TIME_ERROR);
	}
}
