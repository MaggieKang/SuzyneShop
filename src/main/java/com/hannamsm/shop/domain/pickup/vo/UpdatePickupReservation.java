package com.hannamsm.shop.domain.pickup.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class UpdatePickupReservation {
	private int accountNo;
	private String storeId;
	private String slotDt;
	private String slotTime;
	private int allocationQty;
}
