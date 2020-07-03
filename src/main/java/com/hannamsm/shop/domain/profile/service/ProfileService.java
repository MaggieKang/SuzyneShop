package com.hannamsm.shop.domain.profile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.profile.dao.ProfileDao;
import com.hannamsm.shop.domain.profile.vo.Customer;

@Service
public class ProfileService {

	@Autowired
	ProfileDao profileDao;

	public Optional<Customer> findById(String accountId) throws Exception {
		return this.profileDao.findById(accountId);
	}

}
