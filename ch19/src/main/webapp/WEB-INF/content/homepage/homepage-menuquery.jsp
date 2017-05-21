<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>网上订餐系统-美食分类</title>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" /> 
	
	<script type="text/javascript">
		function openWindow(value)
		{
	    	if(window.subsecondwindow){
	       		window.subsecondwindow.close();
	    	}
	    	var sURL = value;
	    	window.subsecondwindow=window.open(sURL,"subsecondwindow","toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,titlebar=0,resizable=0,top=100,left=100,height=600,width=980,status=0");
		}
	</script>
</head>
<body> 
<%@ include file="/common/hander_item.jsp"%>
<div class="main_container" >
 
 <% int c_count = 0;%>
 <s:iterator id="mt" value="menutypelist">
	<br/> 
	<fieldset>
			<legend><font color="#006676"><b>菜单分类：${mt.name }</b></font></legend>
			<s:iterator value="#mt.submenus" id="cmenus">
				<div class="right_new_list">
				   <a href="javascript:openWindow('${ctx }/homepage/homepage!menuview.action?submenuId=${cmenus.id }')">
					<c:choose>
						<c:when test="${empty cmenus.photo}"><img src="images/new/1.jpg" width="120" height="80" /></c:when>
						<c:otherwise><img src="${ctx}/upload/submenu/${cmenus.photo }" width="120" height="80" /></c:otherwise>
					</c:choose>
					</a>
					<div class="list_word">${cmenus.name}</div> 
					<div class="list_money">价格：￥${cmenus.price }</div>
				</div>
			     <%
					++c_count;
						
					if (c_count%6==0) {
				%>
			        <div class="clear"></div>
			       <%}%>
			</s:iterator>
	</fieldset> 
	<%c_count = 0; %>
 </s:iterator> 
</div>
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>

</div>
<!--wai_container-->
</body>
</html>

