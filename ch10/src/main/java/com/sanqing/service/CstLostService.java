package com.sanqing.service;

import com.sanqing.dao.ICstLostDAO;
import com.sanqing.po.CstLost;
import com.sanqing.util.PageResult;

import java.util.Map;

public class CstLostService {

    private ICstLostDAO cstLostDao = null;

    public ICstLostDAO getCstLostDao() {
        return cstLostDao;
    }

    public void setCstLostDao(ICstLostDAO cstLostDao) {
        this.cstLostDao = cstLostDao;
    }

    public PageResult findAll(Map paramMap) {
        return cstLostDao.findAll(paramMap);
    }

    // 增加客户流失信息
    public void add(CstLost cstLost) {
        cstLostDao.save(cstLost);
    }

    // 根据客户编号查询客户信息是否在流失表中
    public boolean findByLstCustNo(String lstCustNo) {
        if (cstLostDao.findByProperty("cstCustomer.custNo", lstCustNo) == null) {
            return false;
        } else {
            return true;
        }
    }

    public CstLost findByLstId(Long lstId) {
        return cstLostDao.findById(lstId);
    }

    public void update(CstLost cstLost) {
        cstLostDao.merge(cstLost);
    }
}
