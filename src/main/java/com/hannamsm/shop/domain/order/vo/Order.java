package com.hannamsm.shop.domain.order.vo;

import java.math.BigDecimal;
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
public class Order {
	private int accountNo;
	private String storeId;
	private String storeNm;
	private String orderId;
	private LocalDateTime orderDate;
	private String titleNm;
	private String customerContactNumber;
	private BigDecimal grandTotalPrice;
	private BigDecimal totalAmount;
	private BigDecimal totalGstFee;
	private BigDecimal totalPstFee;
	private BigDecimal totalEcoFee;
	private BigDecimal totalDepositFee;
	private String slotDt;
	private String slotTime;
	private String startDisplaySlotTime;
	private String endDisplaySlotTime;
	private int itemCount;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
