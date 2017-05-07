package com.sanqing.dao;

import com.sanqing.po.CstLost;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface ICstLostDAO {

    // property constants
    public static final String LST_CUST_MANAGER_NAME = "lstCustManagerName";
    public static final String LST_DELAY = "lstDelay";
    public static final String LST_REASON = "lstReason";
    public static final String LST_STATUS = "lstStatus";

    public abstract void lostCustomer();

    public abstract PageResult findAll(Map paramMap);

    public PageResult findCstLostRept(Map paramMap);

    public abstract void save(CstLost transientInstance);

    public abstract void delete(CstLost persistentInstance);

    public abstract CstLost findById(Long id);

    public abstract List findByExample(CstLost instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByLstCustManagerName(Object lstCustManagerName);

    public abstract List findByLstDelay(Object lstDelay);

    public abstract List findByLstReason(Object lstReason);

    public abstract List findByLstStatus(Object lstStatus);

    public abstract CstLost merge(CstLost detachedInstance);

    public abstract void attachDirty(CstLost instance);

    public abstract void attachClean(CstLost instance);

}