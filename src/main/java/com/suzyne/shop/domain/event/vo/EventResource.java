package com.suzyne.shop.domain.event.vo;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import com.suzyne.shop.domain.event.controller.EventController;

public class EventResource extends EntityModel<Event> {

	public EventResource(Event content, Link... links) {
		super(content, links);
		add(linkTo(EventController.class).withSelfRel());
	}
}
