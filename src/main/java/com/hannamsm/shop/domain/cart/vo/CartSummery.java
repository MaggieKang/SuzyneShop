package com.hannamsm.shop.domain.cart.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CartSummery {
	private int totalQty;
	private BigDecimal totalSalesPrice;
	private BigDecimal totalGstFee;
	private BigDecimal totalPstFee;
	private BigDecimal totalEcoFee;
	private BigDecimal totalDepositFee;
	private BigDecimal grandTotalPrice;
}
