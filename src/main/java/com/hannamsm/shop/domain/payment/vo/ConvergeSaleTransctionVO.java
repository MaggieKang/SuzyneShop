package com.hannamsm.shop.domain.payment.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@XmlRootElement(name="txn")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConvergeSaleTransctionVO {

	@XmlElement(name="ssl_merchant_id")
	private String sslMerchantId;
	@XmlElement(name="ssl_user_id")
	public String sslUserId;
	@XmlElement(name="ssl_pin")
	public String sslPin;
	@XmlElement(name="ssl_test_mode")
	public boolean sslTestMode;
	@XmlElement(name="ssl_transaction_type")
	public String sslTransactionType;
	@XmlElement(name="ssl_card_number")
	public String sslCardNumber;
	@XmlElement(name="ssl_exp_date")
	public String sslExpDate;
	@XmlElement(name="ssl_cvv2cvc2")
	public String sslCvv2cvc2;
	@XmlElement(name="ssl_cvv2cvc2_indicator")
	public String sslCvv2cvc2Indicator;
	@XmlElement(name="ssl_amount")
	private String sslAmount;
	@XmlElement(name="ssl_invoice_number")
	private String sslInvoiceNumber;
}
