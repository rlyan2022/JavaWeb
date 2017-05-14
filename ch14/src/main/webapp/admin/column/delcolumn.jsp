<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ include file="check.jsp" %>

<jsp:useBean id="Column" scope="page" class="com.sanqing.news.manage.Column"/>

<body bgcolor="#799ae1">
<%
	int classId = servlet.requestInt(request,"classId");
	//执行删除
	Column.delColumn(classId);
	servlet.responseUrl(response,"column.jsp");
%>
</body>