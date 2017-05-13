package com.ibeifeng.action;

import com.ibeifeng.dao.CommodityDAO;
import com.ibeifeng.po.Commodity;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class CommodityQueryAction extends ActionSupport {
    private List<Commodity> allCommodity;
    private int recordeSize;
    private CommodityDAO commodityDAO;

    private int start;
    private int limit;


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

    public List<Commodity> getAllCommodity() {
        return allCommodity;
    }

    public void setAllCommodity(List<Commodity> allCommodity) {
        this.allCommodity = allCommodity;
    }

    public int getRecordeSize() {
        return recordeSize;
    }

    public void setRecordeSize(int recordeSize) {
        this.recordeSize = recordeSize;
    }

    public CommodityDAO getCommodityDAO() {
        return commodityDAO;
    }

    public void setCommodityDAO(CommodityDAO commodityDAO) {
        this.commodityDAO = commodityDAO;
    }

    public String execute() throws Exception {
        recordeSize = commodityDAO.findAll().size();
        allCommodity = commodityDAO.findAll(start, limit);
        return this.SUCCESS;
    }

}
