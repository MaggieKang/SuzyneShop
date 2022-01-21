package com.suzyne.shop.domain.order.vo;

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
public class OrderDetailDto {
	private String orderId;
	private String storeId;
	private String itemSalesTypeCd;
	private String itemId;
	private String itemKrNm;
	private String itemEnNm;
	private String itemSize;
	private String itemTaxCd;
	private String itemDepositCd;
	private String itemEcoCd;
	private int orderQty;
	private BigDecimal salesBundleAmount;
	private BigDecimal salesBundleGstFee;
	private BigDecimal salesBundlePstFee;
	private BigDecimal salesBundleEcoFee;
	private BigDecimal salesBundleDepositFee;
	private BigDecimal salesBundleRegularPrice;
	private int salesBundleQty;
	private BigDecimal salesBundlePrice;
	private int salesBundleDiscountRate;
	private String imgUrl;
}
