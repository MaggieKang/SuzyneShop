package com.hannamsm.shop.domain.account.contoller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.service.AccountService;
import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.global.email.EmailDto;
import com.hannamsm.shop.global.email.EmailService;
import com.hannamsm.shop.global.vo.ResponseResult;

@RestController
@RequestMapping(value="/api/account", produces = MediaTypes.HAL_JSON_VALUE)
public class AccountController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	AccountService accountService;
	@Autowired
	EmailService emailService;


	//회원가입 리얼
	//@PostMapping
	@PostMapping(value = "/join", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity queryCreateAccount(@RequestBody @Valid Account reqAccount
            ) throws Exception {
		//accountEmail  password
		System.out.println("queryCreateAccount username :"+reqAccount.getAccountEmail());
		System.out.println("queryCreateAccount password :"+reqAccount.getPassword());

		EmailDto emaildto = new EmailDto();
		//return data
		ResponseResult<Account> resResult = new ResponseResult<Account>();
		//이메일 중복 체크
		int dupAccount = accountService.dupCheckAccount(reqAccount.getAccountEmail());
		if(dupAccount > 0) {
			resResult.setMessage("중복된 회원 아이디가 있습니다.");
		}else {
			emaildto.setAddress(reqAccount.getAccountEmail());
			accountService.createUser(reqAccount);
			emailService.newCustomerMailSend(emaildto);
			resResult.setMessage("회원가입이 완료 되었습니다.");
		}
		resResult.setResult(reqAccount);
		return ResponseEntity.ok(resResult);
	}
	//changePassword
	@PostMapping(value = "/changePassword", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity queryChangePassword(@RequestBody @Valid Account reqAccount
            ) throws Exception {
		//accountEmail  password
		System.out.println("queryCreateAccount oldPassword :"+reqAccount.getPassword());
		System.out.println("queryCreateAccount newPassword :"+reqAccount.getNewPassword());
		System.out.println("queryCreateAccount userId :"+reqAccount.getAccountEmail());
		//current 패스워드 확인 
		Optional<Account> checkPassword =  accountService.checkOldPassword(reqAccount);
		
		accountService.resetPassword(reqAccount);
		
		ResponseResult<Account> resResult = new ResponseResult<Account>();
		resResult.setMessage("패스워드 변경이 완료되었습니다.");
		resResult.setResult(reqAccount);
		return ResponseEntity.ok(resResult);
	}
	//resetPassword
	@PostMapping(value = "/resetPassword", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity queryResetPassword(@RequestBody @Valid Account reqAccount
            ) throws Exception {
		
		accountService.resetPassword(reqAccount);
		//return data
		ResponseResult<Account> resResult = new ResponseResult<Account>();
		resResult.setMessage("패스워드 변경이 완료되었습니다.");
		resResult.setResult(reqAccount);
		return ResponseEntity.ok(resResult);
	}
	//emailResetPassword
		@PostMapping(value = "/emailResetPassword", produces = MediaTypes.HAL_JSON_VALUE)
		public ResponseEntity sendResetEmail(@RequestBody @Valid Account reqAccount
	            ) throws Exception {
			EmailDto emaildto = new EmailDto();
			emaildto.setAddress(reqAccount.getAccountEmail());
			emailService.resetPassword(emaildto);
			//return data
			ResponseResult<Account> resResult = new ResponseResult<Account>();
			resResult.setMessage("이메일이 전송되었습니다.확인하여주십시오.");
			resResult.setResult(reqAccount);
			return ResponseEntity.ok(resResult);
		}
	//find Id
		@PostMapping(value = "/findId", produces = MediaTypes.HAL_JSON_VALUE)
		public ResponseEntity queryFindId(@RequestBody @Valid Customer reqCustomer
	            ) throws Exception {
			Optional<String> resultId = accountService.findUserID(reqCustomer);
			//return data
			ResponseResult<Customer> resResult = new ResponseResult<Customer>();
			String resultEmailId = resultId.get().toString();
			resResult.setMessage("고객님의 회원아이디는"+resultEmailId+"입니다.");
			resResult.setResult(reqCustomer);
			return ResponseEntity.ok(resResult);
		}

//	@RequestMapping(value="/join", method=RequestMethod.POST)
//	public AuthenticationToken join(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session) {
//		String userEmail = authenticationRequest.getUsername();
//		String password = authenticationRequest.getPassword();
//
//		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userEmail, password);
//		Authentication authentication = authenticationManager.authenticate(token);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
//
//		UserDetails account = this.accountService.loadUserByUsername(userEmail);
//		return new AuthenticationToken(account.getUsername(), account.getAuthorities(), session.getId());
//	}

}