package com.hannamsm.shop.domain.membership.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.membership.service.MembershipService;
import com.hannamsm.shop.domain.membership.vo.MembershipSearchDto;
import com.hannamsm.shop.domain.profile.vo.AddressDto;
import com.hannamsm.shop.domain.profile.vo.ProfileDto;
import com.hannamsm.shop.domain.membership.vo.MembershipDto;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResult;

@RestController
@RequestMapping(value="/api/membership", produces = MediaTypes.HAL_JSON_VALUE)
public class MembershipController {
	@Autowired
	MembershipService membershipService;
		
	//find Membership Id
	@PostMapping(value = "/findMembershipInfo")
	public ResponseEntity queryFindMembership(@RequestBody @Valid MembershipSearchDto reqCustomer) throws Exception {
				
		MembershipDto resultCustomer = new MembershipDto();									
		resultCustomer = this.membershipService.findMembership(reqCustomer);		
							
		//return data
		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();
		resResult.setMessage("조회에 성공하였습니다.");
		resResult.setResult(resultCustomer);
		return ResponseEntity.ok(resResult);
	}
	
	@PostMapping(value = "/confirmMembership")
	public ResponseEntity queryConfirmMembership(@RequestBody @Valid MembershipSearchDto reqCustomer)throws Exception{
								
		int confirm = membershipService.confirmNumber(reqCustomer);
		MembershipDto confirmResult = new MembershipDto();
		confirmResult = this.membershipService.findMembership(reqCustomer);
		confirmResult.setConfirm(confirm);
		
		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();		
		if(1==confirm) {			
			resResult.setMessage("인증에 성공 했습니다.");						
		}else {
			resResult.setMessage("인증에 실패 했습니다.");
		}
		resResult.setResult(confirmResult);
		return ResponseEntity.ok(resResult);
	} 
	
	@PostMapping(value = "/searchMembership")
	public ResponseEntity querySearchMembership(@RequestBody MembershipSearchDto reqCustomer) throws Exception{
		
		MembershipDto resultCustomer = new MembershipDto();
		int allCount = membershipService.findAllCount(reqCustomer);
		
		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();
		if(1==allCount) {
			resultCustomer = this.membershipService.searchMembership(reqCustomer);
			resResult.setMessage("조회 성공 했습니다.");
		}else{
			if(0==allCount) {
				resResult.setMessage("일치 하는 데이터가 없습니다.");	
			}else if(1<allCount){
				resResult.setMessage("2건 이상 조회 되었습니다.");				
			}
			resultCustomer = null;			
		}
		resResult.setResult(resultCustomer);	
		return ResponseEntity.ok(resResult);
	}
	
	@PutMapping(value = "/profile")
	public ResponseEntity saveMemProfile(@RequestBody ProfileDto reqProfileDto
										, @CurrentUser Account account) throws Exception {
		reqProfileDto.setAccountNo(account.getAccountNo());
		membershipService.saveMemProfile(reqProfileDto);
		
		ResponseResult<ProfileDto> resResult = new ResponseResult<ProfileDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqProfileDto);		
		return ResponseEntity.ok(resResult);							
	}
	
	@PutMapping(value = "/address")
	public ResponseEntity saveMemAddress(@RequestBody AddressDto reqAddressDto
										, @CurrentUser Account account) throws Exception{
		
		int accountNo = account.getAccountNo();
		int seq = this.membershipService.findAddressSeq(accountNo);
		
		reqAddressDto.setAccountNo(accountNo);
		reqAddressDto.setSeq(seq);
		
		membershipService.saveMemAddress(reqAddressDto);				
		
		ResponseResult<AddressDto> resResult = new ResponseResult<AddressDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqAddressDto);		
		return ResponseEntity.ok(resResult);		
	}
	@GetMapping
	public ResponseEntity queryMembershipID(@CurrentUser Account account) throws Exception{
		
		int accountNo = account.getAccountNo();
		String membershipId = this.membershipService.queryMembershipId(accountNo);
		
		ProfileDto resultProfileDto = new ProfileDto();
		resultProfileDto.setMembershipId(membershipId);
		
		ResponseResult<ProfileDto> resResult = new ResponseResult<ProfileDto>();
		resResult.setMessage("조회 되었습니다.");
		resResult.setResult(resultProfileDto);				
		return ResponseEntity.ok(resResult);
	}
}
