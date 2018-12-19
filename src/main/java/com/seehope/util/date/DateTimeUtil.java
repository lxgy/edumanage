package com.seehope.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 时间处理工具
 * 
 */
public class DateTimeUtil {
	public static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000;

	/**
	 * 获取当前日期是星期几<br>
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String GetWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 当前日期
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return formatDate(new Date());
	}

	/**
	 * 当前时间
	 * 
	 * @return
	 */
	public static String getCurDateTime() {
		return formatDateTime(new Date());
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if (null == date) {
			return "";
		} else {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(date);
		}
	}

	/***
	 * 格式化为中文日期
	 */
	public static String formatChineseDate(Date date) {
		if (null == date) {
			return "";
		} else {
			DateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
			return df.format(date);
		}
	}

	/**
	 * 格式化时间按日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 按格式得到日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateTime(Date date, String format) {
		if (null == date) {
			return "";
		} else {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
	}

	public static int getHour(Date date) {
		if (null == date)
			return 0;
		else
			return date.getHours();
	}

	public static int getMinute(Date date) {
		if (null == date)
			return 0;
		else
			return date.getMinutes();
	}

	public static Date getDate(String dateTime, String format) throws ParseException {
		if (null == dateTime || dateTime.length() == 0)
			return null;
		DateFormat df = new SimpleDateFormat(format);
		return df.parse(dateTime);
	}

	public static Date getDate(String dateTime) {
		if (null == dateTime || dateTime.length() == 0)
			return null;
		DateFormat df = null;
		int len = dateTime.length();
		if (dateTime.length() == "yyyy-MM-dd HH:mm:ss".length())
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else if (dateTime.length() == "yyyy-MM-dd HH:mm".length())
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		else if (dateTime.length() == "yyyy-MM-dd".length())
			df = new SimpleDateFormat("yyyy-MM-dd");
		else if (dateTime.length() == "yyyy-MM-dd HH".length())
			df = new SimpleDateFormat("yyyy-MM-dd HH");

		// add
		else if (len == "HH:mm:ss".length())
			df = new SimpleDateFormat("HH:mm:ss");
		else if (len == "EEE, dd MMM yyyy HH:mm:ss".length())
			df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.US);
		else if (len == "EEE, dd MMM yyyy HH:mm:ss zzz".length())
			df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
		else if (len == "yyyy-MM-ddTHH:mm:ss.SSSZ".length())
			df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		else if (len == "Fri, 06 Jan 2012 06:17:03 GMT".length() || len == "Fri, 6 Jan 2012 06:17:03 GMT".length())
			df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		else if (len == "Tue, 28 Jul 2009 04:08:00 +0800".length() || len == "Tue, 6 Jul 2009 04:08:00 +0800".length())
			df = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
		else if (len == "yyyy-MM".length())
			df = new SimpleDateFormat("yyyy-MM");
		else
			return null;
		try {
			return df.parse(dateTime);
		} catch (ParseException pe) {
			pe.printStackTrace();
			return null;
		}
	}

	public static Date addDay(Date date, int day) {
		return DateUtils.addDays(date, day);
	}

	public static Date addWeek(Date date, int week) {
		return DateUtils.addWeeks(date, week);
	}

	public static Date addMonth(Date date, int month) {
		return DateUtils.addMonths(date, month);
	}

	public static Date addTime(Date date, int hours, int mins, int seconds) {
		Date d = DateUtils.addHours(date, hours);
		d = DateUtils.addMinutes(d, mins);
		return DateUtils.addSeconds(d, seconds);
	}

	public static int get(Date date, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(field);
	}

	public static Date parseDate(String dateString, String pattern) {
		try {
			return DateUtils.parseDate(dateString, new String[] { pattern });
		} catch (Exception e) {
			return null;
		}
	}

	public static Date parseDate(String dateString, String[] patterns) {
		try {
			return DateUtils.parseDate(dateString, patterns);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date newDate(int year, int month, int date) {
		return parseDate("" + year + month + date, month >= 10 ? "yyyyMMdd" : "yyyyMdd");
	}

	/**
	 * 判断年份是否是闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否同一天
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDay(Date date1, Date date2) {
		return DateUtils.isSameDay(date1, date2);
	}

	/**
	 * 得到指定月份的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstMonthDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 日期间隔
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int getInterval(Date d1, Date d2) {
		int betweenDays = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(d1);
		}
		int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
		}

		return betweenDays;
	}

	/*****
	 * 两日期相减(最好用)
	 * @param begin_date
	 * @param end_date
	 * @return
	 */
	public static long getTwoDay(Date begin_date, Date end_date) {
		long day = 0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String sdate = format.format(Calendar.getInstance().getTime());

			if (begin_date == null) {
				begin_date = format.parse(sdate);
			}
			if (end_date == null) {
				end_date = format.parse(sdate);
			}
			day = (end_date.getTime() - begin_date.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return -1;
		}
		return day;
	}

