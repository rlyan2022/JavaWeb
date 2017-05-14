/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.DateUtil;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSReply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class Reply {

    public Reply() {
        sdbo = null;
        rs = null;
    }

    public void insReply(int newsId, String user, String content) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into newsreply(newsId,user,content,replyTime) values(?,?,?,?);";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, newsId);
            sdbo.setString(2, CodeFilter.toHtml(user));
            sdbo.setString(3, CodeFilter.toHtml(content));
            sdbo.setString(4, DateUtil.getStringDateShort());
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Reply insReply() " + e.getMessage());
            Debug.writeLog("Reply insReply(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public boolean isReply(int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isReply = false;
        String strSql = "select replyId from newsreply where newsId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, newsId);
            rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isReply = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Reply listReply() " + nullE.getMessage());
                Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Reply listReply() " + sqlE.getMessage());
            Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isReply;
    }

    public Iterator listReply(int newsId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "select * from newsreply where newsId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, newsId);
            rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSReply tableReply = new NEWSReply();
                    tableReply.setNewsId(rs.getInt("newsId"));
                    tableReply.setContent(rs.getString("content"));
                    tableReply.setUser(rs.getString("user"));
                    tableReply.setReplyTime(rs.getString("replyTime"));
                    vector.add(tableReply);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Reply listReply() " + nullE.getMessage());
                Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Reply listReply() " + sqlE.getMessage());
            Debug.writeLog("Reply listReply(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    private SQLDBOperator sdbo;
    private ResultSet rs;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 219 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method insReply
Couldn't resolve all exception handlers in method insReply
Overlapped try statements detected. Not all exception handlers will be resolved in the method isReply
Couldn't resolve all exception handlers in method isReply
Overlapped try statements detected. Not all exception handlers will be resolved in the method listReply
Couldn't resolve all exception handlers in method listReply
	Exit status: 0
	Caught exceptions:
*/