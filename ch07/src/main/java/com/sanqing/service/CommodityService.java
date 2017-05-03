package com.sanqing.service;

import com.sanqing.page.Page;
import com.sanqing.page.Result;
import com.sanqing.po.Commodity;
import com.sanqing.po.CommodityClass;

import java.util.List;

public interface CommodityService {
    public void addCommodity(Commodity commodity);//保存商品

    public Result findAllCommodity(Page page);//分页查询商品

    public void deleteCommodity(int commodityID);//按ID删除商品

    public List<Commodity> queryByRegTime(int num);//按提交时间倒序查找

    public Commodity queryByCommodityID(int commodityID);//按ID查找商品

    public Result queryByCommodityClass(CommodityClass commodityClass, Page page);
}
