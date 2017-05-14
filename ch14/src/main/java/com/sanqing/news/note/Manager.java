/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news.note;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.DateUtil;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;

import java.sql.ResultSet;

public class Manager {

    public Manager() {
        sdbo = null;
        passWd = null;
    }

    public boolean isAdminOk(String adminName) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOk = false;
        String strSql = "select * from noteadmin where adminName=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, CodeFilter.toHtml(adminName));
            ResultSet rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0) {
                    isOk = true;
                    passWd = rs.getString("adminPasswd");
                }
                rs.close();
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

    public boolean isPasswdOk(String adminPasswd) {
        boolean isOn = false;
        if (passWd.equals(CodeFilter.toHtml(adminPasswd)))
            isOn = true;
        return isOn;
    }

    public boolean isOldPwd(String passWd, String adminName) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isOn = false;
        String sql = "select adminPasswd from noteadmin where adminName=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, adminName);
            ResultSet rs = sdbo.executeQuery();
            rs.next();
            String adminPasswd = rs.getString("adminPasswd");
            if (passWd.equalsIgnoreCase(adminPasswd))
                isOn = true;
            rs.close();
        } catch (Exception e) {
            System.out.print("Manager isOldPwd() " + e.getMessage());
            Debug.writeLog("Manager isOldPwd(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return isOn;
    }

    public void upPasswd(String adminPasswd, String adminName) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "update noteadmin set adminPasswd=? where adminName=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, CodeFilter.toHtml(adminPasswd));
            sdbo.setString(2, CodeFilter.toHtml(adminName));
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Manager upPasswd() " + e.getMessage());
            Debug.writeLog("Manager upPasswd(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void delNote(int noteId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "delete from noteguest where noteId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, noteId);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Manager delNote() " + e.getMessage());
            Debug.writeLog("Manager delNote(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void insReply(int noteId, String content) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "insert into notereply(noteId,content,replyTime) values(?,?,?);";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, noteId);
            sdbo.setString(2, CodeFilter.toHtml(content));
            sdbo.setString(3, DateUtil.getStringDateShort());
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Manager insReply " + e.getMessage());
            Debug.writeLog("Manager insReply, Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    private SQLDBOperator sdbo;
    private String passWd;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 203 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method upPasswd
Couldn't resolve all exception handlers in method upPasswd
Overlapped try statements detected. Not all exception handlers will be resolved in the method delNote
Couldn't resolve all exception handlers in method delNote
Overlapped try statements detected. Not all exception handlers will be resolved in the method insReply
Couldn't resolve all exception handlers in method insReply
	Exit status: 0
	Caught exceptions:
*/