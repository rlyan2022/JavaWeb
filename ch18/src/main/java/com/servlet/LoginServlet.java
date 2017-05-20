

package com.servlet;

import com.dao.UserDao;
import com.test.JDBconne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


public class LoginServlet extends HttpServlet {
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
        //获得界面中输入数据
        String username = request.getParameter("Username");
        String password = request.getParameter("Password");

        UserDao userDao = new UserDao();
        //check方法  进行数据库比较 判断有没该用户
        boolean value = userDao.check(connection, username, password);
        if (value) {
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("userMain.jsp").forward(request, response);
        } else {

            request.getRequestDispatcher("loginfaild.jsp").forward(request, response);
        }


    }

}
