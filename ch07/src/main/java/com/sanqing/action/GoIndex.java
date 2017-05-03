package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Commodity;
import com.sanqing.po.CommodityClass;
import com.sanqing.service.CommodityClassService;
import com.sanqing.service.CommodityService;

import java.util.List;

public class GoIndex extends ActionSupport {
    private CommodityClassService commodityClassService;// 业务逻辑层
    private List<CommodityClass> commodityClasses;// 商品种类列表
    private CommodityService commodityService;// 业务逻辑层
    private List<Commodity> commodities;// 商品信息列表

    public CommodityClassService getCommodityClassService() {
        return commodityClassService;
    }

    public void setCommodityClassService(
            CommodityClassService commodityClassService) {
        this.commodityClassService = commodityClassService;
    }

    public List<CommodityClass> getCommodityClasses() {
        return commodityClasses;
    }

    public void setCommodityClasses(List<CommodityClass> commodityClasses) {
        this.commodityClasses = commodityClasses;
    }

    public CommodityService getCommodityService() {
        return commodityService;
    }

    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }

    public String execute() throws Exception {
        commodityClasses = commodityClassService.findAllCommodityClass();// 查询所有的商品种类
        commodities = commodityService.queryByRegTime(9);//查找最新的九条商品记录
        return SUCCESS;
    }
}
