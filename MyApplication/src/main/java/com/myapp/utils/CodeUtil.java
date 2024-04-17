package com.myapp.utils;


import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CodeUtil {
	
	public static LocalDate getLocalDateWithoutTime(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    date = calendar.getTime();
	    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
