<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐店公告</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">			
		var deleteAction = 'notice!delete.action';
		
		//修改公告的显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "notice!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(280, 100);
					$.messager.show(0, "<span style='color:green'>餐店公告：<strong>"+title+"</strong><br>是否显示:  修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",5000);
				}	
			});	
		}
		
		//修改公告是否置顶
		function changeIsTop(id,title,isTopValue,isTopName){
			$.ajax({
				type: "POST",
				url: "notice!changeIsTop.action",
				data: "isTop="+isTopValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(280, 100);
					$.messager.show(0, "<span style='color:green'>餐店公告：<strong>"+title+"</strong><br>是否置顶：  修改为<font color='red'><strong>"+isTopName+"</strong></font>！</span>",5000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset>
	<legend>餐店公告管理</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="notice.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" />  
<input type="hidden" name="pageSize" id="pageSize"/>
<div id="listContent">

<display:table id="row" name="noticelist" requestURI="notice.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column title="公告标题" sortable="true">
			<a href="notice!view.action?id=${row.id }&sign=Y">${row.title }</a>
		</display:column>
		<display:column title="是否显示" sortable="true" >
			<select id="isDisplay" name="isDisplay" onchange="changeIsDisplay('${row.id}','${row.title}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="1" <c:if test="${row.isDisplay == '1'}">selected</c:if>>是</option>
				<option value="2" style="color:gray" <c:if test="${row.isDisplay == '2'}">selected</c:if>>否</option>
			</select>
		</display:column>
		<display:column title="是否置顶" sortable="true" >
			<select id="isTop" name="isTop" onchange="changeIsTop('${row.id}','${row.title}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="2" <c:if test="${row.isTop == '2'}">selected</c:if>>否</option>
				<option value="1" style="color:orange" <c:if test="${row.isTop == '1'}">selected</c:if>>是</option>
			</select>
		</display:column>
		<display:column property="summary" title="公告摘要" sortable="true" />
		<display:column property="recordMan" title="发布人" sortable="true" />
		<display:column property="recordTime" title="发布时间" sortable="true" format="{0,date,yyyy-MM-dd}"/>
			
		<display:column title="操作" style="width:60px;">	 
			<a href="notice!input.action?id=${row.id }">修改</a>
			<a href="javascript:if(confirm('确定要删除‘${row.title}’吗?'))deleteItem(${row.id})">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
</form>
<div><a href="notice!input.action">发布公告</a></div>	
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	１、点击公告标题可以查看该公告的详细信息；<br/>
	２、选择‘是否显示’的内容可以改变该公告的显示状态(选择‘否’表示在前台页面不显示该公告的信息)；<br/>
	３、选择‘是否置顶’的内容可以改变该公告是否在页面相应的位置优先显示。
	
	</font>
</div>
</fieldset>
</body>
</html>