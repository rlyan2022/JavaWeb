package com.servlet;

import com.dao.Tocom;
import com.dao.User;
import com.dao.UserDao;
import com.test.JDBconne;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class NewUser extends HttpServlet {
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

        User user = new User();
        user.setUsername(request.getParameter("Username"));
        user.setPassword(request.getParameter("Password"));
        user.setName(Tocom.toCN(request.getParameter("Name")));
        String Sex = request.getParameter("Sex");
        if (Sex.equals("1")) {
            Sex = "男";
        } else {
            Sex = "女";
        }
        user.setSex(Sex);
        user.setTel(request.getParameter("Tel"));
        user.setEmail(request.getParameter("Email"));
        UserDao userDao = new UserDao();
        // insert方法向数据库插入用户信息
        int value = userDao.insert(connection, user);
        if (value > 0) {
            // 注册成功
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerblest.jsp");
            dispatcher.forward(request, response);
        } else {
            // 注册失败
            RequestDispatcher dispatcher = request.getRequestDispatcher("registerfaild.jsp");
            dispatcher.forward(request, response);
        }


    }

}