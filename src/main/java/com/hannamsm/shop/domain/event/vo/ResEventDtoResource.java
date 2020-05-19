package com.hannamsm.shop.domain.event.vo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.hannamsm.shop.domain.event.controller.EventController;

public class ResEventDtoResource extends EntityModel<ResEventDto> {

	public ResEventDtoResource(ResEventDto content, Link... links) {
		super(content, links);
		add(linkTo(EventController.class).withSelfRel());
	}
}
