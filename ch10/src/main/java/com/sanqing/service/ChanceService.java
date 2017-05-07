package com.sanqing.service;

import com.sanqing.dao.ISalChanceDAO;
import com.sanqing.po.SalChance;
import com.sanqing.util.PageResult;

import java.util.Map;

public class ChanceService {
    private ISalChanceDAO chanceDao = null;

    // ISalChanceDAO的get和set方法
    public ISalChanceDAO getChanceDao() {
        return chanceDao;
    }

    public void setChanceDao(ISalChanceDAO chanceDao) {
        this.chanceDao = chanceDao;
    }

    // 查询所有销售机会信息
    public PageResult findAll(Map paramMap) {
        return chanceDao.findAll(paramMap);
    }

    // 根据编号查询营销机会信息
    public boolean findById(Long chcId) {
        if (chanceDao.findById(chcId) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 返回一个SalChance的对象
    public SalChance findByChcId(Long chcId) {
        return chanceDao.findById(chcId);
    }

    // 新增销售机会信息
    public void add(SalChance chance) {
        chanceDao.save(chance);
    }

    // 修改销售机会信息
    public void update(SalChance chance) {
        chanceDao.merge(chance);
    }

    // 删除营销机会信息
    public void del(SalChance salChance) {
        chanceDao.delete(salChance);
    }

}
