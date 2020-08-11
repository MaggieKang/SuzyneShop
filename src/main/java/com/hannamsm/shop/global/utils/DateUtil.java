package com.hannamsm.shop.global.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * 날짜로 요일 구하기
	 * @param date
	 * @param formatStr "yyyy-MM-dd"
	 * @return String {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"}
	 * @throws ParseException
	 * ex) DateUtil.getDayOfWeek("2020-08-10", "yyyy-MM-dd");
	 */
	public String getDayOfWeek(String date, String formatStr) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		Calendar cal = Calendar.getInstance();
		Date getDate = format.parse(date);
		cal.setTime(getDate);
		int w = cal.get(Calendar.DAY_OF_WEEK)-1;
		System.out.println(date + "는 " + week[w] +"요일 입니다.");

		return week[w];
	}
}
