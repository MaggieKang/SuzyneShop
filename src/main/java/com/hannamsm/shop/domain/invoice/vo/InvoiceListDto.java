package com.hannamsm.shop.domain.invoice.vo;

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
public class InvoiceListDto {
	private String storeId;
	private int rowNo;
    private String invoiceId;
    private int invoiceIndexNo;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime invoiceDate;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime orderDate;
    private String slotDt;
    private String slotTime;
    private String startDisplaySlotTime;
    private String endDisplaySlotTime;
    private String customerPhoneNumber;
    private String customerFirstName;
    private String customerLastName;
    private int salesQty;
    private int orderQty;
    private String itemFisrtName;
}
