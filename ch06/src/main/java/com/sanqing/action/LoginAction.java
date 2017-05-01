package com.sanqing.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 用户登录
 */
@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends ActionSupport {

    @Resource
    private UserService userService;
    private String username;
    private String password;

    @Override
    public String execute() throws Exception {
        if (!userService.login(username, password)) {
            return this.INPUT;
        } else {
            Map session = ActionContext.getContext().getSession();
            session.put("user", userService.find(username));
        }
        return this.SUCCESS;
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
}
