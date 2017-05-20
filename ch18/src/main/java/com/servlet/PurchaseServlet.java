

package com.servlet;

import com.dao.DinDao;
import com.dao.Sched;
import com.test.JDBconne;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

public class PurchaseServlet extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        DinDao din = new DinDao();
        // 获得航班信息
        Sched sch = (Sched) request.getSession().getAttribute("sch");
        // 获得用户所订票数
        int bal = Integer.parseInt(request.getParameter("piao"));
        // 获得用户名
        String id = (String) request.getSession().getAttribute("username");
        // inset 插入用户所购买航班票的信息
        int value = din.inset(connection, sch, bal, id);
        if (value > 0) //判断插入是否成功
        {
            // 插入成功  update修改该航班的票数
            int wat = din.update(connection, sch, bal);
            if (wat > 0) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("ExamineSerlvet");
                dispatcher.forward(request, response);
            } else {

                RequestDispatcher dispatcher = request.getRequestDispatcher("affirmfaild.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // 插入失败
            RequestDispatcher dispatcher = request.getRequestDispatcher("affirmfaild.jsp");
            dispatcher.forward(request, response);
        }


    }

}
