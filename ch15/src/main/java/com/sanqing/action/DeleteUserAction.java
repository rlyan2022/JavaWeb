package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.PayOutDAO;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.PayOut;
import com.sanqing.po.User;

import java.util.List;

public class DeleteUserAction extends ActionSupport {
    private String id;
    private UserDAO userDAO;
    private PayOutDAO payOutDAO;

    private boolean success;
    private String msg;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
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

    public String execute() throws Exception {
        User user = null;
        String[] ids = id.split(",");
        for (String strId : ids) {
            int intId = Integer.parseInt(strId);
            user = userDAO.findById(intId);
            //根据用户信息得到该用户对应的所有的支出记录
            List<PayOut> allPayOut = payOutDAO.findByProperty("user", user);
            //循环进行删除
            for (PayOut payOut : allPayOut) {
                payOutDAO.delete(payOut);
            }
            userDAO.delete(user);
        }
        success = true;
        msg = "删除用户记录成功";
        return this.SUCCESS;
    }

    public PayOutDAO getPayOutDAO() {
        return payOutDAO;
    }

    public void setPayOutDAO(PayOutDAO payOutDAO) {
        this.payOutDAO = payOutDAO;
    }

}
