<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title> 
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/widgets/kindeditor/kindeditor.js" type="text/javascript" charset="utf-8" ></script>
	
	<script type="text/javascript">
		KE.show({
			id:'content'
		});

		$(document).ready(function(){
			$("#title").focus();
			$("#inputForm").validate({
				 rules: {
	    			title: {
	    				required: true
	    			},
	    			sequence: {
	    				required: true,
   						digits:true,
   						min:1,
   						max:255
	    			}     		
				}
			});
		});
	</script>
</head>
<body>
<fieldset>
	<legend><s:if test="id == null">增加餐店简介信息</s:if>	<s:else>修改餐店简介记录 - ${title}</s:else></legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="listContent">
	<form id="inputForm" name="inputForm" action="aboutme!save.action" method="post" onsubmit="return checkSubmit()">
	<input type="hidden" id="id" name="id" value="${id}" /> 
	<table class="inputView">
		<tr>
			<td align="right">简介标题：</td>
			<td><input type="text" size="20" id="title" name="title" value="${title}" maxlength="100" />
				<font color="#006676">请输入与已有简介标题不相同的简介标题</font>
			</td>
		</tr>
		<tr>
			<td align="right">显示顺序：</td>
			<td><input type="text" size="5" id="sequence" name="sequence" value="${sequence}" maxlength="3" />
				<font color="#006676">请输入0-255之间的整数</font>
			</td>
		</tr>	
		<tr>
			<td align="right">是否显示：</td>
			<td>
				<select name="isDisplay">
					<option value="1" <s:if test="isDisplay==1">selected</s:if>>是</option>
					<option value="2" <s:if test="isDisplay==2">selected</s:if>>否</option>
				</select>
				<font color="#006676">选择‘否’则表示该简介不在页面显示</font>
			</td>
		</tr>		
		<s:if test="id != null">
			<tr>
				<td align="right">创建人：</td>
				<td><input type="text" size="20" name="recordMan" value="${recordMan}" maxlength="20" /></td>
			</tr>
			<tr>
				<td align="right">创建时间：</td>
				<td>${createTime }</td>
			</tr>
			<tr>
				<td align="right">编辑人：</td>
				<td> ${editor.name }</td>
			</tr>
			<tr>
				<td align="right">编辑时间：</td>
				<td>${edittime }</td>
			</tr>
		</s:if>
		<tr>
			<td nowrap="nowrap" align="right">简介内容：</td>
			<td><textarea id="content" title="如果您需要上传图片，请控制图片的大小在100K以内！" name="content" style="width:600px;height:250px;visibility:hidden;" class="required">${content}</textarea></td>
		</tr>	

		<tr>
			<td colspan="2" align="center" height="30">
				<s:if test="id == null"><input type="submit" value=" 提 交 " /></s:if><s:else><input type="submit" value=" 修 改 " /></s:else>&nbsp;&nbsp;
				<input type="button" value=" 取 消 " onclick="history.back()" />	
			</td>
		</tr>
	</table>	
	</form>
</div>
<div>
	<font color="#006676">
	提示：<br/>
	１、简介内容可以上传图片，图片大小请控制在100K以下；<br/>
	２、如果您上传的图片超过100K,请使用<a href="http://www.neoimaging.cn/" target="_blank">图片压缩软件</a>将图片压缩到100k以下再上传；<br/>
	３、如果您需要上传大于100K的图片，请<a href="http://www.tienting.com" target="_blank">联系我们</a>。	
	</font>
</div>
</fieldset>
</body>
</html>