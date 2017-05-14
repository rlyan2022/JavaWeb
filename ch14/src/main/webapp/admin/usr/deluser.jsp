<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="check.jsp"%>

<jsp:useBean id="user" class="com.sanqing.news.manage.Usr"/>
<%
	if(user.adminPurview(adminName)>1)
		servlet.responseUrl(response,"../../error/error.jsp");
%>
<body bgcolor="#799ae1">
<%
	//删除用户
	user.delUser(servlet.requestStr(request,"userName"));
	servlet.responseUrl(response,"user.jsp");
%>
</body>