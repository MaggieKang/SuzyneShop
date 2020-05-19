package com.hannamsm.shop.global.error;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.validation.Errors;

import com.hannamsm.shop.domain.index.controller.IndexController;

public class ErrorsResource extends EntityModel<Errors> {
	public ErrorsResource(Errors errors, Link... links) {
		super(errors, links);
		add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
	}

}
