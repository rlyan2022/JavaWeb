<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entity.*"%>

<%
	UserInfo checkLogin = (UserInfo)session.getAttribute("users");
	if(checkLogin == null){
		response.sendRedirect("login.jsp");
	}
 %>
