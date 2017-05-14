/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.database;

import com.sanqing.common.Debug;
import com.sanqing.pool.DBConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// Referenced classes of package com.sanqing.database:
//            DBOperator

public class SQLDBOperator extends DBOperator {

    private SQLDBOperator(String strKey) {
        prepstmt = null;
        poolName = strKey;
        conManager = DBConnectionManager.getInstance();
        conn = conManager.getConnection(strKey);
    }

    public void Close() {
        try {
            conManager.freeConnection(poolName, conn);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void executeUpdate(String strSql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(strSql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In executeUpdate(String), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }

    public ResultSet executeQuery(String strSql) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(strSql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In executeQuery(String), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        return rs;
    }

    public void prepareStatement(String strSql) {
        try {
            prepstmt = conn.prepareStatement(strSql);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In prepareStatement(String strSql), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }

    public void setString(int index, String value) {
        try {
            prepstmt.setString(index, value);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In setString(int index,String value), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }

    public void setInt(int index, int value) {
        try {
            prepstmt.setInt(index, value);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In setInt(int index,int value), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }

    public void clearParameters() {
        try {
            prepstmt.clearParameters();
            prepstmt = null;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In clearParameters(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }

    public PreparedStatement getPreparedStatement() {
        return prepstmt;
    }

    public void executeUpdate() {
        try {
            if (prepstmt != null)
                prepstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In executeUpdate(String), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }

    public ResultSet executeQuery() {
        ResultSet rs = null;
        try {
            if (prepstmt != null)
                rs = prepstmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("In executeQuery(String), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        return rs;
    }

    public Connection getConnection() {
        return conn;
    }

    public static SQLDBOperator getInstance(String strKey) {
        if (instance == null)
            instance = new SQLDBOperator(strKey);
        return instance;
    }

    private DBConnectionManager conManager;
    private String poolName;
    private PreparedStatement prepstmt;
    private Connection conn;
    private static SQLDBOperator instance;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 670 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/