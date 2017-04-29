package com.sanqing.daoImpl;

import com.sanqing.bean.Message;
import com.sanqing.dao.MessageDAO;
import com.sanqing.util.DBConnection;
import com.sanqing.util.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements MessageDAO {

    public void addMessage(Message message) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String addSQL = "insert into tb_message(messageTitle,messageContent,employeeID,publishTime) values(?,?,?,?)";
        PreparedStatement pstmt = null;                    //声明预处理对象
        try {
            pstmt = conn.prepareStatement(addSQL);        //获得预处理对象并赋值
            pstmt.setString(1, message.getMessageTitle());    //设置第一个参数
            pstmt.setString(2, message.getMessageContent());//设置第二个参数
            pstmt.setInt(3, message.getEmployeeID());            //设置第三个参数
            pstmt.setTimestamp(4, new Timestamp(message.getPublishTime().getTime()));
            pstmt.executeUpdate();                                //执行更新
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
    }

    public void deleteMessage(int messageID) {

    }

    public List<Message> findAllMessagee(Page page) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String findSQL = "select * from tb_message " +
                "order by  publishTime desc limit ?,?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        List<Message> messages = new ArrayList<Message>();
        try {
            pstmt = conn.prepareStatement(findSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, page.getBeginIndex());        //查询起始点
            pstmt.setInt(2, page.getEveryPage());        //查询记录数
            rs = pstmt.executeQuery();                //执行查询
            while (rs.next()) {
                Message message = new Message();
                message.setMessageID(rs.getInt(1));    //设置消息ID
                message.setMessageTitle(rs.getString(2));//设置消息标题
                message.setMessageContent(
                        rs.getString(3));            //设置消息内容
                message.setEmployeeID(rs.getInt(4));//设置员工编号
                message.setPublishTime(rs.getTimestamp(5));//设置发布时间
                messages.add(message);//添加消息
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return messages;
    }

    public Message findMessageById(int messageID) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String querySQL = "select * from tb_message where messageID = ?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        Message message = null;
        try {
            pstmt = conn.prepareStatement(querySQL);        //获得预处理对象并赋值
            pstmt.setInt(1, messageID);
            rs = pstmt.executeQuery();                    //执行查询
            if (rs.next()) {
                message = new Message();
                message.setMessageID(rs.getInt(1));    //设置消息ID
                message.setMessageTitle(rs.getString(2));//设置消息标题
                message.setMessageContent(
                        rs.getString(3));            //设置消息内容
                message.setEmployeeID(rs.getInt(4));//设置员工编号
                message.setPublishTime(rs.getTimestamp(5));//设置发布时间
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return message;
    }

    public void updateMessage(Message message) {

    }

    public int findAllCount() {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String findSQL = "select count(*) from tb_message";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = conn.prepareStatement(findSQL);        //获得预处理对象并赋值
            rs = pstmt.executeQuery();                    //执行查询
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                        //关闭结果集对象
            DBConnection.close(pstmt);                    //关闭预处理对象
            DBConnection.close(conn);                    //关闭连接对象
        }
        return count;
    }
}
