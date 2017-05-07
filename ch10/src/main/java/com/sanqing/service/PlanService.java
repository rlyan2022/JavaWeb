package com.sanqing.service;

import com.sanqing.dao.ISalChanceDAO;
import com.sanqing.dao.ISalPlanDAO;
import com.sanqing.po.SalChance;
import com.sanqing.po.SalPlan;
import com.sanqing.util.PageResult;

import java.util.Map;

public class PlanService {

    private ISalPlanDAO planDao = null;
    private ISalChanceDAO salchanceDao = null;

    public ISalChanceDAO getSalchanceDao() {
        return salchanceDao;
    }

    public void setSalchanceDao(ISalChanceDAO salchanceDao) {
        this.salchanceDao = salchanceDao;
    }

    public ISalPlanDAO getPlanDao() {
        return planDao;
    }

    public void setPlanDao(ISalPlanDAO planDao) {
        this.planDao = planDao;
    }

    //查询销售机会的编号。。。
    public SalChance findByChcId(Long chcId) {
        return salchanceDao.findById(chcId);
    }

    //修改销售机会的编号。。。。
    public void updateChance(SalChance salChance) {
        salchanceDao.merge(salChance);
    }

    // 查询所有的销售机会记录。。。
    public PageResult findAll(Map paramMap) {
        return planDao.findAll(paramMap);
    }

    // 查询计划项。。。
    public PageResult findTodo(Map paramMap) {
        return planDao.findPlanTodo(paramMap);
    }

    // 判断销售机会是否存在。。。
    public boolean findById(Long plaId) {
        if (planDao.findById(plaId) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 查找plaId。。。
    public SalPlan findByPlaId(Long plaId) {
        return planDao.findById(plaId);
    }

    // 添加计划项。。。
    public void addPlan(SalPlan salplan) {
        planDao.save(salplan);
    }

    // 修改计划项。。。。
    public void updatePlan(SalPlan salplan) {
        planDao.merge(salplan);
    }

    // 删除计划项。。。
    public void delPlan(SalPlan salplan) {
        planDao.delete(salplan);
    }

}
