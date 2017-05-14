/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing;

import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;

public class abc {

    public abc() {
        sdbo = null;
    }

    public void insSql(int myOther, int classId, int kindId, String headTitle, String content, String connect, String author,
                       String editor, String newsFrom, int hits, int top, int state, int tag, String newsTime) {
        java.sql.ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into news(myOther,classId,kindId,headTitle,content,connect,author,editor,newsFrom,hits,top,state,tag,newsTime) values(" + myOther + ",'" + classId + "','" + kindId + "','" + headTitle + "','" + content + "','" + connect + "','" + author + "','" + editor + "','" + newsFrom + "'," + hits + "," + top + "," + state + "," + tag + ",'" + newsTime + "');";
        try {
            rs = sdbo.executeQuery(strSql);
        } catch (Exception sqlE) {
            System.out.print("NewsShow topNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void insSql1(String userName, String passWd, int sex, String question, String answer, String emailAddr, String qq,
                        String http, String regTime) {
        java.sql.ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into newsusr(userName,passWd,sex,question,answer,emailAddr,qq,http,regTime) values('" + userName + "','" + passWd + "'," + sex + ",'" + question + "','" + answer + "','" + emailAddr + "','" + qq + "','" + http + "','" + regTime + "');";
        try {
            rs = sdbo.executeQuery(strSql);
        } catch (Exception sqlE) {
            System.out.print("NewsShow topNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void insql2(int noteId, String userName, String email, String qq, String url, String headTitle, String content,
                       String image, String noteTime) {
        java.sql.ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into NOTEGuest(noteId,userName,email,qq,url,headTitle,content,image,noteTime) values('" + noteId + "','" + userName + "','" + email + "','" + qq + "','" + url + "','" + headTitle + "','" + content + "','" + image + "','" + noteTime + "');";
        try {
            rs = sdbo.executeQuery(strSql);
        } catch (Exception sqlE) {
            System.out.print("NewsShow topNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
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
Overlapped try statements detected. Not all exception handlers will be resolved in the method insSql
Couldn't resolve all exception handlers in method insSql
Overlapped try statements detected. Not all exception handlers will be resolved in the method insSql1
Couldn't resolve all exception handlers in method insSql1
Overlapped try statements detected. Not all exception handlers will be resolved in the method insql2
Couldn't resolve all exception handlers in method insql2
	Exit status: 0
	Caught exceptions:
*/