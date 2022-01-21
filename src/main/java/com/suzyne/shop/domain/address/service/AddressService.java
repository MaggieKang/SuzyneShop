package com.suzyne.shop.domain.address.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suzyne.shop.domain.address.dao.AddressDao;
import com.suzyne.shop.domain.address.vo.AccountAddress;
import com.suzyne.shop.domain.address.vo.AccountAddressDto;
import com.suzyne.shop.domain.address.vo.Address;



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
	
	public int updataDefaultAddress(int beSeq) throws Exception{
		return addressDao.updataDefaultAddress(beSeq);
	}
	public List<Address> getCity() throws Exception{
		return addressDao.getCity();
	}
	public List<Address> getProvince() throws Exception{
		return addressDao.getProvince();
	}
	public List<Address> getCountry() throws Exception{
		return addressDao.getCountry();
	}
}
