

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

public class AffirmServlet extends HttpServlet {
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
        DinDao din = new DinDao();
        Sched sch = new Sched();
        //从JSP页面中获取所选择的航班号
        String hao = request.getParameter("hao");
        // check 查询错选择航班的信息
        sch = din.check(connection, hao);
        // 获得航班机票票数
        int value = sch.getPiaosu();
        // 在JSP页面中以下拉菜单进行选择票数
        String[] abc = new String[value];
        for (int i = 0; i < value; i++) {
            abc[i] = String.valueOf(i + 1);
        }
        request.getSession().setAttribute("abc", abc);
        request.getSession().setAttribute("sch", sch);
        // 获得该系统中该航班的票数,如果等于0 则不可售票
        if (sch.getPiaosu() == 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("affirmfailder.jsp");
            dispatcher.forward(request, response);

        }
        if (sch.getPiaosu() > 0)// 如果大于0 则可以售票
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("affirmflight.jsp");
            dispatcher.forward(request, response);

        }


    }

}
