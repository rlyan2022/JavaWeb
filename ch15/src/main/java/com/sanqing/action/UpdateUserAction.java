package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.User;

public class UpdateUserAction extends ActionSupport {
    private int id;
    private String field;
    private String value;
    private UserDAO userDAO;
    private boolean success;
    private String msg;

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

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String execute() throws Exception {
        //取得该id对应的用户
        User user = userDAO.findById(id);
        //判断该id对应的用户是否存在
        if (user == null) {
            success = false;
            msg = "更新用户信息失败!";
        } else {
            //根据field来知道需要更新哪个属性
            if ("username".equals(field)) {
                user.setUsername(value);
                if (userDAO.findByUsername(value).size() != 0) {
                    success = false;
                    msg = "用户更新数据失败，该用户已经被占用!";
                } else {
                    //更新用户信息
                    userDAO.attachDirty(user);
                    success = true;
                    msg = "用户更新数据成功!";
                }
            } else if ("password".equals(field)) {
                user.setPassword(value);
                //更新用户信息
                userDAO.attachDirty(user);
                success = true;
                msg = "用户更新数据成功!";
            }
        }
        return this.SUCCESS;
    }
}
