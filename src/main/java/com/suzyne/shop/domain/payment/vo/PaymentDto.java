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
public class PaymentDto {
	// Payemnt ID
	private String paymentId;
	// 카드이름
	private String cardName;
	// 카드마지막4자리번호
	private String cardLastNo;
	// 유효기간월
	private String expirationMonth;
	// 유효기간년
	private String expirationYear;
	// 기본여부
	private boolean isDefaultPayment;
}
