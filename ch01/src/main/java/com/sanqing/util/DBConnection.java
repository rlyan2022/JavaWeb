package com.sanqing.util;

import java.sql.*;

public class DBConnection {
    private static final String DBDRIVER = "com.mysql.jdbc.Driver";            //驱动类类名
    private static final String DBURL = "jdbc:mysql://localhost:3308/db_affairmanage";//连接URL
    private static final String DBUSER = "root";                                //数据库用户名
    private static final String DBPASSWORD = "1016";                            //数据库密码

    public static Connection getConnection() {
        Connection conn = null;                                                    //声明一个连接对象
        try {
            Class.forName(DBDRIVER);                                            //注册驱动
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);        //获得连接对象
        } catch (ClassNotFoundException e) {                                    //捕获驱动类无法找到异常
            e.printStackTrace();
        } catch (SQLException e) {                                                //捕获SQL异常
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {//关闭连接对象
        if (conn != null) {                //如果conn连接对象不为空
            try {
                conn.close();            //关闭conn连接对象对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement pstmt) {//关闭预处理对象
        if (pstmt != null) {                //如果pstmt预处理对象不为空
            try {
                pstmt.close();            //关闭pstmt预处理对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {//关闭结果集对象
        if (rs != null) {                //如果rs结果集对象不为null
            try {
                rs.close();                //关闭rs结果集对象
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
