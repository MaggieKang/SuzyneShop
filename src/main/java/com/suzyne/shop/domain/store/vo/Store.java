package com.suzyne.shop.domain.store.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class Store {

	private String storeId;
	private String storeNm;
	private String address;
	private String city;
	private String province;
	private String postalCd;
	private String storeOpenTime;
	private String telephone;
	private boolean isUse;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
