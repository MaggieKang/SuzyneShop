package com.hannamsm.shop.domain.address.dao;

import java.util.List;

import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.domain.address.vo.AccountAddressDto;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface AddressDao {
	public List<AccountAddress> findAll(AccountAddress accountAddress) throws Exception;
	
	public int saveAddress(AccountAddress accountAddress) throws Exception;
	
	public int deleteAddress(AccountAddressDto accountAddressDto) throws Exception;
}
