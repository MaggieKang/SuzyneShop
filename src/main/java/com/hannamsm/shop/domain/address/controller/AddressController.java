package com.hannamsm.shop.domain.address.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.address.service.AddressService;
import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.domain.address.vo.AccountAddressDto;
import com.hannamsm.shop.domain.address.vo.Address;
import com.hannamsm.shop.domain.address.vo.AddressList;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResult;
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
		
	@PutMapping
	public ResponseEntity saveAddress(@RequestBody @Valid AccountAddress reqAccountAddress
									 , @CurrentUser Account account) throws Exception{
		
		reqAccountAddress.setAccountNo(account.getAccountNo());
		addressService.saveAddress(reqAccountAddress);
		int beSeq = reqAccountAddress.getBeSeq();
		if(0<beSeq) {
			addressService.updataDefaultAddress(beSeq);
		}
		
		ResponseResult<AccountAddress> resResult = new ResponseResult<AccountAddress>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqAccountAddress);
		
		return ResponseEntity.ok(resResult);
	}
	@DeleteMapping(value = "/{seq}", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity deleteAddress(@PathVariable int seq
									   , @RequestBody @Valid AccountAddressDto reqAccountAddressDto
									   , @CurrentUser Account account) throws Exception{
		
		reqAccountAddressDto.setAccountNo(account.getAccountNo());
		addressService.deleteAddress(reqAccountAddressDto);
		
		ResponseResult<AccountAddressDto> resResult = new ResponseResult<AccountAddressDto>();
		resResult.setMessage("삭제 되었습니다.");
		resResult.setResult(reqAccountAddressDto);
		
		return ResponseEntity.ok(resResult);				
	}
	@GetMapping(value ="/getAddress")
	public ResponseEntity getAddress() throws Exception{
		
		List<Address> cityList = addressService.getCity();
		List<Address> provinceList = addressService.getProvince();
		List<Address> countryList = addressService.getCountry();
		
		AddressList addressList = new AddressList();
		addressList.setCityList(cityList);
		addressList.setProvinceList(provinceList);
		addressList.setCountryList(countryList);
		
		ResponseResult<AddressList> resResult = new ResponseResult<AddressList>();
		resResult.setMessage("조회되었습니다.");		
		resResult.setResult(addressList);				
		
		return ResponseEntity.ok(resResult);
	}
}
