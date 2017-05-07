package com.sanqing.struts.action;

import com.sanqing.po.SysUser;
import com.sanqing.service.LoginService;
import com.sanqing.service.RightService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

public class LoginAction extends DispatchAction {
    private LoginService loginService = null;
    private RightService rightService = null;

    public RightService getRightService() {
        return rightService;
    }

    public void setRightService(RightService rightService) {
        this.rightService = rightService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    // 用户登录
    public ActionForward doLogin(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("登录系统");
        HttpSession session = request.getSession();
        // session.setMaxInactiveInterval(30);
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        try {
            List<SysUser> users = loginService.validUser(userName);
            if (users.isEmpty()) {
                out.print("{success:false,msg:'此用户不存在'}");
            } else {
                for (int i = 0; i < users.size(); i++) {
                    if (password.equals(users.get(i).getUsrPassword())) {
                        System.out.println("登录成功");
                        out.print("{success:true,msg:'登录成功'}");

                        Long userId = users.get(i).getUsrId();
                        Long roleId = users.get(i).getSysRole().getRoleId();
                        System.out.println("角色编号：" + roleId);
                        // 把用户的名称、角色名称还有编号放入到session中
                        session.setAttribute("userId", userId);
                        session.setAttribute("userName", userName);
                        session.setAttribute("roleId", roleId);
                        // 用户成功登录之后把他的权限(即可以访问的地址)放入到Session中
                        String[] rightUrl = rightService.findRight(users.get(i)
                                .getSysRole().getRoleId());
                        session.setAttribute("rightUrl", rightUrl);
                        for (int j = 0; j < rightUrl.length; j++) {
                            System.out.println("该用户可以访问的地址有："
                                    + rightUrl[j].toString());
                        }
                    } else {
                        out.print("{success:false,msg:'登录失败,密码不正确'}");
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{success:false,msg:'系统异常,登录失败'}");
        }
        return null;
    }

    // 验证用户旧密码
    // public ActionForward doValidPwd(ActionMapping mapping, ActionForm form,
    // HttpServletRequest request, HttpServletResponse response)
    // throws Exception {
    // PrintWriter out = response.getWriter();
    // // 从session中取出当前用户的信息以修改密码
    // // SysUser user = loginService.findById(userId);
    // // 获得用户输入的旧密码，并判断是否与数据的原密码相等
    // String oldPwd = request.getParameter("oldPwd");
    // // if (!user.getUsrPassword().equals(oldPwd)) {
    // out.print("输入的旧密码不正确");
    // }
    // return null;
    // }

    // 修改用户密码
    public ActionForward doUpdatePwd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("修改用户密码");
        PrintWriter out = response.getWriter();
        // 从session中取出当前用户的信息以修改密码
        HttpSession sess = request.getSession();
        Long userId = (Long) sess.getAttribute("userId");
        System.out.println("用户编号：" + userId);
        SysUser user = loginService.findById(userId);

        // 获得用户输入的旧密码，并判断是否与数据的原密码相等
        String oldPwd = request.getParameter("oldPwd");
        if (!user.getUsrPassword().equals(oldPwd)) {
            out.print("{success:false,msg:'输入的旧密码不正确'}");
        } else {
            try {
                user.setUsrPassword(request.getParameter("newPwd"));
                loginService.updatePwd(user);
                out.print("{success:true,msg:'修改成功'}");
            } catch (Exception e) {
                e.printStackTrace();
                out.print("{success:false,msg:'系统异常,修改失败'}");
            }
        }

        return null;
    }

}