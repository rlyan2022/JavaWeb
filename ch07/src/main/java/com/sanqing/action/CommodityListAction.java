package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.page.Page;
import com.sanqing.page.Result;
import com.sanqing.po.Commodity;
import com.sanqing.service.CommodityService;

import java.util.List;

public class CommodityListAction extends ActionSupport {
    private CommodityService commodityService;//业务逻辑层
    private List<Commodity> commodities;// 商品信息列表
    private int currentPage;            //当前页
    private Page page;                    //分页信息

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


    public int getCurrentPage() {
        return currentPage;
    }


    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public Page getPage() {
        return page;
    }


    public void setPage(Page page) {
        this.page = page;
    }

    public String execute() throws Exception {
        Page newPage = new Page();                //设置分页信息
        newPage.setCurrentPage(currentPage);    //设置当前页
        newPage.setEveryPage(10);                //设置每页显示
        Result result = commodityService.findAllCommodity(newPage);//获取查询结果
        page = result.getPage();        //获取分页信息
        commodities = result.getList();    //获取商品信息列表
        return SUCCESS;
    }
}
