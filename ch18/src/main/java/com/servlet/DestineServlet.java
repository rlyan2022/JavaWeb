

package com.servlet;

import com.dao.SchedDao;
import com.test.JDBconne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class DestineServlet extends HttpServlet {
    private Connection connection;

    public void init() throws ServletException {
        super.init();
        connection = JDBconne.getConnetion();
    }

    public void destroy() {
        super.destroy();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String param = request.getParameter("param");

        SchedDao schedDao = new SchedDao();

        if (param.equalsIgnoreCase("1")) {
            //descry 获得有效航班的信息 将返回信息放入ArrayList动态数组中
            // ArrayList动态数组可以在JSP页面中用c:forEach 循环进行打印
            ArrayList sched = schedDao.descry(connection);
            request.getSession().setAttribute("sched", sched);
            request.getRequestDispatcher("destineflight.jsp").forward(request, response);
        } else if (param.equalsIgnoreCase("2")) {
            // quest 获得所有制定航班的信息
            ArrayList sched = schedDao.quest(connection);
            request.getSession().setAttribute("sched", sched);
            request.getRequestDispatcher("examFlight.jsp").forward(request, response);
        }


    }

}
