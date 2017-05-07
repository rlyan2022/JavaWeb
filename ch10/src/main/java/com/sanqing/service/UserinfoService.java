package com.sanqing.service;

import com.sanqing.dao.ISysRightDAO;
import com.sanqing.dao.ISysRoleDAO;
import com.sanqing.dao.ISysUserDAO;
import com.sanqing.po.SysUser;
import com.sanqing.util.PageResult;

import java.util.Map;

public class UserinfoService {
    private ISysUserDAO userDao = null;
    private ISysRoleDAO roleDao = null;
    private ISysRightDAO rightDao = null;

    public ISysRoleDAO getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(ISysRoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    public ISysUserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(ISysUserDAO userDao) {
        this.userDao = userDao;
    }

    // 查询所有用户信息
    public PageResult findAll(Map paramMap) {
        return userDao.findAll(paramMap);
    }

    // 查询出所有角色名称
    public PageResult findAllRoleName() {
        return roleDao.findAll();
    }

    // 根据编号查询用户信息
    public boolean findById(Long usrId) {
        if (userDao.findById(usrId) == null) {
            return false;
        } else {
            return true;
        }
    }

    // 添加用户信息
    public void add(SysUser sysUser) {
        userDao.save(sysUser);
    }

    // 返回一个SysUser的对象
    public SysUser findByUsrId(Long usrId) {
        return userDao.findById(usrId);
    }

    // 修改用户信息
    public void update(SysUser sysUser) {
        userDao.merge(sysUser);
    }

    // 删除用户信息
    public void del(SysUser sysUser) {
        userDao.delete(sysUser);
    }

    public ISysRightDAO getRightDao() {
        return rightDao;
    }

    public void setRightDao(ISysRightDAO rightDao) {
        this.rightDao = rightDao;
    }
}
