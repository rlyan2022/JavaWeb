package com.sanqing.service;

import com.sanqing.dao.ISysUserDAO;
import com.sanqing.po.SysUser;

import java.util.List;

public class LoginService {
    private ISysUserDAO sysUserDao = null;

    public ISysUserDAO getSysUserDao() {
        return sysUserDao;
    }

    public void setSysUserDao(ISysUserDAO sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    // 验证用户
    public List<SysUser> validUser(String userName) {
        return sysUserDao.findByUsrName(userName);
    }

    // 修改用户密码
    public void updatePwd(SysUser user) {
        sysUserDao.merge(user);
    }

    public SysUser findById(Long userId) {
        return sysUserDao.findById(userId);
    }
}
