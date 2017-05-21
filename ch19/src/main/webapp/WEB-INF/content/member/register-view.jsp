<%@ page language="java" pageEncoding="UTF-8"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
</head>

<body onload="initDate();">
	<%  
		response.sendRedirect("member.action"); 
	%>
</body>
</html>


<script type="text/javascript">
	function initDate() {
		alert("您已成功注册成为会员，请登录！");
	}
</script>
