package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.bean.VoteOption;
import com.sanqing.dao.VoteOptionDAO;
import com.sanqing.daoFactory.VoteOptionDAOFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;

public class DoVoteAction extends ActionSupport {
    private int voteOptionID;
    private String otherOption;
    private int voteID;
    private int channelID;

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }

    public int getVoteID() {
        return voteID;
    }

    public void setVoteID(int voteID) {
        this.voteID = voteID;
    }

    public int getVoteOptionID() {
        return voteOptionID;
    }

    public void setVoteOptionID(int voteOptionID) {
        this.voteOptionID = voteOptionID;
    }

    public String getOtherOption() {
        return otherOption;
    }

    public void setOtherOption(String otherOption) {
        this.otherOption = otherOption;
    }

    public String execute() throws Exception {
        VoteOptionDAO voteOptionDAO = VoteOptionDAOFactory
                .getVoteOptionDAOInstance();
        //首先判断用户是否能进行投票
        Cookie cookies[] = ServletActionContext.getRequest().getCookies();//取出cookies

        for (Cookie cookie : cookies) {//遍历cookies

            if (cookie.getValue().equals(Integer.toString(voteID))) {//如果用户已经投过票
                this.addActionError("您今天已经投过票了，请明天再来！");
                return this.INPUT;
            }
        }
        //判断是否选择其他选项
        if (voteOptionID == 0) {
            //添加该选项
            VoteOption voteOption = new VoteOption();
            voteOption.setVoteID(voteID);
            voteOption.setVoteOptionName(otherOption);
            voteOption.setTicketNum(1);
            voteOptionDAO.addVoteOption(voteOption);
        } else {
            //取出以前的投票选项
            VoteOption voteOption = voteOptionDAO.findVoteOptionById(voteOptionID);
            int ticketNum = voteOption.getTicketNum();
            //更新选项的投票数
            voteOption.setTicketNum(ticketNum + 1);
            voteOptionDAO.updateVoteOption(voteOption);
            //更新完成后，添加cookie，防止重复投票
            Cookie cookie = new Cookie("hasVote" + voteID, Integer.toString(voteID));
            ServletActionContext.getResponse().addCookie(cookie);
        }
        return this.SUCCESS;
    }

}
