package com.valtech.poc.sms.entities;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarUtil {

	public static boolean isDateDisabled(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
			return true;
		}
		return false;
	}

}