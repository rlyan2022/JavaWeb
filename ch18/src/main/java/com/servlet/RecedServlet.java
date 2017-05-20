

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

public class RecedServlet extends HttpServlet {
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获得用户所要退的航班号
        String hao = request.getParameter("hao");
        DinDao dinDao = new DinDao();
        Sched sed = new Sched();
        String id = (String) request.getSession().getAttribute("username");
        // 获得用户所要退票的航班的信息
        sed = dinDao.reced(connection, hao, id);
        //compareDate 判断航班日期是否在服务器日期之后
        boolean j = dinDao.compareDate(sed.getRqi());

        if (j == true) {
            //如果航班日期在服务器之后则可以退票
            int value = sed.getPiaosu();
            String[] abc = new String[value];
            for (int i = 0; i < value; i++) {
                abc[i] = String.valueOf(i + 1);
            }
            request.getSession().setAttribute("id", id);
            request.getSession().setAttribute("abc", abc);
            request.getSession().setAttribute("sed", sed);
            RequestDispatcher dispatcher = request.getRequestDispatcher("recedeticket.jsp");
            dispatcher.forward(request, response);
        }
        if (j == false) {
            //如果服务器时间超过航班日期时间 则不能进行退票操作
            RequestDispatcher dispatcher = request.getRequestDispatcher("recedfaild.jsp");
            dispatcher.forward(request, response);
        }


    }

}
