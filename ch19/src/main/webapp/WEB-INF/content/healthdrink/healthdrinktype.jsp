<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>健康饮食类型管理</title>
	<%@ include file="/common/meta.jsp"%> 
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script> 
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		//修改是否显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "healthdrinktype!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(200, 70);
					$.messager.show(0, "<span style='color:green'>健康饮食类型：<strong>"+title+"</strong><br>是否显示 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		//修改显示顺序
		function changeSequence(id,title,sequenceValue){
			if(isNaN(sequenceValue) || sequenceValue>255 || sequenceValue<=0) {
				$.messager.anim('fade', 1000);
				$.messager.lays(200, 70);
				$.messager.show(0, "<span style='color:red'>显示顺序请输入0-255之间的整数！</span>",5000);
				return false;
			}
			$.ajax({
				type: "POST",
				url: "healthdrinktype!changeSequence.action",
				data: "sequence="+sequenceValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(200, 70);
					$.messager.show(0, "<span style='color:green'>健康饮食类型：<strong>"+title+"</strong><br>显示顺序： 修改为<font color='red'><strong>"+sequenceValue+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset>
	<legend>健康饮食类型管理</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<!-- <form action="healthdrinktype!search.action" method="post">
	类型名称：<input name="filter_LIKE_typeName" size="12"/>&nbsp;&nbsp;&nbsp;&nbsp;
	是否显示：<select name="filter_EQ_isDisplay">
				<option value="1">是</option>
				<option value="2">否</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 				 
	<input type="submit" value="查 询"/> 
</form> -->

<form id="mainForm" action="healthdrinktype.action" method="post">
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<!--  <input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />-->
<input type="hidden" name="id" id="id" />

<div id="listContent">
	<display:table id="row" name="healthdrinktypelist" requestURI="healthdrinktype.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column property="typeName" title="类型名称" sortable="true" />
		<display:column title="是否显示" sortable="true" >
			<select id="isDisplay" name="isDisplay" onchange="changeIsDisplay('${row.id}','${row.typeName}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="1" <c:if test="${row.isDisplay == '1'}">selected</c:if>>显示</option>
				<option value="2" style="color:gray" <c:if test="${row.isDisplay == '2'}">selected</c:if>>不显示</option>
			</select>
		</display:column>	
		<display:column title="排序" sortable="true" >
			<input type="text" name="sequence" value="${row.sequence}" onchange="changeSequence('${row.id}','${row.typeName}',this.value)" size="3" maxlength="3"/>
		</display:column>
		<display:column title="类型描述" sortable="true">
			${row.description }
		</display:column>
		<display:column title="操作" style="width:60px;">	 
			<a href="healthdrinktype!input.action?id=${row.id }">修改</a>
			<a href="healthdrinktype!delete.action?id=${row.id }">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>         
</form>

<a href="healthdrinktype!input.action">增加饮食类型</a>
<br />
<div>
	<font color="#006676">
		提示：<br/>
		１、是否显示：‘不显示’的饮食类型及其饮食文章在前台健康饮食栏目不显示；<br/>
		２、排序：数值(0-255)越小，排序越前。
	</font>
</div>
</fieldset>
</body>
</html>