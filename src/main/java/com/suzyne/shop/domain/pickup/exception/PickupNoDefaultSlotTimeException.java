package com.suzyne.shop.domain.pickup.exception;

import com.suzyne.shop.global.error.ErrorCode;
import com.suzyne.shop.global.exception.BusinessException;

public class PickupNoDefaultSlotTimeException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public PickupNoDefaultSlotTimeException() {
		super(ErrorCode.PICKUP_NO_DEFAULT_SLOT_TIME_ERROR);
	}
}