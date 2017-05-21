<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>饮食类型修改</title>	
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		$("#typeName").focus();
		$("#inputForm").validate({
			 rules: {
				typeName: { 
        			required: true, 
        			remote: "healthdrinktype!checkType.action?orgTypeName="+encodeURIComponent('${typeName}')+"&a="+Math.random()    		
    			},
    			sequence: {
    				digits:true,
    				min:1,
    				max:255
    			},
    			description: {
    				maxlength: 230
    			}      		
			},
			messages: {
				typeName: {
					remote: "该饮食类型已存在"
				}
			}
		});
	});	
	</script>
</head>
<body>
<fieldset>
	<legend><s:if test="id == null">添加饮食类型信息</s:if><s:else>修改饮食类型信息 - ${typeName}</s:else></legend>
<div id="listContent">
<form id="inputForm" action="healthdrinktype!save.action" method="post">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputContent">
		<tr>
			<td align="right">饮食类型名称：</td>
			<td><input type="text" id="typeName" name="typeName" value="${typeName}" maxlength="40" size="16"/></td>
		</tr>
		<tr>
			<td align="right">是否显示：</td>
			<td>
				<select name="isDisplay">
					<option value="1" <s:if test="isDisplay==1">selected</s:if>>是</option>
					<option value="2" <s:if test="isDisplay==2">selected</s:if>>否</option>
				</select><font color="#006676">注：‘显示’的饮食类型及该类型的饮食文章在前台健康饮食栏目显示</font>
			</td>
		</tr>
		<tr>
			<td align="right">排序：</td>
			<td><input type="text" name="sequence" value="${sequence}" maxlength="4" size="4"/>
				<font color="#006676">数值(0-255)越小，排序越前</font>
			</td>
		</tr>
		<s:if test="id != null">
			<tr>
				<td align="right">编辑人：</td>
				<td>${editor.name}</td>
			</tr>
			<tr>
				<td align="right">编辑时间：</td>
				<td>${edittime }</td>
			</tr>
		</s:if>
		<tr>
			<td align="right">类型描述：</td>
			<td>
				<textarea name="description" title="类型描述长度请控制在100个汉字以下" style="width:400px;height:100px;">${description}</textarea>
			</td>
		</tr>

		<tr>
			<td colspan="2" align="center" height="50">
				<input type="submit" value=" 提 交 " />&nbsp; &nbsp;
				<input type="button" value=" 取 消 " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
</div>

</fieldset>
</body>
</html>