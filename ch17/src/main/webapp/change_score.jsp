<%@ page language="java" import="com.sanqing.course.util.*, com.sanqing.course.model.*" %>
<%
String id = request.getParameter("id");
Float score = Float.parseFloat(request.getParameter("score"));

Mark mark = Functions.getMarkById(id);

mark.setScore(score);

Functions.updateMark(mark);

response.setContentType("text/html;charset=UTF-8");
response.setHeader("Cache-Control", "no-store"); //HTTP1.1
response.setHeader("Pragma", "no-cache"); //HTTP1.0
response.setDateHeader("Expires", 0); //prevents catching at proxy server

%>
