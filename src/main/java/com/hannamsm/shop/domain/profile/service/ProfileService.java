package com.hannamsm.shop.domain.profile.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.profile.dao.ProfileDao;
import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.domain.profile.vo.ProfileDto;
import com.hannamsm.shop.domain.profile.vo.AddressDto;

@Service
public class ProfileService {

	@Autowired
	ProfileDao profileDao;

	public Optional<Customer> findById(Customer customer) throws Exception {
		return this.profileDao.findById(customer);
	}
	public int saveProfile(ProfileDto profileDto) throws Exception{	
		return profileDao.saveProfile(profileDto);
	}
	public int saveAddress(AddressDto addressDto) throws Exception{	
		return profileDao.saveAddress(addressDto);
	}

}
