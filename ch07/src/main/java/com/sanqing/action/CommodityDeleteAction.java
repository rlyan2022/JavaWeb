package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.service.CommodityService;

public class CommodityDeleteAction extends ActionSupport {
    private Integer commodityID;//商品编号
    private CommodityService commodityService;//商品业务逻辑组件

    public void setCommodityID(Integer commodityID) {
        this.commodityID = commodityID;
    }

    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    public String execute() throws Exception {
        commodityService.deleteCommodity(commodityID);
        return SUCCESS;
    }
}
