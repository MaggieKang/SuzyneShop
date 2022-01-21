package com.suzyne.shop.domain.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class NewPaymentDto {
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
}
