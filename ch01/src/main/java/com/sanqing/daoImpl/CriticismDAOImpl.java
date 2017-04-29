package com.sanqing.daoImpl;

import com.sanqing.bean.Criticism;
import com.sanqing.dao.CriticismDAO;
import com.sanqing.util.DBConnection;

import java.sql.*;

public class CriticismDAOImpl implements CriticismDAO {
    public void addCriticism(Criticism criticism) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String addSQL = "insert into tb_criticism(criticismContent,employeeID,criticismTime,messageID) values(?,?,?,?)";
        PreparedStatement pstmt = null;                    //声明预处理对象
        try {
            pstmt = conn.prepareStatement(addSQL);        //获得预处理对象并赋值
            pstmt.setString(1, criticism.getCriticismContent());    //设置第一个参数
            pstmt.setInt(2, criticism.getEmployeeID());//设置第二个参数
            pstmt.setTimestamp(3, new Timestamp(criticism.getCriticismTime().getTime()));//设置第三个参数
            pstmt.setInt(4, criticism.getMessageID());
            pstmt.executeUpdate();                                //执行更新
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
    }

    public Criticism findCriticismByMsgID(int messageID) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String findSQL = "select * from tb_criticism where messageID = ?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        Criticism criticism = new Criticism();
        try {
            pstmt = conn.prepareStatement(findSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, messageID);
            rs = pstmt.executeQuery();                //执行查询
            if (rs.next()) {
                criticism.setCriticismID(rs.getInt(1));
                criticism.setCriticismContent(rs.getString(2));
                criticism.setEmployeeID(rs.getInt(3));
                criticism.setCriticismTime(rs.getTimestamp(4));
                criticism.setMessageID(rs.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return criticism;
    }
}
