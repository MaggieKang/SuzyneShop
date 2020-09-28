package com.hannamsm.shop.domain.invoice.dao;

import com.hannamsm.shop.domain.invoice.vo.CreateNewInvoiceDto;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface InvoiceDao {

	public String getNewInvoiceId(String storeId) throws Exception;

	public int createNewInvoice(CreateNewInvoiceDto createNewInvoiceDto) throws Exception;

	public int createNewInvoiceDetail(CreateNewInvoiceDto createNewInvoiceDto) throws Exception;
}
