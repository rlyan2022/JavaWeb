package com.sanqing.service;

import com.sanqing.dao.ICstServiceDAO;
import com.sanqing.po.CstService;
import com.sanqing.util.PageResult;

import java.util.Map;

public class CstServiceService {

    private ICstServiceDAO cstServiceDao = null;

    public ICstServiceDAO getCstServiceDao() {
        return cstServiceDao;
    }

    public void setCstServiceDao(ICstServiceDAO cstServiceDao) {
        this.cstServiceDao = cstServiceDao;
    }

    public CstService findById(Long svrId) {
        return cstServiceDao.findById(svrId);
    }

    // 创建客户服务信息
    public void add(CstService cstService) {
        cstServiceDao.save(cstService);
    }

    // 修改客户服务信息
    public void update(CstService cstService) {
        cstServiceDao.merge(cstService);
    }

    // 查询客户服务信息
    public PageResult findAll(Map paramMap) {
        return cstServiceDao.findAll(paramMap);
    }

    public CstService findBySvrId(Long svrId) {
        return cstServiceDao.findById(svrId);
    }

    // 查询客户服务信息中的类型信息
    public PageResult findBySvrType(Map paramMap) {
        return findBySvrType(paramMap);
    }

    // 删除客户服务信息
    public void del(CstService cstService) {
        cstServiceDao.delete(cstService);
    }

}
