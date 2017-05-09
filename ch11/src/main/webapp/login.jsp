<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    <title>用户登录</title>
  </head>
  <body>
  <div align="center">
  <s:form action="login.action" method="post" validate="true">
    <h3 align="center">登录</h3>
  	<s:textfield name="user.userName" label="用户名"/>
    <s:password name="user.password" label="密码"/>
  	<s:submit label="登录" align="center"/>
  </s:form>
  </div>
  </body>
</html>
