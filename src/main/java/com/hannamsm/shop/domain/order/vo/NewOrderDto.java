package com.hannamsm.shop.domain.order.vo;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class NewOrderDto {
	private int accountNo;
	private String orderId;
	@NotEmpty
	private String customerContactNumber;
	@NotEmpty
	private String storeId;
	@NotEmpty
	private String slotDt;
	@NotEmpty
	private String slotTime;
}
