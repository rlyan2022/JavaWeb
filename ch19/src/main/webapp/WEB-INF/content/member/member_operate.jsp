<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/meta.jsp"%>
<head>
	<title>会员信息管理</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	 		
		//修改显示
		function changeIsDisplay(id,title,isDisplayValue,isDisplayName){
			$.ajax({
				type: "POST",
				url: "member_operate!changeIsDisplay.action",
				data: "status="+isDisplayValue+"&id="+id,
				success: function(msg){
					$.messager.anim('fade', 1000);
					$.messager.lays(300, 70);
					$.messager.show(0, "<span style='color:green'>会员：<strong>"+title+"</strong><br>使用状态 ：修改为<font color='red'><strong>"+isDisplayName+"</strong></font>！</span>",3000);
				}	
			});	
		}
	</script>
</head>
<body>
<fieldset>
	<legend>会员信息管理</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="member_operate.action" method="post">
<!-- 隐藏关于我们内容   ID -->
<input type="hidden" name="id" id="id" /> 
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />

<div id="listContent">
<display:table id="row" name="memberlist" requestURI="member_operate.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column title="用户帐号" sortable="true">
			<a href="member_operate!view.action?id=${row.id }&sign=Y">${row.loginCode }</a>
		</display:column>
		<display:column property="memberName" title="姓名" sortable="true" /> 
		<display:column title="性别"  >
			<c:if test="${row.sex == '1'}">女</c:if>
			<c:if test="${row.sex == '2'}">男</c:if> 
			</select>
		</display:column>
		<display:column title="使用状态"  >
			<select name="status" onchange="changeIsDisplay('${row.id}','${row.status}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="1" <c:if test="${row.status == '1'}">selected</c:if>>正常使用</option>
				<option value="2" <c:if test="${row.status == '2'}">selected</c:if>>停用</option>
				<option value="">&nbsp;</option>
			</select>
		</display:column>
		<display:column property="mealCurrency" title="餐币" sortable="true"/>
		<display:column property="integral" title="可用积分" sortable="true"/> 
		<display:column property="historyIntegral" title="历史积分" sortable="true"/>
		<display:column property="registerTime" title="注册时间" sortable="true" format="{0,date,yyyy-MM-dd}"/>	
		
		<display:column title="操作" style="width:60px;">	 
			<a href="member_operate!input.action?id=${row.id }">修改</a>
			<a href="member_operate!delete.action?id=${row.id }">删除</a>
		</display:column>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
<%//@ include file="/common/pagingbanner.jsp"%>
</form>
<div><a href="member_operate!input.action">增加会员</a></div>	
<br/>
<div>
	<font color="#006676">
	提示：<br/>
	１、会员的 可用积分+历史积分＝总积分；<br/>
	２、使用状态为‘停用’的会员在将不能登录订餐系统。	
	</font>
</div>
</fieldset>
</body>
</html>