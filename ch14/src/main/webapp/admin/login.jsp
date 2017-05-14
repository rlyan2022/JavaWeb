<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import = "java.io.*"%>
<%@ page import = "java.sql.*"%>
<%@ page import = "com.sanqing.servlet.*"%>
<%@ page import = "com.sanqing.news.manage.*"%>

<jsp:useBean id="syslogin" scope="page" class="com.sanqing.persistence.NEWSAdmin"/>
<jsp:setProperty name="syslogin" property="*"/>
<jsp:useBean id="isIn" class="com.sanqing.news.manage.ISLogin"/>
<jsp:useBean id="SessionManager" class="com.sanqing.servlet.SessionManager"/>

<body bgcolor="#799ae1">
<%
	if(isIn.isUsernameOk(syslogin.getUserName())){//用户名判断
		if(isIn.isPasswordOk(syslogin.getUserName(),syslogin.getPassWd())){//密码判断
			SessionManager.setSession(session,"user",syslogin.getUserName());
			isIn.upTimeAndIp(request.getRemoteAddr());
			DOServlet.responseUrl(response,"default.jsp");
		}
		else{
			out.println("<Script language=JavaScript>alert('请输入正确的密码!');JavaScript:history.back();</Script>");
		}
	}
	else{
		out.println("<Script language=JavaScript>alert('请输入正确的用户名!');JavaScript:history.back();</Script>");
	}
%>
</body>