package com.sanqing.dao;

import com.sanqing.po.OrdersLine;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface IOrdersLineDAO {

    // property constants
    public static final String ODD_COUNT = "oddCount";
    public static final String ODD_UNIT = "oddUnit";
    public static final String ODD_PRICE = "oddPrice";

    public abstract PageResult findCustProffer(Map paramMap);

    public abstract PageResult findAll(Map paramMap);

    public abstract void save(OrdersLine transientInstance);

    public abstract void delete(OrdersLine persistentInstance);

    public abstract OrdersLine findById(Long id);

    public abstract List findByExample(OrdersLine instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByOddCount(Object oddCount);

    public abstract List findByOddUnit(Object oddUnit);

    public abstract List findByOddPrice(Object oddPrice);

    public abstract OrdersLine merge(OrdersLine detachedInstance);

    public abstract void attachDirty(OrdersLine instance);

    public abstract void attachClean(OrdersLine instance);

}