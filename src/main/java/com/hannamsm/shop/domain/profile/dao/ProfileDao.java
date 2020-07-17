package com.hannamsm.shop.domain.profile.dao;

import java.util.Optional;


import com.hannamsm.shop.domain.profile.vo.Customer;
import com.hannamsm.shop.domain.profile.vo.ProfileDto;
import com.hannamsm.shop.domain.profile.vo.AddressDto;

public interface ProfileDao {
	public Optional<Customer> findById(Customer customer) throws Exception;
	
	public int saveProfile(ProfileDto profileDto) throws Exception;
	
	public int saveAddress(AddressDto addressDto) throws Exception;
}
