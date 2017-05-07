package com.sanqing.dao;

import com.sanqing.po.BasDict;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface IBasDictDAO {

    // property constants
    public static final String DICT_TYPE = "dictType";
    public static final String DICT_ITEM = "dictItem";
    public static final String DICT_VALUE = "dictValue";
    public static final String DICT_IS_EDITABLE = "dictIsEditable";

    public abstract PageResult findAll(Map paramMap);

    public abstract void save(BasDict transientInstance);

    public abstract void delete(BasDict persistentInstance);

    public abstract BasDict findById(Long id);

    public abstract List findByExample(BasDict instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByDictType(Object dictType);

    public abstract List findByDictItem(Object dictItem);

    public abstract List findByDictValue(Object dictValue);

    public abstract List findByDictIsEditable(Object dictIsEditable);

    public abstract List findAll();

    public abstract BasDict merge(BasDict detachedInstance);

    public abstract void attachDirty(BasDict instance);

    public abstract void attachClean(BasDict instance);

}