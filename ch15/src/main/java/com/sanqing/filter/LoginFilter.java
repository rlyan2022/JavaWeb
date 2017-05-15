package com.sanqing.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        //如果用户请求了index.html，这时就必须做登录判断，判断用户是否登录。
        if ("/Pfms/index.html".equals(uri)) {
            if (username == null || "".equals(username)) {
                response.sendRedirect("login.html");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

}
