package com.hannamsm.shop.domain.cart.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.InvalidValueException;

public class CartItemDuplicationException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public CartItemDuplicationException(String value) {
		super(value, ErrorCode.CART_ITEM_DUPLICATION);
	}
}
