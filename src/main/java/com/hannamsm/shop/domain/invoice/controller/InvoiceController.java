package com.hannamsm.shop.domain.invoice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hannamsm.shop.domain.account.vo.Account;
import com.hannamsm.shop.domain.invoice.service.InvoiceService;
import com.hannamsm.shop.domain.invoice.vo.InvoiceDetailDto;
import com.hannamsm.shop.domain.invoice.vo.InvoiceSearch;
import com.hannamsm.shop.global.adapter.CurrentUser;
import com.hannamsm.shop.global.vo.ResponseResultsByPaging;

@RestController
@RequestMapping(value="/api/invoice", produces = MediaTypes.HAL_JSON_VALUE)
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;

	/*
	 * 인보이스 상세 조회
	 */
	@GetMapping("/{invoiceId}")
	public ResponseEntity queryInvoice(@PathVariable String invoiceId,
			@RequestParam(value = "storeId", defaultValue = "") String storeId,
            @CurrentUser Account account) throws Exception {

		InvoiceSearch invoiceSearch = InvoiceSearch.builder()
				.storeId(storeId)
				.invoiceId(invoiceId)
				.build();

		int allCount = this.invoiceService.findByIdCount(invoiceSearch);
		List<InvoiceDetailDto> list = this.invoiceService.findById(invoiceSearch);

		//return data
    	ResponseResultsByPaging<InvoiceDetailDto> resResult = new ResponseResultsByPaging<InvoiceDetailDto>();
		resResult.setMessage("조회되었습니다.");
		resResult.setTotalCount(allCount);
        resResult.setCurrentCount(list.size());
        resResult.setResultList(list);
        resResult.update();

        return ResponseEntity.ok(resResult);
	}
}
