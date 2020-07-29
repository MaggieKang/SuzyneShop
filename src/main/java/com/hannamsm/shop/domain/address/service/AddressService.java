package com.hannamsm.shop.domain.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.address.dao.AddressDao;
import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.domain.address.vo.AccountAddressDto;



@Service
public class AddressService {
	@Autowired
	AddressDao addressDao;
	
	public List<AccountAddress> findAll(AccountAddress accountAddress) throws Exception{
		return addressDao.findAll(accountAddress);
	}
	public int saveAddress(AccountAddress accountAddress) throws Exception{
		return addressDao.saveAddress(accountAddress);
	}
	public int deleteAddress(AccountAddressDto accountAddressDto) throws Exception{
		return addressDao.deleteAddress(accountAddressDto);
	}
}
