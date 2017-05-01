package com.sanqing.interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class UserInterceptor extends AbstractInterceptor {

    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext context = invocation.getInvocationContext();
        //获得session
        Map session = context.getContext().getSession();
        String username = (String) session.get("username");
        //判断用户合法性
        if (username == null || "".equals(username)) {
            //返回用户登陆页面
            return Action.LOGIN;
        } else {
            //进行下一步操作，没有拦截
            return invocation.invoke();
        }
    }

}
