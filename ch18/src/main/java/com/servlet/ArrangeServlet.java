

package com.servlet;

import com.dao.Anpai;
import com.dao.DinDao;
import com.dao.SchedDao;
import com.test.JDBconne;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;

public class ArrangeServlet extends HttpServlet {
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
        Anpai anpai = new Anpai();
        // 获得管理员所选择的航班号
        anpai.setHao(request.getParameter("Hao"));
        DinDao din = new DinDao();
        // getDate 获得客户端的当前日期时间
        Date date = din.getDate(connection);
        // 从页面中获取管理员安排的日期时间 并以Date类型储存
        Date Rqi = Date.valueOf(request.getParameter("user"));
        anpai.setRqi(Rqi);
        SchedDao schedDao = new SchedDao();
        // check 将获得的日期放入对应的航班信息表中
        int value = schedDao.check(connection, anpai);
        if (value > 0) {

            RequestDispatcher dispatcher = request.getRequestDispatcher("arrangBlest.jsp");
            dispatcher.forward(request, response);
        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("arrangFaild.jsp");
            dispatcher.forward(request, response);
        }


    }

}
