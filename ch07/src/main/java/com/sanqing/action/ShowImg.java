package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Commodity;
import com.sanqing.service.CommodityService;

import java.io.ByteArrayInputStream;

public class ShowImg extends ActionSupport {
    private CommodityService commodityService;// 业务逻辑层
    private int CommodityID;
    private ByteArrayInputStream inputStream;

    public CommodityService getCommodityService() {
        return commodityService;
    }

    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    public int getCommodityID() {
        return CommodityID;
    }

    public void setCommodityID(int commodityID) {
        CommodityID = commodityID;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String execute() throws Exception {
        Commodity commodity = commodityService.queryByCommodityID(CommodityID);
        inputStream = new ByteArrayInputStream(commodity.getImage());
        return SUCCESS;
    }
}
