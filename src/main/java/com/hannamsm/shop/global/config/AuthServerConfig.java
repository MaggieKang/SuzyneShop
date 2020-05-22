package com.hannamsm.shop.global.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import com.hannamsm.shop.domain.account.service.AccountService;
import com.hannamsm.shop.global.properties.AppProperties;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	DataSource dataSource;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AccountService accountService;

	@Autowired
	TokenStore tokenStore;

	@Autowired
	AppProperties appProperties;

	//OAuth2 인증서버 자체의 보안 정보를 설정하는 부분
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.passwordEncoder(passwordEncoder)
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
		// Token 정보를 API(/oauth/check_token)를 활성화 시킨다.(기본:denyAll)
	}

	//Client에 대한 정보를 설정하는 부분
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//				.withClient(appProperties.getClientId())
//				.authorizedGrantTypes("password", "refresh_token")
//				.scopes("read", "write")
//				.secret(this.passwordEncoder.encode(appProperties.getClientSecret()))
//				.accessTokenValiditySeconds(8 * 6 * 10 * 60)
//				.refreshTokenValiditySeconds(5 * 8 * 6 * 10 * 60);
		clients.jdbc(dataSource);
//			.passwordEncoder(passwordEncoder);
	}

	//OAuth2 서버가 작동하기 위한 Endpoint에 대한 정보를 설정
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
			.authenticationManager(authenticationManager)
			.userDetailsService(accountService)
			.pathMapping("/oauth/token", "/api/account/login");
	}
}
