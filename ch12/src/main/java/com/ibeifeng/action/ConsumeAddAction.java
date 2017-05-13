package com.ibeifeng.action;

import com.ibeifeng.dao.ConsumeDAO;
import com.ibeifeng.po.Commodity;
import com.ibeifeng.po.Consume;
import com.ibeifeng.po.Vip;
import com.opensymphony.xwork2.ActionSupport;

public class ConsumeAddAction extends ActionSupport {
    private Integer vipId;
    private String name;
    private Integer commodityId;
    private String commodityName;
    private Double price;
    private Double practicePrice;

    private boolean success;
    private String msg;

    private ConsumeDAO consumeDAO;


    public Integer getVipId() {
        return vipId;
    }


    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Integer getCommodityId() {
        return commodityId;
    }


    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }


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


    public Double getPracticePrice() {
        return practicePrice;
    }


    public void setPracticePrice(Double practicePrice) {
        this.practicePrice = practicePrice;
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


    public ConsumeDAO getConsumeDAO() {
        return consumeDAO;
    }


    public void setConsumeDAO(ConsumeDAO consumeDAO) {
        this.consumeDAO = consumeDAO;
    }


    public String execute() throws Exception {
        Consume consume = new Consume();

        //vipId
        Vip vip = new Vip();
        vip.setVipId(vipId);
        consume.setVip(vip);

        //name
        consume.setName(name);

        //commodityId
        Commodity commodity = new Commodity();
        commodity.setCommodityId(commodityId);
        consume.setCommodity(commodity);

        //commodityName
        consume.setCommodityName(commodityName);

        //price
        consume.setPrice(price);

        //praticePrice
        consume.setPracticePrice(practicePrice);

        //save
        consumeDAO.save(consume);

        success = true;
        msg = "添加VIP消费信息成功!";

        return this.SUCCESS;
    }
}
