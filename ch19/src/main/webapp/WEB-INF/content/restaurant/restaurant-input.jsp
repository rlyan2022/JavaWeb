<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐店基本信息管理</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />		
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/widgets/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8" ></script>
	
	<script type="text/javascript">
		KE.show({
			id:'description'
		}); 
		
		$(document).ready(function(){
			$("#name").focus();
			$("#inputForm").validate({
				 rules: {
					name: { 
	        			required: true
	    			},
					service: { 
	        			required: true,
	        			maxlength: 200
	    			}
				}
			});
		});
	</script>
</head>
<body>
<div id="listContent"> 
	<fieldset>
		<legend><s:if test="id == null">新增餐店基本信息</s:if><s:else>修改餐店基本信息－${name}</s:else></legend>
		<div id="message"><s:actionmessage theme="mytheme"/></div>
    <div id="inputContent">
    <form id="inputForm" action="restaurant!save.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputView" style="margin-left:20px;">
		<tr>
			<td nowrap="nowrap" align="right">店名：</td>
			<td colspan="2"><input type="text" value="${name}" name="name" size="30" maxlength="100" class="required"/><font color="red">*</font></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">地址：</td>
			<td colspan="2"><input type="text" value="${address}" name="address" size="30" maxlength="100" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">地图：</td>
			<td colspan="2"><input type="file" name="upload" size="30" maxlength="100"/><font color="#006676">不上传图片请置空</font></td>
		</tr>
		<s:if test="map != null">
			<tr> 
				<td>已上传的地图：</td>
				<td colspan="2">
					<img src="${ctx}/upload/restaurant/${map}"/>				
				</td>
			</tr>
		</s:if>
		<tr>
			<td nowrap="nowrap" align="right">餐店负责人：</td>
			<td colspan="2"><input type="text" value="${principal}" name="principal" size="30" maxlength="30"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">联系电话：</td>
			<td colspan="2"><input type="text" value="${phone}" name="phone" size="30" maxlength="50"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">手机：</td>
			<td colspan="2"><input type="text" value="${mobile}" name="mobile" size="30" maxlength="50"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">邮箱地址：</td>
			<td colspan="2"><input type="text" value="${email}" name="email" size="30" maxlength="50"/></td>
		</tr>	
		<tr>
			<td align="right">编辑人：</td>
			<td colspan="2"> ${editor.name }</td>
		</tr>
		<tr>
			<td align="right">编辑时间：</td>
			<td colspan="2">${edittime }</td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right">温馨提示：</td>
			<td>
				<textarea id="service" name="service" style="width:400px;height:100px;" class="required">${service}</textarea>
			</td>
			<td>
				用于前台主页‘温馨提示’栏目显示<br/><br/>
				回车/换行请在文字末尾加：&lt;br/&gt; <br/>
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">描述：</td>
			<td colspan="2"><textarea id="description" title="如果您需要上传图片，请控制图片的大小在100K以内！" name="description" style="width:600px;height:250px;visibility:hidden;" class="required">${description}</textarea></td>
		</tr>	
		<tr>
			<td colspan="3" align="center" height="50">
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
