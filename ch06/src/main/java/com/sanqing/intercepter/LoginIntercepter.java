package com.sanqing.intercepter;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sanqing.po.User;

import java.util.Map;

public class LoginIntercepter extends AbstractInterceptor {
    private static final long serialVersionUID = 6203506362291764836L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();//获得ActionContext对象
        Map session = ctx.getSession();        //获得session对象
        User user = (User) session.get("user");//获得用户登录信息
        if (user != null) {    //如果不为空，则表示已经登录
            return invocation.invoke();//继续执行后面的操作
        }
        return "input";//跳转到登录页面
    }
}
