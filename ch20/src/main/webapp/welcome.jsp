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

<title><bean:message key="welcome.page.title" /></title>

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

<form name="form1" action="logout.do" method="post">
<table width="200" border="1">
	<tr>
		<td colspan="2"><bean:message key="welcome.page.title" /></td>
	<tr>
		<td><bean:message key="welcome.page.username" /></td>
		<td><%=(String) session.getAttribute("username")%></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" name="submit"
			value='<bean:message key="welcome.page.logout" />'></td>
	</tr>
</table>
</form>

</body>
</html>
