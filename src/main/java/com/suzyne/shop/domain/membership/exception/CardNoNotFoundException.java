package com.suzyne.shop.domain.membership.exception;

import com.suzyne.shop.global.exception.EntityNotFoundException;

public class CardNoNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public CardNoNotFoundException(String target) {
		super(target +" is not found!");
	}
}
