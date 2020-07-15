package com.hannamsm.shop.domain.profile.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class Customer {
	private int accountNo;
	private String firstName;
	private String lastName;
	private String customerEmail;
	private String customerPhoneNumber;
	private String extensionNumber;
	private String address;
	private String city;
	private String province;
	private String country;
	private String postalCd;
	private boolean isDefaultAddress;

}
