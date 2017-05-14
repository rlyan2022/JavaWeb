/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.common;

import java.text.SimpleDateFormat;
import java.util.Date;

// Referenced classes of package com.sanqing.common:
//            Debug

public class DateUtil {

    public DateUtil() {
    }

    public static String getNowDate() {
        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = format.format(dateTime);
        Debug.writeLog(strTime);
        return strTime;
    }

    public static String getStringDate() {
        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strTime = format.format(dateTime);
        return strTime;
    }

    public static String getStringDateShort() {
        Date dateTime = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String strTime = format.format(dateTime);
        return strTime;
    }
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 203 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/