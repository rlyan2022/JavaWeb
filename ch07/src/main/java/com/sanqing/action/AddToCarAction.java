package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Commodity;
import com.sanqing.po.CommodityClass;
import com.sanqing.service.CommodityClassService;
import com.sanqing.service.CommodityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddToCarAction extends ActionSupport {
    private CommodityService commodityService;// 业务逻辑层
    private CommodityClassService commodityClassService;// 业务逻辑层
    private List<CommodityClass> commodityClasses;// 商品种类列表
    private int commodityID;

    public CommodityService getCommodityService() {
        return commodityService;
    }

    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

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

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public String execute() throws Exception {
        commodityClasses = commodityClassService.findAllCommodityClass();// 查询所有的商品种类
        Commodity commodity = commodityService.queryByCommodityID(commodityID);//获得商品信息
        Map session = ActionContext.getContext().getSession();//获得session对象
        List<Commodity> car = null;                //声明一个购物车
        if (session.get("car") == null) {    //如果session中不存在购物车
            car = new ArrayList<Commodity>();    //新建一个ArrayList实例
        } else {
            car = (List<Commodity>) session.get("car");//取得购物车
        }
        car.add(commodity);//将商品添加到购物车中
        session.put("car", car);//将购物车保存在session中
        return SUCCESS;
    }
}
