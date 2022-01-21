package com.suzyne.shop.domain.cart.vo;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CartItemDto {
	private int accountNo;
	@NotEmpty
	private String storeId;
	@NotEmpty
	private String itemId;
	@NotEmpty
	private String itemSalesTypeCd;
	@Min(0)
	private int itemQty;

    private String upc;
    private String itemKrNm;
    private String itemEnNm;
    private String categoryCd;
    private String itemTaxCd;
    private String itemDepositCd;
    private String itemEcoCd;
    private String itemSize;
    private String salesUnit;
    private int salesBundleQty;
    private int salesLimitQty;
    private BigDecimal salesPrice;
    private BigDecimal regularPrice;
    private BigDecimal totalSalesPrice;
    private int discountRate;
    private String imgUrl;
}
