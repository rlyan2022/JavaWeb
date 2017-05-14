/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news;

import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;

import java.sql.ResultSet;

public class Test {

    public Test() {
        sdbo = null;
    }

    public int getTotalUser() {
        int total = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strQuery = "select count(*) as total from usr where purview=?;";
        int str = 1;
        try {
            sdbo.prepareStatement(strQuery);
            sdbo.setInt(1, str);
            ResultSet rs = sdbo.executeQuery();
            try {
                if (rs.next())
                    total = rs.getInt("total");
            } catch (Exception e) {
                e.printStackTrace(System.out);
                Debug.writeLog("In getTotalss(), Exception Occured ! Info: " + e.getLocalizedMessage());
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In getTotal(), Exception Occured ! Info: " + e.getLocalizedMessage());
        }
        return total;
    }

    private SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 218 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/