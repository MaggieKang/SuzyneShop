package com.hannamsm.shop.domain.profile.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.profile.service.ProfileService;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.global.vo.ResponseResutl;

@RestController
@RequestMapping(value="/api/profile", produces = MediaTypes.HAL_JSON_VALUE)
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	/*
	 * Profile 조회
	 */
	@GetMapping("/{accountId}")
	public ResponseEntity queryProfile(@PathVariable String accountId) throws Exception {

		Optional<Customer> optionaProfile = this.profileService.findById(accountId);
		if(optionaProfile.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		ResponseResutl<Customer> result = new ResponseResutl<Customer>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionaProfile.get());
		return ResponseEntity.ok(result);
	}
}
