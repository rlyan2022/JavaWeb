package com.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private FilterConfig filterConfig = null;

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(this.filterConfig.getInitParameter("encoding"));
        response.setCharacterEncoding(this.filterConfig.getInitParameter("encoding"));
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

}
