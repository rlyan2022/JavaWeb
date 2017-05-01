package com.sanqing.action;

import com.sanqing.po.User;
import com.sanqing.service.UserService;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("userManageAction")
@Scope("prototype")
public class UserManageAction extends BaseAction {

    @Resource
    private UserService userService;
    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;
    /* 级别 */
    private int grade;

    /**
     * 添加用户输入界面
     *
     * @return
     */
    public String addUI() {
        return "add";
    }

    /**
     * 添加用户
     *
     * @return
     */
    public String add() {
        User user = new User();
        user.setGrade(grade);
        user.setPassword(password);
        user.setUsername(username);
        userService.save(user);
        return "pub_add_success";
    }

    /**
     * 修改界面
     *
     * @return
     */
    public String updateUI() {
        User user = userService.find(username);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("user", user);
        return "update";
    }

    /**
     * 修改界面
     *
     * @return
     */
    public String update() {
        User user = new User();
        user.setGrade(grade);
        user.setUsername(username);
        user.setPassword(password);
        userService.update(user);
        return "pub_update_success";
    }

    /**
     * 删除
     *
     * @return
     */
    public String del() {
        userService.delete(username);
        return "pub_del_success";
    }

    /**
     * 查询用户
     *
     * @return
     */
    public String query() {
        return "query";
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
