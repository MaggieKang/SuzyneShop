package com.hannamsm.shop.domain.event.vo;

import java.util.List;

import com.hannamsm.shop.global.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class EventResult extends BaseVO {

	private List list1;
	private List list2;
	private List list3;
}