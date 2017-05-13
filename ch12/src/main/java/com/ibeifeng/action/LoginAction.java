package com.ibeifeng.action;

import com.ibeifeng.dao.UserDAO;
import com.ibeifeng.po.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

public class LoginAction extends ActionSupport {
    private String username;
    private String password;
    private String randCode;
    private boolean success;
    private String msg;
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public String getRandCode() {
        return randCode;
    }

    public void setRandCode(String randCode) {
        this.randCode = randCode;
    }

    public String execute() throws Exception {
        //获得的当前正确的验证码
        String rand = (String) ActionContext.getContext().getSession().get("rand");

        if (rand.equals(randCode)) {
            //判断用户是否存在
            User user = userDAO.findById(username);
            if (user == null) {
                success = false;
                msg = "用户名不存在";
            } else {
                if (password.equals(user.getPassword())) {
                    success = true;
                    msg = "用户登录成功";
                    Map session = ActionContext.getContext().getSession();
                    session.put("username", username);
                } else {
                    success = false;
                    msg = "密码不正确";
                }
            }
        } else {
            success = false;
            msg = "验证码输入错误";
        }
        return this.SUCCESS;
    }
}
