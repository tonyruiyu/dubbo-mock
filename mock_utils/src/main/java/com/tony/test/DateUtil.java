package com.tony.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期帮助工具
 * 
 * @author wangyudong
 * @version
 * @see
 * @since
 */
public class DateUtil {

    /**
     * 日期对象转换成指定格式的字符串
     * 
     * @param date
     * @param format
     *            指定的日期格式
     * @return
     */
    public static String date2String(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 把一定格式的日期字符串 转换成为日期对象
     * 
     * @param dateString
     * @param format
     * @return
     */
    public static Date string2Date(String dateString, String format) {

        if (dateString == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得比当前时间早 【seconds】 秒 的时间对象
     * 
     * @param seconds
     * @return
     */
    public static Date getDateBefore(int seconds, Date date) {
        Date before = new Date(date.getTime() - (seconds * 1000));
        return before;
    }

    /**
     * 获得比当前时间晚【seconds】 秒 的时间对象
     * 
     * @param seconds
     * @return
     */
    public static Date getDateAfter(int seconds, Date date) {
        Date after = new Date(date.getTime() + (seconds * 1000));
        return after;
    }

    public static String getLastDayOfMonth(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return formatDate(cal.getTime(), "yyyy-MM-dd 23:59:59");
    }

    public static String getFirstDayOfMonth(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return formatDate(cal.getTime(), "yyyy-MM-dd 00:00:00");
    }
    
    public static String getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return formatDate(cal.getTime(), "yyyy-MM-dd 23:59:59");
    }
    
    public static String getStartTimeOfDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
        return formatDate(cal.getTime(), "yyyy-MM-dd 00:00:00");
    }
    
    public static String getEndTimeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return formatDate(cal.getTime(), "yyyy-MM-dd 23:59:59");
    }

    public static String getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return formatDate(cal.getTime(), "yyyy-MM-dd 00:00:00");
    }
    
    public static String getToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return formatDate(cal.getTime(), "yyyy-MM-dd 00:00:00");
    }
    
    public static String getFirstDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, 2);
        return formatDate(cal.getTime(), "yyyy-MM-dd 00:00:00");
    }
    
    public static String getLastDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, 7);
        cal.add(Calendar.DATE, 1);
        return formatDate(cal.getTime(), "yyyy-MM-dd 23:59:59");
    }

    public static String formatDate(Date aDate, String dateformat) {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(dateformat);
        return bartDateFormat.format(aDate);
    }

//    public static void main(String[] args) {
//        Date d = new Date();
//        System.out.println(formatDate(getFirstDayOfMonth(d), "yyyy-MM-dd 00:00:00") + "~"
//                + formatDate(getLastDayOfMonth(d), "yyyy-MM-dd 00:00:00"));
//    }

}
