package com.suzyne.shop.domain.event.vo;

import java.time.LocalDateTime;

import com.suzyne.shop.global.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class Event extends BaseVO {

	private Integer id;
	private String name;
	private String description;
	private LocalDateTime beginEnrollmentDateTime;
	private LocalDateTime closeEnrollmentDateTime;
	private LocalDateTime beginEventDateTime;
	private LocalDateTime endEventDateTime;
	private String location; //(optional) 이게 없으면 온라인 모임
	private int basePrice; //(optional)
	private int maxPrice; //(optional)
	private int limitOfEnrollment;
	private boolean offline;
	private boolean free;
	private EventStatus eventStatus = EventStatus.DRAFT;

	public void update() {
		if(this.basePrice == 0 && this.maxPrice == 0) {
			this.free = true;
		} else {
			this.free = false;
		}

		if(this.location == null || this.location.isBlank()) {
			this.offline = false;
		} else {
			this.offline = true;
		}
	}
}
