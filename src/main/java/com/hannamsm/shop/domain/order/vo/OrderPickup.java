package com.hannamsm.shop.domain.order.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class OrderPickup {
	private int accountNo;
	private String orderId;
	private String storeId;
	private String slotDt;
	private String slotTime;
	private String pickupStoreId;
	private String orderPickupStatusCd;
	private String regDate;
	private String regPerson;
	private String lastModDate;
	private String lastModPerson;
}
