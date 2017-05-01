package com.sanqing.servlet;

import com.sanqing.bean.Employee;
import com.sanqing.bean.Reply;
import com.sanqing.dao.ReplyDAO;
import com.sanqing.factory.ReplyDAOFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CommitReply extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");//设置参数编码格式
        String replyContent = request.
                getParameter("replyContent");//获得输入的回复内容
        int messageID = Integer.parseInt(
                request.getParameter("messageID"));//获得消息编号
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = null;
        //是哪个员工发布的回复
        Employee employee = (Employee) request.getSession().getAttribute("employee");
        if (employee == null) {
            request.setAttribute("error", "要进行回复必须首先进行身份识别");

        } else {
            if (replyContent == null || "".equals(replyContent)) { //如果用户没有输入标题
                request.setAttribute("error", "必须输入回复内容");
            } else {
                Reply replay = new Reply();
                replay.setReplyContent(replyContent);//设置回复内容
                replay.setMessageID(messageID);        //设置消息编号
                replay.setEmployeeID(employee.getEmployeeID());//设置回复人
                replay.setReplyTime(new Date());    //设置回复时间
                ReplyDAO replayDAO = ReplyDAOFactory.getReplyDAOInstance();
                replayDAO.addReplay(replay);        //完成消息回复的添加
            }
        }
        dispatcher = servletContext.getRequestDispatcher(
                "/GetMessage?messageID=" + messageID);//跳转回消息查看页
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
