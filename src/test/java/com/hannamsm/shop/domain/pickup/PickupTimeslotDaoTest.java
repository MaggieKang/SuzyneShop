package com.hannamsm.shop.domain.pickup;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hannamsm.shop.domain.pickup.dao.PickupTimeslotDao;
import com.hannamsm.shop.domain.pickup.vo.PickupTimeslot;
import com.hannamsm.shop.domain.pickup.vo.PickupSlogDtSearch;
import com.hannamsm.shop.domain.pickup.vo.PickupSlotTimeSearch;
import com.hannamsm.shop.global.BaseDaoTest;

@Disabled
public class PickupTimeslotDaoTest extends BaseDaoTest {

	@Autowired
	private PickupTimeslotDao pickupTimeslotDao;

	@Test
	@DisplayName("픽업 날짜 시간표 조회 테스트")
	public void findBySlotDt() throws Exception {
		String targetDay = LocalDate.of(2020,6,13).toString();
		PickupSlogDtSearch input = PickupSlogDtSearch.builder()
				.storeId("bbr")
				.slotDt(targetDay)
				.build();

		List<PickupTimeslot> pickupTimeslots = this.pickupTimeslotDao.findBySlotDt(input);
		System.out.println(pickupTimeslots.toString());

		assertAll("stores",
				()->assertNotNull(pickupTimeslots));
	}

	@Test
	@DisplayName("픽업 시간 조회 테스트")
	public void findBySlotTime() throws Exception {
		String targetDay = LocalDate.of(2020,6,25).toString();
		String targetTime = LocalTime.of(11,0,0,0).toString();

		PickupSlotTimeSearch inPickupTimeslot = PickupSlotTimeSearch.builder()
				.storeId("st002")
				.slotDt(targetDay)
				.slotTime(targetTime)
				.build();

		Optional<PickupTimeslot> optionalPickupTimeslot = this.pickupTimeslotDao.findBySlotTime(inPickupTimeslot);
		System.out.println(optionalPickupTimeslot.get().toString());

		assertAll("store",
				()->assertNotNull(optionalPickupTimeslot.get()));
	}

	@Test
	@DisplayName("픽업 시간 저장 테스트")
	public void saveSlotTime() throws Exception {
		LocalDate targetDay = LocalDate.of(2020,6,14);
		String targetTime = LocalTime.of(9,0,0,0).toString();

		PickupTimeslot inPickupTimeslot = PickupTimeslot.builder()
				.storeId("bbr")
				.slotDt(targetDay)
				.slotTime(targetTime)
				.allocationQty(999)
				.reservedQty(0)
				.regPerson("sysadmin")
				.lastModPerson("sysadmin")
				.build();

		int count = this.pickupTimeslotDao.saveSlotTime(inPickupTimeslot);
		System.out.println(count);

		assertAll("count",
				()->assertNotNull(count));
	}

	@Test
	@DisplayName("픽업 날짜 시간표 삭제 테스트")
	public void deleteSlotDt() throws Exception {
		LocalDate targetDay = LocalDate.of(2020,6,14);
		String targetTime = LocalTime.of(9,0,0,0).toString();

		PickupTimeslot inPickupTimeslot = PickupTimeslot.builder()
				.storeId("bbr")
				.slotDt(targetDay)
				.slotTime(targetTime)
				.build();

		int count = this.pickupTimeslotDao.deleteTimeslot(inPickupTimeslot);

		assertAll("count",
				()->assertNotNull(count));
	}

}
