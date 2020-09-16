package com.hannamsm.shop.domain.account.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.account.dao.AccountDao;
import com.hannamsm.shop.domain.account.exception.AccountIdNotFoundException;
import com.hannamsm.shop.domain.account.exception.AccountNotMatchPasswordException;
import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.global.adapter.AccountAdapter;

@Service
public class AccountService implements UserDetailsService{

	@Autowired
	AccountDao accountDao;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		Account account = accountDao.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException(userEmail));

		List<String> roles = accountDao.findRolesByUserNo(account.getAccountNo());
		account.setRoles(roles);

		return new AccountAdapter(account);
	}

    public UserDetails readAccount(String userEmail) {
		Account account = accountDao.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException(userEmail));

		List<String> roles = accountDao.findRolesByUserNo(account.getAccountNo());
		account.setRoles(roles);

		return new AccountAdapter(account);
    }

 //아이디 중복 체크
    public Optional<Account> dupAccount(String accountEmail) {
    	Optional<Account> account = accountDao.findByUserEmail(accountEmail);
    	return account;
    }
//아이디 생성
    public void createUser(Account account) {
		accountDao.createAccount(account);
    }
//이전 패스워드 인증(비밀번호 변경시)
    public Optional<Account> checkOldPassword(Account account) {
    	Optional<Account> resultAccount =  accountDao.checkOldPassword(account);
    	resultAccount.orElseThrow(() -> new AccountNotMatchPasswordException());
    	return resultAccount;
    }
//password update
    public void resetPassword(Account account) {
    	 accountDao.resetPassword(account);
    	return;
    }
 //find ID
    public Optional<String> findUserID(Customer customer) {
    	Optional<String> returnStr = accountDao.findUserID(customer);
    	returnStr.orElseThrow(() -> new AccountIdNotFoundException());
    	return returnStr;
    }
    public void deleteAccount(String userEmail) {
    	Account account = accountDao.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException(userEmail));

    	this.accountDao.deleteAccount(account.getAccountNo());
    	this.accountDao.deleteAuthorities(account.getAccountNo());
    }

    public void deleteAuthoriry(String userEmail, String role) {
    	Account account = accountDao.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException(userEmail));

    	this.accountDao.deleteAuthority(account.getAccountNo(), role);
    }

}