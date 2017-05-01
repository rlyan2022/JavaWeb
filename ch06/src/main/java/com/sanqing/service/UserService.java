package com.sanqing.service;

import com.sanqing.dao.DAO;
import com.sanqing.po.User;

/**
 * 用户业务接口
 */
public interface UserService extends DAO<User> {

    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public boolean login(String username, String password);
}
