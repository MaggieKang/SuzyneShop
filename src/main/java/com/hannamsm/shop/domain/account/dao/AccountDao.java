package com.hannamsm.shop.domain.account.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface AccountDao {
	public Optional<Account> findByUserNo(int userNo);

	public Optional<Account> findByUserEmail(String userEmail);

	public List<String> findRolesByUserNo(int userNo);

	public String dupAccount(String userEmail);

	public String checkOldPassword(String oldPassword);

	public void updatePassword(String oldPassword);

	public void createAccount(Account account);

	public void deleteAccount(@Param("accountNo") int userNo);

	public void createAuthority(@Param("accountNo") int userNo, @Param("authCd") String role);

	public void deleteAuthority(@Param("accountNo") int userNo, @Param("authCd") String role);

	public void deleteAuthorities(@Param("accountNo") int userNo);

}