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
public class CartItem {
	private String accountId;
	private String itemId;
	private int itemQty;
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
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
