package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Commodity;
import com.sanqing.po.CommodityClass;
import com.sanqing.service.CommodityClassService;
import com.sanqing.service.CommodityService;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeleteFromCar extends ActionSupport {
    private int commodityID;
    private CommodityService commodityService;// 业务逻辑层
    private CommodityClassService commodityClassService;// 业务逻辑层
    private List<CommodityClass> commodityClasses;// 商品种类列表

    public int getCommodityID() {
        return commodityID;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public CommodityService getCommodityService() {
        return commodityService;
    }

    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    public CommodityClassService getCommodityClassService() {
        return commodityClassService;
    }

    public void setCommodityClassService(CommodityClassService commodityClassService) {
        this.commodityClassService = commodityClassService;
    }

    public List<CommodityClass> getCommodityClasses() {
        return commodityClasses;
    }

    public void setCommodityClasses(List<CommodityClass> commodityClasses) {
        this.commodityClasses = commodityClasses;
    }

    public String execute() throws Exception {
        commodityClasses = commodityClassService.findAllCommodityClass();// 查询所有的商品种类
        Map session = ActionContext.getContext().getSession();//获得session对象
        List<Commodity> car = (List<Commodity>) session.get("car");//取得购物车
        Iterator<Commodity> it = car.iterator();
        for (int i = car.size(); it.hasNext(); i--) {
            Commodity com = it.next();
            if (com.getCommodityId() == commodityID) {
                int index = car.indexOf(com);
                it.remove();        //这行代码是关键。
            }
        }
        session.put("car", car);//将重新购物车保存到session中
        return SUCCESS;
    }
}
