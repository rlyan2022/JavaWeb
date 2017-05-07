package com.sanqing.dao;

import com.sanqing.po.Orders;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface IOrdersDAO {

    // property constants
    public static final String ODR_CUSTOMER = "odrCustomer";
    public static final String ODR_ADDR = "odrAddr";
    public static final String ODR_STATUS = "odrStatus";

    public abstract PageResult findAll(Map paramMap);

    public abstract void save(Orders transientInstance);

    public abstract void delete(Orders persistentInstance);

    public abstract Orders findById(Long id);

    public abstract List findByExample(Orders instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByOdrCustomer(Object odrCustomer, Object odrDate);

    public abstract List findByOdrAddr(Object odrAddr);

    public abstract List findByOdrStatus(Object odrStatus);

    public abstract Orders merge(Orders detachedInstance);

    public abstract void attachDirty(Orders instance);

    public abstract void attachClean(Orders instance);

}