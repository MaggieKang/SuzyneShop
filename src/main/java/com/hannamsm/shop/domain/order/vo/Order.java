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
	private String orderId;
	private LocalDateTime orderDate;
	private String customerContactNumber;
	private String storeId;
	private BigDecimal totalAmount;
	private BigDecimal totalGstFee;
	private BigDecimal totalPstFee;
	private BigDecimal totalHstFee;
	private BigDecimal totalEcoFee;
	private BigDecimal totalDepositFee;
	private String accountId;
	private String slotDt;
	private String slotTime;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
