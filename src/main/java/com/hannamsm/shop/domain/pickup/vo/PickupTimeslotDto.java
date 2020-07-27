package com.hannamsm.shop.domain.pickup.vo;

import java.time.LocalDate;

import javax.validation.constraints.Min;
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
public class PickupTimeslotDto {
	private int accountNo;
	@NotEmpty
	private LocalDate slotDt;
	@NotEmpty
	private String slotTime;
	@NotEmpty
	private String storeId;
	@Min(0)
	private int allocationQty;
	@Min(0)
	private int reservedQty;
}
