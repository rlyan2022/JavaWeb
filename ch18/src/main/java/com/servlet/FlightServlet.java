package com.servlet;

import com.test.JDBconne;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class FlightServlet extends HttpServlet {
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
        // 制定航班号 以下拉菜单的形式表示
        String[] a = new String[100];
        for (int i = 0, j = 100; i < 100; i++, j++) {
            a[i] = "T" + String.valueOf(j + 1);
        }
        // 制定航班飞行时间  以下拉菜单的形式表示
        String[] b = new String[24];
        for (int i = 0; i < 24; i++) {
            b[i] = String.valueOf(i + 1);
        }
        // 制定航班价格 以下拉菜单的形式表示
        String[] c = new String[100];
        for (int i = 0; i < 100; i++) {
            c[i] = String.valueOf(i + 1) + "0";
        }
        // 制定航班票数 以下拉菜单的形式表示
        String[] d = new String[200];
        for (int i = 0; i < 200; i++) {
            d[i] = String.valueOf(i + 1);
        }
        request.getSession().setAttribute("a", a);
        request.getSession().setAttribute("b", b);
        request.getSession().setAttribute("c", c);
        request.getSession().setAttribute("d", d);
        // request.getSession().setAttribute("flight",flight);
        request.getRequestDispatcher("estaflight.jsp").forward(request, response);


    }

}
