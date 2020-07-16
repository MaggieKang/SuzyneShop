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
import com.hannamsm.shop.domain.account.exception.AccountAlreadyMemberException;
import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.cart.exception.CartItemNotFoundException;
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
   

  
    public String dupCheckAccount(String accountEmail) {
    	Optional<Account> account  = accountDao.findByUserEmail(accountEmail);
    	//account.orElseThrow(() -> new AccountAlreadyMemberException());
    	return account.equals(Optional.empty()) ? "" : account.get().getAccountEmail() ;
    }
    
    public void createUser(Account account) {
		accountDao.createAccount(account);
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
