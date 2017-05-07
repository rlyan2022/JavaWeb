package com.sanqing.service;

import com.sanqing.dao.ISysRightDAO;
import com.sanqing.dao.ISysRoleRightDAO;
import com.sanqing.po.SysRight;
import com.sanqing.util.PageResult;

import java.util.Map;

public class RightService {
    private ISysRightDAO rightDao = null;
    private ISysRoleRightDAO roleRightDao = null;

    public ISysRoleRightDAO getRoleRightDao() {
        return roleRightDao;
    }

    public void setRoleRightDao(ISysRoleRightDAO roleRightDao) {
        this.roleRightDao = roleRightDao;
    }

    public ISysRightDAO getRightDao() {
        return rightDao;
    }

    public void setRightDao(ISysRightDAO rightDao) {
        this.rightDao = rightDao;
    }

    public String[] findRight(Long roleId) {
        return roleRightDao.findRight(roleId);
    }

    // 查询所有的数据。。。。
    public PageResult findAll(Map paramMap) {
        return rightDao.findRight(paramMap);
    }

    // 查找ID。。。。
    public SysRight findRightId(Long rightid) {
        return rightDao.findById(rightid);
    }

    // 判断rightCode是否存在。。。
    public boolean isRightCode(Long rightCode) {
        if (rightDao.findById(rightCode) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 判断rightCode在role_right表中是否存在。。
    public boolean isRoleRight(Long rightCode) {
        if (roleRightDao.findByProperty("sysRight.rightCode", rightCode).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 添加。。。。。
    public void addRight(SysRight right) {
        rightDao.save(right);
    }

    // 修改。。。
    public void updateRight(SysRight right) {
        rightDao.merge(right);
    }

    // 删除数据。。。。
    public void delRight(SysRight right) {
        rightDao.delete(right);
    }
}
