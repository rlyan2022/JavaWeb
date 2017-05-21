<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>送餐员信息管理</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#name").focus();
			$("#inputForm").validate({
				 rules: {
					name: { 
	        			required: true
	    			},
	    			code: { 
	        			required: true
	    			},
	    			serial: {
	    				required: true,
	    				digits: true,
	    				min:1,
	    				max:255
	    			},
	    			remark: {
	    				maxlength: 400
	    			}     		
				}
			});
		});
		function checkForm(){		
			
		}
	</script>
</head>
<body>
<div> 
	<fieldset style="width:95%">
		<legend><s:if test="id == null">新增送餐员信息</s:if><s:else>修改送餐员信息－${name}</s:else></legend>
    <div id="listContent">
    <form id="inputForm" action="assignman!save.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputView" style="margin-left:20px;">
		<tr>
			<td nowrap="nowrap" align="right">姓名：</td>
			<td><input type="text" value="${name}" name="name" size="10" maxlength="20" class="required"/><font color="red">*</font></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">编号：</td>
			<td><input type="text" value="${code}" name="code" size="10" maxlength="20" class="required"/><font color="red">*</font></td>
		</tr>
		<tr>
			<td align="right">是否送餐：</td>
			<td>
				<select name="status">
					<option value="1" <s:if test="status==1">selected</s:if>>送餐</option>
					<option value="2" <s:if test="status==2">selected</s:if>>不送餐</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">性别：</td>
			<td>
				<select name="sex">
					<option value="1" <s:if test="sex==1">selected</s:if>>男</option>
					<option value="2" <s:if test="sex==2">selected</s:if>>女</option>
				</select>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">排序：</td>
			<td><input type="text" value="${serial}" name="serial" size="5" maxlength="3" class="required"/>
				<font color="red">数值(0-255)越小，排序越前</font>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">照片：</td>
			<td><input type="file" name="upload" size="30" maxlength="100"/><font color="#006676">不上传图片请置空</font></td>
		</tr>
		<s:if test="photo != null">
			<tr> 
				<td align="right">已上传的照片：</td>
				<td>
					<img src="${ctx}/upload/assignman/${photo}"/>				
				</td>
			</tr>
		</s:if>
		<tr>
			<td nowrap="nowrap" align="right">联系电话：</td>
			<td><input type="text" value="${phone}" name="phone" size="30" maxlength="50"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">邮箱地址：</td>
			<td><input type="text" value="${email}" name="email" size="30" maxlength="50"/></td>
		</tr>		
		<tr>
			<td nowrap="nowrap" align="right">备注：</td>
			<td><textarea name="remark" title="请输入200个汉字以下的备注信息" style="width:500px;height:150px;">${remark}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="50">
				<input type="submit" value=" 提交 " />&nbsp;&nbsp;
				<input type="button" value=" 返回 " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
	</div>
	</fieldset>
</div>
</body>
</html>
