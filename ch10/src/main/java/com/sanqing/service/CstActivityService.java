package com.sanqing.service;

import com.sanqing.dao.ICstActivityDAO;
import com.sanqing.po.CstActivity;
import com.sanqing.util.PageResult;

import java.util.Map;

public class CstActivityService {
    ICstActivityDAO cstActivityDao = null;

    public ICstActivityDAO getCstActivityDao() {
        return cstActivityDao;
    }

    public void setCstActivityDao(ICstActivityDAO cstActivityDao) {
        this.cstActivityDao = cstActivityDao;
    }

    /*
     * 客户交往记录管理 @method
     * findAll,findByAtvId,findById,addCstActivity,updateCstActivity,delCstActivity
     */
    // 查询客户交往记录
    public PageResult findAll(Map paramMap) {
        return cstActivityDao.findAll(paramMap);
    }

    // 判断客户交往记录是否存在
    public boolean findByAtvId(Long atvId) {
        if (cstActivityDao.findById(atvId) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 返回车个CstActivity对象
    public CstActivity findById(Long atvId) {
        return cstActivityDao.findById(atvId);
    }

    // 新增客户交往记录
    public void addCstActivity(CstActivity cstActivity) {
        cstActivityDao.save(cstActivity);
    }

    // 修改客户交往记录
    public void updateCstActivity(CstActivity cstActivity) {
        cstActivityDao.merge(cstActivity);
    }

    // 删除客户交往记录
    public void delCstActivity(CstActivity cstActivity) {
        cstActivityDao.delete(cstActivity);
    }
}
