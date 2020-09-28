package com.hannamsm.shop.domain.address.dao;

import java.util.List;
import java.util.Optional;

import com.hannamsm.shop.domain.address.vo.AccountAddress;
import com.hannamsm.shop.domain.address.vo.AccountAddressDto;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;

@HnsShopConnMapper
public interface AddressDao {
	public List<AccountAddress> findAll(AccountAddress accountAddress) throws Exception;

	public Optional<AccountAddress> findById(AccountAddress accountAddress) throws Exception;

	public int saveAddress(AccountAddress accountAddress) throws Exception;

	public int deleteAddress(AccountAddressDto accountAddressDto) throws Exception;

	public int updataDefaultAddress(int beSeq) throws Exception;
}
