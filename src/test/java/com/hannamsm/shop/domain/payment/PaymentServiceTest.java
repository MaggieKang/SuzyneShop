package com.hannamsm.shop.domain.payment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.account.dao.AccountDao;
import com.hannamsm.shop.domain.payment.service.PaymentService;
import com.hannamsm.shop.domain.payment.vo.ConvergeSaleTransctionVO;
import com.hannamsm.shop.global.BaseServiceTest;

public class PaymentServiceTest extends BaseServiceTest {

	@Autowired
	PaymentService paymentService;

	@Autowired
	AccountDao accountDao;

	@Test
	@DisplayName("사용자 조회 테스트")
	@Disabled
	public void findByUsername() throws Exception {
		// Given
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumIntegerDigits(2);
		format.setRoundingMode(RoundingMode.HALF_EVEN);

		ConvergeSaleTransctionVO convergeSaleTransctionVO = ConvergeSaleTransctionVO.builder()
				.sslCardNumber("4159288888888882")
				.sslExpDate("0623")
				.sslCvv2cvc2("1234")
				.sslAmount(format.format(new BigDecimal(1.00)))
				.build();
		// When
		this.paymentService.callConvergeForSaleTransction(convergeSaleTransctionVO);

		// Then
//		assertThat(userDetails.getUsername()).isEqualTo(username);
	}

}
