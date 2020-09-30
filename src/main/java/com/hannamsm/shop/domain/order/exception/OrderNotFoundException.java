package com.hannamsm.shop.domain.order.exception;

import com.hannamsm.shop.global.exception.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String target) {
		super("Order is not found! ("+target+")");
	}
}
