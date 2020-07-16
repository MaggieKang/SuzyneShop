package com.hannamsm.shop.domain.event.validator;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.hannamsm.shop.domain.cart.exception.CartItemWrongEndEventException;
import com.hannamsm.shop.domain.cart.exception.CartItemWrongPricesException;
import com.hannamsm.shop.domain.event.vo.EventDto;

@Component
public class EventValidator {

	public void validate(EventDto eventDto) {
		if(eventDto.getBasePrice() > eventDto.getMaxPrice()
				&& eventDto.getMaxPrice() != 0) {
			throw new CartItemWrongPricesException("MaxPrice");
		}

		LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
		if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime())
				|| endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())
				|| endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
			throw new CartItemWrongEndEventException("EndEventDateTime");
		}

		// TODO beginEventDateTime
		// TODO CloseEnrollmentDateTime
	}
}
