package com.suzyne.shop.domain.cart.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.InvalidValueException;

public class CartItemDuplicationException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public CartItemDuplicationException(String value) {
		super(value, ErrorCode.CART_ITEM_DUPLICATION);
	}
}
