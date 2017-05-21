<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订餐系统后台管理</title>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />	
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
<style>
html,body{margin:0; padding:0; position:relative; font-family:"微软雅黑", "黑体", "宋体"; color:#1577ca; font-size:12px;}
body{ background:#ffffff url(images/bg_admin_login_body.jpg) repeat-x;}
.admin_login_top{width:893px; height:268px; margin:0 auto; background-image:url(images/bg_admin_login_wrapage.jpg); position:relative;}
#btn_submit{cursor:pointer;}
#random{cursor:pointer;}
</style>
<%@ include file="/common/meta.jsp"%>

	
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>	
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>

  	<script>
   	$(document).ready(function(){
   		$("j_username").focus();
		$("#loginForm").validate({
			rules: { 
				j_username: "required",
				j_password: "required",
				j_captcha: "required"
			}
		});
		
		$("#random").click(function(){
			var timenow = new Date().getTime();
				this.src="${ctx}/security/jcaptcha.jpg?d="+timenow;
		});
	

		$("#btn_submit").click(function(){
			$("#loginForm").submit();
		});	
		
		$(document).keypress(function(e){
			if(e.keyCode == 13){
				$("#loginForm").submit();
			}
		 })

	});
	
	function guide() {
		alert("欢迎使用网上订餐系统！\n登录用户名：admin\n密\t码：123456");
	}
  	</script>
</head>

<body onload="guide();">
<div class="admin_login_top"></div>

<div class="admin_login_main">
<form id="loginForm" action="${ctx}/j_spring_security_check" method="post">		
  <table width="600" height="95" border="000" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="278" rowspan="6"><img src="images/admin_login_logo.jpg" width="278" height="72" /></td>
      <td width="8" rowspan="6"><img src="images/admin_login_fengge.jpg" width="8" height="224" /></td>
      <td colspan="3"><p>&nbsp;</p></td>
    </tr>
    <tr>
      <td width="60" align="center">用户名</td>
      <td colspan="2"><input style="width:140px;" type="text" id="j_username" name="j_username" <s:if test="not empty param.error"> value="<%=session.getAttribute(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)%>"</s:if> class="required" /></td>
      </tr>
    <tr>
      <td align="center">密　码</td>
      <td colspan="2"><input style="width:140px;" type='password' id='j_password' name='j_password' class="required" /></td>
      </tr>
    <tr>
      <td align="center">验证码</td>
      <td width="70"><img id="random" src="${ctx}/security/jcaptcha.jpg" alt="换一个" border="0" /></td>
      <td align="left"><input style="width:70px;" type="text" id="j_captcha" name="j_captcha"  class="required"/></td>
    </tr>
    <tr>
      <td colspan="3"><table width="100%" border="000" cellspacing="0" cellpadding="0">
        <tr>
          <td width="49%" align="center"><input type="checkbox" name="_spring_security_remember_me" />
两周内记住我</td>
          <td width="51%" align="left"><a href="#"><img id="btn_submit" src="images/btn_admin_login.jpg" width="55" height="21" border="0" /></a></td>
        </tr>
      </table></td>
    </tr>
    <tr>
      <td colspan="3"><p><a href="#"></a><a href="#"></a></p>
      <p>&nbsp;</p></td>
    </tr>
  </table>
  <br />
</form>
</div>
<div style="width:150px; margin:0 auto;">
	<%if("1".equals(request.getParameter("error"))){%>
		<span style="color:red"> 用户名密码错误,请重试.</span>
	<%} if("2".equals(request.getParameter("error"))){%>
		<span style="color:red"> 验证码错误,请重试.</span>
	<%}	if("3".equals(request.getParameter("error"))){%>
		<span style="color:red"> 此帐号已从别处登录.</span>
	<%}%>
</div>
</body>
</html>
