<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%> 
<%@ page import="cn.com.tienting.modules.utils.DateUtils" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>会员免费注册</title>
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	
	<script type="text/javascript">
		function registerSubmit(){							
			document.inputForm.action = "register!input.action";
			document.inputForm.submit();
		}
		
		function agree(){		
			if (inputForm.elements["checkId"].checked) {
				inputForm.elements["register"].disabled = "";
			} else {
				inputForm.elements["register"].disabled = "disabled";
			}
		}
	</script>	 
</head>
<body> 
<%@ include file="/common/hander.jsp"%>
<div class="main_container">
<br/>
 <div id="message" align="center"><s:actionmessage theme="mytheme"/></div>
<form id="inputForm" name="inputForm" action="register.action" method="post" >
	<% String register_sign = DateUtils.getCurrentDate("yyyy-MM-dd")+"tienting"; %>
	<input type="hidden" name="register_sign" value="<%=register_sign%>"/>
	<br/>
	<table align="center" width="90%">
  		<tr>
  			<td align="center" height="280" valign="top">
  				&nbsp;&nbsp;注册条款
  				
  				<br />
  			</td>
  		</tr>
  		
  		<tr>
  			<td align="center">
  				<input type="checkbox" id="checkId" onclick="return agree();"/>我已阅读并同意以上会员章程
  			</td>
  		</tr>
		<tr>
			<td style="border-top:1px #cccccc dashed;">&nbsp;</td>
		</tr>
	</table>	
	
	<table align="center" width="90%">
		<tr>
			<td align="center">
				<input type="button" id="register" onclick="return registerSubmit();" value="下一步" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" onclick="window.close();" value="取 消"/>
			</td>
		</tr>
	</table>
</form>
<!--</div>-->
<div style="clear:both;"></div>
<%@ include file="/common/footer_item.jsp"%>
<!--wai_container-->
</div>
</body>
</html>

