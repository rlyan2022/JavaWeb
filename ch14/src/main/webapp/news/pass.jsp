<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.sql.*"%>
<%@ page import = "com.sanqing.servlet.*"%>

<jsp:useBean id="SessionManager" class="com.sanqing.servlet.SessionManager"/>
<jsp:useBean id="userM" scope="page" class="com.sanqing.news.UserManage"/>
<jsp:useBean id="newsU" scope="page" class="com.sanqing.persistence.NEWSUsr"/>
<jsp:setProperty name="newsU" property="*"/>

<%
	/**
	out.print(newsU.getUserName());
	out.print(newsU.getPassWd());
	out.print(newsU.getPwdAgain());
	out.print(newsU.getSex());
	out.print(newsU.getQuestion());
	out.print(newsU.getAnswer());
	out.print(newsU.getEmailAddr());
	out.print(newsU.getQq());
	out.print(newsU.getHttp());
	*/
	if(!userM.isUser(newsU.getUserName())){
			userM.RegUser(newsU.getUserName(),newsU.getPassWd(),newsU.getSex(),newsU.getQuestion(),newsU.getAnswer(),newsU.getEmailAddr(),newsU.getQq(),newsU.getHttp());
			SessionManager.setSession(session,"sessionuser",newsU.getUserName());
			DOServlet.responseUrl(response,"personal.jsp");
	}
	else{
		out.println("<Script language=JavaScript>alert('此用户已经被别人注册，请重新填过!');JavaScript:history.back();</Script>");
	}
%>