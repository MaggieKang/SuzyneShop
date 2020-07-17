package com.hannamsm.shop.domain.profile.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.profile.exception.AccountNoNotFoundException;
import com.hannamsm.shop.domain.profile.service.ProfileService;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.domain.profile.vo.ProfileDto;
import com.hannamsm.shop.domain.profile.vo.AddressDto;
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
		customer.setAccountNo(account.getAccountNo());		
		Optional<Customer> optionaProfile = this.profileService.findById(customer);
		optionaProfile.orElseThrow(() -> new AccountNoNotFoundException(customer.getAccountNo()));		
		if(optionaProfile.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		ResponseResutl<Customer> result = new ResponseResutl<Customer>();
		result.setMessage("조회하였습니다.");
		result.setResult(optionaProfile.get());	
		System.out.println(result.toString());
		return ResponseEntity.ok(result);
	}

	@PutMapping
	public ResponseEntity saveProfile(@RequestBody @Valid ProfileDto reqProfileDto
									, @CurrentUser Account account) throws Exception {
		reqProfileDto.setAccountNo(account.getAccountNo());
		profileService.saveProfile(reqProfileDto);

		ResponseResutl<ProfileDto> resResult = new ResponseResutl<ProfileDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqProfileDto);
		System.out.println(reqProfileDto.toString());
		return ResponseEntity.ok(resResult);
	}
	@PutMapping(value = "/address")
	public ResponseEntity saveAddress(@RequestBody @Valid AddressDto reqAddressDto
									, @CurrentUser Account account) throws Exception {

		reqAddressDto.setAccountNo(account.getAccountNo());
		profileService.saveAddress(reqAddressDto);		

		ResponseResutl<AddressDto> resResult = new ResponseResutl<AddressDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqAddressDto);
		return ResponseEntity.ok(resResult);
	}
}
