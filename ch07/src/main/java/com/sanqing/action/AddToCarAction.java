
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

/**
 *
 */
public class AddToCarAction extends ActionSupport {
    /**
     * 业务逻辑层
     */
    private CommodityService commodityService;
    /**
     * 业务逻辑层
     */
    private CommodityClassService commodityClassService;
    /**
     * 商品种类列表
     */
    private List<CommodityClass> commodityClasses;
    /**
     *
     */
    private int commodityID;

    /**
     *
     * @return
     */
    public CommodityService getCommodityService() {
        return commodityService;
    }

    /**
     *
     * @param commodityService
     */
    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    /**
     *
     * @return
     */
    public CommodityClassService getCommodityClassService() {
        return commodityClassService;
    }

    /**
     *
     * @param commodityClassService
     */
    public void setCommodityClassService(
            CommodityClassService commodityClassService) {
        this.commodityClassService = commodityClassService;
    }

    /**
     *
     * @return
     */
    public List<CommodityClass> getCommodityClasses() {
        return commodityClasses;
    }

    /**
     *
     * @param commodityClasses
     */
    public void setCommodityClasses(List<CommodityClass> commodityClasses) {
        this.commodityClasses = commodityClasses;
    }

    /**
     *
     * @return
     */
    public int getCommodityID() {
        return commodityID;
    }

    /**
     * 社区
     * @param commodityID 社区ID
     */
    public void setCommodityID(final int commodityID) {
        this.commodityID = commodityID;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public String execute() throws Exception {
        //查询所有的商品种类
        commodityClasses = commodityClassService.findAllCommodityClass();
        //获得商品信息
        Commodity commodity = commodityService.queryByCommodityID(commodityID);
        //获得session对象
        Map session = ActionContext.getContext().getSession();
        List<Commodity> car = null;                //声明一个购物车
        if (session.get("car") == null) {    //如果session中不存在购物车
            car = new ArrayList<Commodity>();    //新建一个ArrayList实例
        } else {
            //取得购物车
            car = (List<Commodity>) session.get("car");
        }

        //将商品添加到购物车中
        car.add(commodity);
        //将购物车保存在session中
        session.put("car", car);
        return SUCCESS;
    }
}
