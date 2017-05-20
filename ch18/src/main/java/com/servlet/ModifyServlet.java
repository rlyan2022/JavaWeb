

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

public class ModifyServlet extends HttpServlet {
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
        if (param.equalsIgnoreCase("0")) {
            String username = (String) request.getSession().getAttribute("username");
            UserDao userDao = new UserDao();
            // 利用username 进行数据库的查询
            // 将返回的用户信息放入User对象中
            User user = userDao.getUser(connection, username);
            String ho = user.getSex();
            String mo;
            if (ho.equals("男")) {
                mo = "女";
            } else {
                mo = "男";
            }
            // 将性别放入session 中
            request.getSession().setAttribute("ho", ho);
            request.getSession().setAttribute("mo", mo);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("info.jsp").forward(request, response);
        }


    }

}
