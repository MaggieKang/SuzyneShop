package com.suzyne.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class HnsShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HnsShopApplication.class, args);
	}

}
