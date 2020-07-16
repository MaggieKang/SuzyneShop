package com.hannamsm.shop.global.properties;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "tomcat")
@Getter @Setter
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
