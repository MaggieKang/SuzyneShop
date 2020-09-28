package com.hannamsm.shop.domain.invoice.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CreateNewInvoiceDto {
	private int accountNo;
	private String storeId;
	private String invoiceId;
	private String orderId;
	private BigDecimal totalPayAmount;
	private String customerContactNumber;
	private String billingAddress;
}
