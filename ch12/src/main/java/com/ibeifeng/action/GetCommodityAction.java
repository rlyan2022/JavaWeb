package com.ibeifeng.action;

import com.ibeifeng.dao.CommodityDAO;
import com.ibeifeng.po.Commodity;
import com.opensymphony.xwork2.ActionSupport;

public class GetCommodityAction extends ActionSupport {
    private Integer commodityId;

    private boolean success;
    private String msg;
    private Commodity commodity;

    private CommodityDAO commodityDAO;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
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

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public CommodityDAO getCommodityDAO() {
        return commodityDAO;
    }

    public void setCommodityDAO(CommodityDAO commodityDAO) {
        this.commodityDAO = commodityDAO;
    }

    public String execute() throws Exception {
        if (commodityId != null && commodityId != 0) {
            commodity = commodityDAO.findById(commodityId);
            if (commodity == null) {
                success = false;
                msg = "找不到相应的商品信息";
            } else {
                success = true;
                msg = "成功获得商品信息";
            }
        }
        return this.SUCCESS;
    }
}
