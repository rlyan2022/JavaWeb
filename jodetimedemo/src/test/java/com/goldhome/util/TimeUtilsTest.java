package com.goldhome.util;

import org.junit.*;

/**
 * Created by Administrator on 2017/5/13.
 */
public class TimeUtilsTest
{
    @BeforeClass
    public static void globalInit()
    {
    }

    @AfterClass
    public static void globalDestory()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        TimeUtils.setSystemTimeZone("America/Santiago");
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void dateToUtcTest()
    {
        String format = "yyyy-MM-dd HH:mm";
        System.out.println(TimeUtils.dateToUtc("2017-05-13 23:00", format));
        System.out.println(TimeUtils.dateToUtc("2017-04-13 23:00", format));
        //2017-05-13 23:00(DST) ---- 1494727200
        //2017-05-14 00:00 ---- 1494734400
    }

    @Test
    public void utcToDateTest()
    {
        String format = "yyyy-MM-dd HH:mm";
        long utc = TimeUtils.dateToUtc("2017-05-13 23:00", format);
        String dateStr = TimeUtils.utcToDate(utc, format);
        System.out.println(dateStr);
        System.out.println( TimeUtils.utcToDate(1492135200L, format));
    }

    @Test
    public void isDaylightTimeTest()
    {
        System.out.println("2017-05-13 23:00(DST) is lightDay ?" + TimeUtils.isDaylightTime(1494727200));
        System.out.println("2017-05-14 00:00 is lightDay ?" + TimeUtils.isDaylightTime(1494734400));
    }

    @Test
    public void getCurrentDateTest()
    {
        String format = "yyyy-MM-dd HH:mm";
        System.out.println(TimeUtils.getCurrentDate(format));
    }

    @Test
    public void getTimeZoneOffsetTest()
    {
        System.out.println(TimeUtils.getTimeZoneOffset(1494727200L));
        System.out.println(TimeUtils.getTimeZoneOffset(1494734400L));
    }

    @Test
    public void dateAddDstTest()
    {
        String format = "yyyy-MM-dd HH:mm";
        System.out.println(TimeUtils.dateAddDst("2017-05-13 23:00", format));
        System.out.println(TimeUtils.dateAddDst("2017-05-14 00:00", format));
    }

    @Test
    public void getDaySuffTest()
    {
        System.out.println(TimeUtils.getDaySuff(1494727200L));
        System.out.println(TimeUtils.getDaySuff(1494734400L));
    }

    @Test
    public void getMonthSuffTest()
    {
        System.out.println(TimeUtils.getMonthSuff(1492135200L));
        System.out.println(TimeUtils.getMonthSuff(1494734400L));
    }

}
