package com.hannamsm.shop.domain.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.invoice.dao.InvoiceDao;
import com.hannamsm.shop.domain.invoice.vo.CreateNewInvoiceDto;

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
}
