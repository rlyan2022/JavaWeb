<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.tienting.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/meta.jsp"%>
<head>
	<title>权限管理</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	
	<script type="text/javascript">	
	var modifyAction = 'authority!input.action';
	var deleteAction = 'authority!delete.action';
	
	//修改层级代码
	function changeLayerCode(id,displayName,value){
		$.ajax({
			type: "POST",
			url: "authority!changeLayerCode.action",
			data: "layerCode="+value+"&id="+id,
			success: function(msg){
				$.messager.anim('fade', 1000);
				$.messager.lays(200, 70);
				$.messager.show(0, "<span style='color:green'>权限名称：<strong>"+displayName+"</strong><br>层级代码修改成<font color='red'><strong>"+value+"</strong></font>！</span>",2000);
			}	
		});		
	}

	//改变权限类型
	function changeType(id,displayName,typeValue,typeName){
		$.ajax({
			type: "POST",
			url: "authority!changeType.action",
			data: "type="+typeValue+"&id="+id,
			success: function(msg){
				$.messager.anim('fade', 1000);
				$.messager.lays(200, 70);
				$.messager.show(0, "<span style='color:green'>权限名称：<strong>"+displayName+"</strong><br>修改成<font color='red'><strong>"+typeName+"</strong></font>类型！</span>",2000);
			}	
		});	
	}
	</script>
</head>

<body>
<fieldset><legend>功能权限管理</legend>
<div id="message"><s:actionmessage theme="mytheme" /></div>
<div id="filter">
	<form action="authority!search.action" method="post">
		你好,<%=SpringSecurityUtils.getCurrentUserName()%>.&nbsp;&nbsp;
		权限标志: <input type="text" name="filter_LIKE_name" value="${param['filter_LIKE_name']}"  size="10"/>
		权限名称: <input type="text" name="filter_LIKE_displayName" value="${param['filter_LIKE_displayName']}" size="8"/>
		<!-- 为了在页面显示值在控制层变量不能取parent.id 所以改成parent8id在后台工具类HibernateWebUtils中再转回来 -->
		<s:select name="filter_EQ_parent8id___Long" label="层级" labelposition="top" list="listAuthorities" listKey="id" listValue="displayName" headerKey="" headerValue="" />
		<s:select name="filter_EQ_type" label="类型" labelposition="top" list="#{'':'','url':'url'}" listKey="key" listValue="value" />
		<input type="submit" value="搜索" />
	</form>
</div> 
<form id="mainForm" action="authority.action" method="post">
<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}"/>
<input type="hidden" name="id" id="id" />

<div id="listContent">
	<display:table id="row" name="allAuthorities" requestURI="authority.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column property="name" title="权限标志" sortable="true"   />
		<display:column property="displayName" title="权限名称" sortable="true" />
		<display:column property="parent.displayName" title="上一级" sortable="true"  />
		
		<display:column title="层级" sortable="true" >
			<input type="text" name="layerCodeInput" value="${row.layerCode}" onchange="changeLayerCode('${row.id}','${row.displayName}',this.value)" 
			 size="20" maxlength="20"/>
		</display:column>
		<display:column title="权限类型" >
			<select id="type2" name="type2" onchange="changeType('${row.id}','${row.displayName}',this.options[this.options.selectedIndex].value,this.options[this.options.selectedIndex].text)" >
				<option value="url" <c:if test="${row.type == 'url'}">selected</c:if>>url</option>
				<option value="" <c:if test="${row.type == ''}">selected</c:if>></option>
			</select>
		</display:column>
		<display:column property="url" title="URL" sortable="true"  />
		<security:authorize ifAnyGranted="AUTHORITY">
		<display:column  title="操作" >	
			<a href="javascript:modifyItem(${row.id})">修改</a>、
			<a href="javascript:if(confirm('确定要删除【${row.name}】吗？'))deleteItem(${row.id})">删除</a>
		</display:column>
		</security:authorize>
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div>
</form>
<security:authorize ifAnyGranted="AUTHORITY">
	<div><a href="authority!input.action">增加新授权</a></div><br/>
</security:authorize>
<div>
	<font color="#006676">
	提示：<br/>
	１、通过帐号、角色、权限管理能最大限度限制用户的非法操作；<br/>
	２、功能权限在程序开发和基本数据导入时已完成配置，无需操作（操作请慎重）。<br/>
	</font>
</div>
</fieldset>
</body>
</html>