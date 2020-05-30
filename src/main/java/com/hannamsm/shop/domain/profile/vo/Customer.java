package com.hannamsm.shop.domain.profile.vo;

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
public class Customer {
	private String accountId;
	private String customerNm;
	private String customerEmail;
	private String customerPhoneNumber;
	private String customerGender;
	private String customerKrNm;
	private String customerEnNm;
	private String customerLangCd;
	private int membershipNo;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
