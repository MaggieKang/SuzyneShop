package com.suzyne.shop.domain.account;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.suzyne.shop.domain.account.dao.AccountDao;
import com.suzyne.shop.domain.account.service.AccountService;
import com.suzyne.shop.domain.account.vo.Account;
import com.suzyne.shop.global.BaseServiceTest;

public class AccountServiceTest extends BaseServiceTest {

	@Autowired
	AccountService accountService;

	@Autowired
	AccountDao accountDao;

	@Test
	@DisplayName("사용자 조회 테스트")
	@Disabled
	public void findByUsername() {
		// Given
		String username = "9000";

		// When
		UserDetailsService userDetailsService = (UserDetailsService) this.accountService;
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		// Then
		assertThat(userDetails.getUsername()).isEqualTo(username);
	}

	@Test
	@DisplayName("사용자 조회 실패 테스트")
	@Disabled
	public void findByUsernameFail() {
		// Given
		String username = "100610";

		try {
			UserDetailsService userDetailsService = (UserDetailsService) this.accountService;
			userDetailsService.loadUserByUsername(username);
		} catch (UsernameNotFoundException e) {
			// Then
			System.out.println(e.getMessage());
			assertThat(e.getMessage()).containsSequence(username);
		}

	}

	@Test
	@DisplayName("사용자 생성 테스트")
	@Disabled
	public void createUser() {
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");
		roles.add("USER");

		Account account = Account.builder()
				.accountEmail("dev9000@gmail.com")
				.password("1234")
				.roles(roles)
				.build();
		this.accountService.createUser(account);

		// When
		UserDetails userDetails = this.accountService.loadUserByUsername(account.getAccountEmail());

		// Then
		assertThat(userDetails.getUsername()).isEqualTo(account.getAccountEmail());
	}

	@Test
	@DisplayName("사용자 생성 실패 테스트")
	@Disabled
	public void createUserFail() {
		//TODO 사용자 생성 실패 테스트
	}

	@Test
	@DisplayName("사용자 삭제 테스트")
	@Disabled
	public void deleteUser() {
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");
		roles.add("USER");

		Account account = Account.builder()
				.accountEmail("9001")
				.password("1234")
				.roles(roles)
				.build();
//		this.accountService.createUser(account);

		this.accountService.deleteAccount(account.getAccountEmail());
	}
}
