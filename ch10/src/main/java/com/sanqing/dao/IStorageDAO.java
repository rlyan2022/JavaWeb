package com.sanqing.dao;

import com.sanqing.po.Storage;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface IStorageDAO {

    // property constants

    public static final String STK_WAREHOUSE = "stkWarehouse";
    public static final String STK_WARE = "stkWare";
    public static final String STK_COUNT = "stkCount";
    public static final String STK_MEMO = "stkMemo";

    public abstract PageResult findAll(Map paramMap);


    public abstract void save(Storage transientInstance);

    public abstract void delete(Storage persistentInstance);

    public abstract Storage findById(Long id);

    public abstract List findByExample(Storage instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByStkWarehouse(Object stkWarehouse);

    public abstract List findByStkWare(Object stkWare);

    public abstract List findByStkCount(Object stkCount);

    public abstract List findByStkMemo(Object stkMemo);

    public abstract List findAll();

    public abstract Storage merge(Storage detachedInstance);

    public abstract void attachDirty(Storage instance);

    public abstract void attachClean(Storage instance);

}