package com.sanqing.course.web;

/**
 *
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class UserLoginFilter implements Filter {


    /**
     * 配置允许的角色
     */
    //private String allowRole = null;

    /**
     * 重定向的URL
     */
    private String redirectURl = null;

    public void init(FilterConfig filterConfig) throws ServletException {

        //得到允许的角色,这个参数是由web.xml里的allowRole所指定
        //allowRole = filterConfig.getInitParameter("allowRole");

        //指定要重定向的页面
        redirectURl = "logon.jsp";
    }

    /**
     * 在过滤器中实现权限控制
     */
    public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) sRequest;
        HttpServletResponse response = (HttpServletResponse) sResponse;
        HttpSession session = request.getSession();

        String p = request.getParameter("p");

        // 如果回话中的用户为空,页面重新定向到登录页面
        if(session.getAttribute("logon") == null) {
            if(p.equals("logon")) {
                filterChain.doFilter(sRequest, sResponse);
            } else {
                response.sendRedirect(redirectURl);
            }
        } else {
            filterChain.doFilter(sRequest, sResponse);
        }
    }

    public void destroy() {
    }
}
