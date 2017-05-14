/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news.manage;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.DateUtil;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ISLogin {

    public ISLogin() {
        sdbo = null;
    }

    public boolean isUsernameOk(String useName) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk = false;
        String strSql = "select * from newsadmin where userName=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, CodeFilter.toHtml(useName));
            ResultSet rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0) {
                    isOk = true;
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                Debug.writeLog("ISLogin_rs isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("ISLogin isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return isOk;
    }

    public boolean isPasswordOk(String userName, String passWd) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk = false;
        String strSql = "select * from newsadmin where userName=? and passWd=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, CodeFilter.toHtml(userName));
            sdbo.setString(2, CodeFilter.toHtml(passWd));
            ResultSet rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0) {
                    isOk = true;
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                Debug.writeLog("ISLogin_rs isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("ISLogin isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return isOk;
    }

    public void upTimeAndIp(String ip) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk = false;
        String strSql = "update newsadmin set lastLogin=?,lastLoginIP=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, DateUtil.getNowDate());
            sdbo.setString(2, ip);
            sdbo.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("ISLogin isok(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public String strTime(String userName) {
        String strSql = "select * from newsadmin where userName='" + userName + "';";
        String strTime = null;
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                strTime = rs.getString("lastLogin");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("ISLogin timeIp() " + nullE.getMessage());
                Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("ISLogin timeIp() " + sqlE.getMessage());
            Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return strTime;
    }

    public String strIp(String userName) {
        String strSql = "select * from newsadmin where userName='" + userName + "';";
        String strIp = null;
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                strIp = rs.getString("lastLoginIp");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("ISLogin timeIp() " + nullE.getMessage());
                Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("ISLogin timeIp() " + sqlE.getMessage());
            Debug.writeLog("ISLogin timeIp(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return strIp;
    }

    private SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 624 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method upTimeAndIp
Couldn't resolve all exception handlers in method upTimeAndIp
Overlapped try statements detected. Not all exception handlers will be resolved in the method strTime
Couldn't resolve all exception handlers in method strTime
Overlapped try statements detected. Not all exception handlers will be resolved in the method strIp
Couldn't resolve all exception handlers in method strIp
	Exit status: 0
	Caught exceptions:
*/