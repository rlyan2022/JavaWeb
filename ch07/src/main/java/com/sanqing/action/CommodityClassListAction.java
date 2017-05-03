package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.page.Page;
import com.sanqing.page.Result;
import com.sanqing.po.CommodityClass;
import com.sanqing.service.CommodityClassService;

import java.util.List;

public class CommodityClassListAction extends ActionSupport {
    private CommodityClassService commodityClassService;//业务逻辑层
    private int currentPage;            //当前页
    private Page page;                    //分页信息
    private List<CommodityClass> commodityClasses;//商品种类列表

    public List<CommodityClass> getCommodityClasses() {
        return commodityClasses;
    }

    public void setCommodityClassService(CommodityClassService commodityClassService) {
        this.commodityClassService = commodityClassService;
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

    public CommodityClassService getCommodityClassService() {
        return commodityClassService;
    }

    public void setCommodityClasses(List<CommodityClass> commodityClasses) {
        this.commodityClasses = commodityClasses;
    }

    public String execute() throws Exception {
        Page newPage = new Page();                //设置分页信息
        newPage.setCurrentPage(currentPage);    //设置当前页
        newPage.setEveryPage(10);                //设置每页显示
        Result result = commodityClassService.findAllCommodityClass(newPage);//获取查询结果
        page = result.getPage();        //获取分页信息
        commodityClasses = result.getList();    //获取商品信息列表
        return SUCCESS;
    }
}
