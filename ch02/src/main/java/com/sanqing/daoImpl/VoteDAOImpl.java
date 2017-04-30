package com.sanqing.daoImpl;

import com.sanqing.bean.Vote;
import com.sanqing.dao.VoteDAO;
import com.sanqing.util.DBConnection;
import com.sanqing.util.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoteDAOImpl implements VoteDAO {

    public void addVote(Vote vote) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String addSQL = "insert into " +
                "tb_vote(voteName,channelID) values(?,?)";
        PreparedStatement pstmt = null;                    //声明预处理对象
        try {
            pstmt = conn.prepareStatement(addSQL);        //获得预处理对象并赋值
            pstmt.setString(1, vote.getVoteName());        //设置投票名称
            pstmt.setInt(2, vote.getChannelID());        //设置频道ID
            pstmt.executeUpdate();                                //执行添加
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
    }

    public void deleteVote(int voteID) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String deleteSQL = "delete from tb_vote where voteID=?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        try {
            pstmt = conn.prepareStatement(deleteSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, voteID);                        //设置投票编号
            pstmt.executeUpdate();                                //执行删除
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
    }

    public List<Vote> findAllVote(Page page) {
        Connection conn = DBConnection.getConnection();        //获得连接对象
        String findByIDSQL = "select * from tb_vote limit ?,?";        //查询SQL语句
        PreparedStatement pstmt = null;    //声明预处理对象
        ResultSet rs = null;
        List<Vote> votes = new ArrayList<Vote>();
        try {
            pstmt = conn.prepareStatement(findByIDSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, page.getBeginIndex());
            pstmt.setInt(2, page.getEveryPage());
            rs = pstmt.executeQuery();                        //执行查询
            while (rs.next()) {
                Vote vote = new Vote();
                vote.setVoteID(rs.getInt(1));
                vote.setVoteName(rs.getString(2));
                vote.setChannelID(rs.getInt(3));
                votes.add(vote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return votes;
    }

    public Vote findVoteById(int voteID) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String querySQL = "select * from tb_vote where voteID = ?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        Vote vote = null;
        try {
            pstmt = conn.prepareStatement(querySQL);        //获得预处理对象并赋值
            pstmt.setInt(1, voteID);
            rs = pstmt.executeQuery();                    //执行查询
            if (rs.next()) {
                vote = new Vote();
                vote.setVoteID(rs.getInt(1));
                vote.setVoteName(rs.getString(2));
                vote.setChannelID(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return vote;
    }

    public void updateVote(Vote vote) {

    }

    public Vote findVoteByName(String voteName) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String querySQL = "select * from tb_vote where voteName = ?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        Vote vote = null;
        try {
            pstmt = conn.prepareStatement(querySQL);        //获得预处理对象并赋值
            pstmt.setString(1, voteName);
            rs = pstmt.executeQuery();                    //执行查询
            if (rs.next()) {
                vote = new Vote();
                vote.setVoteID(rs.getInt(1));
                vote.setVoteName(rs.getString(2));
                vote.setChannelID(rs.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return vote;
    }

    public int findAllCount() {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String findSQL = "select count(*) from tb_vote";
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

    public List<Vote> findVoteByChannel(Page page, int channelID) {
        Connection conn = DBConnection.getConnection();        //获得连接对象
        String findByIDSQL = "select * from tb_vote where channelID=? limit ?,?";        //查询SQL语句
        PreparedStatement pstmt = null;    //声明预处理对象
        ResultSet rs = null;
        List<Vote> votes = new ArrayList<Vote>();
        try {
            pstmt = conn.prepareStatement(findByIDSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, channelID);
            pstmt.setInt(2, page.getBeginIndex());
            pstmt.setInt(3, page.getEveryPage());
            rs = pstmt.executeQuery();                        //执行查询
            while (rs.next()) {
                Vote vote = new Vote();
                vote.setVoteID(rs.getInt(1));
                vote.setVoteName(rs.getString(2));
                vote.setChannelID(rs.getInt(3));
                votes.add(vote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs);                                //关闭结果集对象
            DBConnection.close(pstmt);                            //关闭预处理对象
            DBConnection.close(conn);                            //关闭连接对象
        }
        return votes;
    }

    public int fintCountByChannel(int channelID) {
        Connection conn = DBConnection.getConnection();    //获得连接对象
        String findSQL = "select count(*) from tb_vote where channelID=?";
        PreparedStatement pstmt = null;                    //声明预处理对象
        ResultSet rs = null;
        int count = 0;
        try {
            pstmt = conn.prepareStatement(findSQL);        //获得预处理对象并赋值
            pstmt.setInt(1, channelID);
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
