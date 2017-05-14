/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class SearchNews {

    public SearchNews() {
        sdbo = null;
        search = null;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return CodeFilter.toHtml(search);
    }

    public void setSelect(int select) {
        this.select = select;
    }

    public int getSelect() {
        return select;
    }

    public int sumNews() {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews = 0;
        switch (select) {
            default:
                break;

            case 0: // '\0'
                String strSql1 = "select newsId from news where state=1 and headTitle like '%" + search + "%';";
                try {
                    rs = sdbo.executeQuery(strSql1);
                    try {
                        rs.last();
                        sumNews = rs.getRow();
                        rs.close();
                    } catch (NullPointerException nullE) {
                        System.out.print("SearchNews sumNews() " + nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                } catch (SQLException sqlE) {
                    System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                } finally {
                    sdbo.Close();
                }
                break;

            case 1: // '\001'
                String strSql2 = "select newsId from news where state=1 and content like '%" + search + "%';";
                try {
                    try {
                        rs = sdbo.executeQuery(strSql2);
                        try {
                            rs.last();
                            sumNews = rs.getRow();
                            rs.close();
                        } catch (NullPointerException nullE) {
                            System.out.print("SearchNews sumNews() " + nullE.getMessage());
                            Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                        }
                        break;
                    } catch (SQLException sqlE) {
                        System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                        Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                    }
                    break;
                } finally {
                    sdbo.Close();
                }
        }
        return sumNews;
    }

    public Iterator listNews() {
        ResultSet rs = null;
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews = 0;
        switch (select) {
            default:
                break;

            case 0: // '\0'
                String strSql1 = "select newsId,headTitle,newsTime from news where state=1 and headTitle like '%" + search + "%';";
                try {
                    rs = sdbo.executeQuery(strSql1);
                    try {
                        while (rs.next()) {
                            NEWSTable tableNews = new NEWSTable();
                            tableNews.setNewsId(rs.getInt("newsId"));
                            tableNews.setHeadTitle(rs.getString("headTitle"));
                            tableNews.setNewsTime(rs.getString("newsTime"));
                            vector.add(tableNews);
                        }
                        rs.close();
                    } catch (NullPointerException nullE) {
                        System.out.print("SearchNews sumNews() " + nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                } catch (SQLException sqlE) {
                    System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                } finally {
                    sdbo.Close();
                }
                break;

            case 1: // '\001'
                String strSql2 = "select newsId,headTitle,newsTime from news where state=1 and content like '%" + search + "%';";
                try {
                    try {
                        rs = sdbo.executeQuery(strSql2);
                        try {
                            while (rs.next()) {
                                NEWSTable tableNews = new NEWSTable();
                                tableNews.setNewsId(rs.getInt("newsId"));
                                tableNews.setHeadTitle(rs.getString("headTitle"));
                                tableNews.setNewsTime(rs.getString("newsTime"));
                                vector.add(tableNews);
                            }
                            rs.close();
                        } catch (NullPointerException nullE) {
                            System.out.print("SearchNews sumNews() " + nullE.getMessage());
                            Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                        }
                        break;
                    } catch (SQLException sqlE) {
                        System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                        Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                    }
                    break;
                } finally {
                    sdbo.Close();
                }
        }
        return vector.iterator();
    }

    public int sumNews(String user) {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews = 0;
        switch (select) {
            default:
                break;

            case 0: // '\0'
                String strSql1 = "select newsId from news where headTitle like '%" + search + "%' and author='" + user + "';";
                try {
                    rs = sdbo.executeQuery(strSql1);
                    try {
                        rs.last();
                        sumNews = rs.getRow();
                        rs.close();
                    } catch (NullPointerException nullE) {
                        System.out.print("SearchNews sumNews() " + nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                } catch (SQLException sqlE) {
                    System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                } finally {
                    sdbo.Close();
                }
                break;

            case 1: // '\001'
                String strSql2 = "select newsId from news where content like '%" + search + "%' and author='" + user + "';";
                try {
                    try {
                        rs = sdbo.executeQuery(strSql2);
                        try {
                            rs.last();
                            sumNews = rs.getRow();
                            rs.close();
                        } catch (NullPointerException nullE) {
                            System.out.print("SearchNews sumNews() " + nullE.getMessage());
                            Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                        }
                        break;
                    } catch (SQLException sqlE) {
                        System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                        Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                    }
                    break;
                } finally {
                    sdbo.Close();
                }
        }
        return sumNews;
    }

    public Iterator listNews(String user) {
        ResultSet rs = null;
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sumNews = 0;
        switch (select) {
            default:
                break;

            case 0: // '\0'
                String strSql1 = "select newsId,headTitle,newsTime from news where headTitle like '%" + search + "%' and author='" + user + "';";
                try {
                    rs = sdbo.executeQuery(strSql1);
                    try {
                        while (rs.next()) {
                            NEWSTable tableNews = new NEWSTable();
                            tableNews.setNewsId(rs.getInt("newsId"));
                            tableNews.setHeadTitle(rs.getString("headTitle"));
                            tableNews.setNewsTime(rs.getString("newsTime"));
                            vector.add(tableNews);
                        }
                        rs.close();
                    } catch (NullPointerException nullE) {
                        System.out.print("SearchNews sumNews() " + nullE.getMessage());
                        Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                    }
                } catch (SQLException sqlE) {
                    System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                    Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                } finally {
                    sdbo.Close();
                }
                break;

            case 1: // '\001'
                String strSql2 = "select newsId,headTitle,newsTime from news where content like '%" + search + "%' and author='" + user + "';";
                try {
                    try {
                        rs = sdbo.executeQuery(strSql2);
                        try {
                            while (rs.next()) {
                                NEWSTable tableNews = new NEWSTable();
                                tableNews.setNewsId(rs.getInt("newsId"));
                                tableNews.setHeadTitle(rs.getString("headTitle"));
                                tableNews.setNewsTime(rs.getString("newsTime"));
                                vector.add(tableNews);
                            }
                            rs.close();
                        } catch (NullPointerException nullE) {
                            System.out.print("SearchNews sumNews() " + nullE.getMessage());
                            Debug.writeLog("SearchNews sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
                        }
                        break;
                    } catch (SQLException sqlE) {
                        System.out.print("NewsShow sumNews() " + sqlE.getMessage());
                        Debug.writeLog("NewsShow sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
                    }
                    break;
                } finally {
                    sdbo.Close();
                }
        }
        return vector.iterator();
    }

    public Iterator hotNews() {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId,headTitle from news order by hits desc limit 0,8;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("NewsShow hotNews() " + nullE.getMessage());
                Debug.writeLog("NewsShow hotNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("NewsShow hotNews() " + sqlE.getMessage());
            Debug.writeLog("NewsShow hotNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public SQLDBOperator sdbo;
    private String search;
    private int select;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 218 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumNews
Couldn't resolve all exception handlers in method sumNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method listNews
Couldn't resolve all exception handlers in method listNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumNews
Couldn't resolve all exception handlers in method sumNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method listNews
Couldn't resolve all exception handlers in method listNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method hotNews
Couldn't resolve all exception handlers in method hotNews
	Exit status: 0
	Caught exceptions:
*/