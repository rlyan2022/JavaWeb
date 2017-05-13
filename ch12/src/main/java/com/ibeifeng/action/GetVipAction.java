package com.ibeifeng.action;

import com.ibeifeng.dao.VipDAO;
import com.ibeifeng.po.Vip;
import com.opensymphony.xwork2.ActionSupport;

public class GetVipAction extends ActionSupport {
    private Integer vipId;

    private boolean success;
    private String msg;
    private Vip vip;

    private VipDAO vipDAO;

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
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

    public Vip getVip() {
        return vip;
    }

    public void setVip(Vip vip) {
        this.vip = vip;
    }

    public VipDAO getVipDAO() {
        return vipDAO;
    }

    public void setVipDAO(VipDAO vipDAO) {
        this.vipDAO = vipDAO;
    }

    public String execute() throws Exception {
        if (vipId != null && vipId != 0) {
            vip = vipDAO.findById(vipId);
            if (vip == null) {
                success = false;
                msg = "该VIP信息不存在!";
            } else {
                success = true;
                msg = "成功取得VIP信息";
            }
        }
        return this.SUCCESS;
    }
}
