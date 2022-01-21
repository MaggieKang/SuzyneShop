package com.suzyne.shop.domain.item.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String categoryCd;
	private String categoryNm;
	private String parentCategoryNm;
	private String itemTaxCd;
	private String itemDepositCd;
	private String itemEcoCd;
	private String itemSize;
	private String itemBrand;
    private String itemWeight;
    private String itemWeightUnit;
	private String salesUnit;

	private BigDecimal receivingPrice;
	private BigDecimal regularPrice;

	private int promotionBundleQty;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime promotionStartDate;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime promotionEndDate;
	private BigDecimal promotionPrice;
	private int promotionDiscountRate;

	private int memberLimitQty;
	private int memberBundleQty;
    @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime memberStartDate;
    @JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime memberEndDate;
	private BigDecimal memberPrice;
	private BigDecimal memberDiscountRate;

	private String galCode;
	private String prodOwnCode;
	private String suppCode;
	private String prodId;
	private String itemType;
	private String itemType2;

    private String imgUrl;
    private String eventTag;
    private String itemDescription;
	private boolean isUse;
	private LocalDateTime regDate;
	private String regPerson;
	private LocalDateTime lastModDate;
	private String lastModPerson;
}
