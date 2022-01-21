package com.suzyne.shop.global.properties;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "tomcat")
public class TomcatProperties {
	@NotEmpty
	String ajpProtocol;

	@NotEmpty
	int ajpPort;

	@NotEmpty
	boolean ajpEnabled;

//	public boolean isAjpEnabled() {
//		return this.ajpEnabled;
//	}
}
