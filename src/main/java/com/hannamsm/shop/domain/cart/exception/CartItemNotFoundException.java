package com.hannamsm.shop.domain.cart.exception;

import com.hannamsm.shop.global.exception.EntityNotFoundException;

public class CartItemNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public CartItemNotFoundException(String target) {
		super(target +" is not found!");
	}
}
