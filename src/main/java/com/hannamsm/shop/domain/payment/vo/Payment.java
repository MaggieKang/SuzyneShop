package com.hannamsm.shop.domain.payment.vo;

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
public class Payment {
	private String accountNo;
	// Payemnt ID
	private String paymentId;
	// 카드이름
	private String cardName;
	// 카드번호
	private String cardNo;
	// 유효기간월
	private String expirationMonth;
	// 유효기간년
	private String expirationYear;
	// 기본여부
	private boolean isDefault;
	// CVC번호
	private String cardVerificationCode;
	// 최초등록일시 : 최초등록일시
	private LocalDateTime regDate;
	// 최초등록사용자 : 최초등록사용자
	private String regPerson;
	// 마지막변경일시 : last_mod_date
	private LocalDateTime lastModDate;
	// 마지막변경사용자 : 마지막 변경 사용자
	private String lastModPerson;
}
