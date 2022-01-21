package com.suzyne.shop.domain.payment.vo;

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
public class ConvergeSaleTransctionResVO {
	@XmlElement(name="ssl_issuer_response")
	private String sslIssuerResponse;
	@XmlElement(name="ssl_issue_points")
	private String sslIssuePoints;
	@XmlElement(name="ssl_card_number")
	private String sslCardNumber;
	@XmlElement(name="ssl_departure_date")
	private String sslDepartureDate;
	@XmlElement(name="ssl_oar_data")
	private String sslOarData;
	@XmlElement(name="ssl_result")
	private String sslResult;
	@XmlElement(name="ssl_txn_id")
	private String sslTxnId;
	@XmlElement(name="ssl_loyalty_program")
	private String sslLoyaltyProgram;
	@XmlElement(name="ssl_avs_response")
	private String sslAvsResponse;
	@XmlElement(name="ssl_approval_code")
	private String sslApprovalCode;
	@XmlElement(name="ssl_account_status")
	private String sslAccountStatus;
	@XmlElement(name="ssl_amount")
	private String sslAmount;
	@XmlElement(name="ssl_txn_time")
	private String sslTxnTime;
	@XmlElement(name="ssl_promo_code")
	private String sslPromoCode;
	@XmlElement(name="ssl_exp_date")
	private String sslExpDate;
	@XmlElement(name="ssl_completion_date")
	private String sslCompletionDate;
	@XmlElement(name="ssl_card_short_description")
	private String sslCardShortDescription;
	@XmlElement(name="ssl_customer_code")
	private String sslCustomerCode;
	@XmlElement(name="ssl_card_type")
	private String sslCardType;
	@XmlElement(name="ssl_access_code")
	private String sslAccessCode;
	@XmlElement(name="ssl_transaction_type")
	private String sslTransactionType;
	@XmlElement(name="ssl_loyalty_account_balance")
	private String sslLoyaltyAccountBalance;
	@XmlElement(name="ssl_salestax")
	private String sslSalestax;
	@XmlElement(name="ssl_enrollment")
	private String sslEnrollment;
	@XmlElement(name="ssl_account_balance")
	private String sslAccountBalance;
	@XmlElement(name="ssl_ps2000_data")
	private String sslPs2000Data;
	@XmlElement(name="ssl_result_message")
	private String sslResultMessage;
	@XmlElement(name="ssl_invoice_number")
	private String sslInvoiceNumber;
	@XmlElement(name="ssl_cvv2_response")
	private String sslCvv2Response;
	@XmlElement(name="ssl_tender_amount")
	private String sslTenderAmount;
	@XmlElement(name="ssl_partner_app_id")
	private String sslPartnerAppId;
	@XmlElement(name="errorCode")
	private String errorCode;
	@XmlElement(name="errorName")
	private String errorName;
	@XmlElement(name="errorMessage")
	private String errorMessage;

}
