package com.suzyne.shop.global.properties;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "my-app")
public class AppProperties {
	@NotEmpty
	private String testUsername;

	@NotEmpty
	private String testPassword;

	@NotEmpty
	private String clientId;

	@NotEmpty
	private String clientSecret;
}
