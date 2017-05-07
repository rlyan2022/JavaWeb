package com.sanqing.service;

import com.sanqing.dao.ICstManagerDAO;
import com.sanqing.po.CstManager;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public class CstManagerService {
    private ICstManagerDAO cstManagerDao = null;

    public ICstManagerDAO getCstManagerDao() {
        return cstManagerDao;
    }

    public void setCstManagerDao(ICstManagerDAO cstManagerDao) {
        this.cstManagerDao = cstManagerDao;
    }

    // 查询出所有客户经理的名称
    public PageResult findAllCstManager(Map paramMap) {
        return cstManagerDao.findAll(paramMap);
    }

    // 根据经理名称查编号
    public Long findManId(String manName) {
        Long manId = null;
        List<CstManager> cstManager = cstManagerDao.findByManName(manName);
        for (CstManager manager : cstManager) {
            manId = manager.getManId();
        }
        return manId;
    }
}
