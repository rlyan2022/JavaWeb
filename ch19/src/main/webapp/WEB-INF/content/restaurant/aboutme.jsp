<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>餐店简介</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	
		//var viewAction = 'aboutme!view.action';
		//var modifyAction = 'aboutme!input.action';
		var deleteAction = 'aboutme!delete.action';
		
		//修改简介的显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "aboutme!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(200, 70);
					$.messager.show(0, "<span style='color:green'>餐店简介：<strong>"+title+"</strong><br>是否显示 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		//修改简介的显示顺序
		function changeSequence(id,title,sequenceValue){
			if(isNaN(sequenceValue) || sequenceValue>255 || sequenceValue<=0) {
				$.messager.anim('fade', 1000);
				$.messager.lays(200, 70);
				$.messager.show(0, "<span style='color:red'>显示顺序请输入0-255之间的整数！</span>",5000);
				return false;
			}
			$.ajax({
				type: "POST",
				url: "aboutme!changeSequence.action",
				data: "sequence="+sequenceValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(200, 70);
					$.messager.show(0, "<span style='color:green'>餐店简介：<strong>"+title+"</strong><br>显示顺序： 修改为<font color='red'><strong>"+sequenceValue+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset>
	<legend>餐店简介管理</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="aboutme.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" />  
<input type="hidden" name="pageSize" id="pageSize" />

<div id="listContent">

<display:table id="row" name="aboutmelist" requestURI="aboutme.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column title="简介标题" sortable="true">
			<a href="aboutme!view.action?id=${row.id }&sign=Y">${row.title}</a>
		</display:column>
		<display:column title="是否显示" sortable="true" >
			<select id="isDisplay" name="isDisplay" onchange="changeIsDisplay('${row.id}','${row.title}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="1" <c:if test="${row.isDisplay == '1'}">selected</c:if>>是</option>
				<option value="2" style="color:gray" <c:if test="${row.isDisplay == '2'}">selected</c:if>>否</option>
			</select>
		</display:column>	
		<display:column title="显示顺序" sortable="true" >
			<input type="text" name="sequence" value="${row.sequence}" onchange="changeSequence('${row.id}','${row.title}',this.value)" size="3" maxlength="3"/>
		</display:column>
		<display:column property="content" title="内容简介" sortable="true" />
		<display:column property="recordMan" title="创建人" sortable="true" />
		<display:column property="createTime" title="创建时间" sortable="true" format="{0,date,yyyy-MM-dd}"/>
		
		<display:column title="操作" style="width:60px;">	 
			<a href="aboutme!input.action?id=${row.id }">修改</a>
			<a href="javascript:if(confirm('您确定要删除‘${row.title }’简介吗？'))deleteItem(${row.id})">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
</form>
<div><a href="aboutme!input.action">增加餐店简介</a></div>	
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	１、点击简介标题可以查看该简介的详细信息；<br/>
	２、选择‘是否显示’的内容可以改变该简介的显示状态(选择‘否’表示在前台页面不显示该简介的信息)；<br/>
	３、在‘显示顺序’文本框输入整数(0-255)可以改变该简介的显示顺序。
	
	</font>
</div>
</fieldset>
</body>
</html>