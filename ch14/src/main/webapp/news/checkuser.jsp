<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.sql.*"%>
<%@ page import = "com.sanqing.servlet.*"%>
<%@ page import = "com.sanqing.news.manage.*"%>

<jsp:useBean id="syslogin" scope="page" class="com.sanqing.persistence.NEWSUsr"/>
<jsp:setProperty name="syslogin" property="*"/>
<jsp:useBean id="userM" class="com.sanqing.news.UserManage"/>
<jsp:useBean id="sessionManager" class="com.sanqing.servlet.SessionManager"/>
<body bgcolor="#799ae1">
<%
	if(userM.isUsernameOk(syslogin.getUserName())){//用户名判断
		if(userM.isPasswordOk(syslogin.getPassWd())){//密码判断
			userM.upLoadTime(syslogin.getUserName());
			sessionManager.setSession(session,"sessionuser",syslogin.getUserName());
			DOServlet.responseUrl(response,"personal.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('请输入正确的密码!');JavaScript:history.back();</Script>");
		}
	}
	else{
		out.println("<Script language=JavaScript>alert('请输入正确的用户名!');JavaScript:history.back();</Script>");
	}
%>
