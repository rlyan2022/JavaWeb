package com.sanqing.servlet;

import com.sanqing.bean.Message;
import com.sanqing.dao.MessageDAO;
import com.sanqing.factory.MessageDAOFactory;
import com.sanqing.util.Page;
import com.sanqing.util.PageUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetMessageList extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int currentPage = 0;
        String currentPageStr = request.getParameter("currentPage");
        if (currentPageStr == null || "".equals(currentPageStr)) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPageStr);
        }

        MessageDAO messageDAO = MessageDAOFactory.getMessageAOInstance();
        Page page = PageUtil.createPage(5, messageDAO.findAllCount(), currentPage);
        List<Message> messages = messageDAO.findAllMessagee(page);
        request.setAttribute("messageList", messages);
        request.setAttribute("page", page);
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.
                getRequestDispatcher("/msgList.jsp");    //跳转到消息列表页
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
