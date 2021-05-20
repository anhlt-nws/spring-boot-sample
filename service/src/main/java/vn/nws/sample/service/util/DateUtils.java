package vn.nws.sample.service.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * The Class DateUtils.
 */
public class DateUtils {
	
	/**
	 * Format.
	 *
	 * @param date the date
	 * @param format the format
	 * @return the string
	 */
	public static String format(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);  
        return dateFormat.format(date); 
	}
	
	/**
	 * Format.
	 *
	 * @param timestamp the timestamp
	 * @param format the format
	 * @return the string
	 */
	public static String format(Timestamp timestamp, String format) {
		Date date = timestamp;
		return format(date, format);
	}
	
	/**
	 * Now by format.
	 *
	 * @param format the format
	 * @return the string
	 */
	public static String nowByFormat(String format) {
		Date now = new Date();
		return format(now, format);
	}
	
	/**
	 * As date.
	 *
	 * @param localDate the local date
	 * @return the date
	 */
	public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
 
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
 
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
 
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
