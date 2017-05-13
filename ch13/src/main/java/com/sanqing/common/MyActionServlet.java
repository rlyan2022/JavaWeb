/**
 *
 */
package com.sanqing.common;

import org.apache.struts.action.ActionServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
public class MyActionServlet extends ActionServlet {

    /**
     *
     */
    public MyActionServlet() {
    }

    protected void process(HttpServletRequest request, HttpServletResponse response)
            throws java.io.IOException, javax.servlet.ServletException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        super.process(request, response);
    }

}
