package com.sanqing.daoImpl;

import com.sanqing.bean.Reply;
import com.sanqing.dao.ReplyDAO;
import com.sanqing.util.DBConnection;
import com.sanqing.util.Page;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReplyDAOImpl implements ReplyDAO {
    public void addReplay(Reply replay) {
        Connection conn = DBConnection.getConnection();        //获得连接对象
        String addSQL = "insert into tb_reply(replyContent," +
                "employeeID,replyTime,messageID) values(?,?,?,?)";
        PreparedStatement pstmt = null;                        //声明预处理对象
        try {
            pstmt = conn.prepareStatement(addSQL);            //获得预处理对象并赋值
            pstmt.setString(1, replay.getReplyContent());    //设置回复内容
            pstmt.setInt(2, replay.getEmployeeID());        //设置回复人
            pstmt.setTimestamp(3, new Timestamp(
                    replay.getReplyTime().getTime()));        //设置回复时间
            pstmt.setInt(4, replay.getMessageID());            //设置消息编号
            pstmt.executeUpdate();                                //执行更新
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
    }

    public int findCountByMsgID(int messageID) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String findSQL = "select count(*) from tb_reply where messageID = ?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = conn.prepareStatement(findSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, messageID);
            rs = pstmt.executeQuery();                //执行查询
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return count;
    }

    public List<Reply> findReplayByMsgID(int messageID, Page page) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String findSQL = "select * from tb_reply" +
                " where messageID = ? limit ?,?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        List<Reply> replays = new ArrayList<Reply>();
        try {
            pstmt = conn.prepareStatement(findSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, messageID);
            pstmt.setInt(2, page.getBeginIndex());
            pstmt.setInt(3, page.getEveryPage());
            rs = pstmt.executeQuery();                //执行查询
            while (rs.next()) {
                Reply reply = new Reply();
                reply.setReplyID(rs.getInt(1));        //设置回复编号
                reply.setReplyContent(rs.getString(2));//设置回复内容
                reply.setEmployeeID(rs.getInt(3));//设置员工编号
                reply.setReplyTime(rs.getTimestamp(4));//设置回复时间
                reply.setMessageID(rs.getInt(5));//设置消息编号
                replays.add(reply);//添加消息回复
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return replays;
    }
}
