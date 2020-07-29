package com.hannamsm.shop.domain.membership.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.membership.service.MembershipService;
import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.global.vo.ResponseResult;

@RestController
@RequestMapping(value="/api/membership", produces = MediaTypes.HAL_JSON_VALUE)
public class MembershipController {
	@Autowired
	MembershipService membershipService;
	//find Membership Id
	@PostMapping(value = "/findMembershipInfo", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity queryFindMembership(@RequestBody @Valid MembershipDto reqCustomer
            ) throws Exception {
		System.out.println("findMembershipInfo getLastName: "+reqCustomer.getEnName());
		System.out.println("findMembershipInfo getPhone: "+reqCustomer.getPhone());
		Optional<MembershipDto> resultCustomer = membershipService.findMembership(reqCustomer);
		//return data
		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();
		resResult.setMessage("조회에 성공하였습니다.");
		resResult.setResult(resultCustomer.get());
		return ResponseEntity.ok(resResult);
	}
}
