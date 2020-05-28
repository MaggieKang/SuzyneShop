package com.hannamsm.shop.domain.cart.vo;

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
public class CartSummery {

	private String accountId;
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
	private String categoryCd;
	private boolean isUse;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
