package com.sanqing.service;

import com.sanqing.page.Page;
import com.sanqing.page.Result;
import com.sanqing.po.CommodityClass;

import java.util.List;

public interface CommodityClassService {
    public void addCommodityClass(CommodityClass commodityClass);//保存商品种类

    public Result findAllCommodityClass(Page page);//分页查询商品种类

    public List<CommodityClass> findAllCommodityClass();//查询所有商品种类

    public CommodityClass queryByID(int commodityClassID);
}
