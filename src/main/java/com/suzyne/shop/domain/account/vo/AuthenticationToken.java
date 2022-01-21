package com.suzyne.shop.domain.account.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class AuthenticationToken {

	private String username;
	private Collection<? extends GrantedAuthority> authoriteis;
	private String token;
}
