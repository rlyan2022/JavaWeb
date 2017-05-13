package com.ibeifeng.action;

import com.ibeifeng.dao.CommodityDAO;
import com.ibeifeng.po.Commodity;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class CommodityAddAction extends ActionSupport {
    private String commodityName;
    private Double price;
    private Double agio;

    private boolean success;
    private String msg;

    private CommodityDAO commodityDAO;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAgio() {
        return agio;
    }

    public void setAgio(Double agio) {
        this.agio = agio;
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

    public CommodityDAO getCommodityDAO() {
        return commodityDAO;
    }

    public void setCommodityDAO(CommodityDAO commodityDAO) {
        this.commodityDAO = commodityDAO;
    }

    public String execute() throws Exception {
        //商品名称不能重复，所有首先根据该商品名称来查询是否已经存在这样的商品
        List<Commodity> commodities = commodityDAO.findByCommodityName(commodityName);
        //如果不存在
        if (commodities.size() == 0) {
            //实例化，并设置值
            Commodity commodity = new Commodity();
            commodity.setCommodityName(commodityName);
            commodity.setPrice(price);
            commodity.setAgio(agio);
            //进行保存
            commodityDAO.save(commodity);
            success = true;
            msg = "商品录入成功!";
        }
        //如果存在
        else {
            success = false;
            msg = "商品录入失败，该商品已经存在!";
        }
        return this.SUCCESS;
    }

}
