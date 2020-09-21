package com.hannamsm.shop.domain.payment.vo;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class SavePaymentDto {
	private int accountNo;
	private String storeId;
	@NotNull
	private String paymentId;
	@NotNull
	private boolean isDefault;
}
