package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtils {
    
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyyMMdd";
    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 
     * date 类型转化成字符串
     *
     * @param date
     * @param patter
     * @return
     */
    public static String dateToString(Date date, String patter) {
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        return sdf.format(date);
    }
    
    /**
     * 
     * 根据格式要求，获取当前时间
     *
     * @param patter
     * @return
     */
    public static Date nowByDate(String patter){
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        Date date = new Date();
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
    
    /**
     * 
     * 传入日期格式，转化个需要的格式
     *
     * @param patter
     * @return
     */
    public static Date byDate(Date date,String patter){
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        try {
            return sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
    
    /**
     * 
     * 传入日期格式，转化个需要的格式
     *
     * @param patter
     * @return
     */
    public static Date byString(String date,String patter){
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    /**
     *  获取时间差（年-月-日）方法
     * @param
     * @return
     */
    public static String getTime(Date currentTime,Date firstTime){
        long diff = currentTime.getTime() - firstTime.getTime();//这样得到的差值是微秒级别
        Calendar currentTimes =dataToCalendar(currentTime);//当前系统时间转Calendar类型
        Calendar  firstTimes =dataToCalendar(firstTime);//查询的数据时间转Calendar类型
        int year = currentTimes.get(Calendar.YEAR) - firstTimes.get(Calendar.YEAR);//获取年
        int month = currentTimes.get(Calendar.MONTH) - firstTimes.get(Calendar.MONTH);
        int day = currentTimes.get(Calendar.DAY_OF_MONTH) - firstTimes.get(Calendar.DAY_OF_MONTH);
        if (day < 0) {
            month -= 1;
            currentTimes.add(Calendar.MONTH, -1);
            day = day + currentTimes.getActualMaximum(Calendar.DAY_OF_MONTH);//获取日
        }
        if (month < 0) {
            month = (month + 12) % 12;//获取月
            year--;
        }
        long days = diff / (1000 * 60 * 60 * 24);
        String CountTime=year+"-"+month+"-"+day;
        return CountTime;
    }
    //Date类型转Calendar类型
    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取相差的天数
     * @param
     * @return
     */
    public static long getDifferenceDay(Date currentTime,Date firstTime){
        long diff = currentTime.getTime() - firstTime.getTime();//这样得到的差值是微秒级别
        long days = diff / (1000 * 60 * 60 * 24);
        return days;
    }
    /**
     * 当前日期加上天数后的日期
     * @param num 为增加的天数
     * @return
     */
    public static String plusDay(int num){
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currdate = format.format(d);
        System.out.println("现在的日期是：" + currdate);

        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DATE, num);// num为增加的天数，可以改变的
        d = ca.getTime();
        String enddate = format.format(d);
        System.out.println("增加天数以后的日期：" + enddate);
        return enddate;
    }
    
    /**
     * 
     * 获取当前 年月
     *
     * @return
     */
    public static String currentMonth() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }
    
    public static Date stringToDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(dateString);
        }catch(Exception e) {}
        return null;
    }
}
