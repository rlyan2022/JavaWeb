package com.sanqing.dao;

import com.sanqing.po.SalChance;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface ISalChanceDAO {

    // property constants
    public static final String CHC_SOURCE = "chcSource";
    public static final String CHC_CUST_NAME = "chcCustName";
    public static final String CHC_TITLE = "chcTitle";
    public static final String CHC_RATE = "chcRate";
    public static final String CHC_LINKMAN = "chcLinkman";
    public static final String CHC_TEL = "chcTel";
    public static final String CHC_DESC = "chcDesc";
    public static final String CHC_CREATE_BY = "chcCreateBy";
    public static final String CHC_DUE_TO = "chcDueTo";
    public static final String CHC_STATUS = "chcStatus";

    public abstract PageResult findAll(Map paramMap);

    public abstract void save(SalChance transientInstance);

    public abstract void delete(SalChance persistentInstance);

    public abstract SalChance findById(Long id);

    public abstract List findByExample(SalChance instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByChcSource(Object chcSource);

    public abstract List findByChcCustName(Object chcCustName);

    public abstract List findByChcTitle(Object chcTitle);

    public abstract List findByChcRate(Object chcRate);

    public abstract List findByChcLinkman(Object chcLinkman);

    public abstract List findByChcTel(Object chcTel);

    public abstract List findByChcDesc(Object chcDesc);

    public abstract List findByChcCreateBy(Object chcCreateBy);

    public abstract List findByChcDueTo(Object chcDueTo);

    public abstract List findByChcStatus(Object chcStatus);

    public abstract SalChance merge(SalChance detachedInstance);

    public abstract void attachDirty(SalChance instance);

    public abstract void attachClean(SalChance instance);

}