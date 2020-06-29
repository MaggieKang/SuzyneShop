package com.hannamsm.shop.domain.item.vo;

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
public class Item {

	/* 상품ID */
	private String itemId;
	private String storeId;
	private String upc;
	private String itemKrNm;
	private String itemEnNm;
	private BigDecimal receivingPrice;
	private BigDecimal regularPrice;
	private String categoryCd;
	private String itemTaxCd;
	private String itemDepositCd;
	private String itemEcoCd;
	private String itemSize;
	private String saleUnit;
    private int promotionBundleQty;
	private LocalDateTime promotionStartDate;
	private LocalDateTime promotionEndDate;
	private BigDecimal promotionPrice;
	private String galCode;
	private String prodOwnCode;
	private String suppCode;
	private String prodId;
	private String itemType;
	private String itemType2;
	private BigDecimal salesPrice;
    private int discountRate;
    private String imgUrl;
	private boolean isUse;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
