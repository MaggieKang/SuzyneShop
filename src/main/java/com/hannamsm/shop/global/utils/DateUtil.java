package com.hannamsm.shop.global.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	/**
	 * 날짜로 요일 구하기
	 * @param date
	 * @param formatStr "yyyy-MM-dd"
	 * @return String {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"}
	 * @throws Exception
	 * ex) DateUtil.getDayOfWeek("2020-08-10", "yyyy-MM-dd");
	 */
	public static String getDayOfWeek(String date, String formatStr) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String[] week = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		Calendar cal = Calendar.getInstance();
		Date getDate = format.parse(date);
		cal.setTime(getDate);
		int w = cal.get(Calendar.DAY_OF_WEEK)-1;

		return week[w];
	}

	/**
	 * 오늘 월일 구하기
	 * @return String
	 * @throws Exception
	 */
	public static String getCurrntDate() throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.CANADA_FRENCH);
		Date toDate = new Date();
		return simpleDateFormat.format(toDate);
	}
}
