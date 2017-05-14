<%@ page import = "com.sanqing.servlet.*"%>
<jsp:useBean id="sessionManager" class="com.sanqing.servlet.SessionManager"/>
<jsp:useBean id="servlet" scope="page" class="com.sanqing.servlet.DOServlet"/>
<jsp:useBean id="usr" class="com.sanqing.news.manage.Usr"/>
<%
	String adminName=sessionManager.getSession(session,"user");
	int purview = usr.adminPurview(adminName);
	if(adminName==null || purview>1)
	servlet.responseUrl(response,"../../error/error.jsp");
%>