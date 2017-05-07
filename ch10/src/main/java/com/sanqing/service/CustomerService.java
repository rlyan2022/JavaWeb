package com.sanqing.service;

import com.sanqing.dao.ICstCustomerDAO;
import com.sanqing.po.CstCustomer;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public class CustomerService {

    private ICstCustomerDAO custDao = null;

    public ICstCustomerDAO getCustDao() {
        return custDao;
    }

    public void setCustDao(ICstCustomerDAO custDao) {
        this.custDao = custDao;
    }

    /*
     * 客户信息管理 @method findAll,findById,findByCustName,add,update,del
     */
    // 查询客户信息
    public PageResult findAll(Map paramMap) {
        return custDao.findAll(paramMap);
    }

    //判断客户是否存在
    public boolean findById(String custNo) {
        if (custDao.findById(custNo) == null) {
            return false;
        } else {
            return true;
        }
    }

    public CstCustomer findByCustNo(String custNo) {
        return custDao.findById(custNo);
    }

    // 根据客户名称查询客户信息
    public List<CstCustomer> findByCustName(String svrCustName) {
        return custDao.findByCustName(svrCustName);
    }

    // 新增客户信息
    public void add(CstCustomer customer) {
        custDao.save(customer);
    }

    // 修改客户信息
    public void update(CstCustomer customer) {
        custDao.merge(customer);
    }

    // 删除客户信息
    public void del(CstCustomer customer) {
        custDao.delete(customer);
    }
}
