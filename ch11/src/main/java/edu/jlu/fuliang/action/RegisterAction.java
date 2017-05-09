package edu.jlu.fuliang.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jlu.fuliang.Service.UserManageService;
import edu.jlu.fuliang.domain.User;

public class RegisterAction extends ActionSupport {
    private User user;
    private UserManageService userManageService;

    public String execute() throws Exception {
        userManageService.register(user);
        return SUCCESS;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserManageService(UserManageService userManageService) {
        this.userManageService = userManageService;
    }
}
