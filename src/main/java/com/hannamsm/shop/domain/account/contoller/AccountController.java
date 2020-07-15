package com.hannamsm.shop.domain.account.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.service.AccountService;
import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.account.vo.AuthenticationRequest;
import com.hannamsm.shop.domain.account.vo.AuthenticationToken;
import com.hannamsm.shop.domain.cart.vo.CartItem;
import com.hannamsm.shop.domain.cart.vo.CartItemSearch;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResutl;
import com.hannamsm.shop.global.vo.ResponseResutlsByPaging;

@RestController
@RequestMapping(value="/api/account", produces = MediaTypes.HAL_JSON_VALUE)
public class AccountController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	AccountService accountService;

	//회원가입 리얼
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public ResponseEntity queryCreateAccount(@RequestParam(value = "accountEmail", defaultValue = "") String accountEmail,
			@RequestParam(value = "password", defaultValue = "") String password
            ) throws Exception {
		
//		if(storeId==null || storeId.isEmpty()) {
//			throw new Exception("storeId is null!!!");
//		}
		System.out.println("queryCreateAccount username :"+accountEmail.toString());
		System.out.println("queryCreateAccount password :"+password.toString());
		
		Account reqAccount = new Account();
		reqAccount.setAccountEmail(accountEmail);
		reqAccount.setPassword(password);
		
		accountService.createUser(reqAccount);

		//return data
		ResponseResutl<Account> resResult = new ResponseResutl<Account>();
		resResult.setMessage("회원가입이 완료 되었습니다.");
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
