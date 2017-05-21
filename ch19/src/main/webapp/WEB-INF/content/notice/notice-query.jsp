<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title }</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" /> 
    <script type="text/javascript" src="${ctx}/js/jquery_hp.js"></script> 
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/js/form.js" type="text/javascript"></script>	 
</head>
<body> 
<%@ include file="/common/hander.jsp"%>
<div class="main_container">
<div class="main_left_container">
<div class="left_column"><div class="ico_jpg" style="background-image:url(images/jpg_active.jpg); width:45px; height:33px;  "></div>
<li>餐店公告</li>
</div>
<div class="left_content">

	<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
		<form id="mainForm" action="notice!query.action" method="post">
		  	<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
			<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
			<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
			<input type="hidden" name="page.order" id="order" value="${page.order}" />
		  	<table>
				<s:iterator id="notid" value="page.result">
	  				<tr>
	  					<td><a href="notice!view.action?id=${notid.id }" target="v_iframe">${notid.title }</a></td>
	  					<td><fmt:formatDate value="${notid.recordTime}" pattern="dd/MM"/></td>
	  				</tr>
	  			</s:iterator>
			</table>
		  </form>
	</div>
	<%@ include file="/common/pagingbanner2.jsp"%>
</div>
<div class="left_foot"></div>

</div>

<div class="main_right_container">

	<iframe width="100%" name="v_iframe" height="100%" src="notice!view.action?id=${id }" frameborder="0" scrolling="no"></iframe>
</div><!-- main_right_container -->

</div>
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>

</div>
<!--wai_container-->
</body>
</html>

