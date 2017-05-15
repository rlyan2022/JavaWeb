package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.User;

import java.util.List;

public class UserAddAction extends ActionSupport {
    private String username;
    private String password;
    private boolean success;
    private String msg;
    private UserDAO userDAO;
    private String repassword;


    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() throws Exception {
        if (repassword != null && password != null && repassword.equals(password)) {
            List users = userDAO.findByUsername(username);
            //判断用户名是否存在
            if (users.size() == 0) {
                //如果不存在，则直接保存
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                userDAO.save(user);
                success = true;
                msg = "用户保存成功";
            } else {
                success = false;
                msg = "用户名已经存在，请输入其他用户名!";
            }
        } else {
            success = false;
            msg = "密码和确认密码不同!";
        }
        return this.SUCCESS;
    }
}
