package com.hannamsm.shop.domain.profile.dao;

import java.util.Optional;


import com.hannamsm.shop.domain.profile.vo.Customer;

public interface ProfileDao {
	public Optional<Customer> findById(Customer customer) throws Exception;
	
	public int saveProfile(Customer customer) throws Exception;
	
	public int saveAddress(Customer customer) throws Exception;
}
