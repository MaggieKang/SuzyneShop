package com.suzyne.shop.domain.cart.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class CartItemAlreadyUseException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public CartItemAlreadyUseException() {
		super(ErrorCode.CART_ITEM_ALREADY_USE);
	}
}
