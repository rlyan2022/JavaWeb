

package com.servlet;

import com.dao.User;
import com.dao.UserDao;
import com.test.JDBconne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class PassServlet extends HttpServlet {
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


        // 从Session 中获得该客户的用户名
        String username = (String) request.getSession().getAttribute("username");
        UserDao userDao = new UserDao();
        //获得用户注册信息
        User user = userDao.getUser(connection, username);
        String pass = user.getPassword();
        String passed = request.getParameter("Password");
        String pass1 = request.getParameter("Password1");
        // 根据界面输入的密码与用户的密码进行比较
        if (pass.equals(passed)) {
            //如果两者值相等
            //UpdatePass 进行密码修改操作
            int value = userDao.UpdatePass(connection, pass1, username);
            if (value > 0) {
                request.getRequestDispatcher("amendsucceed.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("amendfail.jsp").forward(request, response);
            }

        } else {
            //如果密码不相等
            request.getRequestDispatcher("amenderror.jsp").forward(request, response);
        }

    }
}
