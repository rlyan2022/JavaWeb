<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>网上订餐系统--会员注册</title>
	<%@ include file="/common/meta.jsp"%>  
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	  
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script> 
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" /> 
	
	<script type="text/javascript">
		$(document).ready(function(){
		$("#loginCode").focus();
		$("#inputForm").validate({
			 rules: {  
			 	loginCode: {
			 		required: true, 
    				remote:"register!checkLoginCode.action?orgLoginCode="+encodeURIComponent('${loginCode}')				 		
			 	},
			 	memberName: {
			 		required: true,
			 		minlength:2,
    				maxlength:20	
			 	},
            	password: {
    				required: true,
    				minlength:6,
    				maxlength:16
    			}, 
    			passwordConfirm: {
    				required: true,
    				equalTo:"#password"
    			},
    			idcardNO: {
    				required: true,
    				minlength:6,
    				maxlength:18
    			},
    			email: "email"
			},
			messages: {
				loginCode: {
			    	remote:"该账户已被使用,请重新输入!"
			    },
				passwordConfirm: {
					equalTo: "输入与上面相同的密码"
				}
			}
		});
	});	
		
	</script>	 
</head>
<body> 
<%@ include file="/common/hander_item.jsp"%>

<div class="main_container">

<!-- div class="main_right_container">-->
<form id="inputForm" name="inputForm" action="register!save.action" method="post">
	<br/><div id="message" align="center"><s:actionmessage theme="mytheme"/></div>	<br/>	
	<fieldset>
   		<table align="center" width="80%"> 
   			<tr>
   				<td colspan="2" bgcolor="#e3ecf1" align="center" height="30"><font color="#DD5800" size="3px;">会员注册</font></td>
   			</tr>
   			<tr>
   				<td align="right"><b>登录账户：</b></td>
   				<td>
   					<input type="text" id="loginCode" name="loginCode" value="${loginCode }" size="20" maxlength="20"/>
   					<font color="red">*必填，下同</font>(请输入字母或者字母和数字的组合)
				</td>
   			</tr> 	
   			<tr>
   				<td align="right"><b>登录密码：</b></td>
   				<td>
   					<input type="password" id="password" name="password" size="20" maxlength="20"/>
   					<font color="red">*</font>(请输入字母、数字或者字母和数字的组合且长度为6-16的字符串)
				</td>
   			</tr>
   			<tr>
   				<td align="right"><b>密码确认：</b></td>
   				<td>
   					<input type="password" id="passwordConfirm" name="passwordConfirm" size="20" maxlength="20"/>
				</td>
   			</tr>
   			<tr>
   				<td align="right"><b>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</b></td>
   				<td>
   					<input type="text" name="memberName" size="20" maxlength="20"/>
   					<font color="red">*</font>(请输入您的称呼，如：刘先生)
				</td>
   			</tr> 
   			<tr>
   				<td align="right"><b>性&nbsp;&nbsp;&nbsp;&nbsp;别：</b></td>
   				<td>
   					<select name="sex">
   						<option value="1">女</option>
   						<option value="2">男</option>
   					</select>
				</td>
   			</tr> 
   			<tr>
   				<td align="right"><b>证件号码：</b></td>
   				<td>
   					<input type="text" name="idcardNO" size="20" maxlength="20"/>
   					<font color="red">*</font>（请输入6-18位的证件号码）
				</td>
   			</tr> 
   			<tr>
   				<td align="right"><b>联系电话：</b></td>
   				<td>
   					<input type="text" name="relationPhone" size="30" maxlength="20"/>
				</td>
   			</tr> 
   			<tr>
   				<td align="right"><b>E-mail：</b></td>
   				<td>
   					<input type="text" name="email" size="30" maxlength="50"/>
   					<font color="red">*</font>（密码忘记时，需要证件号码和注册邮箱取回）
				</td>
   			</tr> 
   			<tr>
   				<td align="right"><b>送餐地址：</b></td>
   				<td>
   					<input type="text" name="assignAddress" size="30" maxlength="50"/>
				</td>
   			</tr> 
   			
   			<tr>
   				<td height="50">&nbsp;</td>
   				<td valign="bottom">
					<input type="submit" value="确 定"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="reset" value="重 填"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" onclick="window.close();" value="取 消"/>
				</td> 
   			</tr>
   		</table> 
   		<br/>
   </fieldset>
</form>
</div>
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>
<!--wai_container-->
</div>
</body>
</html>

