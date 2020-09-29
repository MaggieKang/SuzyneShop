package com.hannamsm.shop.domain.pickup.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hannamsm.shop.domain.pickup.dao.PickupTimeslotDao;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslot;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslotDefault;
import com.hannamsm.shop.domain.pickup.vo.UpdatePickupReservation;
import com.hannamsm.shop.global.utils.DateUtil;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtDefaultSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;

@Service
public class PickupTimeslotService {

	@Autowired
	PickupTimeslotDao pickupTimeslotDao;

	public List<PickupTimeslot> findBySlotDt(PickupSlogDtSearch pickupSlogDtSearch) throws Exception {
		return pickupTimeslotDao.findBySlotDt(pickupSlogDtSearch);
	}

	public Optional<PickupTimeslot> findBySlotTime(PickupSlotTimeSearch pickupTimeslotSearch) throws Exception {
		return pickupTimeslotDao.findBySlotTime(pickupTimeslotSearch);
	}

	public int savePickupTimeslot(PickupTimeslot pickupTimeslot) throws Exception {
		return pickupTimeslotDao.saveSlotTime(pickupTimeslot);
	}

	public int deletePickupTimeslot(PickupTimeslot pickupTimeslot) throws Exception {
		return pickupTimeslotDao.deleteTimeslot(pickupTimeslot);
	}

	public List<PickupTimeslotDefault> findBySlotDtDefault(PickupSlogDtDefaultSearch pickupSlogDtDefaultSearch) throws Exception {
		return pickupTimeslotDao.findBySlotDtDefault(pickupSlogDtDefaultSearch);
	}

	public int updatePickupReservation(UpdatePickupReservation updatePickupReservation) throws Exception {
		String dayWeek = DateUtil.getDayOfWeek(updatePickupReservation.getSlotDt(), "yyyy-MM-dd");

		PickupSlogDtDefaultSearch pickupSlogDtDefaultSearch = PickupSlogDtDefaultSearch.builder()
				.storeId(updatePickupReservation.getStoreId())
				.slotDt(updatePickupReservation.getSlotDt())
				.defaultSlotTime(updatePickupReservation.getSlotTime())
				.defaultDayWeek(dayWeek)
				.build();
		// 픽업기본설정 조회
		PickupTimeslotDefault pickupTimeslotDefault = pickupTimeslotDao.findBySlotDtDefaultByDayTime(pickupSlogDtDefaultSearch)
				.orElseThrow();

		updatePickupReservation.setAllocationQty(pickupTimeslotDefault.getAllocationQty());
		// 픽업예약 업데이트
		return pickupTimeslotDao.updatePickupReservation(updatePickupReservation);
	}

}
