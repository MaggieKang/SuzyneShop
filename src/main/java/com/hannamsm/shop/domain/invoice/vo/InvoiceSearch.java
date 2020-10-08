package com.hannamsm.shop.domain.invoice.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class InvoiceSearch {
	private int accountNo;
	private String storeId;
	private String invoiceId;
	private String orderId;
	private String customerPhoneNo;
	private String customerNm;
	private String searchStartDate;
	private String searchEndDate;

	private int startRownum;
	private int page;
	private int listSize;

	public InvoiceSearch(int page, int listSize) {
		this.page = page;
		this.listSize = listSize;
		updatePaging();
	}

	public void updatePaging() {
		this.startRownum = (page - 1) * listSize;
	}
}
