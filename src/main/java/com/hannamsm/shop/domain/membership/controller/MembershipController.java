package com.hannamsm.shop.domain.membership.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.membership.service.MembershipService;
import com.hannamsm.shop.domain.membership.vo.MembershipSearchDto;
import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.global.vo.ResponseResult;

@RestController
@RequestMapping(value="/api/membership", produces = MediaTypes.HAL_JSON_VALUE)
public class MembershipController {
	@Autowired
	MembershipService membershipService;
	//find Membership Id
	@PostMapping(value = "/findMembershipInfo", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity queryFindMembership(@RequestBody @Valid MembershipSearchDto reqCustomer
            ) throws Exception {
				
		MembershipDto resultCustomer = new MembershipDto();	
		
		int allCount = this.membershipService.findAllCount(reqCustomer);
		
		if(1==allCount) {
			resultCustomer = this.membershipService.findMembership(reqCustomer);
		}else {
			resultCustomer = null;
		}
			
		//return data
		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();
		resResult.setMessage("조회에 성공하였습니다.");
		resResult.setResult(resultCustomer);
		return ResponseEntity.ok(resResult);
	}
	
	@PostMapping(value = "/confirmMembership", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity queryConfirmMembership(@RequestBody @Valid MembershipSearchDto reqCustomer
			)throws Exception{
								
		int confirm = membershipService.confirmNumber(reqCustomer);
		MembershipDto confirmResult = new MembershipDto();
		confirmResult = this.membershipService.findMembership(reqCustomer);
		confirmResult.setResult(confirm);
		
		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();
		
		if(1==confirm) {			
			resResult.setMessage("인증에 성공 했습니다.");						
		}else {
			resResult.setMessage("인증에 실패 했습니다.");
		}
		resResult.setResult(confirmResult);
		return ResponseEntity.ok(resResult);
	} 
}
