package com.sanqing.servlet;

import com.sanqing.bean.Criticism;
import com.sanqing.bean.Message;
import com.sanqing.bean.Reply;
import com.sanqing.dao.CriticismDAO;
import com.sanqing.dao.MessageDAO;
import com.sanqing.dao.ReplyDAO;
import com.sanqing.factory.CriticismDAOFactory;
import com.sanqing.factory.MessageDAOFactory;
import com.sanqing.factory.ReplyDAOFactory;
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

public class GetMessage extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int messageID = Integer.
                parseInt(request.getParameter("messageID"));//取得消息编号
        MessageDAO messageDAO
                = MessageDAOFactory.getMessageAOInstance();//获得MessageDAO实例
        Message message =
                messageDAO.findMessageById(messageID);//查询该编号对应的消息
        request.setAttribute("message", message); //将消息保存在request范围

        //获得该消息对应的所有回复
        int currentPage = 0;
        String currentPageStr = request.
                getParameter("currentPage");//获得消息回复列表的当前页信息
        if (currentPageStr == null || "".equals(currentPageStr)) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPageStr);
        }
        ReplyDAO replayDAO = ReplyDAOFactory.getReplyDAOInstance();
        Page page = PageUtil.createPage(5, replayDAO.
                findCountByMsgID(messageID), currentPage);//设置分页信息
        List<Reply> replys = replayDAO.
                findReplayByMsgID(messageID, page);//分页查询消息回复
        request.setAttribute("replyList", replys);//保存消息回复列表
        request.setAttribute("page", page);//保存分页信息

        //获得该消息对象的批复
        CriticismDAO criticismDAO = CriticismDAOFactory.getCriticismDAOInstance();
        Criticism criticism = criticismDAO.findCriticismByMsgID(messageID);
        request.setAttribute("criticism", criticism);

        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = servletContext.
                getRequestDispatcher("/showMsg.jsp");    //跳转到消息显示页
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
