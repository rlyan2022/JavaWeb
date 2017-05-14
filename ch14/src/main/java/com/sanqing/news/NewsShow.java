/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news;

import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class NewsShow {

    public NewsShow() {
        sdbo = null;
    }

    public int sumNews() {
        int sum = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select count(newsId) as total from news where state=1;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                sum = rs.getInt("total");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow sumNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow sumNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public int sumUser() {
        int sum = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select count(userName) as total from newsusr;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                sum = rs.getInt("total");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow sumUser() " + nullE.getMessage());
                Debug.writeLog("NewsShow sumUser(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow sumUser() " + sqlE.getMessage());
            Debug.writeLog("NewsShow sumUser(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public boolean isTime() {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isTime = false;
        String strSql = "select newsId from news where state=1;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isTime = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow isTime() " + nullE.getMessage());
                Debug.writeLog("NewsShow isTime(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow isTime() " + sqlE.getMessage());
            Debug.writeLog("NewsShow isTime(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isTime;
    }

    public String lastTime() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String newsTime = null;
        String strSql = "select newsTime from news where state=1 order by newsTime desc;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                newsTime = rs.getString("newsTime");
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow lastTime() " + nullE.getMessage());
                Debug.writeLog("NewsShow lastTime(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow lastTime() " + sqlE.getMessage());
            Debug.writeLog("NewsShow lastTime(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return newsTime;
    }

    public boolean isNews() {
        boolean isNews = false;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId from news where state=1;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isNews = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow isTopNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow isTopNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isNews;
    }

    public boolean isTopNews() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isTopNews = false;
        ResultSet rs = null;
        String strSql = "select newsId from news where state=1 and top=1;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isTopNews = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow isTopNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow isTopNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow isTopNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isTopNews;
    }

    public Iterator topNews() {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector = new Vector();
        String strSql = "select newsId,classId,headTitle from news where state=1 and top=1 order by newsTime desc limit 0,1;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setClassId(rs.getInt("classId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow topNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow topNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow topNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public boolean isNewNews() {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        boolean isNewNews = false;
        Vector vector = new Vector();
        String strSql = "select newsId from news where state=1 and top=0;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isNewNews = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow isNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow isNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow isNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow isNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isNewNews;
    }

    public Iterator newNews() {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector = new Vector();
        String strSql = "select newsId,classId,headTitle from news where state=1 and top=0 order by newsTime desc limit 0,9;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setClassId(rs.getInt("classId"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow newNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow newNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public Iterator hotNews() {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector = new Vector();
        String strSql = "select newsId,headTitle,hits,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 order by hits desc limit 0,10;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setHits(rs.getInt("hits"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow newNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow newNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public Iterator listNews(int newsId) {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector = new Vector();
        String strSql = "select * from news where newsId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, newsId);
            rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setClassId(rs.getInt("classId"));
                    tableNews.setKindId(rs.getInt("kindId"));
                    tableNews.setMyOther(rs.getInt("myOther"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setContent(rs.getString("content"));
                    tableNews.setConnect(rs.getString("connect"));
                    tableNews.setAuthor(rs.getString("author"));
                    tableNews.setEditor(rs.getString("editor"));
                    tableNews.setNewsFrom(rs.getString("newsFrom"));
                    tableNews.setHits(rs.getInt("hits"));
                    tableNews.setNewsTime(rs.getString("newsTime"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow newNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow newNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public String valueMyOther(int intMyOther) {
        String strMyOther = "\u539F\u521B";
        if (intMyOther == 1)
            strMyOther = "\u8F6C\u8F7D";
        return strMyOther;
    }

    public void upReadTime(int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update news set hits=hits+1 where newsId='" + newsId + "';";
        try {
            sdbo.executeUpdate(strSql);
        } catch (Exception e) {
            System.out.print("NewsShow newNews() " + e.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public Iterator listHotNews(int kindId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        Vector vector = new Vector();
        String strSql = "select newsId,headTitle from news where state=1 and KindId=? order by hits desc limit 0,8;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, kindId);
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
                System.out.print("NewsShow newNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow newNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public String strClass(int classId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strClass = null;
        String strSql = "select content from newsclass where classId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            rs = sdbo.executeQuery();
            try {
                rs.next();
                strClass = rs.getString("content");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow newNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow newNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return strClass;
    }

    public String strKind(int kindId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strKind = null;
        String strSql = "select content from newskind where kindId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, kindId);
            rs = sdbo.executeQuery();
            try {
                rs.next();
                strKind = rs.getString("content");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow newNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow newNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow newNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return strKind;
    }

    private SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 219 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumNews
Couldn't resolve all exception handlers in method sumNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumUser
Couldn't resolve all exception handlers in method sumUser
Overlapped try statements detected. Not all exception handlers will be resolved in the method isTime
Couldn't resolve all exception handlers in method isTime
Overlapped try statements detected. Not all exception handlers will be resolved in the method lastTime
Couldn't resolve all exception handlers in method lastTime
Overlapped try statements detected. Not all exception handlers will be resolved in the method isNews
Couldn't resolve all exception handlers in method isNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method isTopNews
Couldn't resolve all exception handlers in method isTopNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method topNews
Couldn't resolve all exception handlers in method topNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method isNewNews
Couldn't resolve all exception handlers in method isNewNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method newNews
Couldn't resolve all exception handlers in method newNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method hotNews
Couldn't resolve all exception handlers in method hotNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method listNews
Couldn't resolve all exception handlers in method listNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method upReadTime
Couldn't resolve all exception handlers in method upReadTime
Overlapped try statements detected. Not all exception handlers will be resolved in the method listHotNews
Couldn't resolve all exception handlers in method listHotNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method strClass
Couldn't resolve all exception handlers in method strClass
Overlapped try statements detected. Not all exception handlers will be resolved in the method strKind
Couldn't resolve all exception handlers in method strKind
	Exit status: 0
	Caught exceptions:
*/