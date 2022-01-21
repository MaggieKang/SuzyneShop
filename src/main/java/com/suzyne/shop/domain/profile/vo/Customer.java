package com.suzyne.shop.domain.profile.vo;

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
	private String accountEmail;
	private String firstName;
	private String lastName;
	private String customerEmail;
	private String customerPhoneNumber;
	private String extensionNumber;
	private String membershipId;
	private boolean isMembership;
	private int seq;
	private String address;
	private String city;
	private String cityNm;
	private String province;
	private String provinceNm;
	private String country;
	private String countryNm;
	private String postalCd;
	private boolean isDefaultAddress;

}
