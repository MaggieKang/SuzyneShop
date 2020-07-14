package com.hannamsm.shop.domain.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.account.dao.AccountDao;
import com.hannamsm.shop.domain.account.vo.Account;
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

		List<String> roles = accountDao.findRolesByUserId(account.getAccountId());
		account.setRoles(roles);

		return new AccountAdapter(account);
	}

    public UserDetails readAccount(String userEmail) {
		Account account = accountDao.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException(userEmail));

		List<String> roles = accountDao.findRolesByUserId(account.getAccountId());
		account.setRoles(roles);

		return new AccountAdapter(account);
    }

    public void createUser(Account account) {
		String rawPassword = account.getPassword();
		String encodedPassword = this.passwordEncoder.encode(rawPassword);
		account.setPassword(encodedPassword);

		accountDao.createAccount(account);

		List<String> list = account.getRoles();
		for (String role : list) {
			accountDao.createAuthority(account.getAccountId(), role);
		}
    }

    public void deleteAccount(String userEmail) {
    	Account account = accountDao.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException(userEmail));

    	this.accountDao.deleteAccount(account.getAccountId());
    	this.accountDao.deleteAuthorities(account.getAccountId());
    }

    public void deleteAuthoriry(String userEmail, String role) {
    	Account account = accountDao.findByUserEmail(userEmail)
				.orElseThrow(() -> new UsernameNotFoundException(userEmail));

    	this.accountDao.deleteAuthority(account.getAccountId(), role);
    }

}
