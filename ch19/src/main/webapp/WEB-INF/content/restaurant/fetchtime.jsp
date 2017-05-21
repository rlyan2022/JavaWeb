<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>送餐时间管理</title>
	<%@ include file="/common/meta.jsp"%> 
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	　
		var deleteAction = 'fetchtime!delete.action';
		
	</script>
</head>
<body>
<fieldset style="width: 90%;margin-left: 50px;">
	<legend>送餐时间管理</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="fetchtime.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" />  　

<div id="listContent">

	<table align="center" width="80%">
		<tr>
			<th>序号</th>
			<th>送餐时间</th>
			<th>编辑人</th>
			<th>&nbsp;</th>
			<th>序号</th>
			<th>送餐时间</th>
			<th>编辑人</th>
			<th>&nbsp;</th>
		</tr>
		<%int count = 0; %>
		<s:iterator id="row" value="fetchtimelist">
			<% if (count%2 == 0) {%>
				<tr>
			<%} %>  
					<td align="center"><%=count+1 %></td>
					<td align="center">${row.fetchtime }</td>
					<td align="center">${row.editorName }</td>
					<td align="center">
						<a href="javascript:if(confirm('您确定要删除送餐时间：‘${row.fetchtime }’吗？'))deleteItem(${row.id})">删除</a>
					</td>				
			<% if (count%2 == 1) {%>
				</tr>
			<%} 
				count++;
			%>
		</s:iterator>
		<%if (count%2 > 0) { %>
				<td colspan="4">&nbsp;</td>
			</tr>
		<% } %>
	</table>
</div>
</form>
<div style="margin-left: 100px;"><a href="fetchtime!input.action">增加送餐时间</a></div>	
<br/>
<div style="margin-left: 100px;">
	<font color="#006676">
	提示：<br/>
	１、送餐时间在前台订餐车的送到时间下拉框显示。<br/> 
	
	</font>
</div>
</fieldset>
</body>
</html>