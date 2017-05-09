package edu.jlu.fuliang.action;

import com.opensymphony.xwork2.ActionSupport;
import edu.jlu.fuliang.Service.UserManageService;
import edu.jlu.fuliang.domain.User;
import org.apache.struts2.ServletActionContext;

public class LoginAction extends ActionSupport {
    private User user;
    private UserManageService userManageService;

    public String execute() throws Exception {
        if (userManageService.login(user)) {
            ServletActionContext.getContext().getSession().put("user", user);
            return SUCCESS;
        }
        return LOGIN;
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
