<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>权限管理</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		$("#name").focus();
		$("#inputForm").validate({
			 rules: {
				name: { 
        			required: true, 
        			remote: "authority!checkName.action?orgAuthorityName="+encodeURIComponent('${name}')+"&a="+Math.random(),
        			maxlength:40        		
    			},
    			displayName: { 
        			required: true, 
        			remote: "authority!checkDisplayName.action?orgDisplayName="+encodeURIComponent('${displayName}')+"&a="+Math.random(),
        			maxlength:64
    			},
    			path: {
    				maxlength:100
    			},
    			layerCode: {
    				maxlength:20
    			}       		
			},
			messages: {
				name: {
					remote: "权限标志已存在"
				},
				displayName: {
					remote: "权限名称已存在"
				}
			}
		});
	});	
	</script>
</head>

<body>
<fieldset>
<legend><s:if test="id == null">创建</s:if><s:else>修改</s:else>权限</legend>

<div id="inputContent">
	<form id="inputForm" action="authority!save.action" method="post">
	<input type="hidden" name="id" value="${id}" />
	<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
	<table class="inputView">		
		<tr>
			<td>授权标志:</td>
			<td><input type="text" name="name" size="30" maxlenght="40" id="name" value="${name}" /></td>
		</tr>
		<tr>
			<td>授权名称:</td>
			<td><input type="text" name="displayName" size="30" maxlenght="60" id="displayName"  value="${displayName}" /></td>
		</tr>				
		<tr>
			<td>权限类型:</td>
			<td><input type="text" name="type" size="30" maxlenght="20" id="type"  value="${type}" /></td>
		</tr>
		<tr>
			<td>上一级:</td>
			<td>
				<s:select name="parentid" list="allAuthorities" listKey="id" listValue="displayName" headerKey="0" headerValue="--请选择--" theme="simple"/> 
			</td>
		</tr>
		<tr>
			<td>层级代码:</td>
			<td><input type="text" name="layerCode" size="30" maxlength="20" id="layerCode"  value="${layerCode}" /></td>
		</tr>
		<tr>
			<td>url:</td>
			<td><input type="text" name="url" size="40" maxlength="200" id="url"  value="${url}" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center" >			
				<input type="submit" value="提交" />&nbsp; 			
				<input type="button" value="取消" onclick="history.back()"/>
			</td>
		</tr>
	</table>
	</form>
</div>
</fieldset>
</body>
</html>