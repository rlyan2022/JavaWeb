package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.PayOutDAO;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.PayOut;
import com.sanqing.po.User;

import java.text.SimpleDateFormat;
import java.util.Map;

public class AddPayOutAction extends ActionSupport {
    private String payOutName;
    private Double payOutMoney;
    private String payOutDate;
    private boolean success;
    private String msg;
    private PayOutDAO payOutDAO;
    private UserDAO userDAO;

    public String getPayOutName() {
        return payOutName;
    }

    public void setPayOutName(String payOutName) {
        this.payOutName = payOutName;
    }

    public Double getPayOutMoney() {
        return payOutMoney;
    }

    public void setPayOutMoney(Double payOutMoney) {
        this.payOutMoney = payOutMoney;
    }

    public String getPayOutDate() {
        return payOutDate;
    }

    public void setPayOutDate(String payOutDate) {
        this.payOutDate = payOutDate;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PayOutDAO getPayOutDAO() {
        return payOutDAO;
    }

    public void setPayOutDAO(PayOutDAO payOutDAO) {
        this.payOutDAO = payOutDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public String execute() throws Exception {
        //获得当前登陆的用户
        Map session = ActionContext.getContext().getSession();
        String username = (String) session.get("username");
        User user = (User) userDAO.findByUsername(username).get(0);

        //添加支出记录
        PayOut payOut = new PayOut();
        payOut.setPayOutName(payOutName);
        payOut.setPayOutMoney(payOutMoney);
        payOut.setPayOutDate(new SimpleDateFormat("yyyy年MM月dd日").parse(payOutDate));
        payOut.setUser(user);
        payOutDAO.save(payOut);
        success = true;
        msg = "添加支出记录成功!";
        return this.SUCCESS;
    }


}
