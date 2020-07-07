package com.hannamsm.shop.domain.profile.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.profile.service.ProfileService;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResutl;

@RestController
@RequestMapping(value="/api/profile", produces = MediaTypes.HAL_JSON_VALUE)
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	/*
	 * Profile 조회
	 */
	@GetMapping
	public ResponseEntity queryProfile(@CurrentUser Account account) throws Exception {
		
		Customer customer = new Customer();
		customer.setAccountId(account.getAccountId());

		Optional<Customer> optionaProfile = this.profileService.findById(customer);
		if(optionaProfile.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		ResponseResutl<Customer> result = new ResponseResutl<Customer>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionaProfile.get());
		return ResponseEntity.ok(result);
	}
}
