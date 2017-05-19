<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<html>
<head>
	<%@ include file="/commons/meta.jsp" %>
	<link href="${ctx}/styles/admin/admin.css" type="text/css" rel="stylesheet">
</head>

<body>
<div id="menu" style="margin-top:10px;">
	<ul>
	<logic:present name="admin" scope="session">
		<li>
			<a href="${ctx}/user.do?p=list&pageNo=1&flag=true" target="mainFrame">用户管理</a>
		</li>
		<li>
			<a href="${ctx}/team.do?p=list&pageNo=1&flag=true" target="mainFrame">班级管理</a>
		</li>
		<li>
			<a href="${ctx}/course.do?p=list&pageNo=1&flag=true" target="mainFrame">课程管理</a>
		</li>
		<li>
			<a href="${ctx}/teacher.do?p=list&pageNo=1&flag=true" target="mainFrame">教师管理</a>
		</li>
		<li>
			<a href="${ctx}/student.do?p=list&pageNo=1&flag=true" target="mainFrame">学生管理</a>
		</li>
		<li>
			<a href="${ctx}/mark.do?p=list&pageNo=1&flag=true" target="mainFrame">成绩管理</a>
		</li>
		</logic:present>

		<logic:present name="teacher" scope="session">
			<li>
				<a href="${ctx}/courseSchedule.do?p=input" target="mainFrame">成绩录入</a>
			</li>
		</logic:present>

		<li>
			<a href="${ctx}/user.do?p=logoff" target="_parent">登录/退出系统</a>
		</li>
		

	</ul>
</div>
</body>
</html>