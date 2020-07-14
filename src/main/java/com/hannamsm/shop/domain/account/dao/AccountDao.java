package com.hannamsm.shop.domain.account.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.hannamsm.shop.domain.account.vo.Account;

public interface AccountDao {
	public Optional<Account> findByUserId(String userId);

	public Optional<Account> findByUserEmail(String userEmail);

	public List<String> findRolesByUserId(String userId);

	public void createAccount(Account account);

	public void deleteAccount(@Param("id") String userId);

	public void createAuthority(@Param("id") String userId, @Param("role") String role);

	public void deleteAuthorities(@Param("id") String userId);

	public void deleteAuthority(@Param("id") String userId, @Param("role") String role);

}
