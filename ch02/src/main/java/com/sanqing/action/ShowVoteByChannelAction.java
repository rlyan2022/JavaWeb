package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.bean.Vote;
import com.sanqing.bean.VoteOption;
import com.sanqing.bean.VoteResult;
import com.sanqing.dao.VoteDAO;
import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.daoFactory.VoteDAOFactory;
import com.sanqing.daoFactory.VoteOptionDAOFactory;
import com.sanqing.util.Page;
import com.sanqing.util.PageUtil;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowVoteByChannelAction extends ActionSupport {
    private int channelID;
    private int currentPage;

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String execute() throws Exception {
        VoteDAO voteDAO = VoteDAOFactory.getVoteDAOInstance();
        VoteOptionDAO voteOptionDAO = VoteOptionDAOFactory
                .getVoteOptionDAOInstance();
        // 获得该频道下的记录数
        int totalCount = voteDAO.fintCountByChannel(channelID);
        // 设置分页信息
        Page page = PageUtil.createPage(3, totalCount, currentPage);
        //取得该频道下的记录
        List<Vote> votes = voteDAO.findVoteByChannel(page, channelID);
        //存放所有投票和投票选项
        List<VoteResult> voteResultList = new ArrayList<VoteResult>();
        for (Vote vote : votes) {
            //查询该投票下的所有投票选项
            List<VoteOption> voteOptions = voteOptionDAO
                    .findVoteOptionByVoteID(vote.getVoteID());
            VoteResult voteResult = new VoteResult();
            voteResult.setVote(vote);
            voteResult.setVoteOptions(voteOptions);
            voteResultList.add(voteResult);
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("voteResultList", voteResultList);
        request.setAttribute("page", page);
        return this.SUCCESS;
    }
}
