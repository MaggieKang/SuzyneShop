package com.hannamsm.shop.domain.payment.vo;

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
	// 계정번호 : 계정번호
	private int accountNo;

	// 기본여부
	private boolean isDefault;

}
