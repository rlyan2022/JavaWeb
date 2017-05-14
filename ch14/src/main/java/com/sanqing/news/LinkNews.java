/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news;

import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSClass;
import com.sanqing.persistence.NEWSKind;
import com.sanqing.persistence.NEWSTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class LinkNews {

    public LinkNews() {
        sdbo = null;
    }

    public Iterator showClass() {
        Vector vector = new Vector();
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "select * from newsclass;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSClass tableClass = new NEWSClass();
                    tableClass.setClassId(rs.getInt("classId"));
                    tableClass.setContent(rs.getString("content"));
                    vector.add(tableClass);
                }
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
        return vector.iterator();
    }

    public boolean isConnectLink(String connect) {
        boolean isLink = false;
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "select newsId from news where state=1 and connect like '%" + connect + "%';";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isLink = true;
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
        return isLink;
    }

    public Iterator connectLink(String connect) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 and connect like '%" + connect + "%' order by newstime desc limit 0,8;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
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
        return vector.iterator();
    }

    public boolean isKind(int classId) {
        boolean isKind = false;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select kindId from newskind where classId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isKind = true;
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
        return isKind;
    }

    public Iterator kindShow(int classId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select * from newskind where classId=? order by kindId asc;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSKind tableKind = new NEWSKind();
                    tableKind.setKindId(rs.getInt("kindId"));
                    tableKind.setContent(rs.getString("content"));
                    tableKind.setClassId(rs.getInt("classId"));
                    vector.add(tableKind);
                }
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
        return vector.iterator();
    }

    public boolean isNewsShow(int kindId) {
        boolean isNews = false;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId from news where kindId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, kindId);
            rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isNews = true;
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
        return isNews;
    }

    public Iterator newsShow(int kindId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 and kindId=? order by newsTime desc limit 0,6;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, kindId);
            rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
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
        return vector.iterator();
    }

    public Iterator hotNewsClass(int classId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId,headTitle from news where state=1 and classId=? order by hits desc limit 0,8;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
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
                System.out.print("NewsShow sumNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow sumNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public int sumNews(int kindId) {
        int sum = 0;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select count(newsId) as total from news where state=1 and kindId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, kindId);
            rs = sdbo.executeQuery();
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

    public Iterator listNews(int kindId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=1 and kindId=? order by newsTime desc;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, kindId);
            rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
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
        return vector.iterator();
    }

    public Iterator hotNewsKind(int kindId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId,headTitle from news where kindId=? order by hits desc limit 0,6;";
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
                System.out.print("NewsShow sumNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow sumNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public String strKind(int kindId) {
        String strKind = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
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
                System.out.print("NewsShow sumNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow sumNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return strKind;
    }

    public String nearTime(int kindId) {
        String strTime = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select DATE_FORMAT(newsTime,'%Y\u5E74%c\u6708%e\u65E5  %k\u65F6%i\u5206%S\u79D2') as time from news where kindId='" + kindId + "' order by newsTime limit 0,1;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                strTime = rs.getString("time");
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
        return strTime;
    }

    public SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 234 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method showClass
Couldn't resolve all exception handlers in method showClass
Overlapped try statements detected. Not all exception handlers will be resolved in the method isConnectLink
Couldn't resolve all exception handlers in method isConnectLink
Overlapped try statements detected. Not all exception handlers will be resolved in the method connectLink
Couldn't resolve all exception handlers in method connectLink
Overlapped try statements detected. Not all exception handlers will be resolved in the method isKind
Couldn't resolve all exception handlers in method isKind
Overlapped try statements detected. Not all exception handlers will be resolved in the method kindShow
Couldn't resolve all exception handlers in method kindShow
Overlapped try statements detected. Not all exception handlers will be resolved in the method isNewsShow
Couldn't resolve all exception handlers in method isNewsShow
Overlapped try statements detected. Not all exception handlers will be resolved in the method newsShow
Couldn't resolve all exception handlers in method newsShow
Overlapped try statements detected. Not all exception handlers will be resolved in the method hotNewsClass
Couldn't resolve all exception handlers in method hotNewsClass
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumNews
Couldn't resolve all exception handlers in method sumNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method listNews
Couldn't resolve all exception handlers in method listNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method hotNewsKind
Couldn't resolve all exception handlers in method hotNewsKind
Overlapped try statements detected. Not all exception handlers will be resolved in the method strKind
Couldn't resolve all exception handlers in method strKind
Overlapped try statements detected. Not all exception handlers will be resolved in the method nearTime
Couldn't resolve all exception handlers in method nearTime
	Exit status: 0
	Caught exceptions:
*/