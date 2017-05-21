<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>帐号管理</title>
	<%@ include file="/common/meta.jsp"%>

	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#loginName").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: { 
				loginName: { 
       				required: true, 
       				remote: "user!checkLoginName.action?orgLoginName="+encodeURIComponent('${loginName}')
   				},
           		name: "required",
           		password: {
   					required: true,
   					minlength:6
   				}, 
   				passwordConfirm: {
   					equalTo:"#password"
   				},
   				email:"email"
			},
			messages: {
				loginName: {
					remote: "用户登录名已存在"
				},
				passwordConfirm: {
					equalTo: "输入与上面相同的密码"
				}
			}
		});
		$("#changePassword").click(function(){			
			$("#password").removeAttr('disabled').val("");
			$("#passwordConfirm").removeAttr('disabled').val("");
			$("#loginName").removeAttr('disabled');
			$("#changePassword").attr("disabled","true");
			$("#updatePassword").attr("value","true");
		});
	});
	</script>
</head>

<body>
<fieldset>
<legend><s:if test="id == null">创建</s:if><s:else>修改</s:else>用户</legend> 
<div id="message"><s:actionmessage theme="mytheme"/></div>

<div id="inputContent">
	<form id="inputForm" action="user!save.action" method="post">
	<input type="hidden" name="id" value="${id}" />
	<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
	<input type="hidden" name="page.pageRequest" value="${page.pageRequest}" />
	<input type="hidden" id="updatePassword" name="updatePassword"/>
	<table class="inputView">
		<tr>
			<td align="right">登录名：</td>
			<td><input type="text" name="loginName" size="20" maxlenght="20" id="loginName" value="${loginName}" <s:if test="id!=null">disabled</s:if>/>
				<font color="#006676">登录名请输入字母、数字或者字母、数字的组合。</font>
			</td>
		</tr>
		<tr>
			<td align="right">用户名：</td>
			<td><input type="text" name="name" size="20" maxlenght="30" value="${name}" /></td>
		</tr>
		<tr>
			<td align="right">密码：</td>
			<td><input type="password" id="password" name="password" size="20" maxlenght="20" value="${password}" <s:if test="id!=null">disabled</s:if>/></td>
		</tr>
		<tr>
			<td align="right">确认密码：</td>
			<td><input type="password" id="passwordConfirm" name="passwordConfirm" size="20"  value="${password}" <s:if test="id!=null">disabled</s:if>/></td>
		</tr>
		<tr>
			<td align="right">邮箱：</td>
			<td><input type="text" name="email" size="40" value="${email}" maxlenght="50"/></td>
		</tr>
		<s:if test="id!=1">
		<tr>
			<td align="right">状态：</td>
			<td>			
				<select name="enabled">
					<option value="1" <s:if test="enabled==1">selected</s:if>>启用</option>
					<option value="0" <s:if test="enabled==0">selected</s:if>>停用</option>
				</select>			
			</td>
		</tr>
		</s:if>
		<tr>
			<td align="right">角色：</td>
			<td>
				<div>
					<s:checkboxlist id="checkedRoleIds" name="checkedRoleIds"  list="allRoles"  listKey="id" listValue="cnname" theme="mytheme"/>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="提　交" />&nbsp;&nbsp;&nbsp; &nbsp;   
				<s:if test="id!=null"><input id="changePassword" type="button" value="重置密码" /></s:if>&nbsp;
				<input type="button" value="取　消" onclick="history.back()"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</fieldset>
</body>
</html>