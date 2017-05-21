<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>菜单评价管理</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	
	<script type="text/javascript">	　
		var deleteAction = 'menuevaluate!delete.action';

	 //修改显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "menuevaluate!changeIsDisplay.action",
				data: "status="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					if (msg == "true") {
						$.messager.show(0, "<span style='color:green'>菜单：<strong>"+title+"</strong><br>的评价状态 是否显示 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
					} else {
						$.messager.show(0, "抱歉！数据有误，菜单评价的状态：是否显示 修改不成功！",3000);
					}
				}	
			});	
		}
	</script>
</head>

<body>
<fieldset>
<legend>餐店菜单管理</legend>
<br/>
<form id="mainForm" action="menuevaluate.action" method="post">
<input type="hidden" name="id" id="id" /> 
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />
<div id="message"><s:actionmessage theme="mytheme"/></div>

<div id="listContent">
	<display:table id="row" name="page.result" requestURI="menuevaluate.action" pagesize="${pageSize}" class="listView" >
		<display:column property="menuName" title="菜单名称" sortable="true" />
		<display:column property="memberCode" title="会员帐号" sortable="true" /> 	
		<display:column property="content" title="评价内容" sortable="true" />
		<display:column title="是否前台显示"  >
			<select name="status" onchange="changeIsDisplay('${row.id}','${row.menuName}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)">
				<option value="1" <c:if test="${row.status=='1'}">selected</c:if>>显示</option>
				<option value="2" <c:if test="${row.status=='2'}">selected</c:if>>不显示</option>
			</select>
		</display:column>  
		<display:column property="evaluateTime" title="评价时间" sortable="true" format="{0,date,yyyy-MM-dd HH:mm}"/>	
		<display:column  title="操作" style="width:60px;">	
			<a href="menuevaluate!input.action?id=${row.id }">修改</a>
			<a href="#" onclick="if(confirm('确定要删除‘${row.menuName}’的评价内容吗?'))deleteItem(${row.id})">删除</a>
		</display:column> 
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>	
</div>
</form> 
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	１、菜单评价内容的是否显示选项为：‘显示’的记录才会在前台页面显示，否则不显示（客户看不到）。	
	</font>
</div>
</fieldset>
</body>
</html>



