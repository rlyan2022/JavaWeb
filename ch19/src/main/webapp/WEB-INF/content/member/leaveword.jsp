<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>客户留言管理</title>
	<%@ include file="/common/meta.jsp"%> 
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script> 
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		var deleteAction = 'leaveword!delete.action';
	
		//修改是否显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "leaveword!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>客户留言(标题)：<strong>"+title+"</strong><br>是否显示 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset>
	<legend>客户留言管理</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<!-- <form action="healthdrinktype!search.action" method="post">
	类型名称：<input name="filter_LIKE_typeName" size="12"/>&nbsp;&nbsp;&nbsp;&nbsp;
	是否显示：<select name="filter_EQ_isDisplay">
				<option value="1">是</option>
				<option value="2">否</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 				 
	<input type="submit" value="查 询"/> 
</form> -->

<form id="mainForm" action="leaveword.action" method="post">
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />
<input type="hidden" name="id" id="id" />

<div id="listContent">
	<display:table id="row" name="page.result" requestURI="leaveword.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column title="类别" sortable="true" >
			<c:if test="${row.type == '1'}">意见</c:if> 
			<c:if test="${row.type == '2'}"><font color="blue">建议</font></c:if>
			<c:if test="${row.type == '3'}">咨询</c:if>
		</display:column>
		<display:column property="name" title="联系人" sortable="true" />
		<display:column property="phone" title="联系电话" sortable="true" />
		<display:column property="email" title="邮箱地址" sortable="true" />
		<display:column title="留言时间" sortable="true">
			<fmt:formatDate value="${row.leaveTime}" pattern="yy-MM-dd HH:mm"/>
		</display:column>
		<display:column title="留言内容" sortable="true">
			<c:choose>
				<c:when test="${fn:length(row.content)>=20}">
						${fn:substring(row.content,0,20)}...
				</c:when>
				<c:otherwise>
					${row.content }
				</c:otherwise>
			</c:choose>	
		</display:column>
		<display:column title="是否显示">
			<select name="status" onchange="changeIsDisplay('${row.id}','${row.name}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="1" <c:if test="${row.status == '1'}">selected</c:if>>显示</option>
				<option value="2" style="color:gray" <c:if test="${row.status == '2'}">selected</c:if>>不显示</option>
			</select>
		</display:column> 
		<display:column title="回复人" sortable="true">
			<c:choose>
				<c:when test="${empty row.answerMan}">
					未回复
				</c:when>
				<c:otherwise>
					${row.answerMan}
				</c:otherwise>
			</c:choose>
		</display:column>
		<display:column title="操作" style="width:90px;">	 
			<a href="leaveword!input.action?id=${row.id }">修改/回复</a>&nbsp;
			<a href="#" onclick="if(confirm('确定要删除留言：‘${row.name}’吗?'))deleteItem(${row.id})">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>         
</form>　
</fieldset>
</body>
</html>