package com.hannamsm.shop.domain.profile;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.profile.dao.ProfileDao;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.global.BaseDaoTest;

public class ProfileDaoTest extends BaseDaoTest {

	@Autowired
	private ProfileDao profileDao;

	@Test
	@DisplayName("고객 Profile 조회 테스트")
	public void findById() throws Exception {
		Optional<Customer> profile = this.profileDao.findById("9000");
		System.out.println(profile.toString());

		assertAll("profile",
				()->assertNotNull(profile));
	}
}
