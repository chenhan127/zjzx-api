/**
 * freeze.com Inc.
 * Copyright (c) 2012-2012 All Rights Reserved.
 */
package com.zjzx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;

/**
 * 日期工具类
 * 
 * @author renxiao.wu
 * @version $Id: DateUtil.java, v 0.1 2012-8-11 下午01:35:13 renxiao.wu Exp $
 */
public abstract class DateUtil {

    /** yyyy-MM-dd HH:mm:ss.SSS */
    private static final String YYYY_MM_DD_HHMMSSsss = "yyyy-MM-dd HH:mm:ss.SSS";

    /** yyyy-MM-dd HH:mm:ss */
    private static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    /** yyyy-MM-dd HH:mm */
    private static final String YYYY_MM_DD_HHMM = "yyyy-MM-dd HH:mm";

    /** yyyy-MM-dd */
    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    /** yyyyMMdd */
    private static final String YYYYMMDDHH = "yyyyMMddHH";

    /** yyyyMMddHH */
    private static final String YYYYMMDD = "yyyyMMdd";

    
	public static final Map<Integer, String> WeekMap = new HashMap<Integer, String>();
	static {
		WeekMap.put(1, "一");
		WeekMap.put(2, "二");
		WeekMap.put(3, "三");
		WeekMap.put(4, "四");
		WeekMap.put(5, "五");
		WeekMap.put(6, "六");
		WeekMap.put(7, "日");
		WeekMap.put(0, "日");
	}
    /**
     * 日期转换为字符串，格式：yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param date
     *            日期
     * @return 字符串
     */
    public static final String convertY_M_D_H_M_S_s(Date date) {
        if (null == date) {
            return null;
        }
        return getDateFormat(YYYY_MM_DD_HHMMSSsss).format(date);
    }

    /**
     * 日期转换为字符串，格式：yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     *            日期
     * @return 字符串
     */
    public static final String convertY_M_D_H_M_S(Date date) {
        if (null == date) {
            return null;
        }
        return getDateFormat(YYYY_MM_DD_HHMMSS).format(date);
    }

    /**
     * 日期转换为字符串，格式：yyyy-MM-dd
     * 
     * @param date
     *            日期
     * @return 字符串
     */
    public static final String convertY_M_D(Date date) {
        if (null == date) {
            return null;
        }
        return getDateFormat(YYYY_MM_DD).format(date);
    }

    /**
     * 日期转换为字符串，格式：yyyy-MM-dd HH:mm
     * 
     * @param date
     *            日期
     * @return 字符串
     */
    public static final String convertY_M_D_H_M(Date date) {
        if (null == date) {
            return null;
        }
        return getDateFormat(YYYY_MM_DD_HHMM).format(date);
    }

    /**
     * 日期转换为字符串，格式：yyyyMMddHH
     * 
     * @param date
     *            日期
     * @return 字符串
     */
    public static final String convertYMDH(Date date) {
        if (null == date) {
            return null;
        }
        return getDateFormat(YYYYMMDDHH).format(date);
    }

    /**
     * 日期转换为字符串，格式：yyyyMMdd
     * 
     * @param date
     *            日期
     * @return 字符串
     */
    public static final String convertYMD(Date date) {
        if (null == date) {
            return null;
        }
        return getDateFormat(YYYYMMDD).format(date);
    }

    /**
     * 获取当前时间字符串，格式：yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @return 字符串
     */
    public static final String getNowYMDHMSs() {
    	System.setProperty("user.timezone","Asia/Shanghai");  
        return convertY_M_D_H_M_S_s(new Date());
    }

    /**
     * 获取当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
     * 
     * @return 字符串
     */
    public static final String getNowYMDHMS() {
    	System.setProperty("user.timezone","Asia/Shanghai");  
        return convertY_M_D_H_M_S(new Date());
    }

    /**
     * 获取当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
     * 
     * @return 字符串
     */
    public static final String getNowYMD() {
    	System.setProperty("user.timezone","Asia/Shanghai");  
        return convertYMD(new Date());
    }

    /**
     * 获取前一天时间字符串，格式：yyyy-MM-dd HH:mm:ss
     * 
     * @return 字符串
     */
    public static final String getYesterdayYMD() {
    	System.setProperty("user.timezone","Asia/Shanghai");  
        return convertYMD(DateUtil.addDay(new Date(), -1));
    }

