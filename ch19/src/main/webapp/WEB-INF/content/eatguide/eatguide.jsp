<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/meta.jsp"%>
<head>
	<title>订餐指南</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	 		
		var deleteAction = 'eatguide!delete.action';
		
		//修改显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "eatguide!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>订餐指南：<strong>"+title+"</strong><br>是否显示 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		//修改置顶
		function changeIsTop(id,title,isTopValue,isTopName){
			$.ajax({
				type: "POST",
				url: "eatguide!changeIsTop.action",
				data: "isTop="+isTopValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>订餐指南：<strong>"+title+"</strong><br>是否置顶 ：修改为<font color='red'><strong>"+isTopName+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset>
	<legend>订餐指南管理</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="eatguide.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" /> 
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />

<div id="listContent">
<display:table id="row" name="eatguidelist" requestURI="eatguide.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column title="标题" sortable="true">
			<a href="eatguide!view.action?id=${row.id }&sign=Y">${row.title }</a>
		</display:column>
		<display:column property="expert" title="作者" sortable="true" />
		<display:column property="source" title="来源" sortable="true" /> 	
		<display:column title="是否显示"  >
			<select name="isDisplay" onchange="changeIsDisplay('${row.id}','${row.title}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="1" <c:if test="${row.isDisplay == '1'}">selected</c:if>>显示</option>
				<option value="2" style="color:gray" <c:if test="${row.isDisplay == '2'}">selected</c:if>>不显示</option>
			</select>
		</display:column>
		<display:column title="是否置顶"  >
			<select name="isTop" onchange="changeIsTop('${row.id}','${row.title}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="2" <c:if test="${row.isTop == '2'}">selected</c:if>>不置顶</option>
				<option value="1" style="color:orange" <c:if test="${row.isTop == '1'}">selected</c:if>>置顶</option>
			</select>
		</display:column>
		<display:column property="summary" title="摘要" sortable="true"/> 
		<display:column property="recordTime" title="发布时间" sortable="true" format="{0,date,yyyy-MM-dd}"/>	
		<display:column title="操作" style="width:60px;">	 
			<a href="eatguide!input.action?id=${row.id }">修改</a>
			<!--  <a href="eatguide!delete.action?id=${row.id }">删除</a>-->
			<a href="javascript:if(confirm('确定要删除‘${row.title}’吗?'))deleteItem(${row.id})">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
<%//@ include file="/common/pagingbanner.jsp"%>
</form>
<div><a href="eatguide!input.action">发布订餐指南</a></div>	
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	１、是否显示：‘不显示’的订餐指南信息在前台页面不显示；<br/>
	２、是否置顶：‘置顶’的订餐指南信息在前台页面订餐指南栏顶端显示。	
	</font>
</div>
</fieldset>
</body>
</html>