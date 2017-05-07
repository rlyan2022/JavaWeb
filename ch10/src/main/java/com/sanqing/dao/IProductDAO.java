package com.sanqing.dao;

import com.sanqing.po.Product;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface IProductDAO {

    // property constants
    public static final String PROD_NAME = "prodName";
    public static final String PROD_TYPE = "prodType";
    public static final String PROD_BATCH = "prodBatch";
    public static final String PROD_UNIT = "prodUnit";
    public static final String PROD_PRICE = "prodPrice";
    public static final String PROD_MEMO = "prodMemo";

    public abstract PageResult findAll(Map paramMap);

    public abstract void save(Product transientInstance);

    public abstract void delete(Product persistentInstance);

    public abstract Product findById(Long id);

    public abstract List findByExample(Product instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByProdName(String prodName);

    public abstract List findByProdType(Object prodType);

    public abstract List findByProdBatch(Object prodBatch);

    public abstract List findByProdUnit(Object prodUnit);

    public abstract List findByProdPrice(Object prodPrice);

    public abstract List findByProdMemo(Object prodMemo);

    public abstract List findAll();

    public abstract Product merge(Product detachedInstance);

    public abstract void attachDirty(Product instance);

    public abstract void attachClean(Product instance);

}