package com.hannamsm.shop.domain.address;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.address.dao.AddressDao;
import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.global.BaseDaoTest;

@Disabled
public class AddressDaoTest extends BaseDaoTest {
	
	@Autowired
	private AddressDao addressDao;
	
	@Test
	@DisplayName("고객 Address 조회 테스트")
	public void findAll() throws Exception{
		AccountAddress accountAddress = AccountAddress.builder()
				.accountNo(1)
				.build();
		List<AccountAddress> list = this.addressDao.findAll(accountAddress);
		System.out.println(list.toString());
		
		assertAll("list",
				()->assertNotNull(list));
	}

}
