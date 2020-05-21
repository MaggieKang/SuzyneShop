package com.hannamsm.shop.domain.favourite.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.global.vo.ResponseResutl;

@RestController
@RequestMapping(value="/api/favourite", produces = MediaTypes.HAL_JSON_VALUE)
public class FavouriteController {

	//TODO 자주가는 매장 조회
	@GetMapping("/{id}")
	public ResponseEntity getFavourite(@PathVariable Integer id) throws Exception {
		
		ResponseResutl<Event> result = new ResponseResutl<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
}