	/**
	 * 日期月份间隔
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMonthInterval(Date startDate, Date endDate) {
		int betweenMonths = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(startDate);
		c2.setTime(endDate);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(startDate);
		}

		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);

		int m1 = c1.get(Calendar.MONTH);
		int m2 = c2.get(Calendar.MONTH);

		if (y2 > y1) {
			betweenMonths += (y2 - y1) * 12;
		}
		betweenMonths += (m2 - m1);

		return betweenMonths;
	}

	/**
	 * 日期周间隔
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getWeekInterval(Date startDate, Date endDate) {
		int betweenWeeks = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(startDate);
		c2.setTime(endDate);
		// 保证第二个时间一定大于第一个时间
		if (c1.after(c2)) {
			c1 = c2;
			c2.setTime(startDate);
		}

		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);

		int w1 = c1.get(Calendar.WEEK_OF_YEAR);
		int w2 = c2.get(Calendar.WEEK_OF_YEAR);

		betweenWeeks += (w2 - w1);
		int betweenYears = y2 - y1;
		for (int i = 0; i < betweenYears; i++) {
			c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
			betweenWeeks += c1.getMaximum(Calendar.WEEK_OF_YEAR);
		}

		return betweenWeeks;
	}

	/**
	 * 时间间隔,单位分
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDateTimeInterval(String startDate, String endDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = new Date();
		Date end = new Date();
		try {
			start = format.parse(startDate); // 将字符型转换成日期型
			end = format.parse(endDate); // 将字符型转换成日期型
		} catch (Exception e) {
			e.printStackTrace();
		}
		long time = end.getTime() - start.getTime(); // 返回毫秒数
		return (int) time / 1000 / 60;
	}

	/**
	 * 获取日期中的天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		if (date == null)
			return 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回开始时间 到结束时间 期间的 日期间隔（精确到天）
	 * 
	 * @param sdate
	 * @param edate
	 * @return <p>
	 *         return List
	 *         </p>
	 */
	public static List<String> getDatesBetween(Date sdate, Date edate) {
		if (null == sdate || null == edate) {
			return null;
		}
		Calendar calendars = Calendar.getInstance();
		calendars.setTime(sdate);
		Calendar calendare = Calendar.getInstance();
		calendare.setTime(edate);
		if (calendare.before(calendars)) {
			Calendar temp = calendare;
			calendare = calendars;
			calendars = temp;
		}
		List<String> list = new ArrayList<String>();
		while (!calendars.after(calendare)) {
			list.add(DateTimeUtil.formatDateTime(calendars.getTime(), "MM-dd"));
			calendars.add(Calendar.DATE, 1);
		}
		return list;
	}

	public static final int JANUARY = 0;

	public static final int FEBRUARY = 1;

	public static final int MARCH = 2;

	public static final int APRIL = 3;

	public static final int MAY = 4;

	public static final int JUNE = 5;

	public static final int JULY = 6;

	public static final int AUGUST = 7;

	public static final int SEPTEMBER = 8;

	public static final int OCTOBER = 9;

	public static final int NOVEMBER = 10;

	public static final int DECEMBER = 11;

	public static final int SUNDAY = 0;

	public static final int MONDAY = 1;

	public static final int TUESDAY = 2;

	public static final int WEDNESDAY = 3;

	public static final int THURSDAY = 4;

	public static final int FRIDAY = 5;

	public static final int SATURDAY = 6;

	public static final String TIMEUNIT_MINUTE = "M";

	public static final String TIMEUNIT_HOUR = "H";

	public static final String TIMEUNIT_DAY = "D";

	public static final String PERIODUNIT_DAY = "day";

	public static final String PERIODUNIT_WEEK = "week";

	public static final String PERIODUNIT_MONTH = "month";

	public static final String PERIODUNIT_YEAR = "year";

	public static void main(String[] args) {
		System.out.println(getCurDate());
	}

}
