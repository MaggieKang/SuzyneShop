package com.suzyne.shop.domain.membership.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suzyne.shop.domain.account.vo.Account;
import com.suzyne.shop.domain.membership.service.MembershipService;
import com.suzyne.shop.domain.membership.vo.MembershipDto;
import com.suzyne.shop.domain.membership.vo.MembershipSearchDto;
import com.suzyne.shop.domain.profile.vo.AddressDto;
import com.suzyne.shop.domain.profile.vo.ProfileDto;
import com.suzyne.shop.global.adapter.CurrentUser;
import com.suzyne.shop.global.vo.ResponseResult;

@RestController
@RequestMapping(value="/api/membership", produces = MediaTypes.HAL_JSON_VALUE)
public class MembershipController {
	@Autowired
	MembershipService membershipService;

	/*
	 * Profile membership Check
	 * 멤버쉽 ID 조회  by srypos cardNo
	 */
	@PostMapping(value = "/findMembershipInfo")
	public ResponseEntity queryFindMembership(@RequestBody @Valid MembershipSearchDto reqCustomer) throws Exception {

		String message = null;

		String membershipId = reqCustomer.getCardNo();
		int dupCheck = this.membershipService.membershipDupCheck(membershipId);

		MembershipDto resultCustomer = new MembershipDto();
		if(0==dupCheck) {
			resultCustomer = this.membershipService.findMembership(reqCustomer);
			message = "조회 되었습니다.";
		}else {
			message = "이미 등록된 membership 입니다.";
			resultCustomer.setDupCheck(dupCheck);
		}
		//return data
		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();
		resResult.setMessage(message);
		resResult.setResult(resultCustomer);
		return ResponseEntity.ok(resResult);
	}

	/*
	 * Profile membership Confirm
	 * MembershipId duplicate check
	 * 멤버쉽 ID 조회 후 확인 (이름, 전화번호)
	 */
	@PostMapping(value = "/confirmMembership")
	public ResponseEntity queryConfirmMembership(@RequestBody @Valid MembershipSearchDto reqCustomer)throws Exception{

		String message =null;
		int confirm = membershipService.confirmNumber(reqCustomer);

		MembershipDto confirmResult = new MembershipDto();

		if(1==confirm) {
			confirmResult = this.membershipService.findMembership(reqCustomer);
			message = "인증에 성공 했습니다.";
		}else {
			message = "인증에 실패 했습니다.";
		}
		confirmResult.setConfirm(confirm);

		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();

		resResult.setMessage(message);
		resResult.setResult(confirmResult);
		return ResponseEntity.ok(resResult);
	}
	/*
	 * Init check membershipId
	 * account-left membership 메뉴 멤버쉽 찾기
	 */
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

	/*
	 * Find MembershipID by phone and name
	 * account-left membership 메뉴 멤버쉽 찾기
	 * 일치 하는 데이터가 1건일때만 결과 전송
	 */
	@PostMapping(value = "/searchMembership")
	public ResponseEntity querySearchMembership(@RequestBody MembershipSearchDto reqCustomer) throws Exception{

		String message =null;
		MembershipDto resultCustomer = new MembershipDto();
		int allCount = membershipService.findAllCount(reqCustomer);

		ResponseResult<MembershipDto> resResult = new ResponseResult<MembershipDto>();
		if(1==allCount) {
			resultCustomer = this.membershipService.searchMembership(reqCustomer);
			message = "조회 성공 했습니다.";
		}else{
			if(0==allCount) {
				message = "일치 하는 데이터가 없습니다.";
			}else if(1<allCount){
				message = "2건 이상 조회 되었습니다.";
			}
			resultCustomer = null;
		}
		resResult.setMessage(message);
		resResult.setResult(resultCustomer);
		return ResponseEntity.ok(resResult);
	}

	/*
	 * Save sryPos membership data to shop profile
	 * Use Profile VO
	 * membershipID duplicate check
	 * 멤버쉽 profile 동기화, 멤버쉽 중복 체크 후 저장
	 */

	@PutMapping(value = "/profile")
	public ResponseEntity saveMemProfile(@RequestBody ProfileDto reqProfileDto
										, @CurrentUser Account account) throws Exception {
		boolean checkId;
		String message = null;
		reqProfileDto.setAccountNo(account.getAccountNo());

		String membershipId = reqProfileDto.getMembershipId();
		int dupCheck = this.membershipService.membershipDupCheck(membershipId);

		if(0==dupCheck) {
			membershipService.saveMemProfile(reqProfileDto);
			checkId = true;
			message = "저장 되었습니다.";
		}else {
			checkId = false;
			message = "이미 등록 된 membership 입니다.";
		}

		ResponseResult<Boolean> resResult = new ResponseResult<Boolean>();
		resResult.setMessage(message);
		resResult.setResult(checkId);
		return ResponseEntity.ok(resResult);
	}

	/*
	 * Save sryPos membership data to shop address
	 * Use Profile VO
	 * Find Address seq number
	 * 멤버쉽 주소 동기화
	 * 기존 저장된 기본 주소가 있을 경우 해당 seq 번호 불러온 후 update
	 */

	@PutMapping(value = "/address")
	public ResponseEntity saveMemAddress(@RequestBody AddressDto reqAddressDto
										, @CurrentUser Account account) throws Exception{

		int accountNo = account.getAccountNo();
		Integer seq = this.membershipService.findAddressSeq(accountNo);

		reqAddressDto.setAccountNo(accountNo);

		if(seq!=null) {
		reqAddressDto.setSeq(seq);
		}

		membershipService.saveMemAddress(reqAddressDto);

		ResponseResult<AddressDto> resResult = new ResponseResult<AddressDto>();
		resResult.setMessage("저장 되었습니다.");
		resResult.setResult(reqAddressDto);
		return ResponseEntity.ok(resResult);
	}

}
