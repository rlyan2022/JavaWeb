<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>
<html>
<head>
	<link href="${ctx}/styles/admin/admin.css" type="text/css" rel="stylesheet">
	<title>${CompanyName}${ProjectName}</title>
	<%@ include file="/commons/meta.jsp" %>
</head>

<frameset rows="55,*" cols="*" frameborder="NO" border="0" framespacing="0" name="main">
	<frame src="${ctx}/admin/top.jsp" name="topFrame" scrolling="NO" noresize>
	<frameset cols="154,*" frameborder="NO" border="0" framespacing="0" name="setyou">
		<frame src="${ctx}/admin/menu.jsp" name="leftFrame" scrolling="NO" noresize>
		<frame src="${ctx}/admin/main.jsp" name="mainFrame" noresize>
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>