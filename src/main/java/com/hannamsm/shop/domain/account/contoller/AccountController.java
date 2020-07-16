package com.hannamsm.shop.domain.account.contoller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
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
import com.hannamsm.shop.global.vo.ResponseResutl;

@RestController
@RequestMapping(value="/api/account", produces = MediaTypes.HAL_JSON_VALUE)
public class AccountController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	AccountService accountService;

	//회원가입 리얼
	//@PostMapping
	@PostMapping(value = "/join", produces = MediaTypes.HAL_JSON_VALUE)
	public ResponseEntity queryCreateAccount(@RequestBody @Valid Account reqAccount
            ) throws Exception {
		//accountEmail  password
		System.out.println("queryCreateAccount username :"+reqAccount.getAccountEmail());
		System.out.println("queryCreateAccount password :"+reqAccount.getPassword());
		
		//이메일 중복 체크
		String dupAccount = accountService.dupCheckAccount(reqAccount.getAccountEmail());

		accountService.createUser(reqAccount);

		//return data
		ResponseResutl<Account> resResult = new ResponseResutl<Account>();	
		System.out.println("queryCreateAccount dupAccount :"+dupAccount);
		if(dupAccount.equals("")) {
		accountService.createUser(reqAccount);
		resResult.setMessage("회원가입이 완료 되었습니다.");
		}else {
			resResult.setMessage("이미 가입된 회원정보가 있습니다.");
		}
		
		resResult.setResult(reqAccount);			
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
