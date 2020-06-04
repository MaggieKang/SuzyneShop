package com.hannamsm.shop.domain.pickup.vo;

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
public class PickupSlotTimeSearch {
	@NotNull
	private String slotDt;
	@NotNull
	private String slotTime;
	@NotNull
	private String storeId;
}
