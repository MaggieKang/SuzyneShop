package com.hannamsm.shop.domain.profile;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.profile.dao.ProfileDao;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.global.BaseDaoTest;

@Disabled
public class ProfileDaoTest extends BaseDaoTest {

	@Autowired
	private ProfileDao profileDao;

	@Test
	@DisplayName("고객 Profile 조회 테스트")
	public void findById() throws Exception {
		Customer customer = Customer.builder()
				.accountNo(1)
				.build();
		
		Optional<Customer> profile = this.profileDao.findById(customer);
		System.out.println(profile.toString());

		assertAll("profile",
				()->assertNotNull(profile));
	}
	
	@Test
	@DisplayName("profile 저장 테스트")
	public void save() throws Exception{
		Customer customer = Customer.builder()
				.accountNo(1)
				.firstName("ss")
				.lastName("baek")
				.customerEmail("1234@gmail.com")
				.customerPhoneNumber("123-456-7897")
				.extensionNumber(null)
				.build();
		int count = this.profileDao.saveProfile(customer);
		System.out.println(count);
		
		assertAll("saveCount",
						()->assertNotNull(count));
	}
}
