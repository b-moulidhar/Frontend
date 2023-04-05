package com.valtech.poc.sms.entities;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {

   public static boolean isDateDisabled(Date date) {
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
    
      int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
      // Disable Saturdays and Sundays
      if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
         return true;
      }

      // Enable all other dates
      return false;
   }

}