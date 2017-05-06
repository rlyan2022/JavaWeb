<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.*"%>

<%
	UserInfo checkMLogin = (UserInfo)session.getAttribute("mUsers");
	if(checkMLogin == null){
		response.sendRedirect("err.htm");
		return;
	}
 %>
