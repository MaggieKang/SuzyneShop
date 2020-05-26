package com.hannamsm.shop.global.adapter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.hannamsm.shop.domain.account.vo.Account;

public class AccountAdapter extends User {
	private static final long serialVersionUID = 1L;

	private Account account;

	public AccountAdapter(Account account) {
		super(account.getAccountId(), account.getPassword(), authorities(account.getRoles()));
		this.account = account;
	}

	private static Collection<? extends GrantedAuthority> authorities(List<String> roles) {
		return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r)).collect(Collectors.toSet());
	}

	public Account getAccount() {
		this.account.setPassword("");
		return this.account;
	}
}
