package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.dao.UserDAO;
import com.sanqing.po.User;

import java.util.List;
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
            List list = userDAO.findByUsername(username);
            if (list.size() == 0) {
                success = false;
                msg = "用户名不存在";
            } else {
                User user = (User) list.get(0);
                if (password.equals(user.getPassword())) {
                    Map session = ActionContext.getContext().getSession();
                    session.put("username", user.getUsername());
                    session.put("quanxian", user.getQuanxian());
                    success = true;
                    msg = "用户登录成功";
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
