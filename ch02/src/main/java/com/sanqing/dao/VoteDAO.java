package com.sanqing.dao;

import com.sanqing.bean.Vote;
import com.sanqing.util.Page;

import java.util.List;


public interface VoteDAO {
    public void addVote(Vote vote);            //添加投票

    public void updateVote(Vote vote);        //更新投票

    public void deleteVote(int voteID);        //删除投票

    public List<Vote> findAllVote(Page page);        //分页查询所有投票

    public List<Vote> findVoteByChannel(Page page, int channelID);//分页查询每频道的投票

    public Vote findVoteById(int voteID);    //通过ID查询投票

    public Vote findVoteByName(String voteName);    //通过ID查询投票

    public int findAllCount();                //查询所有记录数

    public int fintCountByChannel(int channelID);//查询每频道下的记录数
}
