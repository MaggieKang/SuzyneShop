package com.hannamsm.shop.domain.profile.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PutMapping(value = "/saveProfile", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity saveProfile(@RequestBody @Valid Customer reqCustomer
									, @CurrentUser Account account) throws Exception {
		
		reqCustomer.setAccountId(account.getAccountId());
		profileService.saveProfile(reqCustomer);
		
		ResponseResutl<Customer> resResult = new ResponseResutl<Customer>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqCustomer);			
		return ResponseEntity.ok(resResult);
	}
	@PutMapping(value = "/saveAddress", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity saveAddress(@RequestBody @Valid Customer reqCustomer
									, @CurrentUser Account account) throws Exception {
		
		reqCustomer.setAccountId(account.getAccountId());
		profileService.saveAddress(reqCustomer);	
		System.out.println(reqCustomer.toString());
		
		ResponseResutl<Customer> resResult = new ResponseResutl<Customer>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqCustomer);			
		return ResponseEntity.ok(resResult);
	}
}
