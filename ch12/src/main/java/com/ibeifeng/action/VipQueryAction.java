package com.ibeifeng.action;

import com.ibeifeng.dao.VipDAO;
import com.ibeifeng.po.Vip;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class VipQueryAction extends ActionSupport {
    private List<Vip> allVip;
    private int recordSize;
    private int start;
    private int limit;

    private VipDAO vipDAO;

    public List<Vip> getAllVip() {
        return allVip;
    }

    public void setAllVip(List<Vip> allVip) {
        this.allVip = allVip;
    }

    public int getRecordSize() {
        return recordSize;
    }

    public void setRecordSize(int recordSize) {
        this.recordSize = recordSize;
    }

    public VipDAO getVipDAO() {
        return vipDAO;
    }

    public void setVipDAO(VipDAO vipDAO) {
        this.vipDAO = vipDAO;
    }


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String execute() throws Exception {
        allVip = vipDAO.findAll(start, limit);
        recordSize = vipDAO.findAll().size();
        return this.SUCCESS;
    }

}
