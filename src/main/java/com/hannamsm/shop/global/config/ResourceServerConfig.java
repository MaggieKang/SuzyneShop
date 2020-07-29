package com.hannamsm.shop.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("event");
	}

	//RecourceServer 접근권한 설정
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
				.accessDeniedHandler(new OAuth2AccessDeniedHandler())
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/account/login").permitAll()
				.antMatchers(HttpMethod.POST, "/api/account/join").permitAll()
				.antMatchers(HttpMethod.POST, "/api/account/findId").permitAll()
				.antMatchers(HttpMethod.POST, "/api/account/emailResetPassword").permitAll()
				.antMatchers(HttpMethod.GET, "/api/item").permitAll()
				.antMatchers(HttpMethod.GET, "/api/item/{id}").permitAll()
				.antMatchers(HttpMethod.GET, "/api/category").permitAll()
				.anyRequest().authenticated();
	}
}
