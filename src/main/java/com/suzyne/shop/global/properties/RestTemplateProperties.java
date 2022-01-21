package com.suzyne.shop.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "rest-template")
public class RestTemplateProperties {
	/*
	 * restTemplate:
	 *   factory:
	 *     readTimeout: 30000
	 *     connectTimeout: 3000
	 *   httpClient:
	 *     maxConnTotal: 100
	 *     maxConnPerRoute: 5
	 */
	private Factory factory;
	private HttpClient httpClient;

	@Getter @AllArgsConstructor
	public static class Factory {
        private int readTimeout;
        private int connectTimeout;
    }

	@Getter @AllArgsConstructor
	public static class HttpClient {
        private int maxConnTotal;
        private int maxConnPerRoute;
    }
}
