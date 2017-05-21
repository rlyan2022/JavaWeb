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
	    			summary: {
	    				maxlength:200
	    			}   		
				}
			});
		});
	</script>
</head>
<body>
<fieldset>
	<legend><s:if test="id == null">发布订餐指南信息</s:if>	<s:else>修改订餐指南记录 - ${title}</s:else></legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="listContent">
	<form id="inputForm" name="inputForm" action="eatguide!save.action" method="post">
	<input type="hidden" id="id" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td nowrap="nowrap" align="right">饮食标题：</td>
			<td><input type="text" name="title" size="30" value="${title}" maxlenght="100"/></td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right">作者：</td>
			<td><input type="text" name="expert" size="30" value="${expert}" maxlenght="100"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">来源：</td>
			<td><input type="text" name="source" size="30" value="${source}" maxlenght="100"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">是否显示：</td>
			<td>
				<select name="isDisplay">
					<option value="1" <c:if test="${isDisplay eq 1 }">selected</c:if>>显示</option>
					<option value="2" <c:if test="${isDisplay eq 2 }">selected</c:if>>不显示</option>
				</select><font color="#006676">注：‘显示’的订餐指南在前台网页显示</font>		
			</td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">是否置顶：</td>
			<td>
				<select name="isTop">
					<option value="2" <c:if test="${isTop eq 2 }">selected</c:if>>不置顶</option>
					<option value="1" <c:if test="${isTop eq 1 }">selected</c:if>>置顶</option>
				</select><font color="#006676">注：‘置顶’的订餐指南在前台订餐指南栏目顶部顺序显示</font>		
			</td>
		</tr>
		
		<s:if test="id != null">
			<tr>
				<td nowrap="nowrap" align="right">发布人：</td>
				<td><input type="text" name="recordMan" size="20" value="${recordMan}" maxlenght="20"/></td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">发布时间：</td>
				<td>${recordTime }</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">编辑人：</td>
				<td>${editor.name }</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">编辑时间：</td>
				<td>${edittime }</td>
			</tr>
		</s:if>
		<tr>
			<td nowrap="nowrap" align="right">摘要：</td>
			<td><textarea title="饮食摘要请控制在100个汉字以内" name="summary" style="width:400px;height:60px;" >${summary}</textarea></td>
		</tr>	
		<tr>
			<td nowrap="nowrap" align="right">内容：</td>
			<td><textarea id="content" name="content" style="width:600px;height:250px;visibility:hidden;" class="required">${content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value=" 提 交 " />&nbsp; 
				<input type="button" value=" 取 消 " onclick="history.back()" />
			</td>
		</tr>
	</table>
	</form>
</div>
	<div>
		<font color="#006676">
			提示：<br/>
			１、订餐指南可以上传图片，图片大小请控制在100K以下；<br/>
			２、如果您上传的图片超过100K,请使用<a href="http://www.neoimaging.cn/" target="_blank">图片压缩软件</a>将图片压缩到100k以下再上传；<br/>
			３、如果您需要上传大于100K的图片，请<a href="http://www.tienting.com" target="_blank">联系我们</a>。	
		</font>
	</div>
</fieldset>
</body>
</html>