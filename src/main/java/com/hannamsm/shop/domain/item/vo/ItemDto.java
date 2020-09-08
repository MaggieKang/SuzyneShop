package com.hannamsm.shop.domain.item.vo;

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
public class ItemDto {

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
	private String saleUnit;

	private BigDecimal regularPrice;
	private BigDecimal salesPrice;
	private BigDecimal discountRate;

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

    private String imgUrl;
    private String eventTag;
    private String itemDescription;
}
