package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.CommodityClass;
import com.sanqing.service.CommodityClassService;

public class CommodityClassAddAction extends ActionSupport {
    private String commodityClassName;        //商品种类名称
    private CommodityClassService commodityClassService;//业务逻辑层

    public void setCommodityClassName(String commodityClassName) {
        this.commodityClassName = commodityClassName;
    }

    public void setCommodityClassService(CommodityClassService commodityClassService) {
        this.commodityClassService = commodityClassService;
    }

    public String execute() throws Exception {
        CommodityClass commodityClass = new CommodityClass();//新建商品种类对象
        commodityClass.setCommodityClassName(commodityClassName);//设置商品种类名称
        commodityClassService.addCommodityClass(commodityClass);//保存商品种类
        return SUCCESS;
    }
}
