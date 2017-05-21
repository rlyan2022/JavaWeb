<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
		<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/js/validate/jquery.validate.css" type="text/css"	rel="stylesheet" />
	
	<script type="text/javascript">
	 				
		function createLegalCode() {
			var serial = document.inputForm.elements["serial"].value;
			var website = document.inputForm.elements["website"].value;
		
			if (serial=="" && website=="") {
				alert("授权编号、网站域名 都不能为空，请重新输入！");
				return false;
			}
			
			if (confirm('确定要授权吗？')) {
				document.inputForm.action = "islegal!createLegalCode.action";
				document.inputForm.submit();
			}
		}
		//-->
	</script>
</head>
<body>
<div style="">   
	<div id="message"><s:actionmessage theme="mytheme"/></div>
    <form id="inputForm" name="inputForm" action="islegal.action" method="post">
    	<input type="hidden" name="createtag" value="${createtag }"/>
		<table style="margin-left:20px;">
			<tr height="30">
				<td nowrap="nowrap" align="right">授权编号：</td>
				<td><input type="text" value="${serial}" id="serial" name="serial" size="20" maxlength="20"/><font color="red">*</font></td>
			</tr>
			<tr height="30">
				<td nowrap="nowrap" align="right">网站域名：</td>
				<td><input type="text" value="${website}" id="website" name="website" size="30" maxlength="50"/><font color="red">*</font></td>
			</tr>
			 
			<tr>
				<td colspan="2" align="center" height="50">
					<input type="button" onclick="return createLegalCode();" value=" 提交 "/>&nbsp;&nbsp;
					<input type="button" value=" 退出 " onclick="window.close()" />
				</td>
			</tr>
		</table>
	</form> 
</div>
</body>
</html>
