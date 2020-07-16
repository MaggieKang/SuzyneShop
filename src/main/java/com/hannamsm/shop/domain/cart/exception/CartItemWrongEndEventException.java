package com.hannamsm.shop.domain.cart.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.InvalidValueException;

public class CartItemWrongEndEventException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public CartItemWrongEndEventException(String value) {
		super(value, ErrorCode.CART_ITEM_END_EVENT_DATE_TIME);
	}
}
