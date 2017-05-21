<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>健康饮食管理</title>
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
	    			healthdrinktypeid: {
	    				required: true
	    			}   		
				}
			});
		});
		function checkForm(){		
			
		}
	</script>
</head>
<body>
<fieldset>
	<legend><s:if test="id == null">发布健康饮食信息</s:if><s:else>修改健康饮食信息 - ${title}</s:else></legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="listContent">
	<form id="inputForm" action="healthdrink!save.action" method="post" enctype="multipart/form-data" onsubmit="return checkForm();">
	<input type="hidden" name="id" value="${id}" />
	<table class="inputView">
		<tr>
			<td nowrap="nowrap" align="right">饮食类型：</td>
			<td><s:select name="healthdrinktypeid" list="healthdrinktypelist" listKey="id" listValue="typeName" theme="simple"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">标题：</td>
			<td><input type="text" id="title" name="title" size="30" maxlength="150" value="${title}" class="required"/></td>
		</tr>	
		<!-- <tr>
			<td nowrap="nowrap" align="right">标题图片：</td>
			<td><input type="file" id="upload" name="upload" value="${ctx}/upload/news/${titleIco}" src="${ctx}/upload/news/${titleIco}" size="30" />
				<s:if test="id != null">
				   <img src="${ctx}/upload/news/${titleIco}" alt="${ctx}/upload/news/${titleIco}" width="130" height="100" name="imge"/>
				</s:if>
			</td>
		</tr> -->
		<tr>
			<td nowrap="nowrap" align="right">作者：</td>
			<td><input type="text" name="auth" size="30" maxlength="30" value="${auth}"/></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">来源：</td>
			<td><input type="text" name="source" size="30" maxlength="60" value="${source}" /></td>
		</tr>
		<tr>
			<td nowrap="nowrap" align="right">是否显示：</td>
			<td>
				<select name="isDisplay">
					<option value="1" <c:if test="${isDisplay eq 1 }">selected</c:if>>显示</option>
					<option value="2" <c:if test="${isDisplay eq 2 }">selected</c:if>>不显示</option>
				</select><font color="#006676">注：‘显示’的饮食文章在前台健康饮食栏目显示</font>		
			</td>
		</tr>		
		<tr>
			<td nowrap="nowrap" align="right">是否置顶：</td>
			<td>
				<select name="isTop">
					<option value="2" <c:if test="${isTop eq 2 }">selected</c:if>>不置顶</option>
					<option value="1" <c:if test="${isTop eq 1 }">selected</c:if>>置顶</option>
				</select><font color="#006676">注：‘置顶’的饮食文章在前台健康饮食栏目顶部顺序显示</font>		
			</td>
		</tr>
		<s:if test="id != null">
			<tr>
				<td nowrap="nowrap" align="right">发布人：</td>
				<td><input type="text" name="issueMan" size="20" maxlength="20" value="${issueMan}" /></td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">发布时间：</td>
				<td>${issueTime}</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">编辑人：</td>
				<td>${editor.name}</td>
			</tr>
			<tr>
				<td nowrap="nowrap" align="right">编辑时间：</td>
				<td>${edittime}</td>
			</tr>
		</s:if>
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
<br/>
	<div>
		<font color="#006676">
			提示：<br/>
			１、饮食文章可以上传图片，图片大小请控制在100K以下；<br/>
			２、如果您上传的图片超过100K,请使用<a href="http://www.neoimaging.cn/" target="_blank">图片压缩软件</a>将图片压缩到100k以下再上传；<br/>
			３、如果您需要上传大于100K的图片，请<a href="http://www.tienting.com" target="_blank">联系我们</a>。	
		</font>
	</div>
</fieldset>
</body>
</html>