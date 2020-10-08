package com.hannamsm.shop.domain.invoice.dao;

import java.util.List;

import com.hannamsm.shop.domain.invoice.vo.CreateNewInvoiceDto;
import com.hannamsm.shop.domain.invoice.vo.InvoiceDetailDto;
import com.hannamsm.shop.domain.invoice.vo.InvoiceSearch;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface InvoiceDao {

	public String getNewInvoiceId(String storeId) throws Exception;

	public int createNewInvoice(CreateNewInvoiceDto createNewInvoiceDto) throws Exception;

	public int createNewInvoiceDetail(CreateNewInvoiceDto createNewInvoiceDto) throws Exception;

	public int findByIdCount(InvoiceSearch invoiceSearch) throws Exception;

	public List<InvoiceDetailDto> findById(InvoiceSearch invoiceSearch) throws Exception;
}
