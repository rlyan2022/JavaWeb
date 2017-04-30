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

public class ShowVoteAction extends ActionSupport {
    private int currentPage;

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
        int totalCount = voteDAO.findAllCount();
        Page page = PageUtil.createPage(10, totalCount, currentPage);
        List<Vote> votes = voteDAO.findAllVote(page);
        List<VoteResult> voteResultList = new ArrayList<VoteResult>();
        for (Vote vote : votes) {
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
