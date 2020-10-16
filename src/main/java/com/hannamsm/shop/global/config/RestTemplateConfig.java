package com.hannamsm.shop.global.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.hannamsm.shop.global.properties.RestTemplateProperties;

@Configuration
public class RestTemplateConfig {

    @Autowired
    RestTemplateProperties restTemplateProperties;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(restTemplateProperties.getFactory().getReadTimeout());
        factory.setConnectTimeout(restTemplateProperties.getFactory().getConnectTimeout());


        HttpClient httpClient = HttpClientBuilder.create()
    		.setMaxConnTotal(restTemplateProperties.getHttpClient().getMaxConnTotal()) //연결을 유지할 최대 숫자
    		.setMaxConnPerRoute(restTemplateProperties.getHttpClient().getMaxConnPerRoute()) //특정 경로당 최대 숫자
            .build();

        factory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);

        return restTemplate;
    }
}
