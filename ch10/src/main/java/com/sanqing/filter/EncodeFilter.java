package com.sanqing.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

public class EncodeFilter extends HttpServlet implements Filter {
    private FilterConfig filterConfig;
    String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
        this.filterConfig = filterConfig;
    }

    // Process the request/response pair
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) {
        try {
            request.setCharacterEncoding(encoding);
            response.setContentType("text/html;charset=UTF-8");
            filterChain.doFilter(request, response);
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
