package com.suzyne.shop.domain.cart.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.InvalidValueException;

public class CartItemWrongPricesException extends InvalidValueException {
	private static final long serialVersionUID = 1L;

	public CartItemWrongPricesException() {
		super();
	}

	public CartItemWrongPricesException(String value) {
		super(value +" is wrong", ErrorCode.CART_ITEM_WRONG_PRICES);
	}

	public CartItemWrongPricesException(String value, ErrorCode errorCode) {
		super(value +" is wrong", errorCode);
	}

}
