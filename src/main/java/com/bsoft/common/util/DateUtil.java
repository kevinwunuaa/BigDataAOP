package com.bsoft.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期转换工具类.
 * @author Wuyong
 *
 */
public class DateUtil {

	private static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private static ThreadLocal<SimpleDateFormat> dateFormaHolder = new ThreadLocal<SimpleDateFormat>() {
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATE_FORMAT_SHORT);
		}
	};

	private static ThreadLocal<SimpleDateFormat> datetimeFormaHolder = new ThreadLocal<SimpleDateFormat>() {
		protected synchronized SimpleDateFormat initialValue() {
			return new SimpleDateFormat(DATETIME_FORMAT);
		}
	};

	/**
	 * 字符串转化为date类型.
	 * <p>
	 * <h3>说明：</h3>
	 * 支持-或/分隔的日期串，且只识别yyyy/MM/dd的形式
	 * </p>
	 * @param strDate
	 * @return {@link Date}
	 * @throws ParseException
	 */
	public static Date newParseDate(String strDate) throws ParseException {
		if (strDate != null) {
			int index = strDate.indexOf("-");
			if (index > -1) {
				strDate = strDate.replaceAll("-", "/");
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date d = (Date) sdf.parseObject(strDate);
		return d;
	}

	/**
	 * 获得本周星期日的日期.
	 * 
	 * @return {@link java.util.Date}
	 */
	public static Date getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();
		return monday;
	}

	/**
	 * 获得下周星期一日期.
	 * 
	 * @return {@link java.util.Date}
	 */
	public static Date getNextMonday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		return monday;
	}

	/**
	 * 获得下周星期日的日期.
	 * 
	 * @return {@link java.util.Date}
	 */
	public static Date getNextSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		return monday;
	}

	/**
	 * 获得当前日期与本周日相差的天数.
	 * 
	 * @return int days
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		} else if (dayOfWeek == 0) {
			return -6;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 计算年龄（周岁）
	 * 
	 * @param birthday
	 *            出生日期
	 * @param calculateDate
	 *            计算日
	 * @return int years
	 */
	public static int calculateAge(Date birthday, Date calculateDate) {
		Calendar c = Calendar.getInstance();
		if (calculateDate != null) {
			c.setTime(calculateDate);
		}
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthday);
		int age = c.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		c.set(Calendar.YEAR, birth.get(Calendar.YEAR));
		if (dateCompare(c.getTime(), birth.getTime()) < 0) {
			return age - 1;
		}
		return age;
	}

	/**
	 * 比较两个日期的年月日，忽略时分秒.
	 * 
	 * @param d1
	 * @param d2
	 * @return 如果d1晚于d2返回大于零的值，如果d1等于d2返回0，否则返回一个负值
	 */
	public static int dateCompare(Date d1, Date d2) {
		Calendar c = Calendar.getInstance();
		c.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
		c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
		c2.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
		Date date0 = c2.getTime();

		c.setTime(d2);
		c2.set(Calendar.YEAR, c.get(Calendar.YEAR));
		c2.set(Calendar.MONTH, c.get(Calendar.MONTH));
		c2.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
		Date date1 = c2.getTime();

		return date0.compareTo(date1);
	}

	/**
	 * 计算两个日期间的月数.
	 * 
	 * @param date1
	 *            较早的一个日期
	 * @param date2
	 *            较晚的一个日期
	 * @return 如果前面的日期小于后面的日期将返-1
	 */
	public static int getMonths(Date date1, Date date2) {
		Calendar beginDate = Calendar.getInstance();
		beginDate.setTime(date1);
		Calendar now = Calendar.getInstance();
		now.setTime(date2);
		if (beginDate.after(now)) {
			return -1;
		}
		int mon = now.get(Calendar.MONTH) - beginDate.get(Calendar.MONTH);
		if (now.get(Calendar.DAY_OF_MONTH) < beginDate
				.get(Calendar.DAY_OF_MONTH)) {
			if (!(now.getActualMaximum(Calendar.DAY_OF_MONTH) == now
					.get(Calendar.DAY_OF_MONTH))) {
				mon -= 1;
			}
		}
		if (now.get(Calendar.YEAR) == beginDate.get(Calendar.YEAR)) {
			return mon;
		}
		return 12 * (now.get(Calendar.YEAR) - beginDate.get(Calendar.YEAR))
				+ mon;
	}
	
	/**
	 * 解析字符串日期.
	 * <span>格式：yyyy-MM-dd<span>
	 * @param strDate
	 * @return {@link java.util.Date}
	 * @throws ParseException
	 */
	public static Date parseDate(String strDate) throws ParseException {
		return dateFormaHolder.get().parse(strDate);
	}

	/**
	 * 解析字符串日期,格式:yyyy-MM-dd HH:mm:ss.
	 * @param strDate
	 * @return {@link java.util.Date}
	 * @throws ParseException
	 */
	public static Date parseDatetime(String strDate) throws ParseException {
		return datetimeFormaHolder.get().parse(strDate);
	}

	/**
	 * 格式化日期,格式：yyyy-MM-dd.
	 * @param date
	 * @return {@link String}
	 */
	public static String formatDate(Date date) {
		return dateFormaHolder.get().format(date);
	}

	/**
	 * 格式化日期,格式:yyyy-MM-dd HH:mm:ss.
	 * @param date
	 * @return {@link String}
	 */
	public static String formatDatetime(Date date) {
		if (date != null) {
			return datetimeFormaHolder.get().format(date);
		}
		return "";
	}

	/**
	 * 获得时间的各个部分 （比如年、月、日）.
	 * 
	 * @param date 目标日期
	 * @param fmt 日期格式
	 * @param DateStatic Calendar常量
	 * @return int
	 * @throws ParseException
	 */
	public static int get(String date, String fmt, int DateStatic)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(fmt);
		Date d = formatter.parse(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		return calendar.get(DateStatic);
	}

	/**
	 * 把字符串转化成日期型.
	 * @param name 日期串
	 * @param df 日期格式
	 * @param defaultValue name为空时返回的默认日期
	 * @return {@link Date}
	 */
	public static Date getDate(String name, String df, Date defaultValue) {
		if (name == null) {
			return defaultValue;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(df);

		try {
			return formatter.parse(name);
		} catch (ParseException e) {
		}

		return defaultValue;
	}

	/**
	 * 把字符串转化成日期型.
	 * @param dateStr 日期串
	 * @param format 日期格式
	 * @return {@link Date}
	 */
	public static Date getDate(String dateStr, String format) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(dateStr);
		} catch (Exception localException) {
		}
		return null;
	}

	/**
	 * 把字符串转化成日期型。 缺省为当前系统时间.
	 * 
	 * @param name 日期串：格式：yyyy-MM-dd
	 */
	public static Date getDate(String name) {
		return getDate(name, (Date)null);
	}

	/**
	 * 把字符串转化成日期型。 缺省为当前系统时间.
	 * 
	 * @param name 日期串：格式：yyyy-MM-dd HH:mm:ss
	 */
	public static Date getDateTime(String name) {
		return getDateTime(name, null);
	}

	/**
	 * 把字符串转化成日期型.
	 * @param name 日期串：格式yyyy-MM-dd
	 * @param defaultValue
	 * @return {@link Date}
	 */
	public static Date getDate(String name, Date defaultValue) {
		return getDate(name, "yyyy-MM-dd", defaultValue);
	}

	/**
	 * 把字符串转化成日期型.
	 * @param name 日期串：格式yyy-MM-dd HH:mm:ss
	 * @param defaultValue 默认日期
	 * @return {@link Date}
	 */
	public static Date getDateTime(String name, Date defaultValue) {
		return getDate(name, "yyyy-MM-dd HH:mm:ss", defaultValue);
	}

	/**
	 * mysql日期转换.
	 * @param seconds
	 * @return {@link Date}
	 */
	public static Date mysqlDate2Date(int seconds) {
		long l = (long) seconds * 1000;
		Date date = new Date(l);
		return date;
	}

	/**
	 * mysql日期转换.
	 * @param date
	 * @return {@link Long}
	 */
	public static long date2MysqlDate(Date date) {
		return date.getTime() / 1000;
	}

	/**
	 * 返回两个日期的时间差，返回的时间差格式可以是: Calendar.YEAR, Calendar.MONTH, Calendar.DATE
	 * 注意：该功能采用递归的方法，效率还有待解决，如果两个时间之差较大，而要求返回的时间格式又很小，这时效率就很差
	 * 但此方法在要求精度较高的情况下比较有效，如求月份差的时候就比较准确，考虑到了各种情况．如闰月，闰年.
	 * 
	 * @param earlyDate
	 * @param lateDate
	 * @param returnTimeFormat
	 * @return time
	 */
	public static int getBetweenTime(Calendar earlyDate, Calendar lateDate,
			int returnTimeFormat) {
		earlyDate = (Calendar) earlyDate.clone();
		lateDate = (Calendar) lateDate.clone();
		int time = 0;
		while (earlyDate.before(lateDate)) {
			earlyDate.add(returnTimeFormat, 1);
			time++;
		}
		return time;
	}

	/**
	 * 计算所给时间与当前时间相差年数
	 * @param lastDate
	 * @return int
	 */
	public static int getBetweenYear(Date lastDate) {
		int between = Integer.MIN_VALUE;
		if (lastDate != null) {
			Calendar cnow = Calendar.getInstance();
			Calendar clast = Calendar.getInstance();
			clast.setTime(lastDate);
			between = getBetweenTime(clast, cnow, Calendar.YEAR);
		}
		return between;
	}

	/**
	 * 求两个日期相差天数.
	 * 
	 * @param sd
	 *            起始日期，格式yyyy-MM-dd
	 * @param ed
	 *            终止日期，格式yyyy-MM-dd
	 * @return 两个日期相差天数
	 */
	public static long getBetweenDay(String sd, String ed) {
		return ((java.sql.Date.valueOf(ed)).getTime() - (java.sql.Date
				.valueOf(sd)).getTime()) / (3600 * 24 * 1000);
	}

	/**
	 * 求两个日期相差天数.
	 * 
	 * @param sd
	 *            起始日期，格式yyyy-MM-dd
	 * @param ed
	 *            终止日期，格式yyyy-MM-dd
	 * @return 两个日期相差天数
	 */
	public static long getBetweenDay(Date sd, Date ed) {
		return (ed.getTime() - sd.getTime()) / (3600 * 24 * 1000);
	}

	/**
	 * 求所给日期与当前日期相差天数.
	 * @param lastDate
	 * @return int
	 */
	public static int getBetweenDay(Date lastDate) {
		int between = Integer.MIN_VALUE;
		if (lastDate != null) {
			Calendar cnow = Calendar.getInstance();
			Calendar clast = Calendar.getInstance();
			clast.setTime(lastDate);
			between = getBetweenTime(clast, cnow, Calendar.DATE);
		}
		return between;
	}

	/**
	 * 得到给定的时间距离现在的天数.
	 * 
	 * @param last mysql日期
	 * @return int
	 */
	public static int getBetweenDays(int last) {
		Calendar cnow = Calendar.getInstance();
		Calendar clast = Calendar.getInstance();
		clast.setTime(DateUtil.mysqlDate2Date(last));
		int between = getBetweenTime(clast, cnow, Calendar.DATE);
		return between;
	}

	/**
	 * 格式化日期.
	 * @param date
	 * @param formate
	 * @return {@link String}
	 */
	public static String dateFormate(Date date, String formate) {
		if (date != null) {
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					formate);
			return sdf.format(date);
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param seconds
	 * @return {@link String}
	 */
	public static String dateFormateHuman(int seconds) {
		Date date = DateUtil.mysqlDate2Date(seconds);

		Calendar cnow = Calendar.getInstance();
		Calendar clast = Calendar.getInstance();
		cnow.setTime(new Date());
		clast.setTime(date);

		String format = "";
		if (cnow.get(Calendar.YEAR) == clast.get(Calendar.YEAR)
				&& cnow.get(Calendar.MONTH) == clast.get(Calendar.MONTH)
				&& cnow.get(Calendar.DATE) == clast.get(Calendar.DATE)) {
			format = "HH:mm";
		} else if (cnow.get(Calendar.YEAR) == clast.get(Calendar.YEAR)
				&& cnow.get(Calendar.MONTH) == clast.get(Calendar.MONTH)) {
			format = "MM月dd日";
		} else {
			format = "yyyy年MM月dd日";
		}

		return dateFormate(date, format);
	}

	/**
	 * 
	 * @param seconds
	 * @param formate
	 * @return {@link String}
	 */
	public static String dateFormate(int seconds, String formate) {
		Date date = DateUtil.mysqlDate2Date(seconds);
		return dateFormate(date, formate);
	}

	/**
	 * 
	 * @param name
	 * @param defaultValue
	 * @return {@link Date}
	 */
	public static Date getTime(String name, Date defaultValue) {
		return getDate(name, "HH:mm:ss", defaultValue);
	}

	/**
	 * 
	 * @param name
	 * @return {@link Date}
	 */
	public static Date getTime(String name) {
		return getTime(name, null);
	}

	/**
	 * 比较日期值对应的时间与字符串对应的时间值的大小(只比较HH:mm:ss部分).
	 * 
	 * @param dateTime
	 *            日期值
	 * @param timeString
	 *            时间字符串
	 * @return <br>
	 *         dateTime的时间早于 timeString的时间则返回 -1;<br>
	 *         dateTime的时间晚于 timeString的时间则返回 1;<br>
	 *         dateTime的时间等于 timeString的时间则返回 0;
	 */
	public static int dateTimeDiff(Date dateTime, String timeString) {
		Calendar dTime = Calendar.getInstance();
		Calendar sTime = Calendar.getInstance();
		dTime.setTime(dateTime);
		sTime.setTime(getTime(timeString));
		sTime.set(Calendar.YEAR, dTime.get(Calendar.YEAR));
		sTime.set(Calendar.MONTH, dTime.get(Calendar.MONTH));
		sTime.set(Calendar.DATE, dTime.get(Calendar.DATE));
		if (dTime.before(sTime)) {
			return -1;
		}
		if (dTime.after(sTime)) {
			return 1;
		}
		return 0;
	}

	/**
	 * 对日期进行加法运算后返回.
	 * @param dateTime 给定日期
	 * @param field Calendar常量
	 * @param amount 运算量
	 * @return {@link Date}
	 */
	public static Date rollDate(Date dateTime, int field, int amount) {
		if (dateTime != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(dateTime);
			c.add(field, amount);
			return c.getTime();
		}
		return null;
	}

	/**
	 * 功能：计算日期 作者：chendw 时间：2010-3-4 下午07:47:31 版本： 参数： 日期基值 date 计算类型 type 如
	 * Calendar.YEAR、Calendar.MONTH 计算偏值 num
	 * 
	 * @return {@link Date}
	 */
	public static Date caclDate(Date date, int type, int num) {
		Calendar C = Calendar.getInstance();
		C.setTime(date);
		C.add(type, num);
		return C.getTime();
	}

	/**
	 * 获得当前时间月的天数.
	 * 
	 * @param date
	 * @return int
	 */
	public static int getMonthOfDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static void main(String[] args) {
		// System.out.println(DateUtil.dateFormate(new Date(),"yyyy-MM-dd"));
	}

	/**
	 * 日期格式化.
	 * @param date
	 * @param typ
	 * @return {@link String}
	 */
	public static String dateTostring(Date date, String typ) {
		String sdate = (new SimpleDateFormat(typ)).format(date);
		return sdate;
	}

	/**
	 * 功能：根据生日获取用户年龄 作者：zhoutx 时间：2016-11-2 下午03:31:31 参数： 用户生日日期.
	 * @param birthday 生日
	 * 
	 * @return int
	 */
	public static int getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}
	
	/**
	 * 日期添加月操作.
	 * @param date
	 * @param num
	 * @return {@link Date}
	 */
	 public static Date addMonths(Date date, int num)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(2, num);
	    return cal.getTime();
	  }
	  
	 /**
	  * 日期添加日.
	  * @param date
	  * @param num
	  * @return {@link Date}
	  */
	  public static Date addDays(Date date, int num)
	  {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DAY_OF_MONTH, num);
	    return cal.getTime();
	  }
	  
	  /**
	   * 日期比较.
	   * @param date1
	   * @param date2
	   * @return long
	   */
	  public static long compareTime(Date date1, Date date2)
	  {
	    long daterange = date1.getTime() - date2.getTime();
	    
	    return daterange;
	  }

	  /**
	   * 日期格式化.
	   * @param date
	   * @param format
	   * @return {@link String}
	   */
	  public static String dateFormat(Date date, String format)
	  {
	    if (date == null) {
	      return "";
	    }
	    if (date.getTime() <= 0L) {
	      return "";
	    }
	    SimpleDateFormat formatter = new SimpleDateFormat(
	      format);
	    return formatter.format(date);
	  }
	  
	  /**
	   * 日期格式化，格式：yyyy-MM-dd HH:mm:ss.
	   * @param date
	   * @return {@link String}
	   */
	  public static String dateFullFormat(Date date)
	  {
	    if (date.getTime() <= 0L) {
	      return "";
	    }
	    SimpleDateFormat formatter = new SimpleDateFormat(
	      "yyyy-MM-dd HH:mm:ss");
	    return formatter.format(date);
	  }
}
