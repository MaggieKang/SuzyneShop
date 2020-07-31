package com.hannamsm.shop.domain.address.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class AccountAddress {
	private int accountNo;
	private String accountEmail;
	private int seq;
	private String address;
	private String city;
	private String province;
	private String country;
	private String postalCd;
	private boolean isDefaultAddress; 
	private int beSeq;
}
