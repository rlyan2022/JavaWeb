

package com.servlet;

import com.dao.Anpai;
import com.test.JDBconne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class InterServlet extends HttpServlet {
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
        // 获得安排航班的航班号
        String hao = request.getParameter("param");
        Anpai ter = new Anpai();
        ter.setHao(hao);// 将航班号放入Session中用于在页面中显示航班号
        request.getSession().setAttribute("ter", ter);
        request.getRequestDispatcher("intercaTime.jsp").forward(request, response);


    }

}
