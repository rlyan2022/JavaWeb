/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news.manage;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.DateUtil;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSReply;
import com.sanqing.persistence.NEWSTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class News {

    public News() {
        sdbo = null;
    }

    public void insNews(int classId, int kindId, int myOther, String headTitle, String content, String connect, String author,
                        String editor, String newsFrom, int top) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "insert into news(classId,kindId,myOther,headTitle,content,connect,author,editor,newsFrom,top,newsTime,state,tag) values(?,?,?,?,?,?,?,?,?,?,?,1,1);";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, classId);
            sdbo.setInt(2, kindId);
            sdbo.setInt(3, myOther);
            sdbo.setString(4, CodeFilter.toHtml(headTitle));
            sdbo.setString(5, CodeFilter.toHtml(content));
            sdbo.setString(6, CodeFilter.toHtml(connect));
            sdbo.setString(7, CodeFilter.toHtml(author));
            sdbo.setString(8, CodeFilter.toHtml(editor));
            sdbo.setString(9, CodeFilter.toHtml(newsFrom));
            sdbo.setInt(10, top);
            sdbo.setString(11, DateUtil.getNowDate());
            sdbo.executeUpdate();
        } catch (Exception sqlE) {
            System.out.print("News insNews() " + sqlE.getMessage());
            Debug.writeLog("News insNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void insUbbNews(int classId, int kindId, int myOther, String headTitle, String content, String connect, String author,
                           String editor, String newsFrom, int top) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "insert into news(classId,kindId,myOther,headTitle,content,connect,author,editor,newsFrom,top,newsTime,state,tag) values(?,?,?,?,?,?,?,?,?,?,?,1,1);";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, classId);
            sdbo.setInt(2, kindId);
            sdbo.setInt(3, myOther);
            sdbo.setString(4, CodeFilter.toHtml(headTitle));
            sdbo.setString(5, CodeFilter.toUbbHtml(content));
            sdbo.setString(6, CodeFilter.toHtml(connect));
            sdbo.setString(7, CodeFilter.toHtml(author));
            sdbo.setString(8, CodeFilter.toHtml(editor));
            sdbo.setString(9, CodeFilter.toHtml(newsFrom));
            sdbo.setInt(10, top);
            sdbo.setString(11, DateUtil.getNowDate());
            sdbo.executeUpdate();
        } catch (Exception sqlE) {
            System.out.print("News insNews() " + sqlE.getMessage());
            Debug.writeLog("News insNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public Iterator listNews() {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time,hits from news where state=1 order by newsTime desc;";
        try {
            ResultSet rs = sdbo.executeQuery(sql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    tableNews.setHits(rs.getInt("hits"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.println("News istNews() :" + nullE.getMessage());
                Debug.writeLog("News istNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.println("News istNews() :" + sqlE.getMessage());
            Debug.writeLog("News istNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public int sumNews() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sum = 0;
        String sql = "select count(newsId) as total from news where state=1;";
        try {
            ResultSet rs = sdbo.executeQuery(sql);
            try {
                rs.next();
                sum = rs.getInt("total");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.println("News sumNews() :" + nullE.getMessage());
                Debug.writeLog("News sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.println("News sumNews() :" + sqlE.getMessage());
            Debug.writeLog("News sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public int sumSearchNews(String search) {
        String strSearch = CodeFilter.toHtml(search);
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select count(newsId) as total from news where state=1 and headTitle like '%" + strSearch + "%';";
        int total = 0;
        try {
            ResultSet rs = sdbo.executeQuery(sql);
            rs.next();
            total = rs.getInt("total");
            rs.close();
        } catch (Exception e) {
            System.out.print("News sumNews() " + e.getMessage());
            Debug.writeLog("News sumNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return total;
    }

    public Iterator searchNews(String search) {
        String strSearch = CodeFilter.toHtml(search);
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select newsId,headTitle,DATE_FORMAT(newsTime,'%Y-%m-%d') as time,hits from news where state=1 and headTitle like '%" + strSearch + "%';";
        try {
            ResultSet rs;
            NEWSTable tableNews;
            for (rs = sdbo.executeQuery(sql); rs.next(); vector.add(tableNews)) {
                tableNews = new NEWSTable();
                tableNews.setNewsId(rs.getInt("newsId"));
                tableNews.setHeadTitle(rs.getString("headTitle"));
                tableNews.setNewsTime(rs.getString("time"));
                tableNews.setHits(rs.getInt("hits"));
            }

            rs.close();
        } catch (Exception e) {
            System.out.print("News searchNews() " + e.getMessage());
            Debug.writeLog("News searchNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        sdbo.Close();
        return vector.iterator();
    }

    public Iterator editNews(int newsId) {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "select * from news where newsId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, newsId);
            ResultSet rs;
            NEWSTable tableNews;
            for (rs = sdbo.executeQuery(); rs.next(); vector.add(tableNews)) {
                tableNews = new NEWSTable();
                tableNews.setClassId(rs.getInt("classId"));
                tableNews.setKindId(rs.getInt("kindId"));
                tableNews.setMyOther(rs.getInt("myOther"));
                tableNews.setHeadTitle(CodeFilter.unHtml(rs.getString("headTitle")));
                tableNews.setContent(CodeFilter.unHtml(rs.getString("content")));
                tableNews.setConnect(CodeFilter.unHtml(rs.getString("connect")));
                tableNews.setAuthor(rs.getString("author"));
                tableNews.setEditor(rs.getString("editor"));
                tableNews.setNewsFrom(rs.getString("newsFrom"));
                tableNews.setTop(rs.getInt("top"));
            }

            rs.close();
        } catch (Exception e) {
            System.out.print("News editNews() " + e.getMessage());
            Debug.writeLog("News editNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public void upNews(int classId, int kindId, int myOther, String headTitle, String content, String connect, String author,
                       String editor, String newsFrom, int top, int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "update news set classId=?,kindId=?,myOther=?,headTitle=?,content=?,connect=?,author=?,editor=?,newsFrom=?,top=? where newsId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, classId);
            sdbo.setInt(2, kindId);
            sdbo.setInt(3, myOther);
            sdbo.setString(4, CodeFilter.toHtml(headTitle));
            sdbo.setString(5, CodeFilter.toHtml(content));
            sdbo.setString(6, CodeFilter.toHtml(connect));
            sdbo.setString(7, CodeFilter.toHtml(author));
            sdbo.setString(8, CodeFilter.toHtml(editor));
            sdbo.setString(9, CodeFilter.toHtml(newsFrom));
            sdbo.setInt(10, top);
            sdbo.setInt(11, newsId);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("News upNews() " + e.getMessage());
            Debug.writeLog("News upNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void upUbbNews(int classId, int kindId, int myOther, String headTitle, String content, String connect, String author,
                          String editor, String newsFrom, int top, int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "update news set classId=?,kindId=?,myOther=?,headTitle=?,content=?,connect=?,author=?,editor=?,newsFrom=?,top=? where newsId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, classId);
            sdbo.setInt(2, kindId);
            sdbo.setInt(3, myOther);
            sdbo.setString(4, CodeFilter.toHtml(headTitle));
            sdbo.setString(5, CodeFilter.toUbbHtml(content));
            sdbo.setString(6, CodeFilter.toHtml(connect));
            sdbo.setString(7, CodeFilter.toHtml(author));
            sdbo.setString(8, CodeFilter.toHtml(editor));
            sdbo.setString(9, CodeFilter.toHtml(newsFrom));
            sdbo.setInt(10, top);
            sdbo.setInt(11, newsId);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("News upNews() " + e.getMessage());
            Debug.writeLog("News upNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void delNews(int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String sql = "delete from news where newsId=?;";
        try {
            sdbo.prepareStatement(sql);
            sdbo.setInt(1, newsId);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("News delNews() " + e.getMessage());
            Debug.writeLog("News delNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public Iterator listShNews() {
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String strSql = "select newsId,headTitle,author,DATE_FORMAT(newsTime,'%Y-%m-%d') as time from news where state=0;";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setAuthor(rs.getString("author"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("News listShNews() " + nullE.getMessage());
                Debug.writeLog("News listShNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("News listShNews() " + sqlE.getMessage());
            Debug.writeLog("News listShNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public int sumShNews() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sum = 0;
        String sql = "select count(newsId) as total from news where state=0;";
        try {
            ResultSet rs = sdbo.executeQuery(sql);
            try {
                rs.next();
                sum = rs.getInt("total");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.println("News sumNews() :" + nullE.getMessage());
                Debug.writeLog("News sumNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.println("News sumNews() :" + sqlE.getMessage());
            Debug.writeLog("News sumNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public void shNews(int newsId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update news set state=1 where newsId='" + newsId + "'";
        try {
            sdbo.executeUpdate(strSql);
        } catch (Exception e) {
            System.out.print("News listShNews() " + e.getMessage());
            Debug.writeLog("News listShNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public boolean isTalk() {
        ResultSet rs = null;
        String strSql = "select replyId from newsreply";
        boolean isTalk = false;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isTalk = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("News isTalk() " + nullE.getMessage());
                Debug.writeLog("News isTalk(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Usr isAdminName() " + sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isTalk;
    }

    public int sumReplyNews() {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        int sum = 0;
        String sql = "select distinct news.newsId from news,newsreply where news.newsId=newsreply.newsId;";
        try {
            ResultSet rs = sdbo.executeQuery(sql);
            try {
                rs.next();
                sum = rs.getInt("totalNews");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.println("News sumReplyNews() :" + nullE.getMessage());
                Debug.writeLog("News sumReplyNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.println("News sumReplyNews() :" + sqlE.getMessage());
            Debug.writeLog("News sumReplyNews(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public Iterator listReplyNews() {
        ResultSet rs = null;
        String strSql = "select distinct news.newsId,news.headTitle,DATE_FORMAT(news.newsTime,'%Y-%m-%d') as time from news,newsreply where news.newsId=newsreply.newsId;";
        Vector vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                while (rs.next()) {
                    NEWSTable tableNews = new NEWSTable();
                    tableNews.setNewsId(rs.getInt("newsId"));
                    tableNews.setHeadTitle(rs.getString("headTitle"));
                    tableNews.setNewsTime(rs.getString("time"));
                    vector.add(tableNews);
                    rs.close();
                }
            } catch (NullPointerException nullE) {
                System.out.print("News listReplyNews() " + nullE.getMessage());
                Debug.writeLog("News listReplyNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Usr isAdminName() " + sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public int sumReply(int newsId) {
        ResultSet rs = null;
        int sum = 0;
        String strSql = "select count(replyId) as total from newsreply where newsId=?;";
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, newsId);
            rs = sdbo.executeQuery();
            try {
                rs.next();
                sum = rs.getInt("total");
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Usr isAdminName() " + nullE.getMessage());
                Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Usr isAdminName() " + sqlE.getMessage());
            Debug.writeLog("Usr isAdminName(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return sum;
    }

    public Iterator listReply(int newsId) {
        ResultSet rs;
        String strSql;
        Vector vector;
        rs = null;
        strSql = "select replyId,newsId,user,content from newsreply where newsId=?;";
        vector = new Vector();
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        sdbo.prepareStatement(strSql);
        sdbo.setInt(1, newsId);
        rs = sdbo.executeQuery();
        try {
            while (rs.next()) {
                NEWSReply tableReply = new NEWSReply();
                tableReply.setReplyId(rs.getInt("replyId"));
                tableReply.setUser(rs.getString("user"));
                tableReply.setContent(rs.getString("content"));
                vector.add(tableReply);
            }
        } catch (NullPointerException nullE) {
            System.out.print("News listReplyNews() " + nullE.getMessage());
            Debug.writeLog("News listReplyNews(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sdbo.Close();
        return vector.iterator();
    }

    public void delNewsReply(int replyId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "delete from NEWSReply where replyId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, replyId);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("News listShNews() " + e.getMessage());
            Debug.writeLog("News listShNews(), Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    private SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 328 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method insNews
Couldn't resolve all exception handlers in method insNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method insUbbNews
Couldn't resolve all exception handlers in method insUbbNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method listNews
Couldn't resolve all exception handlers in method listNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumNews
Couldn't resolve all exception handlers in method sumNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method editNews
Couldn't resolve all exception handlers in method editNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method upNews
Couldn't resolve all exception handlers in method upNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method upUbbNews
Couldn't resolve all exception handlers in method upUbbNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method delNews
Couldn't resolve all exception handlers in method delNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method listShNews
Couldn't resolve all exception handlers in method listShNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumShNews
Couldn't resolve all exception handlers in method sumShNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method shNews
Couldn't resolve all exception handlers in method shNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method isTalk
Couldn't resolve all exception handlers in method isTalk
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumReplyNews
Couldn't resolve all exception handlers in method sumReplyNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method listReplyNews
Couldn't resolve all exception handlers in method listReplyNews
Overlapped try statements detected. Not all exception handlers will be resolved in the method sumReply
Couldn't resolve all exception handlers in method sumReply
Overlapped try statements detected. Not all exception handlers will be resolved in the method listReply
Couldn't fully decompile method listReply
Couldn't resolve all exception handlers in method listReply
Overlapped try statements detected. Not all exception handlers will be resolved in the method delNewsReply
Couldn't resolve all exception handlers in method delNewsReply
	Exit status: 0
	Caught exceptions:
*/