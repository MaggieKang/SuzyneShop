package com.hannamsm.shop.domain.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class CardPaymentResultVO {
	// 매장ID
	private String storeId;
	// 계정ID
	private int accountNo;
	// 주문ID
	private String orderId;
	// 인보이스ID
	private String invoiceId;

	private String requestUuid;
	// ssl_transaction_type
	private String sslTransactionType;
	// ssl_txn_id
	private String sslTxnId;
	// ssl_txn_time
	private String sslTxnTime;
	// ssl_card_number
	private String sslCardNumber;
	// ssl_card_type
	private String sslCardType;
	// ssl_amount
	private String sslAmount;

	// ssl_result_message
	private String sslResultMessage;
	// ssl_approval_code
	private String sslApprovalCode;
	// ssl_result
	private String sslResult;

	// errorCode
	private String errorCode;
	// errorName
	private String errorName;
	// errorMessage
	private String errorMessage;
}
