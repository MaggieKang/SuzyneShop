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
public class PaymentSearch {
	private int accountNo;
	private String paymentId;
	// 기본여부
	private boolean isDefault;
}
