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
public class OrderDetail {
	private String orderId;
	private String itemId;
	private String storeId;
	private String itemTaxCd;
	private String item_deposit_cd;
	private String itemEcoCd;
	private int orderQty;
	private BigDecimal amount;
	private BigDecimal eachSalePrice;
	private BigDecimal eachGstFee;
	private BigDecimal eachPstFee;
	private BigDecimal eachHstFee;
	private BigDecimal eachEcoFee;
	private BigDecimal eachDepositFee;
	private BigDecimal receivingPrice;
	private BigDecimal regularPrice;
	private int promotionBundleQty;
	private LocalDateTime promotionStartDate;
	private LocalDateTime promotionEndDate;
	private BigDecimal promotionPrice;
	private String invoiceId;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
