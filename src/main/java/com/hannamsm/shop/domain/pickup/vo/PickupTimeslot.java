package com.hannamsm.shop.domain.pickup.vo;

import java.time.LocalDate;
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
public class PickupTimeslot {
	private LocalDate slotDt;
	private String slotTime;
	private String startDisplaySlotTime;
	private String endDisplaySlotTime;
	private String storeId;
	private int allocationQty;
	private int completionQty;
	private boolean pickupAvailable;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
