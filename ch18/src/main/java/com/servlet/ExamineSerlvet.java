

package com.servlet;

import com.dao.DinDao;
import com.test.JDBconne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class ExamineSerlvet extends HttpServlet {

    private Connection connection;

    public void init() throws ServletException {
        super.init();
        //获得数据库连接
        connection = JDBconne.getConnetion();
    }

    public void destroy() {
        super.destroy();
        //关闭数据库连接
        JDBconne.closeConnection();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DinDao dinDao = new DinDao();
        String id = (String) request.getSession().getAttribute("username");
        // 获得客户用户名 查询用户订购的所有航班
        ArrayList ary = dinDao.quest(connection, id);
        request.getSession().setAttribute("ary", ary);
        request.getRequestDispatcher("examineshopping.jsp").forward(request, response);

    }

}
