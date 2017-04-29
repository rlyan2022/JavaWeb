package com.sanqing.servlet;

import com.sanqing.bean.Employee;
import com.sanqing.dao.EmployeeDAO;
import com.sanqing.factory.EmployeeDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StatusRecognise extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext servletContext = getServletContext();    //获得ServletContex
        RequestDispatcher dispatcher = null;
        String employeeID = request.getParameter("employeeID");    //接受员工编号参数
        String password = request.getParameter("password");        //接受系统密码参数
        if (employeeID == null || "".equals(employeeID)) {        //判断是否输入员工编号
            request.setAttribute("error", "请输入员工编号!");
            dispatcher = servletContext.
                    getRequestDispatcher("/statusRecognise.jsp");//设置跳转页面
        } else {
            if (password == null || "".equals(password)) {        //判断是否输入系统密码
                request.setAttribute("error", "请输入系统口令!");
                dispatcher = servletContext.
                        getRequestDispatcher("/statusRecognise.jsp");//设置跳转页面
            } else {
                EmployeeDAO employeeDAO =
                        EmployeeDAOFactory.getEmployeeDAOInstance();//获得DAO实现类实例
                Employee employee = employeeDAO.
                        findEmployeeById(Integer.parseInt(employeeID));//查询员工
                if (employee == null) {
                    request.setAttribute("error", "该员工编号不存在!");
                    dispatcher = servletContext.
                            getRequestDispatcher("/statusRecognise.jsp");
                } else {
                    if (password.equals(employee.getPassword())) {
                        request.getSession().
                                setAttribute("employee", employee);//将员工信息保存到session范围
                        response.sendRedirect("index.jsp");
                        return;
                    } else {
                        request.setAttribute("error", "系统口令不正确!");
                        dispatcher = servletContext.
                                getRequestDispatcher("/statusRecognise.jsp");
                    }
                }
            }
        }
        dispatcher.forward(request, response);//进行跳转
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
