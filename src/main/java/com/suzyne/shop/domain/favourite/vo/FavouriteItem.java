package com.suzyne.shop.domain.favourite.vo;

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
public class FavouriteItem {

	private int accountNo;
	private String storeId;
	private String itemId;
	private String upc;
	private String itemKrNm;
	private String itemEnNm;
	private String itemSize;
	private BigDecimal receivingPrice;
	private BigDecimal regularPrice;
	private String itemTaxCd;
	private String salesUnit;
	private String itemDepositCd;
	private String categoryCd;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
