package io.github.emanuelepaiano.jespresso.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


/**
 * The Class DateUtils.
 */
public class DateUtils {
	
	/**
	 * Sum days to date.
	 *
	 * @param date the date
	 * @param days the days
	 * @return the date
	 */
	public static Date sumDays(Date date, Integer days) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
	}
	
	
	/**
	 * Gets the current timestamp.
	 *
	 * @return the current timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
}
