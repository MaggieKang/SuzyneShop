package com.suzyne.shop.domain.profile.exception;

import com.suzyne.shop.global.exception.EntityNotFoundException;

public class AccountNoNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public AccountNoNotFoundException(int target) {
		super(target +" is not found!");
	}
}
