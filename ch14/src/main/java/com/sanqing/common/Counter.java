/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.common;

import com.sanqing.database.SQLDBOperator;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.sanqing.common:
//            Debug

public class Counter {

    public Counter() {
        sdbo = null;
    }

    public boolean isIp(String ip) {
        boolean isIp = false;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select * from newscommon where ip=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, ip);
            rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isIp = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Counter isIp() " + nullE.getMessage());
                Debug.writeLog("Counter isIp(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Counter isIp() " + sqlE.getMessage());
            Debug.writeLog("Counter isIp(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isIp;
    }

    public int counter() {
        int counter = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select * from newscommon;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                counter = rs.getInt("counter");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Counter isIp() " + nullE.getMessage());
                Debug.writeLog("Counter isIp(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Counter isIp() " + sqlE.getMessage());
            Debug.writeLog("Counter isIp(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return counter;
    }

    public void upCounter(String ip) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update newscommon set counter=counter+1,ip=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, ip);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Counter upCounter() " + e.getMessage());
            Debug.writeLog("Counter upCounter(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    private SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 234 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method isIp
Couldn't resolve all exception handlers in method isIp
Overlapped try statements detected. Not all exception handlers will be resolved in the method counter
Couldn't resolve all exception handlers in method counter
Overlapped try statements detected. Not all exception handlers will be resolved in the method upCounter
Couldn't resolve all exception handlers in method upCounter
	Exit status: 0
	Caught exceptions:
*/