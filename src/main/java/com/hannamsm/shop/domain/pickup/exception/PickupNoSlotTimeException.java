package com.hannamsm.shop.domain.pickup.exception;

import com.hannamsm.shop.global.error.ErrorCode;
import com.hannamsm.shop.global.exception.BusinessException;

public class PickupNoSlotTimeException extends BusinessException{
	private static final long serialVersionUID = 1L;

	public PickupNoSlotTimeException() {
		super(ErrorCode.PICKUP_NO_SLOT_TIME_ERROR);
	}
}