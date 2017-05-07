package com.sanqing.service;

import com.sanqing.dao.IProductDAO;
import com.sanqing.util.PageResult;

import java.util.Map;

public class ProductService {
    private IProductDAO duct = null;

    //查询产品信息
    public PageResult findAll(Map paramMap) {


        return duct.findAll(paramMap);


    }

    public IProductDAO getDuct() {
        return duct;
    }

    public void setDuct(IProductDAO duct) {
        this.duct = duct;
    }


}
