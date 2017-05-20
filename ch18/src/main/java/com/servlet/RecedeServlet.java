

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
import java.sql.Connection;

public class RecedeServlet extends HttpServlet {
    Connection connection;

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
        Sched reced = new Sched();
        DinDao din = new DinDao();
        //获得用户所选择的航班号
        reced = (Sched) request.getSession().getAttribute("sed");
        // 获得下拉菜单中用户所选择退票的数量
        int rec = Integer.parseInt(request.getParameter("piao"));
        // 获得该用户的用户名
        String id = (String) request.getSession().getAttribute("username");
        // amendReced 修改用户在该航班上的所订购的票数
        int value = din.amendReced(connection, reced, rec, id);
        if (value > 0) {
            // 如果退票成功
            // Upreced 修改该航班的票数
            int wat = din.Upreced(connection, reced, rec);
            if (wat > 0) {    //修改成功
                RequestDispatcher dispatcher = request.getRequestDispatcher("ExamineSerlvet");
                dispatcher.forward(request, response);
            } else {
                //修改失败
                RequestDispatcher dispatcher = request.getRequestDispatcher("recedfaild.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            //退票失败
            RequestDispatcher dispatcher = request.getRequestDispatcher("recedfaild.jsp");
            dispatcher.forward(request, response);
        }


    }
}
