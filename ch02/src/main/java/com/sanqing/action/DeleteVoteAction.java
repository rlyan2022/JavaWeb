package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.bean.VoteOption;
import com.sanqing.dao.VoteDAO;
import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.daoFactory.VoteDAOFactory;
import com.sanqing.daoFactory.VoteOptionDAOFactory;

import java.util.List;

public class DeleteVoteAction extends ActionSupport {
    private int voteID;

    public int getVoteID() {
        return voteID;
    }

    public void setVoteID(int voteID) {
        this.voteID = voteID;
    }

    public String execute() throws Exception {
        VoteDAO voteDAO = VoteDAOFactory.getVoteDAOInstance();
        VoteOptionDAO voteOptionDAO = VoteOptionDAOFactory
                .getVoteOptionDAOInstance();
        //通过该投票ID查找该投票下的所有投票选项
        List<VoteOption> voteOptions = voteOptionDAO.findVoteOptionByVoteID(voteID);
        //循环进行删除
        for (VoteOption voteOption : voteOptions) {
            voteOptionDAO.deleteVoteOption(voteOption.getVoteOptionID());
        }
        //再删除该投票
        voteDAO.deleteVote(voteID);
        return this.SUCCESS;
    }
}
