package com.hannamsm.shop.domain.profile.dao;

import java.util.Optional;

import com.hannamsm.shop.domain.profile.vo.Customer;

public interface ProfileDao {
	public Optional<Customer> findById(String id) throws Exception;
}