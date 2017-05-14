/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.news.manage;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.Debug;
import com.sanqing.database.SQLDBOperator;
import com.sanqing.persistence.NEWSClass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

public class Column {

    public Column() {
        sdbo = null;
    }

    public boolean isNullColumn() {
        boolean isNull = false;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String Sql = "select classId from newsclass;";
        try {
            rs = sdbo.executeQuery(Sql);
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isNull = true;
            } catch (NullPointerException nullE) {
                nullE.printStackTrace(System.out);
                Debug.writeLog("Column inNullColumn(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace(System.out);
            Debug.writeLog("Column inNullColumn(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isNull;
    }

    public Iterator getColumn() {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector = new Vector();
        String Sql = "select classId,content from newsclass;";
        try {
            rs = sdbo.executeQuery(Sql);
            try {
                while (rs.next()) {
                    NEWSClass tableClass = new NEWSClass();
                    tableClass.setClassId(rs.getInt("classId"));
                    tableClass.setContent(rs.getString("content"));
                    vector.add(tableClass);
                }
                rs.close();
            } catch (NullPointerException nullE) {
                nullE.printStackTrace(System.out);
                Debug.writeLog("Column getColumn(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace(System.out);
            Debug.writeLog("Column getColumn(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        }
        sdbo.Close();
        return vector.iterator();
    }

    public boolean isIns(int classId) {
        boolean isIns = false;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "select * from newsclass where classId=?";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            ResultSet rs = sdbo.executeQuery();
            try {
                rs.last();
                if (rs.getRow() > 0)
                    isIns = true;
                rs.close();
            } catch (NullPointerException nullE) {
                System.out.print("Column isIns():" + nullE.getMessage());
                Debug.writeLog("Column isIns(), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Column isIns():" + sqlE.getMessage());
            Debug.writeLog("Column isIns(), Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return isIns;
    }

    public void InsColumn(int classId, String content) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        try {
            String strSql = "insert into newsclass(classId,content) values(?,?);";
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            sdbo.setString(2, CodeFilter.toHtml(content));
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Column delColumn() :" + e.getMessage());
            Debug.writeLog("Column delColumn() , Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public void delColumn(int classId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql_class = "delete from newsclass where classId=?";
        String strSql_kind = "delete from newskind where classId=?";
        String strSql_news = "delete from news where classId=?";
        String strSql_news_newsId = "select newsId from news where classId=?";
        String strSql_reply = "delete from newsreply where newsId=?";
        try {
            sdbo.prepareStatement(strSql_class);
            sdbo.setInt(1, classId);
            sdbo.executeUpdate();
            sdbo.prepareStatement(strSql_kind);
            sdbo.setInt(1, classId);
            sdbo.executeUpdate();
            sdbo.prepareStatement(strSql_news);
            sdbo.setInt(1, classId);
            sdbo.executeUpdate();
            sdbo.prepareStatement(strSql_news_newsId);
            sdbo.setInt(1, classId);
            ResultSet rs = sdbo.executeQuery();
            sdbo.prepareStatement(strSql_reply);
            try {
                for (; rs.next(); sdbo.executeUpdate()) {
                    int newsId = rs.getInt("newsId");
                    sdbo.setInt(1, newsId);
                }

            } catch (NullPointerException nullE) {
                System.out.print("Column delColumn() :" + nullE.getMessage());
                Debug.writeLog("Column delColumn() , Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Column delColumn() :" + sqlE.getMessage());
            Debug.writeLog("Column delColumn() , Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public Iterator editColumn(int classId) {
        ResultSet rs = null;
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        Vector vector = new Vector();
        String strSql = "select classId,content from newsclass where classId=?";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setInt(1, classId);
            rs = sdbo.executeQuery();
            try {
                while (rs.next()) {
                    NEWSClass tableClass = new NEWSClass();
                    tableClass.setClassId(rs.getInt("classId"));
                    tableClass.setContent(rs.getString("content"));
                    vector.add(tableClass);
                }
            } catch (NullPointerException nullE) {
                System.out.print("Column isIns() :" + nullE.getMessage());
                Debug.writeLog("Column isIns() , Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Column isIns() :" + sqlE.getMessage());
            Debug.writeLog("Column isIns() , Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return vector.iterator();
    }

    public void upColumn(int classId, String content) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        String strSql = "update newsclass set content=? where classId=?;";
        try {
            sdbo.prepareStatement(strSql);
            sdbo.setString(1, CodeFilter.toHtml(content));
            sdbo.setInt(2, classId);
            sdbo.executeUpdate();
        } catch (Exception e) {
            System.out.print("Column upColumn() :" + e.getMessage());
            Debug.writeLog("Column upColumn() , Exception Occured ! Info :" + e.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
    }

    public String getColumn_newsShow(int classId) {
        if (sdbo == null)
            sdbo = SQLDBOperator.getInstance("Connection");
        ResultSet rs = null;
        String content = null;
        String strSql = "select content from newsclass where classId='" + classId + "';";
        try {
            rs = sdbo.executeQuery(strSql);
            try {
                rs.next();
                content = rs.getString("content");
            } catch (NullPointerException nullE) {
                System.out.print("getColumn_newsShow(int classId) " + nullE.getMessage());
                Debug.writeLog("getColumn_newsShow(int classId), Exception Occured ! Info :" + nullE.getLocalizedMessage());
            }
        } catch (SQLException sqlE) {
            System.out.print("Column getColumn_newsShow(int classId) :" + sqlE.getMessage());
            Debug.writeLog("Column getColumn_newsShow(int classId) , Exception Occured ! Info :" + sqlE.getLocalizedMessage());
        } finally {
            sdbo.Close();
        }
        return content;
    }

    private SQLDBOperator sdbo;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 250 ms
	Jad reported messages/errors:
Overlapped try statements detected. Not all exception handlers will be resolved in the method isNullColumn
Couldn't resolve all exception handlers in method isNullColumn
Overlapped try statements detected. Not all exception handlers will be resolved in the method isIns
Couldn't resolve all exception handlers in method isIns
Overlapped try statements detected. Not all exception handlers will be resolved in the method InsColumn
Couldn't resolve all exception handlers in method InsColumn
Overlapped try statements detected. Not all exception handlers will be resolved in the method delColumn
Couldn't resolve all exception handlers in method delColumn
Overlapped try statements detected. Not all exception handlers will be resolved in the method editColumn
Couldn't resolve all exception handlers in method editColumn
Overlapped try statements detected. Not all exception handlers will be resolved in the method upColumn
Couldn't resolve all exception handlers in method upColumn
Overlapped try statements detected. Not all exception handlers will be resolved in the method getColumn_newsShow
Couldn't resolve all exception handlers in method getColumn_newsShow
	Exit status: 0
	Caught exceptions:
*/