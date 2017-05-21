<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<html:html locale = "true"/> 
<%String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><bean:message key="meeting_edit.page.title" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="styles.css">
</head>

<body>
<TABLE width="100%" class="position">
	<TR>
		<TD><bean:message key="meeting_edit.page.position" /></TD>
		<TD align="right"><a href="meeting.do?method=back"><bean:message
			key="meeting_edit.page.back" /></a></TD>
		<TD width="20"></TD>
	</TR>
</TABLE>
<form name="form1" action="meeting_edit.do?method=update" method="post">
<b><html:errors property="org.apache.struts.action.GLOBAL_MESSAGE" /></b>

<logic:present name="meetingFormBean">
	<html:hidden property="id" name="meetingFormBean"/>
</logic:present>
<logic:notPresent name="meetingFormBean">
	<input type="hidden" name="id">
</logic:notPresent>
		
<TABLE border="0" width="100%">
	<TR>
		<TD><bean:message key="meeting.page.sender" /></TD>
		<TD><logic:present name="meetingFormBean">
			<html:text property="sender" name="meetingFormBean" maxlength="4" readonly="true"/>
		</logic:present> <logic:notPresent name="meetingFormBean">
			<input type="text" name="sender" maxlength="4" readonly>
		</logic:notPresent></TD>
	</TR>
	<TR>
		<TD><bean:message key="meeting.page.starttime" /></TD>
		<TD><logic:present name="meetingFormBean">
			<html:text property="starttime" name="meetingFormBean" maxlength="100"/>
		</logic:present> <logic:notPresent name="meetingFormBean">
			<input type="text" name="starttime" maxlength="100">
		</logic:notPresent> <html:errors property="starttime" /></TD>
	</TR>
	<TR>
		<TD><bean:message key="meeting.page.endtime" /></TD>
		<TD><logic:present name="meetingFormBean">
			<html:text property="endtime" name="meetingFormBean" maxlength="100"/>
		</logic:present> <logic:notPresent name="meetingFormBean">
			<input type="text" name="endtime" maxlength="100">
		</logic:notPresent> <html:errors property="endtime" /></TD>
	</TR>
	<TR>
		<TD><bean:message key="meeting.page.address" /></TD>
		<TD><logic:present name="meetingFormBean">
			<html:text property="address" name="meetingFormBean" maxlength="100"/>
		</logic:present> <logic:notPresent name="meetingFormBean">
			<input type="text" name="address" maxlength="100">
		</logic:notPresent> <html:errors property="address" /></TD>
	</TR>
	<TR>
		<TD><bean:message key="meeting.page.title" /></TD>
		<TD><logic:present name="meetingFormBean">
			<html:text property="title" name="meetingFormBean" maxlength="100"/>
		</logic:present> <logic:notPresent name="meetingFormBean">
			<input type="text" name="title" maxlength="100">
		</logic:notPresent> <html:errors property="title" /></TD>
	</TR>
	<TR>
		<TD><bean:message key="meeting.page.content" /></TD>
		<TD><textarea name="content" cols="60" rows="15"><logic:present name="meetingFormBean"><bean:write property="content" name="meetingFormBean" /></logic:present></textarea>  <html:errors property="content" /></TD>
	</TR>
	<TR>
		<TD colspan="2"><input type="submit"
			value="<bean:message key='button.submit' />"></TD>
	</TR>
</TABLE>
</form>
</body>
</html>
