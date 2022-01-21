package com.suzyne.shop.domain.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suzyne.shop.domain.invoice.dao.InvoiceDao;
import com.suzyne.shop.domain.invoice.vo.CreateNewInvoiceDto;
import com.suzyne.shop.domain.invoice.vo.InvoiceDetailDto;
import com.suzyne.shop.domain.invoice.vo.InvoiceSearch;

@Service
public class InvoiceService {

	@Autowired
	InvoiceDao invoiceDao;

	public CreateNewInvoiceDto createInvoice(CreateNewInvoiceDto createNewInvoiceDto) throws Exception {
		String invoiceId = this.invoiceDao.getNewInvoiceId(createNewInvoiceDto.getStoreId());
		int setInvoiceIndexNo = Integer.parseInt(invoiceId.split("_")[1]);

		createNewInvoiceDto.setInvoiceId(invoiceId);
		createNewInvoiceDto.setInvoiceIndexNo(setInvoiceIndexNo);
		this.invoiceDao.createNewInvoice(createNewInvoiceDto);
		this.invoiceDao.createNewInvoiceDetail(createNewInvoiceDto);
		return createNewInvoiceDto;
	}

	public int findByIdCount(InvoiceSearch invoiceSearch) throws Exception {
		return this.invoiceDao.findByIdCount(invoiceSearch);
	}

	public List<InvoiceDetailDto> findById(InvoiceSearch invoiceSearch) throws Exception {
		return invoiceDao.findById(invoiceSearch);
	}
}
