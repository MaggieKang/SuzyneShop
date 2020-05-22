package com.hannamsm.shop.domain.item.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
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
	private String itemInPrice;
	private String salePrice;
	private String regularPrice;
	private String itemBalance;
	private String taxCd;
	private String saleUnit;
	private String depositCd;
	private String isUse;
	private String regDate;
	private String regPerson;
	private String lastModDate;
	private String lastModPerson;
}
