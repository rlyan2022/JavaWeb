package com.servlet;

import com.dao.Tocom;
import com.dao.User;
import com.dao.UserDao;
import com.test.JDBconne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class NoparamServlet extends HttpServlet {
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
        String param = request.getParameter("param");
        if (param.equalsIgnoreCase("1")) {

            User user = new User();
            user.setUsername(request.getParameter("Username"));
            user.setName(Tocom.toCN(request.getParameter("Name")));
            user.setSex(Tocom.toCN(request.getParameter("Sex")));
            user.setTel(request.getParameter("Tel"));
            user.setEmail(request.getParameter("Email"));
            UserDao userDao = new UserDao();
            // Update 根据用户输入的信息 进行数据库的修改
            int value = userDao.Update(connection, user);
            if (value > 0) {
                request.getSession().setAttribute("user", user);
                request.getRequestDispatcher("infoblest.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("infofaild.jsp").forward(request, response);
            }


        }

    }

}
