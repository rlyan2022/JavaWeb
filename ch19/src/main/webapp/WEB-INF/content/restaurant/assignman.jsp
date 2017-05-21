<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>送餐员管理</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	
		//var viewAction = 'assignman!view.action';
		//var modifyAction = 'assignman!input.action';
		var deleteAction = 'assignman!delete.action';
		
		//修改简介的显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "assignman!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(200, 70);
					$.messager.show(0, "<span style='color:green'>餐店送餐员：<strong>"+title+"</strong><br>是否送餐 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		//修改简介的显示顺序
		function changeSequence(id,title,sequenceValue){
			$.messager.anim('fade', 1000);
			$.messager.lays(200, 70);
			if(isNaN(sequenceValue) || sequenceValue>255 || sequenceValue<=0) {
				$.messager.show(0, "<span style='color:red'>排序请输入0-255之间的整数！</span>",3000);
				return false;
			}
			$.ajax({
				type: "POST",
				url: "assignman!changeSequence.action",
				data: "sequence="+sequenceValue+"&id="+id,
				success: function(msg){
					$.messager.show(0, "<span style='color:green'>餐店送餐员：<strong>"+title+"</strong><br>排序： 修改为<font color='red'><strong>"+sequenceValue+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset style="width:98%">
	<legend>送餐员管理</legend>	
<div id="message"><s:actionmessage theme="mytheme"/></div>
<!-- <form action="assignman!search.action" method="post">
	标题：<input name="filter_LIKE_title" size="12"/>&nbsp;&nbsp;
	发布日期：
	<input type="text" id="filter_GREATEREQ_issueTime___Date" name="filter_GREATEREQ_issueTime___Date" value="${param['filter_GREATEREQ_edittime___Date']}" readonly="readonly" size="10"/>之后
			<script type="text/javascript">
				function catcalc(cal) {
			        var date = cal.date;
			        var time = date.getTime();			
			        var field = $("#filter_GREATEREQ_issueTime___Date");
			        var date2 = new Date(time);
			        field.value = date2.print("%Y-%m-%d");
			    }
				Calendar.setup({
					inputField     : "filter_GREATEREQ_issueTime___Date",
					ifFormat       :    "%Y-%m-%d",
					onUpdate       :    catcalc
				});
			</script>&nbsp;&nbsp;				 
	作者：<input name="filter_LIKE_auth" size="12"/>&nbsp;&nbsp;
	<input type="submit" value="查询"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</form>-->
<form id="mainForm" action="assignman.action" method="post">
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />
<input type="hidden" name="id" id="id" />


<div id="listContent">
<display:table id="row" name="page.result" requestURI="assignman.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column property="code" title="编号" sortable="true"/>
		<display:column title="姓名 " sortable="true">
			<a href="assignman!view.action?id=${row.id }">${row.name }</a>
		</display:column>	
		<display:column title="性别" sortable="true" >
			<c:if test="${row.sex eq 1}">男</c:if>
			<c:if test="${row.sex eq 2}">女</c:if>  
		</display:column>
		<display:column property="phone" title="联系电话" sortable="true" />
		<display:column property="email" title="邮箱地址" sortable="true" />
		<display:column title="是否送餐" >
			<select name="status" onchange="changeIsDisplay('${row.id}','${row.name}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="1" <c:if test="${row.status == '1'}">selected</c:if>>送餐</option>
				<option value="2" style="color:gray" <c:if test="${row.status == '2'}">selected</c:if>>不送餐</option>
			</select>
		</display:column>	
		<display:column title="排序" >
			<input type="text" name="serial" value="${row.serial}" onchange="changeSequence('${row.id}','${row.name}',this.value)" size="3" maxlength="3"/>
		</display:column>
		<display:column property="remark" title="备注"/>
		<display:column title="操作" style="width:60px;">	 
			<a href="assignman!input.action?id=${row.id }">修改</a>
			<a href="javascript:if(confirm('确定要删除‘${row.name}’的记录吗?'))deleteItem(${row.id})">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
</form>
<a href="assignman!input.action">新增送餐员</a>
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	１、是否送餐：‘不送餐’的送餐员不在订单管理的送餐员列表显示；<br/>
	３、排序数值(0-255)越小则该送餐员排序越前（订单管理的送餐员列表）。
	
	</font>
</div>
</fieldset>
</body>
</html>