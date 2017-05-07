package com.sanqing.dao;

import com.sanqing.po.SysRight;
import com.sanqing.util.PageResult;
import com.sanqing.util.RightList;

import java.util.List;
import java.util.Map;

public interface ISysRightDAO {

    // property constants
    public static final String RIGHT_TYPE = "rightType";
    public static final String RIGHT_TEXT = "rightText";
    public static final String RIGHT_URL = "rightUrl";
    public static final String RIGHT_TIP = "rightTip";

    public List<SysRight> findAll();

    public PageResult findRight(Map paramMap);

    public RightList findAllRight();

    public abstract void save(SysRight transientInstance);

    public abstract void delete(SysRight persistentInstance);

    public abstract SysRight findById(Long rightCode);

    public abstract List findByExample(SysRight instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByRightType(Object rightType);

    public abstract List findByRightText(Object rightText);

    public abstract List findByRightUrl(Object rightUrl);

    public abstract List findByRightTip(Object rightTip);

    public abstract SysRight merge(SysRight detachedInstance);

    public abstract void attachDirty(SysRight instance);

    public abstract void attachClean(SysRight instance);

}