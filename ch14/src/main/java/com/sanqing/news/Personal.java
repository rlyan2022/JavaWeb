/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news;

import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class Personal {

    public Personal() {
        sdbo = null;
        user = null;
        rs = null;
    }

    public String setUser(String user) {
        this.user = user;
        return user;
    }

    public String timePersonal() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select regTime from newsusr where userName=?;";
        String time = null;
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, user);
            rs = sdbo.executeQuery();
            rs.next();
            time = rs.getString("regTime");
        } catch (Exception e) {
            System.out.print("Personal timePersonal() info :" + e.getMessage());
            Debug.writeLog("Personal timePersonal(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return time;
    }

    public int sumPersonal() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select count(newsId) as sum from news where author=? and state=1 and tag=0;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, user);
            rs = sdbo.executeQuery();
            rs.next();
            total = rs.getInt("sum");
            rs.close();
        } catch (Exception e) {
            System.out.print("Personal sumPersonal() info :" + e.getMessage());
            Debug.writeLog("Personal sumPersonal(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return total;
    }

    public String pdmPersonal() {
        String grade = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        if (total <= 10)
            purview = 1;
        else if (total > 10 && total <= 30)
            purview = 2;
        else if (total > 30)
            purview = 3;
        String sql = "select grade from newspopedom where gradeId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, purview);
            ResultSet rs = sdbo.executeQuery();
            rs.next();
            grade = rs.getString("grade");
            rs.close();
        } catch (Exception e) {
            System.out.print("Personal pdmPersonal() info :" + e.getMessage());
            Debug.writeLog("Personal pdmPersonal(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return grade;
    }

    public Iterator enNews() {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 and tag=0 and author=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, user);
            NEWSTable tableNews;
            for (rs = sdbo.executeQuery(); rs.next(); vector.add(tableNews)) {
                tableNews = new NEWSTable();
                tableNews.setNewsId(rs.getInt("newsId"));
                tableNews.setHeadTitle(rs.getString("headTitle"));
                tableNews.setNewsTime(rs.getString("time"));
            }

            rs.close();
        } catch (Exception e) {
            System.out.print("Personal pdmPersonal() info :" + e.getMessage());
            Debug.writeLog("Personal enNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public int sumUnNews() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sum = 0;
        String sql = "select count(newsId) as sum from news where state=0 and tag=0 and author=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, user);
            rs = sdbo.executeQuery();
            try {
                rs.next();
                sum = rs.getInt("sum");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Personal sumUnNews() info :" + nullE.getMessage());
                Debug.writeLog("Personal sumUnNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Personal sumUnNews() info :" + sqlE.getMessage());
            Debug.writeLog("Personal sumUnNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public Iterator unNews() {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select newsId,headTitle from news where state=0 and tag=0 and author=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setString(1, user);
            rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Personal unNews() info :" + nullE.getMessage());
                Debug.writeLog("Personal unNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Personal unNews() info :" + sqlE.getMessage());
            Debug.writeLog("Personal unNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    private SQLDBOperator sdbo;
    private String user;
    private ResultSet rs;
    private int total;
    private int purview;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 406 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method timePersonal
Couldn't resolve all exception handlers in method timePersonal
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumPersonal
Couldn't resolve all exception handlers in method sumPersonal
Overlapped try statements detected. Not all exception handlers will be resolved in the method pdmPersonal
Couldn't resolve all exception handlers in method pdmPersonal
Overlapped try statements detected. Not all exception handlers will be resolved in the method enNews
Couldn't resolve all exception handlers in method enNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumUnNews
Couldn't resolve all exception handlers in method sumUnNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method unNews
Couldn't resolve all exception handlers in method unNews
	Exit status: 0
	Caught exceptions:
*/