package com.hannamsm.shop.domain.event.validator;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.hannamsm.shop.domain.event.vo.EventDto;

@Component
public class EventValidator {

	public void validate(EventDto eventDto, Errors errors) {
		if(eventDto.getBasePrice() > eventDto.getMaxPrice()
				&& eventDto.getMaxPrice() != 0) {
			errors.reject("wrongPrices", "MaxPrice is wrong.");
		}
		
		LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
		if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime())
				|| endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime())
				|| endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())) {
			errors.rejectValue("endEventDateTime", "wrongValue", "EndEventDateTime is wrong.");
		}
		
		// TODO beginEventDateTime
		// TODO CloseEnrollmentDateTime
	}
}
