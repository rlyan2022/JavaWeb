package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.PayOutDAO;
import com.sanqing.po.PayOut;

public class DeletePayOutAction extends ActionSupport {
    private String id;
    private PayOutDAO payOutDAO;
    private boolean success;
    private String msg;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String execute() throws Exception {
        String[] ids = id.split(",");
        PayOut payOut = null;
        for (String strId : ids) {
            int intId = Integer.parseInt(strId);
            payOut = payOutDAO.findById(intId);
            payOutDAO.delete(payOut);
        }
        success = true;
        msg = "删除指定支出记录成功";
        return super.execute();
    }

}
