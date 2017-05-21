<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><bean:message key="login.page.title" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>

<body>

<form name="form1" action="login.do" method="post">
<table width="300" border="1">
	<tr>
		<td colspan="2"><bean:message key="login.page.title" /></td>
	</tr>
	<tr>
		<td><bean:message key="login.page.username" /></td>
		<td><logic:present name="loginFormBean">
			<html:text property="username" name="loginFormBean" />
		</logic:present> <logic:notPresent name="loginFormBean">
			<input type="text" name="username">
		</logic:notPresent> <html:errors property="username" /></td>
	</tr>
	<tr>
		<td><bean:message key="login.page.password" /></td>
		<td><logic:present name="loginFormBean">
			<html:password property="password" name="loginFormBean" />
		</logic:present> <logic:notPresent name="loginFormBean">
			<input type="password" name="password">
		</logic:notPresent> <html:errors property="password" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" name="submit"
			value='<bean:message key="login.page.login" />'> <a
			href="register.do?method=init"><bean:message
			key="login.page.register" /></a></td>
	</tr>
</table>
<html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" /></form>

</body>
</html>
