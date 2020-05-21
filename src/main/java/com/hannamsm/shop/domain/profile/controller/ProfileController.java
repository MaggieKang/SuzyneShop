package com.hannamsm.shop.domain.profile.controller;

import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.event.vo.Event;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResutl;

@RestController
@RequestMapping(value="/api/profile", produces = MediaTypes.HAL_JSON_VALUE)
public class ProfileController {

	//TODO Profile 조회
	@GetMapping
	public ResponseEntity queryProfile(@CurrentUser Account account) throws Exception {

		ResponseResutl<Event> result = new ResponseResutl<Event>();
		result.setMessage("조회하였습니다.");
		result.setResult(null);
		return ResponseEntity.ok(result);
	}
}
