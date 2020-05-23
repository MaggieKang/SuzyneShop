package com.hannamsm.shop.domain.account;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.hannamsm.shop.domain.account.dao.AccountDao;
import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.global.BaseDaoTest;

@Transactional
@Rollback
public class AccountDaoTest extends BaseDaoTest {

	@Autowired
	private AccountDao accountDao;

	@Test
	@DisplayName("username 조회 테스트")
	@Disabled
	public void findByUserId() throws Exception {

		Optional<Account> account = this.accountDao.findByUserId("1006");
		System.out.println("id:" + account.get().toString());
		assertAll("2012",
				()->assertNotNull(account.get().getPassword()));
	}

	@Test
	@DisplayName("username Role 조회 테스트")
	@Disabled
	public void findRolesByUserId() throws Exception {
		List<String> rols = this.accountDao.findRolesByUserId("1006");
		System.out.println("rols:" + rols.toString());
		assertAll("ADMIN",()->assertNotNull(rols));
	}

	@Test
	@DisplayName("Account 생성 테스트")
	@Disabled
	public void createAccount() {
		List<String> roles = new ArrayList<String>();
		roles.add("ADMIN");

		Account account = Account.builder()
				.id("9000")
				.password("1234")
				.roles(roles)
				.build();
		this.accountDao.createAccount(account);

		Optional<Account> sAccount = this.accountDao.findByUserId(account.getId());
		System.out.println("id:" + sAccount.get().toString());
		assertAll(account.getId(),
				()->assertNotNull(sAccount.get().getPassword()));
	}

	@Test
	@DisplayName("Account 삭제 테스트")
	@Disabled
	public void deleteAccount() {
		//@Param("id") String userId);
	}

	@Test
	@DisplayName("Account-Authority 생성 테스트")
	@Disabled
	public void createAuthority() {
		//@Param("id") String userId, @Param("role") String role);
	}

	@Test
	@DisplayName("Account-Authority 전체 삭제 테스트")
	@Disabled
	public void deleteAuthorities() {
		//@Param("id") String userId);
	}

	@Test
	@DisplayName("Account-Authority 삭제 테스트")
	@Disabled
	public void deleteAuthority() {
		//@Param("id") String userId, @Param("role") String role);
	}

}
