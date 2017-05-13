package com.goldhome.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 时间处理共同类
 * Created by yanruilin on 2017/5/13.
 */
public class TimeUtils
{
    /**
     * 设置系统时区
     *
     * @param zone 时区
     */
    public static void setSystemTimeZone(String zone)
    {
        //设置系统时区
        DateTimeZone.setDefault(DateTimeZone.forID(zone));
    }

    /**
     * 判断该UTC时间是否是夏令时
     *
     * @param utc UTC时间
     * @return
     */
    public static boolean isDaylightTime(long utc)
    {
        DateTimeZone zone = DateTimeZone.getDefault();
        boolean isStandardOffset = zone.isStandardOffset(utc * 1000);
        return !isStandardOffset;
    }

    /**
     * 日期转换为UTC时间
     *
     * @param date 日期
     * @return utc时间
     */
    public static long dateToUtc(String date, String pattern)
    {
        long utc;

        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = DateTime.parse(date, format);

        utc = dateTime.getMillis() / 1000;
        return utc;
    }

    /**
     * UTC时间转换为日期
     *
     * @param utc     UTC 时间
     * @param pattern 格式化
     * @return 日期
     */
    public static String utcToDate(long utc, String pattern)
    {
        DateTime dateTime = new DateTime(utc * 1000);
        return dateTime.toString(pattern);
    }


    /**
     * 获取当前时间
     *
     * @param pattern 格式化
     * @return 当前日期
     */
    public static String getCurrentDate(String pattern)
    {
        return Instant.now().toDateTime().toString(pattern);
    }

    /**
     * 获取当前时区偏移量
     *
     * @param utc UTC时间
     * @return
     */
    public static int getTimeZoneOffset(long utc)
    {
        DateTimeZone zone = DateTimeZone.getDefault();

        int offset = zone.getOffset(utc * 1000);
        return offset / 1000;
    }

    /**
     * 如果是夏令时时间，添加上(DST)
     *
     * @param dateStr 日期
     * @param pattern 格式化
     * @return 返回修改后的日期
     */
    public static String dateAddDst(String dateStr, String pattern)
    {
        long utc = dateToUtc(dateStr, pattern);
        if (isDaylightTime(utc)) {
            dateStr += "(DST)";
        }

        return dateStr;
    }

    /**
     * 获取天表后缀
     * 当前时间距离19700101有多少天
     *
     * @param utc UTC时间
     * @return days 返回天数
     */
    public static int getDaySuff(long utc)
    {
        DateTime dateTime = new DateTime(utc * 1000);

        int hour = dateTime.getHourOfDay();
        int diff = 12 - hour;

        if (diff > 0) {
            dateTime = dateTime.plusHours(diff);
        } else {
            dateTime = dateTime.minusHours(Math.abs(diff));
        }

        String result = String.valueOf(dateTime.getMillis() / new Long(24 * 60 * 60 * 1000));

        return Integer.parseInt(result);
    }

    /**
     * 获取月表后缀
     * 当前时间距离19700101有多少个月
     *
     * @param utc UTC时间
     * @return months 返回月份
     */
    public static int getMonthSuff(long utc)
    {

        DateTime startTime = new DateTime(0L);
        DateTime endTime  = new DateTime(utc * 1000);
        int day = 15 - endTime.getDayOfMonth();

        if (day > 0)
        {
            endTime = endTime.plusDays(day);
        }
        else
        {
            endTime = endTime.minusDays(Math.abs(day));
        }

        return  Months.monthsBetween(startTime,endTime).getMonths();
    }

}
