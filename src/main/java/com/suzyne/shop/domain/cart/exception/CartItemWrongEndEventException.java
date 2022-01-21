package com.suzyne.shop.domain.cart.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.InvalidValueException;

public class CartItemWrongEndEventException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public CartItemWrongEndEventException(String value) {
		super(value, ErrorCode.CART_ITEM_END_EVENT_DATE_TIME);
	}
}
