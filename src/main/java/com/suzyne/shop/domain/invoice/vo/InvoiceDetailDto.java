package com.suzyne.shop.domain.invoice.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class InvoiceDetailDto {
	private String storeId;
	private int rowNo;
    private String invoiceId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime invoiceDate;
    private String itemId;
    private String upc;
    private String itemKrNm;
    private String itemEnNm;
    private int salesQty;
    private BigDecimal salesBundleAmount;
    private BigDecimal salesBundlePrice;

    private int totalSalesQty;
    private BigDecimal totalPayAmount;
    private BigDecimal totalAmount;
    private BigDecimal totalGstFee;
    private BigDecimal totalPstFee;
    private BigDecimal totalEcoFee;
    private BigDecimal totalDepositFee;
    private String deliveryAddress;
    private String billingAddress;
    private String soldBy;
    private String orderId;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private String customerEMail;
}
