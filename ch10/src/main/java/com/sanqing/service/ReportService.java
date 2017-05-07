package com.sanqing.service;

import com.sanqing.dao.ICstCustomerDAO;
import com.sanqing.dao.ICstLostDAO;
import com.sanqing.dao.ICstServiceDAO;
import com.sanqing.dao.IOrdersLineDAO;
import com.sanqing.util.PageResult;

import java.util.Map;

public class ReportService {
    private IOrdersLineDAO ordersLineDao = null;
    private ICstCustomerDAO customerDao = null;
    private ICstLostDAO cstLostDao = null;
    private ICstServiceDAO cstServiceDao = null;

    public ICstLostDAO getCstLostDao() {
        return cstLostDao;
    }

    public void setCstLostDao(ICstLostDAO cstLostDao) {
        this.cstLostDao = cstLostDao;
    }

    public ICstCustomerDAO getCustomerDao() {
        return customerDao;
    }

    public IOrdersLineDAO getOrdersLineDao() {
        return ordersLineDao;
    }

    public void setOrdersLineDao(IOrdersLineDAO ordersLineDao) {
        this.ordersLineDao = ordersLineDao;
    }

    public void setCustomerDao(ICstCustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    public ICstServiceDAO getCstServiceDao() {
        return cstServiceDao;
    }

    public void setCstServiceDao(ICstServiceDAO cstServiceDao) {
        this.cstServiceDao = cstServiceDao;
    }

    // 查询客户贡献
    public PageResult findCustProffer(Map paramMap) {
        return ordersLineDao.findCustProffer(paramMap);
    }

    // 查询客户构成
    public PageResult findCustStructure(String type) {
        return customerDao.finCustStructure(type);
    }

    //查询客户服务
    public PageResult findCstService(String svrDate) {
        return cstServiceDao.findServiceRept(svrDate);
    }

    //查询客户服务的年份
    public PageResult findSvrDate(Map paramMap) {
        return cstServiceDao.findSvrDate(paramMap);
    }

    // 查询所有流失客户的信息
    public PageResult findAllLose(Map paramMap) {
        return cstLostDao.findCstLostRept(paramMap);
    }
}
