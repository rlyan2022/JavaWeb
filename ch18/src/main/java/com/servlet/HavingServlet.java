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

public class HavingServlet extends HttpServlet {
    private Connection connection;

    public void init() throws ServletException {
        super.init();
        connection = JDBconne.getConnetion();
    }

    public void destroy() {
        super.destroy();
        JDBconne.closeConnection();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SchedDao sched = new SchedDao();
        ArrayList tes = new ArrayList();
        // quest 获得所有定制航班的信息
        tes = sched.quest(connection);
        //将所有航班信息放入Session中  用于在JSP页面中的下拉菜单中显示所有的航班号
        request.getSession().setAttribute("tes", tes);
        request.getRequestDispatcher("arrangTime.jsp").forward(request, response);


    }

}
