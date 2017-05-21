<%@ page language="java" pageEncoding="UTF-8"%>
<%//@ page import="cn.com.tienting.modules.security.springsecurity.SpringSecurityUtils"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>菜单类型管理</title>
		<%@ include file="/common/meta.jsp"%>
		<%//@ include file="/js/widgets/calendar/calendar.jsp"%>
		<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
		<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
		<script src="${ctx}/js/effects/jquery.messager.js"></script>
		<script src="${ctx}/js/form.js" type="text/javascript"></script>
		<script type="text/javascript">	
			//var viewAction = 'menutype!view.action';
			//var modifyAction = 'menutype!input.action';
			var deleteAction = 'menutype!delete.action';
			
			//修改简介的显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "menutype!changeIsDisplay.action",
				data: "isDisplay="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>菜单类型：<strong>"+title+"</strong><br>是否显示： 修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",5000);
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
				url: "menutype!changeSequence.action",
				data: "sequence="+sequenceValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>菜单类型：<strong>"+title+"</strong><br>显示顺序 ：修改为<font color='red'><strong>"+sequenceValue+"</strong></font>！</span>",5000);
				}	
			});	
		}
	</script>

	</head>

	<body>
	<fieldset>
		<legend>菜单类型管理</legend>
	<!-- 
		<div id="menu">
			菜单类型列表
		</div>
		<form id="searchForm" action="menutype!search.action" method="post">
		菜单类型状态:<select name="menuTypeStatus">
		<option value="1">正常使用</option>
		<option value="2">暂停使用</option>
		</select><br/>
	
		是否配菜类型:
		<select name="submenuMenuType">
		<option value="2">普通类型</option>
		<option value="1">配菜类型</option>
		</select><br/>
		
		菜单类型名称:<input type="text" name="menuTypeName"/><br/>
		菜单类型编号:<input type="text" name="menuTypeCode"/><br/>
		编辑时间:在
		<input type="text" id="filter_GREATEREQ_edittime___Date" name="filter_GREATEREQ_edittime___Date" value="${param['filter_GREATEREQ_edittime___Date']}" readonly="readonly" size="20"/>之后
			<script type="text/javascript">
				function catcalc(cal) {
			        var date = cal.date;
			        var time = date.getTime();			
			        var field = $("#filter_GREATEREQ_issueTime___Date");
			        var date2 = new Date(time);
			        field.value = date2.print("%Y-%m-%d");
			    }
				Calendar.setup({
					inputField     : "filter_GREATEREQ_edittime___Date",
					ifFormat       :    "%Y-%m-%d",
					onUpdate       :    catcalc
				});
			</script>		

<br/>
		
		<input type="submit" value="搜索"/>
		</form> -->
	<br/>
	<div id="message"><s:actionmessage theme="mytheme" /></div>
	<form id="mainForm" action="menutype.action" method="post">
			<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}" />
			<input type="hidden" name="id" id="id" />
			<div id="listContent">
				<display:table id="row" name="menutypelist"
					requestURI="menutype.action" pagesize="${pageSize}" class="listView">
					<display:column property="code" title="类型编号" sortable="true" />
					<display:column title="类型名称" sortable="true" group="2">
						${row.name}
					</display:column>
					<display:column title="使用状态" sortable="true" >
						<select name="status" onchange="changeIsDisplay('${row.id}','${row.name}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
							<option value="1" <c:if test="${row.status == '1'}">selected</c:if>>正常使用</option>
							<option value="2" style="color:gray" <c:if test="${row.status == '2'}">selected</c:if>>暂停使用</option>
						</select>
					</display:column>	
					<display:column title="排序" sortable="true" >
						<input type="text" name="sequence" value="${row.sequence}" onchange="changeSequence('${row.id}','${row.name}',this.value)" size="3" maxlength="3"/>
					</display:column>
					<display:column property="description" title="描述" sortable="true" />
					<display:column title="操作" style="width:60px;">
						<a href="menutype!input.action?id=${row.id }">修改</a>&nbsp;
						<a href="javascript:if(confirm('确定要删除‘${row.name}’吗?'))deleteItem(${row.id})">删除</a>
					</display:column>
					<%@ include file="/common/pagingbanner_displayTag.jsp"%>
				</display:table>
			</div>
		</form>
		<div>
			<a href="menutype!input.action">增加菜单类型</a>
		</div>
		<br/>
		<div>
		<font color="#006676">
		提示：<br/>
		１、选择‘使用状态’的内容可以改变该菜单类型的使用状态（‘暂停使用’的菜单类型的菜单不能销售）<br/>
		２、在‘显示顺序’文本框输入整数(0-255)可以改变该菜单类型在前台菜单栏的显示顺序。
		
		</font>
</div>
		</fieldset>
	</body>
</html>