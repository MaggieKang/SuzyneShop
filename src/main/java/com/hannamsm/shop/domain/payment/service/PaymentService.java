package com.hannamsm.shop.domain.payment.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hannamsm.shop.domain.payment.dao.PaymentDao;
import com.hannamsm.shop.domain.payment.exception.PaymentSaleTransctionException;
import com.hannamsm.shop.domain.payment.vo.ConvergeSaleTransctionResVO;
import com.hannamsm.shop.domain.payment.vo.ConvergeSaleTransctionVO;
import com.hannamsm.shop.domain.payment.vo.Payment;
import com.hannamsm.shop.domain.payment.vo.PaymentDto;
import com.hannamsm.shop.domain.payment.vo.PaymentSearch;

@Service
public class PaymentService {

	@Autowired
    RestTemplate restTemplate;

	@Autowired
	PaymentDao paymentDao;

	public List<PaymentDto> find(PaymentSearch paymentSearch) throws Exception {
		return this.paymentDao.find(paymentSearch);
	}

	public PaymentDto findByDefault(PaymentSearch paymentSearch) throws Exception {
		return this.paymentDao.findByDefault(paymentSearch);
	}

	public int insertPayment(Payment payment) throws Exception {
		if(payment.isDefault()) {
			this.paymentDao.saveDefaultAllFalse(payment);
		}
		return this.paymentDao.insert(payment);
	}

	public int savePayment(Payment payment) throws Exception {
		if(payment.isDefault()) {
			this.paymentDao.saveDefaultAllFalse(payment);
		}
		return this.paymentDao.save(payment);
	}

	public int deletePayment(Payment payment) throws Exception {
		return this.paymentDao.delete(payment);
	}

	/*
	 * Sale 결제
	 */
	public void callConvergeForSaleTransction(ConvergeSaleTransctionVO convergeSaleTransctionVO) throws Exception {
		System.out.println("결제(대외계) 시작 ===> ");
		convergeSaleTransctionVO.setSslMerchantId("006448");
		convergeSaleTransctionVO.setSslUserId("webpage");
		convergeSaleTransctionVO.setSslPin("B5XY328XXM1SMDSZO11HTDRTRRJY2ZFMFZG2TXNR3RY8H7C4H8EBOY72WGW52OJD");
		convergeSaleTransctionVO.setSslTestMode(false);
		convergeSaleTransctionVO.setSslTransactionType("ccsale");
		convergeSaleTransctionVO.setSslCvv2cvc2Indicator("1");

		JAXBContext jaxbContext = JAXBContext.newInstance(ConvergeSaleTransctionVO.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(convergeSaleTransctionVO, sw);
		String sendXml = sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>","");
		System.out.println("=======================================");
		System.out.println(sendXml);
		System.out.println("=======================================");

		// Set the Content-Type header
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> requestEntity = new HttpEntity<>(sendXml, requestHeaders);

		String url = "https://elavon.gateway.akana.com/converge/processxml.do";
		String obj = restTemplate.postForObject(url, requestEntity, String.class);

		System.out.println("결제(대외계) ===> "+obj.toString());

		JAXBContext unJaxbContext = JAXBContext.newInstance(ConvergeSaleTransctionResVO.class);
		Unmarshaller jaxbUnmarshaller = unJaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(obj);
		ConvergeSaleTransctionResVO convergeSaleTransctionResVO = (ConvergeSaleTransctionResVO)jaxbUnmarshaller.unmarshal(reader);

		if(null == convergeSaleTransctionResVO) {
			throw new PaymentSaleTransctionException();
		}

		System.out.println("결제(대외계)2 ===> "+convergeSaleTransctionResVO.toString());

		if(!(null == convergeSaleTransctionResVO.getErrorCode() || convergeSaleTransctionResVO.getErrorCode().isEmpty())) {
			//에러 Invaild!
			throw new PaymentSaleTransctionException(convergeSaleTransctionResVO.getErrorCode(), convergeSaleTransctionResVO.getErrorName(), convergeSaleTransctionResVO.getErrorMessage());
		} else {
			if("0".equals(convergeSaleTransctionResVO.getSslResult())) {
				//insert
			} else {
				//에러 Decline!
			}
		}
	}
	/*
	 * Sale 결제 Void (취소)
	 */
	public void callConvergeForVoidTransction() {

	}

	public void callConvergeForReturnTransction() {

	}

	public void callConvergeForCardVerifyTransction() {

	}
}