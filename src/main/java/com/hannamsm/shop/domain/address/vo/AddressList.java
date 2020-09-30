package com.hannamsm.shop.domain.address.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class AddressList  {
	
	private List<Address> cityList;
	private List<Address> provinceList;
	private List<Address> countryList;
}
