<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>健康饮食</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	
		var deleteAction = 'healthdrink!delete.action';
		
		//修改显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "healthdrink!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 100);
					$.messager.show(0, "<span style='color:green'>健康饮食：<strong>"+title+"</strong><br>是否显示 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		//修改置顶
		function changeIsTop(id,title,isTopValue,isTopName){
			$.ajax({
				type: "POST",
				url: "healthdrink!changeIsTop.action",
				data: "isTop="+isTopValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 100);
					$.messager.show(0, "<span style='color:green'>健康饮食：<strong>"+title+"</strong><br>是否置顶 ：修改为<font color='red'><strong>"+isTopName+"</strong></font>！</span>",3000);
				}	
			});	
		}
		
		function changeType(id,title,isTypeValue,isTypeName) {
			$.ajax({
				type: "POST",
				url: "healthdrink!changeType.action",
				data: "typeId="+isTypeValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 100);
					$.messager.show(0, "<span style='color:green'>健康饮食：<strong>"+title+"</strong><br>饮食类型 ：修改为<font color='red'><strong>"+isTypeName+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset>
	<legend>健康饮食管理</legend>	
<div id="message"><s:actionmessage theme="mytheme"/></div>
<!-- <form action="healthdrink!search.action" method="post">
	饮食标题：<input name="filter_LIKE_title" size="12"/>&nbsp;&nbsp;
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
	<input type="submit" value="查询"/> 
</form>-->

<form id="mainForm" action="healthdrink.action" method="post">
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="id" id="id" />

<div id="listContent">
<display:table id="row" name="healthdrinklist" requestURI="healthdrink.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column title="饮食类型" sortable="true">
			<c:if test="${row.typeIsDisplay eq 1}">	
				<select name="typeId" onchange="changeType('${row.id}','${row.title}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
					<s:iterator id="ty" value="healthdrinktypelist">
						<option value="${ty.id}" <c:if test="${ty.id == row.typeId}">selected</c:if>>${ty.typeName}</option>
					</s:iterator>
				</select>	
			</c:if>
			<c:if test="${row.typeIsDisplay eq 2}">
				${row.typeName}
			</c:if>
		</display:column>
		<display:column title="标题" sortable="true">
			<a href="healthdrink!view.action?id=${row.id }&sign=Y">${row.title }</a>
		</display:column>
		<display:column property="auth" title="作者" sortable="true"/>	
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
		<display:column property="issueMan" title="发布人" sortable="true"/>
		<display:column property="issueTime" title="发布时间" sortable="true" format="{0,date,yyyy-MM-dd}"/>
		<display:column title="操作" style="width:60px;">	 
			<c:if test="${row.typeIsDisplay eq 1}">
				<a href="healthdrink!input.action?id=${row.id }&healthdrinktypeid=${row.typeId }">修改</a>
			</c:if> 
			<a href="#" onclick="if(confirm('确定要删除‘${row.title}’吗?'))deleteItem(${row.id})">删除</a>
			<!-- <a href="healthdrink!delete.action?id=${row.id }">删除</a> -->
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
</form>
<a href="healthdrink!input.action">发布健康饮食信息</a>
<br/>
<div>
	<font color="#006676">
		提示：<br/>
		１、饮食类型：选择饮食类型下拉列表可以更改该饮食文章的类型；<br/>
		２、饮食类型：已停用的饮食类型的饮食文章不能修改；<br/>
		３、是否显示：选择‘是否显示’下拉列表可以更改该健康饮食信息是否在前台页面‘健康饮食栏目’显示；<br />
		４、是否置顶：选择‘是否置顶’下拉列表可以更改该健康饮食信息是否在前台页面’健康饮食栏目‘显示。
	</font>
</div>
</fieldset>
</body>
</html>