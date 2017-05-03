package com.sanqing.service.impl;

import com.sanqing.dao.CommodityClassDAO;
import com.sanqing.page.Page;
import com.sanqing.page.PageUtil;
import com.sanqing.page.Result;
import com.sanqing.po.CommodityClass;
import com.sanqing.service.CommodityClassService;

import java.util.List;

public class CommodityClassServiceImpl implements CommodityClassService {
    private CommodityClassDAO commodityClassDAO;//注入数据访问层

    public void setCommodityClassDAO(CommodityClassDAO commodityClassDAO) {
        this.commodityClassDAO = commodityClassDAO;//设置数据访问层
    }

    public void addCommodityClass(CommodityClass commodityClass) {
        commodityClassDAO.save(commodityClass);//完成数据保存
    }

    public Result findAllCommodityClass(Page page) {
        page = PageUtil.createPage(page,
                commodityClassDAO.findAllCount());    //创建分页对象
        List<CommodityClass> commodityClasses
                = commodityClassDAO.findAll(page);    //执行查询
        Result result = new Result();    //创建结果对象
        result.setPage(page);            //设置分页信息
        result.setList(commodityClasses);//设置商品种类列表
        return result;
    }

    public List<CommodityClass> findAllCommodityClass() {
        return commodityClassDAO.findAll();
    }

    public CommodityClass queryByID(int commodityClassID) {
        return commodityClassDAO.findByID(commodityClassID);
    }
}
