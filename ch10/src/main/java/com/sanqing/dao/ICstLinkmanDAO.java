package com.sanqing.dao;

import com.sanqing.po.CstLinkman;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface ICstLinkmanDAO {

    // property constants
    public static final String LKM_NAME = "lkmName";
    public static final String LKM_SEX = "lkmSex";
    public static final String LKM_POSTION = "lkmPostion";
    public static final String LKM_TEL = "lkmTel";
    public static final String LKM_MOBILE = "lkmMobile";
    public static final String LKM_MEMO = "lkmMemo";

    public abstract PageResult findAll(Map paramMap);

    public abstract void save(CstLinkman transientInstance);

    public abstract void delete(CstLinkman persistentInstance);

    public abstract CstLinkman findById(Long id);

    public abstract List findByExample(CstLinkman instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByLkmName(Object lkmName);

    public abstract List findByLkmSex(Object lkmSex);

    public abstract List findByLkmPostion(Object lkmPostion);

    public abstract List findByLkmTel(Object lkmTel);

    public abstract List findByLkmMobile(Object lkmMobile);

    public abstract List findByLkmMemo(Object lkmMemo);

    public abstract CstLinkman merge(CstLinkman detachedInstance);

    public abstract void attachDirty(CstLinkman instance);

    public abstract void attachClean(CstLinkman instance);

}