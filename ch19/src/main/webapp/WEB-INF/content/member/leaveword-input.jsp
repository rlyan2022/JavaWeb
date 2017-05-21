<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改/回复客户留言信息 - ${title}</title>	
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
	$(document).ready(function(){
		$("#answer").focus();
		$("#inputForm").validate({
			 rules: {  
    			answer: {
    				maxlength: 250
    			}      		
			} 
		});
	});	
	</script>
</head>
<body>
<fieldset>
	<legend>修改/回复客户留言信息 - ${title}</legend>
<div id="listContent" style="margin-left: 50px;">
<form id="inputForm" action="leaveword!save.action" method="post">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputContent">
		<!-- <tr>
			<td align="right"><b>留言标题：</b></td>
			<td><input type="text" name="title" value="${title}" maxlength="40" size="30"/></td>
		</tr> -->
		<tr>
			<td align="right"><b>留言类别：</b></td>
			<td>
				<select name="type">
					<option value="1" <s:if test="type==1">selected</s:if>>意见</option>
					<option value="2" <s:if test="type==2">selected</s:if>><font color="blue">建议</font></option>
					<option value="3" <s:if test="type==3">selected</s:if>>咨询</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right"><b>联系人：</b></td>
			<td><input type="text" name="name" value="${name}" maxlength="40" size="30"/></td>
		</tr>
		<tr>
			<td align="right"><b>联系电话：</b></td>
			<td><input type="text" name="phone" value="${phone}" maxlength="40" size="30"/></td>
		</tr>
		<tr>
			<td align="right"><b>邮箱地址：</b></td>
			<td><input type="text" name="email" value="${email}" maxlength="40" size="30"/></td>
		</tr>
		<tr>
			<td align="right"><b>留言时间：</b></td>
			<td>${leaveTime}</td>
		</tr>
		
		<tr>
			<td align="right"><b>是否显示：</b></td>
			<td>
				<select name="status">
					<option value="1" <s:if test="status==1">selected</s:if>>是</option>
					<option value="2" <s:if test="status==2">selected</s:if>>否</option>
				</select>
			</td>
		</tr>　
		<s:if test="id != null">
			<tr>
				<td align="right"><b>回复人：</b></td>
				<td>${editor.name}</td>
			</tr>
			<tr>
				<td align="right"><b>回复时间：</b></td>
				<td>${edittime }</td>
			</tr>
		</s:if>
		<tr>
			<td align="right"><b>回复内容：</b></td>
			<td>
				<textarea name="answer" style="width:500px;height:100px;">${answer}</textarea>
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