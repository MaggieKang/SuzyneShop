package com.hannamsm.shop.domain.account.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class AccountAuthority {
	private int accountNo;
	private String authCd;
	private String regDate;
	private String regPerson;
	private String lastModDate;
	private String lastModPerson;
}
