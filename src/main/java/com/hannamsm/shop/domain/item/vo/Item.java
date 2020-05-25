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
	private String upc;
	private String galCode;
	private String prodOwnCode;
	private String suppCode;
	private String prodId;
	private String itemKrNm;
	private String itemEnNm;
	private String itemSize;
	private String itemType;
	private String itemType2;
	private BigDecimal itemInPrice;
	private BigDecimal salePrice;
	private BigDecimal regularPrice;
	private int itemBalance;
	private String taxCd;
	private String saleUnit;
	private String depositCd;
	private String categoryLevel1Cd;
	private String categoryLevel2Cd;
	private boolean isUse;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
