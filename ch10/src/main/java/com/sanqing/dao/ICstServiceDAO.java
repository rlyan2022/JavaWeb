package com.sanqing.dao;

import com.sanqing.po.CstService;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface ICstServiceDAO {

    // property constants
    public static final String SVR_TYPE = "svrType";
    public static final String SVR_TITLE = "svrTitle";
    public static final String SVR_CUST_NO = "svrCustNo";
    public static final String SVR_STATUS = "svrStatus";
    public static final String SVR_REQUEST = "svrRequest";
    public static final String SVR_CREATE_BY = "svrCreateBy";
    public static final String SVR_DUE_TO = "svrDueTo";
    public static final String SVR_DEAL = "svrDeal";
    public static final String SVR_DEAL_BY = "svrDealBy";
    public static final String SVR_RESULT = "svrResult";
    public static final String SVR_SATISFY = "svrSatisfy";

    public abstract PageResult findAll(Map paramMap);

    public abstract CstService merge(CstService detachedInstance);

    public abstract void save(CstService transientInstance);

    public abstract PageResult findSvrDate(Map paramMap);

    public abstract PageResult findServiceRept(String svrDate);

    public abstract void delete(CstService persistentInstance);

    public abstract CstService findById(Long id);

    public abstract List findByExample(CstService instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findBySvrType(Object svrType);

    public abstract List findBySvrTitle(Object svrTitle);

    public abstract List findBySvrCustNo(Object svrCustNo);

    public abstract List findBySvrStatus(Object svrStatus);

    public abstract List findBySvrRequest(Object svrRequest);

    public abstract List findBySvrCreateBy(Object svrCreateBy);

    public abstract List findBySvrDueTo(Object svrDueTo);

    public abstract List findBySvrDeal(Object svrDeal);

    public abstract List findBySvrDealBy(Object svrDealBy);

    public abstract List findBySvrResult(Object svrResult);

    public abstract List findBySvrSatisfy(Object svrSatisfy);

    public abstract void attachDirty(CstService instance);

    public abstract void attachClean(CstService instance);

}