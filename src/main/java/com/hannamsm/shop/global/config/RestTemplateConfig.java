package com.hannamsm.shop.global.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Value("${restTemplate.factory.readTimeout}")
    private int READ_TIMEOUT;

    @Value("${restTemplate.factory.connectTimeout}")
    private int CONNECT_TIMEOUT;

    @Value("${restTemplate.httpClient.maxConnTotal}")
    private int MAX_CONN_TOTAL;

    @Value("${restTemplate.httpClient.maxConnPerRoute}")
    private int MAX_CONN_PER_ROUTE;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(READ_TIMEOUT);
        factory.setConnectTimeout(CONNECT_TIMEOUT);

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(MAX_CONN_TOTAL) //연결을 유지할 최대 숫자
                .setMaxConnPerRoute(MAX_CONN_PER_ROUTE) //특정 경로당 최대 숫자
                .build();

        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);

        return restTemplate;
    }

}