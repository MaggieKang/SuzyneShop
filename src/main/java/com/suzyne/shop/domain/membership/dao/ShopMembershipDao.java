package com.suzyne.shop.domain.membership.dao;



import com.suzyne.shop.domain.profile.vo.AddressDto;
import com.suzyne.shop.domain.profile.vo.ProfileDto;
import com.suzyne.shop.global.mapper.HnsShopConnMapper;


@HnsShopConnMapper
public interface ShopMembershipDao {
	public int saveMemProfile(ProfileDto profileDto)throws Exception;
	
	public int saveMemAddress(AddressDto addressDto)throws Exception;
	
	public Integer findAddressSeq(int accountNo)throws Exception;
	
	public String queryMembershipId(int accountNo)throws Exception;
	
	public int membershipDupCheck(String membershipId)throws Exception;
	
}
