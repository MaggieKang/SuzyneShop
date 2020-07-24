package com.hannamsm.shop.domain.address.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.address.service.AddressService;
import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResutls;

@RestController
@RequestMapping(value="/api/address", produces = MediaTypes.HAL_JSON_VALUE)
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping
	public ResponseEntity queryAddress(@CurrentUser Account account) throws Exception{
		
		AccountAddress accountAddress = new AccountAddress();
		accountAddress.setAccountNo(account.getAccountNo());
		
		List<AccountAddress> list = this.addressService.findAll(accountAddress);		
		
		//return data
		ResponseResutls<AccountAddress> resResult = new ResponseResutls<AccountAddress>();
		resResult.setMessage("조회되었습니다.");		
		resResult.setResultList(list);				
		
		return ResponseEntity.ok(resResult);
	}

}
