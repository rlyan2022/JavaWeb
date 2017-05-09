<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
  <head>
    <title>用户注册</title>
  </head>
  <body>
   <h3 align="center">注册</h3>
  <p align="center">
  <s:form action="register.action" method="post" validate="true">
  	<s:textfield name="user.userName" label="用户名"/>
    <s:password name="user.password" label="密码"/>
  	<s:submit label="注册" align="center"/>
  </s:form>
  </p>
  </body>
</html>
