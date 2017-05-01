package com.sanqing.servlet;

import com.sanqing.bean.Employee;
import com.sanqing.bean.Message;
import com.sanqing.dao.MessageDAO;
import com.sanqing.factory.MessageDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class MsgPublish extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");        //设置参数编码
        String title = request.getParameter("title");//获得用户输入的标题
        String content = request.getParameter("content");//获得用户输入的标题
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = null;
        //是哪个员工发布的消息
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (employee == null) {
            request.setAttribute("error", "要发布消息必须首先进行身份识别");
            dispatcher = servletContext.
                    getRequestDispatcher("/publishNewMsg.jsp");//跳转回消息发布页面
        } else {
            if (title == null || "".equals(title)) { //如果用户没有输入标题
                request.setAttribute("error", "必须输入消息标题");
                dispatcher = servletContext.
                        getRequestDispatcher("/publishNewMsg.jsp");//跳转回消息发布页面
            } else {
                Message message = new Message();//实例化Message对象
                message.setEmployeeID(employee.getEmployeeID());//设置发布人编号
                message.setMessageTitle(title);//设置消息标题
                message.setMessageContent(content);//设置标题内容
                message.setPublishTime(new Date());//设置发布日期
                MessageDAO messageDAO =
                        MessageDAOFactory.getMessageAOInstance();//获得MessageDAO实例
                messageDAO.addMessage(message);        //添加消息
                dispatcher = servletContext.
                        getRequestDispatcher("/GetMessageList");//跳转回消息发布页面
            }
        }
        dispatcher.forward(request, response);//进行页面跳转
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
