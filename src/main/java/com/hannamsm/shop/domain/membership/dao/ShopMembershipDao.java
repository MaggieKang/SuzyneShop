package com.hannamsm.shop.domain.membership.dao;



import com.hannamsm.shop.domain.profile.vo.AddressDto;
import com.hannamsm.shop.domain.profile.vo.ProfileDto;
import com.hannamsm.shop.global.mapper.HnsShopConnMapper;


@HnsShopConnMapper
public interface ShopMembershipDao {
	public int saveMemProfile(ProfileDto profileDto)throws Exception;
	
	public int saveMemAddress(AddressDto addressDto)throws Exception;
	
	public int findAddressSeq(int accountNo)throws Exception;
	
	public String queryMembershipId(int accountNo)throws Exception;
	
}
