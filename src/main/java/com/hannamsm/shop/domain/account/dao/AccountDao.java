package com.hannamsm.shop.domain.account.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.hannamsm.shop.domain.account.vo.Account;

public interface AccountDao {
	public Optional<Account> findByUserNo(int userNo);

	public Optional<Account> findByUserEmail(String userEmail);

	public List<String> findRolesByUserNo(int userNo);

	public void createAccount(Account account);

	public void deleteAccount(@Param("accountNo") int userNo);

	public void createAuthority(@Param("accountNo") int userNo, @Param("authCd") String role);

	public void deleteAuthority(@Param("accountNo") int userNo, @Param("authCd") String role);

	public void deleteAuthorities(@Param("accountNo") int userNo);

}
