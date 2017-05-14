/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.DateUtil;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class UserNews {

    public UserNews() {
        sdbo = null;
    }

    public void insUserNews(int classId, int kindId, String headTitle, String content, String user, String newsFrom) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "insert into news(classId,kindId,myOther,headTitle,content,newsFrom,author,top,newsTime,state,tag) values(?,?,0,?,?,?,?,0,?,0,0)";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            sdbo.setInt(2, kindId);
            sdbo.setString(3, CodeFilter.toHtml(headTitle));
            sdbo.setString(4, CodeFilter.toHtml(content));
            sdbo.setString(5, CodeFilter.toHtml(newsFrom));
            sdbo.setString(6, user);
            sdbo.setString(7, DateUtil.getNowDate());
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.println("UserNews insUserNews() :" + e.getMessage());
            Debug.writeLog("UserNews insUserNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void delUserNews(int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "delete from news where newsId='" + newsId + "';";
        try {
            sdbo.executeUpdate(strSql);
        } catch (Exception e) {
            System.out.print("UserNews delUserNews() :" + e.getMessage());
            Debug.writeLog("UserNews delUserNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public Iterator editUserNews(int newsId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select * from news where newsId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, newsId);
            ResultSet rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setClassId(rs.getInt("classId"));
                    tableNews.setKindId(rs.getInt("kindId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setContent(rs.getString("content"));
                    tableNews.setNewsFrom(rs.getString("newsFrom"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("News editUserNews() " + nullE.getMessage());
                Debug.writeLog("News editUserNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("News editUserNews() " + sqlE.getMessage());
            Debug.writeLog("News editUserNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public void upUserNews(int classId, int kindId, String headTitle, String content, String newsFrom, int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update news set classId=?,kindId=?,headTitle=?,content=?,newsFrom=?,newsTime='" + DateUtil.getNowDate() + "' where newsId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            sdbo.setInt(2, kindId);
            sdbo.setString(3, CodeFilter.toHtml(headTitle));
            sdbo.setString(4, CodeFilter.toHtml(content));
            sdbo.setString(5, CodeFilter.toHtml(newsFrom));
            sdbo.setInt(6, newsId);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("News upUserNews() " + e.getMessage());
            Debug.writeLog("News upUserNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
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
Overlapped try statements detected. Not all exception handlers will be resolved in the method insUserNews
Couldn't resolve all exception handlers in method insUserNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method delUserNews
Couldn't resolve all exception handlers in method delUserNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method editUserNews
Couldn't resolve all exception handlers in method editUserNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method upUserNews
Couldn't resolve all exception handlers in method upUserNews
	Exit status: 0
	Caught exceptions:
*/