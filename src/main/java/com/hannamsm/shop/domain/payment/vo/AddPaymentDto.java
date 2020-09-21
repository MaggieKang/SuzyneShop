package com.hannamsm.shop.domain.payment.vo;

import javax.validation.constraints.NotEmpty;
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
public class AddPaymentDto {
	private int accountNo;
	private String storeId;
	private String paymentId;
	@NotEmpty
	private String cardName;
	@NotNull
	private String cardNo;
	@NotNull
	private String cardVerificationCode;
	@NotNull
	private String expirationMonth;
	@NotNull
	private String expirationYear;
	@NotNull
	private boolean isDefault;
}
