package com.sanqing.dao;

import com.sanqing.po.CstActivity;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface ICstActivityDAO {

    // property constants
    public static final String ATV_PLACE = "atvPlace";
    public static final String ATV_TITLE = "atvTitle";
    public static final String ATV_DESC = "atvDesc";

    public abstract PageResult findAll(Map paramMap);

    public abstract void save(CstActivity transientInstance);

    public abstract void delete(CstActivity persistentInstance);

    public abstract CstActivity findById(Long id);

    public abstract List findByExample(CstActivity instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByAtvPlace(Object atvPlace);

    public abstract List findByAtvTitle(Object atvTitle);

    public abstract List findByAtvDesc(Object atvDesc);


    public abstract CstActivity merge(CstActivity detachedInstance);

    public abstract void attachDirty(CstActivity instance);

    public abstract void attachClean(CstActivity instance);

}