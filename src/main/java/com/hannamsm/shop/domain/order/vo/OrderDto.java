package com.hannamsm.shop.domain.order.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class OrderDto {
	private int accountNo;
	private String storeId;
	private String storeNm;
	private String orderId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime orderDate;
	private String invoiceId;
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
	private String orderStatusCd;
}
