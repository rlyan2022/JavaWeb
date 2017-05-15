package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.PayOutDAO;
import com.sanqing.po.PayOut;

import java.text.SimpleDateFormat;

public class UpdatePayOutAction extends ActionSupport {
    private int id;
    private String field;
    private String value;
    private PayOutDAO payOutDAO;
    private boolean success;
    private String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PayOutDAO getPayOutDAO() {
        return payOutDAO;
    }

    public void setPayOutDAO(PayOutDAO payOutDAO) {
        this.payOutDAO = payOutDAO;
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
        //取得该id对应的支出记录
        PayOut payOut = payOutDAO.findById(id);
        //判断该id对应的支出记录是否存在
        if (payOut == null) {
            success = false;
            msg = "更新支出记录信息失败!";
        } else {
            //根据field来知道需要更新哪个属性
            if ("payOutName".equals(field)) {
                payOut.setPayOutName(value);
            } else if ("payOutMoney".equals(field)) {
                payOut.setPayOutMoney(Double.parseDouble(value));
            } else if ("payOutDate".equals(field)) {
                payOut.setPayOutDate(new SimpleDateFormat("yyyy年MM月dd日").parse(value));
            }
            payOutDAO.attachDirty(payOut);
            success = true;
            msg = "支出记录更新数据成功!";
        }
        return this.SUCCESS;
    }

}
