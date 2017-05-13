<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
 
<html> 
	<head>
		<title>JSP for UserRoleEditorForm form</title>
	</head>
	<body>
		<html:form action="/userRoleEditor">
		
			allroles : <html:select property="allroles"/><html:errors property="allroles"/><br/>
			userroles : <html:select property="userroles"/><html:errors property="userroles"/><br/>
			<html:submit/><html:cancel/>
		</html:form>
	</body>
</html>

