package com.sanqing.intercepter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sanqing.po.Admin;

import java.util.Map;

public class LoginIntercepter extends AbstractInterceptor {
    private static final long serialVersionUID = 6203506362291764836L;

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        ActionContext ctx = invocation.getInvocationContext();
        Map session = ctx.getSession();
        Admin user = (Admin) session.get("admin");
        if (user != null) {
            return invocation.invoke();
        }
        return "input";
    }

}
