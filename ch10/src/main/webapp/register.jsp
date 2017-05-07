<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="<%=basePath%>">
		<title>用户注册</title>
		
		<!-- 导入Ext -->
		<link href="<%= path %>/ext/resources/css/ext-all.css" rel="stylesheet">
		<script type="text/javascript" src="<%= path %>/ext/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%= path %>/ext/ext-all.js"></script>
		<script type="text/javascript" src="<%= path %>/ext/build/locale/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%= path %>/register.js"></script>
		
		<!-- 导入DWR -->
		<script type="text/javascript" src="dwr/interface/RegisterAction.js"></script>
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<script>
		function callback(data){
			if(data==true)
			{
				{
					success:false;
					errors:{userName:'用户名不能为空'}
				}
			}
		}
		function valid(userName){
			RegisterAction.validUser(userName,callback);
		}
		</script>
	</head>

	<body>
	</body>
</html>
