package com.suzyne.shop.domain.profile.dao;

import java.util.Optional;


import com.suzyne.shop.domain.profile.vo.Customer;
import com.suzyne.shop.domain.profile.vo.ProfileDto;
import com.suzyne.shop.global.mapper.HnsShopConnMapper;
import com.suzyne.shop.domain.profile.vo.AddressDto;

@HnsShopConnMapper
public interface ProfileDao {
	public Optional<Customer> findById(Customer customer) throws Exception;

	public int saveProfile(ProfileDto profileDto) throws Exception;

	public int saveAddress(AddressDto addressDto) throws Exception;
		
}
