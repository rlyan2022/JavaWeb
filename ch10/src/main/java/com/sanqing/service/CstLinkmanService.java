package com.sanqing.service;

import com.sanqing.dao.ICstLinkmanDAO;
import com.sanqing.po.CstLinkman;
import com.sanqing.util.PageResult;

import java.util.Map;

public class CstLinkmanService {
    private ICstLinkmanDAO cstLinkmanDao = null;

    public ICstLinkmanDAO getCstLinkmanDao() {
        return cstLinkmanDao;
    }

    public void setCstLinkmanDao(ICstLinkmanDAO cstLinkmanDao) {
        this.cstLinkmanDao = cstLinkmanDao;
    }

	/*
	 * 客户联系人信息管理 @method findLinkman,saveLinkman,updateLinkman,delLinkman
	 */

    // 根据客户编号查询客户联系人
    public PageResult findLinkman(Map paramMap) {
        return cstLinkmanDao.findAll(paramMap);
    }

    public CstLinkman findById(Long lkmId) {
        return cstLinkmanDao.findById(lkmId);
    }

    //判断客户联系人是否存在
    public boolean findBylkmId(Long lkmId) {
        if (cstLinkmanDao.findById(lkmId) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 新增客户联系人信息
    public void addLinkman(CstLinkman linkman) {
        cstLinkmanDao.save(linkman);
    }

    // 修改客户联系人信息
    public void updateLinkman(CstLinkman linkman) {
        cstLinkmanDao.merge(linkman);
    }

    // 删除客户联系人信息
    public void delLinkman(CstLinkman linkman) {
        cstLinkmanDao.delete(linkman);
    }
}
