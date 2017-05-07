package com.sanqing.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter extends HttpServlet implements Filter {
    private FilterConfig filterConfig;

    // Handle the passed-in FilterConfig
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    // Process the request/response pair
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain filterChain) {
        try {
            HttpServletRequest request = (HttpServletRequest) req;
            HttpServletResponse response = (HttpServletResponse) resp;
            HttpSession sess = request.getSession();

            String page = request.getServletPath();
            if (!"/index.jsp".equals(page) && !"/image.jsp".equals(page)) {
                // 当前访问的不是登录页面
                // 有没有登录过
                if (sess.getAttribute("userName") == null) {
                    // 没有登录
                    response.sendRedirect("index.jsp");
                }
            }
            filterChain.doFilter(req, resp);
        } catch (ServletException sx) {
            filterConfig.getServletContext().log(sx.getMessage());
        } catch (IOException iox) {
            filterConfig.getServletContext().log(iox.getMessage());
        }
    }

    // Clean up resources
    public void destroy() {
    }
}
