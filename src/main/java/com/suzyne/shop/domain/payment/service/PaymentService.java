package com.suzyne.shop.domain.payment.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.suzyne.shop.domain.payment.dao.PaymentDao;
import com.suzyne.shop.domain.payment.exception.PaymentDeclinedException;
import com.suzyne.shop.domain.payment.exception.PaymentSaleTransactionException;
import com.suzyne.shop.domain.payment.vo.CardPaymentResultVO;
import com.suzyne.shop.domain.payment.vo.ConvergeSaleTransctionResVO;
import com.suzyne.shop.domain.payment.vo.ConvergeSaleTransctionVO;
import com.suzyne.shop.domain.payment.vo.Payment;
import com.suzyne.shop.domain.payment.vo.PaymentDto;
import com.suzyne.shop.domain.payment.vo.PaymentSearch;
import com.suzyne.shop.global.utils.DateUtil;

@Service
public class PaymentService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

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
	 * Sale ??????
	 */
	public void callConvergeForSaleTransction(ConvergeSaleTransctionVO convergeSaleTransctionVO, CardPaymentResultVO cardPaymentResultVO) throws Exception {
		log.info("??????(?????????) ?????? ===> ");
		String requestUuid = (DateUtil.getCurrntDate()+"-"+UUID.randomUUID().toString()).replaceAll("-", "");

		convergeSaleTransctionVO.setSslMerchantId("006448");
		convergeSaleTransctionVO.setSslUserId("webpage");
		convergeSaleTransctionVO.setSslPin("B5XY328XXM1SMDSZO11HTDRTRRJY2ZFMFZG2TXNR3RY8H7C4H8EBOY72WGW52OJD");
		convergeSaleTransctionVO.setSslTestMode(false);
		convergeSaleTransctionVO.setSslTransactionType("ccsale");
		convergeSaleTransctionVO.setSslCvv2cvc2Indicator("1");

		//?????? ??????
		{
			cardPaymentResultVO.setRequestUuid(requestUuid);
			cardPaymentResultVO.setSslTransactionType(convergeSaleTransctionVO.getSslTransactionType());
			cardPaymentResultVO.setSslCardNumber(convergeSaleTransctionVO.getSslCardNumber());
			cardPaymentResultVO.setSslAmount(convergeSaleTransctionVO.getSslAmount());
			paymentDao.saveReqRequestCardTranjection(cardPaymentResultVO);
		}

		JAXBContext jaxbContext = JAXBContext.newInstance(ConvergeSaleTransctionVO.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(convergeSaleTransctionVO, sw);
		String sendXml = sw.toString().replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>","");
		log.info("=======================================");
		log.info(sendXml);
		log.info("=======================================");

		// Set the Content-Type header
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<String> requestEntity = new HttpEntity<>(sendXml, requestHeaders);

		String url = "https://elavon.gateway.akana.com/converge/processxml.do";
		String obj = restTemplate.postForObject(url, requestEntity, String.class);

		log.info("??????(?????????) ===> "+obj.toString());

		JAXBContext unJaxbContext = JAXBContext.newInstance(ConvergeSaleTransctionResVO.class);
		Unmarshaller jaxbUnmarshaller = unJaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(obj);
		ConvergeSaleTransctionResVO convergeSaleTransctionResVO = (ConvergeSaleTransctionResVO)jaxbUnmarshaller.unmarshal(reader);

		if(null == convergeSaleTransctionResVO) {
			throw new PaymentSaleTransactionException();
		}

		log.info("??????(?????????)2 ===> "+convergeSaleTransctionResVO.toString());

		//?????? ??????
		{
			cardPaymentResultVO.setRequestUuid(requestUuid);
			cardPaymentResultVO.setErrorCode(convergeSaleTransctionResVO.getErrorCode());
			cardPaymentResultVO.setErrorName(convergeSaleTransctionResVO.getErrorName());
			cardPaymentResultVO.setErrorMessage(convergeSaleTransctionResVO.getErrorMessage());

			cardPaymentResultVO.setSslTxnId(convergeSaleTransctionResVO.getSslTxnId());
			cardPaymentResultVO.setSslTxnTime(convergeSaleTransctionResVO.getSslTxnTime());

			cardPaymentResultVO.setSslCardNumber(convergeSaleTransctionResVO.getSslCardNumber());
			cardPaymentResultVO.setSslCardType(convergeSaleTransctionResVO.getSslCardType());
			cardPaymentResultVO.setSslAmount(convergeSaleTransctionResVO.getSslAmount());

			cardPaymentResultVO.setSslResultMessage(convergeSaleTransctionResVO.getSslResultMessage());
			cardPaymentResultVO.setSslApprovalCode(convergeSaleTransctionResVO.getSslApprovalCode());
			cardPaymentResultVO.setSslResult(convergeSaleTransctionResVO.getSslResult());

			paymentDao.saveResRequestCardTranjection(cardPaymentResultVO);
		}

		if(!(null == convergeSaleTransctionResVO.getErrorCode() || convergeSaleTransctionResVO.getErrorCode().isEmpty())) {
			//?????? Invaild!
			throw new PaymentSaleTransactionException(convergeSaleTransctionResVO.getErrorCode()+", "+convergeSaleTransctionResVO.getErrorName()+", "+convergeSaleTransctionResVO.getErrorMessage());
		} else {
			/*
			 * Transaction Outcome
			 *  - An ssl_result = 0 indicates an approved transaction.
			 *  - An ssl_result not equal to 0 indicates a declined and unauthorized transaction.
			 */
			if("0".equals(convergeSaleTransctionResVO.getSslResult())) {
			} else {
				//?????? Decline!
				throw new PaymentDeclinedException(convergeSaleTransctionResVO.getSslResultMessage());
			}
		}
	}
	/*
	 * Sale ?????? Void (??????)
	 */
	public void callConvergeForVoidTransction() {

	}

	public void callConvergeForReturnTransction() {

	}

	public void callConvergeForCardVerifyTransction() {

	}
}