    /**
     * 字符串转换为日期
     * 
     * @param dateStr
     *            字符串
     * @return 日期
     */
    public static final Date getByYMDHMSsStr(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        try {
            return getDateFormat(YYYY_MM_DD_HHMMSSsss).parse(dateStr);
        } catch (ParseException e) {
        	e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串转换为日期
     * 
     * @param dateStr
     *            字符串
     * @return 日期
     */
    public static final Date getByYMDHMSStr(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }

        try {
            return getDateFormat(YYYY_MM_DD_HHMMSS).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 返回增加N秒后的时间
     * 
     * @param date
     *            原时间
     * @param second
     *            增加秒数
     * @return 目标时间
     */
    public static final Date addSecond(Date date, int second) {

        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 返回增加N天后的时间
     * 
     * @param date
     *            原时间
     * @param second
     *            增加秒数
     * @return 目标时间
     */
    public static final Date addDay(Date date, int day) {

        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 返回增加N分后的时间
     * 
     * @param date
     *            原时间
     * @param second
     *            增加秒数
     * @return 目标时间
     */
    public static final Date addMinute(Date date, int minute) {

        if (null == date) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取秒数
     * 
     * @param date
     *            时间
     * @return 秒数
     */
    public static final int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取两个时间相差的秒数
     * 
     * @param o1
     * @param o2
     * @return 秒数
     */
    public static final int subSecond(Date o1, Date o2) {
        if (null == o1 || null == o2) {
            return 0;
        }

        long subTime = o1.getTime() - o2.getTime();

        return Math.abs(Long.valueOf(subTime / 1000).intValue());
    }

    /**
     * 获取两个时间相差的小时数
     * 
     * @param o1
     * @param o2
     * @return 小时数
     */
    public static final int subHour(Date o1, Date o2) {
        if (null == o1 || null == o2) {
            return 0;
        }

        long subTime = o1.getTime() - o2.getTime();

        return Math.abs(Double.valueOf(subTime / (60 * 60 * 1000)).intValue());
    }

    /**
     * 获取日期格式化
     * 
     * @param pattern
     * @return
     */
    private static DateFormat getDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        TimeZone timeZoneCN = TimeZone.getTimeZone("Asia/Shanghai");
        df.setTimeZone(timeZoneCN);
        df.setLenient(false);
        return df;
    }
    /**
     * 获取当日开始时间
     * @return
     */
    public static String getToDayBeginTimeStr(){
    	System.setProperty("user.timezone","Asia/Shanghai");   
    	return DateUtil.convertY_M_D(new Date(getStartTime()))+" 00:00:00";
    }
    
//    public static void main(String[] args){
//    	// System.out.println(monthAddFrist("2017-12-02"));
//    	System.out.println(monthAdd("2017-12-02", -6));
//    }
    /**
     * 获取当日结束时间
     * @return
     */
    public static String getToDayEndTimeStr(){
    	System.setProperty("user.timezone","Asia/Shanghai");  
    	return DateUtil.convertY_M_D(new Date(getEndTime()))+" 23:59:59";
    }
    
    /**
     * 获取当日结束时间
     * @return
     */
    public static String getTodayEndTimeStr(){
    	System.setProperty("user.timezone","Asia/Shanghai");  
    	return DateUtil.convertY_M_D(new Date())+" 23:59:59";
    }
    
    /**
     * 获取当日开始时间
     * @return
     */
    public static String getTodayBeginTimeStr(){
    	System.setProperty("user.timezone","Asia/Shanghai");  
    	return DateUtil.convertY_M_D(new Date())+" 00:00:00";
    }
    
    
    private static Long getStartTime(){ 
    	System.setProperty("user.timezone","GMT+08");
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime();  
    }
    private static Long getEndTime(){  
    	System.setProperty("user.timezone","GMT+08");
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime().getTime();  
    }  
    
    public static String getNowWeekEnd(){
    	return DateUtil.convertY_M_D_H_M_S(DateUtil.addDay(DateUtil.getByYMDHMSStr(getNowWeekBegin()), 7));
    }
    public static Date formatDateStrToDate(String dateStr,String fmt){
    	DateFormat df = new SimpleDateFormat(fmt);
    	try {
    		TimeZone timeZoneCN = TimeZone.getTimeZone("Asia/Shanghai");
            df.setTimeZone(timeZoneCN);
			return df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public static String formatDateToMM_DD(String dateStr){
    	DateFormat df = new SimpleDateFormat("MM-dd");
    	TimeZone timeZoneCN = TimeZone.getTimeZone("Asia/Shanghai");
        df.setTimeZone(timeZoneCN);
    	Date date = formatDateStrToDate(dateStr,DateUtil.YYYY_MM_DD);
    	return df.format(date);
    }
    
    public static String getNowWeekBegin() {
    	int mondayPlus;
    	Calendar cd = Calendar.getInstance();
    	// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
    	int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
    	if(dayOfWeek == 0){
    		dayOfWeek = 7;
    	}
    	if (dayOfWeek == 1) {
    		mondayPlus = 0;
    	} else {
    		mondayPlus = 1 - dayOfWeek;
    	}
    	GregorianCalendar currentDate = new GregorianCalendar();
    	currentDate.add(GregorianCalendar.DATE, mondayPlus);
    	Date monday = currentDate.getTime();


    	DateFormat df = new SimpleDateFormat(DateUtil.YYYY_MM_DD);
    	TimeZone timeZoneCN = TimeZone.getTimeZone("Asia/Shanghai");
        df.setTimeZone(timeZoneCN);
    	String preMonday = df.format(monday);

    	return preMonday + " 00:00:00";


    	}
    
    public static Integer getweekOfMounth(String dateString){

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date =null;
		try {
			TimeZone timeZoneCN = TimeZone.getTimeZone("Asia/Shanghai");
			sdf.setTimeZone(timeZoneCN);
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);
    	return weekOfMonth;
    }
    
    public static Integer getCurWeek(){
    	String date = DateUtil.convertY_M_D(new Date());
    	return getweekOfMounth(date);
    }
    
    /**
     * return 09:21
     * @param date
     * @return
     */
    public static String getCurHour(Date date){
    	String result = convertY_M_D_H_M(date);	// "yyyy-MM-dd HH:mm"
    	return result.substring(11);
    }
    
    public static String spliceDateToY_M_S_H_M(String dateStr){
    	String[] array = dateStr.split(" ");
    	try{
	    	String datePrifix = array[0];
	    	String suffix = array[1];
	    	String suffixArray[] = suffix.split(":");
	    	return datePrifix+" "+suffixArray[0]+":"+suffixArray[1];
    	}catch(Exception e){
    		return dateStr;
    	}
    
    	
    }
    
    public static String getLastMonthDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.add(c.MONTH, -1);// 得到上个月的月份
		java.util.Date d = c.getTime();
		return sdf.format(d);
	}
    
    /**
	 * 获取当前月最大天数
	 **/
	public static int getMaxDay(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, 1);
		c.add(Calendar.DAY_OF_YEAR, -1);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	public static int getMaxDay(String rootdate) {
		int year = Integer.parseInt(rootdate.split("-")[0]);
		int month = Integer.parseInt(rootdate.split("-")[1]);
		return getMaxDay(year, month);
	}
	
	
	/**
	 * 根据年月日获取星期几
	 **/
	public static int dayForWeek(String pTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}
	
	/**
	 * 将日期字符格式话Date 年-月
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parseDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date dt = null;
		try {
			dt = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return dt;
	}
	
	public static String getLastMonth(String date) {
		String dateArray[] = date.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.set(Integer.parseInt(dateArray[0]), Integer.parseInt(dateArray[1]), 1);
		c.add(c.MONTH, -2);
		java.util.Date d = c.getTime();
		return sdf.format(d);
	}
	
	 /** 
     * 月份加一 
     * @param date 
     * @return 
     */  
    public static String monthAddFrist(String date){  
          
        DateFormat df=new SimpleDateFormat("yyyy-MM");  
        try {  
            Calendar ct=Calendar.getInstance();  
            ct.setTime(df.parse(date));  
            ct.add(Calendar.MONTH, +1);  
            return df.format(ct.getTime());  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return ""; 
    }
    
    public static String monthAdd(String date,int num){  
        
        DateFormat df=new SimpleDateFormat("yyyy-MM");  
        try {  
            Calendar ct=Calendar.getInstance();  
            ct.setTime(df.parse(date));  
            ct.add(Calendar.MONTH, num);  
            return df.format(ct.getTime());  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return ""; 
    }
        
	/**
	 * 获取当前月多少周
	 * 
	 * @param dateStr
	 * @return
	 */
	public static int getZouByMounth(String dateStr) {
		Date date = parseDate(dateStr);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}
	public static String addZero(int num) {
		if (num >= 10) {
			return num + "";
		}
		return "0" + num;
	}
    /**
     * 返回中文: 星期几
     * @param date
     * @return
     */
    public static String getCurWeekDay(Date date){
    	Calendar cal = Calendar.getInstance();  
	    int weekDay = cal.get(Calendar.DAY_OF_WEEK); 
	    if(weekDay == Calendar.MONDAY)
	    	return "星期一";
	    else if(weekDay == Calendar.TUESDAY)
	    	return "星期二";
	    else if(weekDay == Calendar.WEDNESDAY)
	    	return "星期三";
	    else if(weekDay == Calendar.THURSDAY)
	    	return "星期四";	
	    else if(weekDay == Calendar.FRIDAY)
	    	return "星期五";
	    else if(weekDay == Calendar.SATURDAY)
	    	return "星期六";
	    else 
	    	return "星期日";
    }
    
    
}
