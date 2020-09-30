package com.hannamsm.shop.domain.order.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.InvalidValueException;

public class OrderWrongStatusException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public OrderWrongStatusException() {
		super();
	}

	public OrderWrongStatusException(String value) {
		super("Order status is wrong!!! ("+value+")", ErrorCode.ORDER_STATUS_WRONG_PRICES);
	}

}
