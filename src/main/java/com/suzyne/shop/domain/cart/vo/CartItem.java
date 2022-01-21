package com.suzyne.shop.domain.cart.vo;

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
public class CartItem {
	private int accountNo;
	private String storeId;
	private String itemId;
	/**
	 * 상품판매종류
	 * R01:일반가격
	 * P01:프로모션(단품), P02:프로모션(묶음)
	 * M01:회원제(단품), M02:회원제(묶음)
	 */
	private String itemSalesTypeCd;
	private int itemQty;
	private String upc;
	private String itemKrNm;
	private String itemEnNm;
	private BigDecimal regularPrice;
	private String categoryCd;
	private String itemTaxCd;
	private String itemDepositCd;
	private String itemEcoCd;
	private String itemSize;
	private String salesUnit;
	private int promotionBundleQty;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
    private BigDecimal promotionPrice;
    private BigDecimal salesPrice;
    private int discountRate;
	private String imgUrl;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
