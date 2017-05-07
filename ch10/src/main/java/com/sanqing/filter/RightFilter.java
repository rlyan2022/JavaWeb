package com.sanqing.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RightFilter extends HttpServlet implements Filter {
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
            PrintWriter out = response.getWriter();
            HttpSession sess = request.getSession();
            // 从Session取出用户可以访问的地址
            String[] rightUrl = (String[]) sess.getAttribute("rightUrl");
            System.out.println("执行用户权限过滤");
            String page = request.getServletPath();
            System.out.println("你当前访问的路径为:" + page);
            if ("/login.do".equals(page) || ("/myRight.do").equals(page)) {
                filterChain.doFilter(request, response);
                return;
            }
            boolean isCall = false;
            for (int i = 0; i < rightUrl.length; i++) {
                if (page.equals(rightUrl[i])) {
                    isCall = true;
                    break;
                } else {
                    isCall = false;
                }
            }
            if (isCall == true) {
                System.out.println("允许访问");
                filterChain.doFilter(request, response);
            } else {
                System.out.println("无权访问");
                out.print("对不起,您无权访问");
            }

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
