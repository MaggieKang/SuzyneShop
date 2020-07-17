package com.hannamsm.shop.domain.profile.exception;

import com.hannamsm.shop.global.exception.EntityNotFoundException;

public class AccountNoNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 1L;

	public AccountNoNotFoundException(int target) {
		super(target +" is not found!");
	}
}
