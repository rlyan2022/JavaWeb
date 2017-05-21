<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title }</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" /> 
    <script type="text/javascript" src="${ctx}/js/jquery_hp.js"></script> 
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	<script src="${ctx}/js/form.js" type="text/javascript"></script>	 
</head>
<body> 
<%@ include file="/common/hander_item.jsp"%>
<div class="main_container">
<div class="main_left_container">
<div class="left_column"><div class="ico_jpg" style="background-image:url(images/jpg_active.jpg); width:45px; height:33px;  "></div>
<li>餐店公告</li>
</div>
<div class="left_content">

	<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
		<form id="mainForm" action="homepage!noticequery.action" method="post">
			<input type="hidden" name="noticeId" value="${noticeId}" />
		  	<input type="hidden" name="pageSize" id="pageSize" value="${noticePage.pageSize}"/>
			<input type="hidden" name="noticePage.pageNo" id="pageNo" value="${noticePage.pageNo}"/>
			<input type="hidden" name="noticePage.orderBy" id="orderBy" value="${noticePage.orderBy}"/>
			<input type="hidden" name="noticePage.order" id="order" value="${noticePage.order}" />
		  	<table width="100%">
				<%int rowid = 0;%>
				<s:iterator id="notid" value="noticePage.result">
					<%if (rowid == 0){%>
						<s:if test="noticeId == null">
							<c:set var="noticeId" value="${notid.id}" scope="request"/>
						</s:if>
					<%}%>
	  				<tr>
	  					<td style="border-bottom:1px #cccccc dashed;"><a href="homepage!noticeview.action?noticeId=${notid.id }" target="m_iframe">${notid.title }</a></td>
	  					<!--<td align="right" style="border-bottom:1px #cccccc dashed;"><fmt:formatDate value="${notid.recordTime}" pattern="MM-dd"/></td>-->
	  				</tr>
					<%rowid = 1;%>
	  			</s:iterator>
			</table>
		  </form>
	</div>
	<%@ include file="/common/pagingbanner_notice.jsp"%>
</div>
<div class="left_foot"></div>

<!-- 热门菜单 -->
<div class="left_column">
<div class="ico_jpg" style="background-image:url(images/jpg_caishi.jpg); width:45px; height:44px; margin-bottom:-5px; margin-right:-9px;"></div>
<li>热门菜单TOP 10</li></div>
<div class="left_content">
 <table width="100%">
	<tr>
		<td bgcolor="#cccccc"><b>菜单名称</b></td>
		<td bgcolor="#cccccc"><b>菜单名称</b></td> 
	</tr>
	<%int count=0; %>
	<s:iterator id="pmenu" value="submenulist">
		<%if (count%2==0){ %>
		<tr>
		<%}%>
			<td style="border-bottom:1px #cccccc dashed;" title="价格：${pmenu.price }元"> 
				<font color="#DD5800"><%=count+1 %>.</font>
				<a href="${ctx }/homepage/homepage!menuview.action?submenuId=${pmenu.id }" target="m_iframe">${pmenu.name }</a>
			</td> 
		<%if (count%2==1){ %>
		</tr>
		<%} count++;%>		
	</s:iterator>
	 
	<%
		//菜单为奇数时补全html
		if (count%2 > 0) { %>
			<td style="border-bottom:1px #cccccc dashed;" colspan="2"> 
		</tr>
	<%} %>
</table> 
</div>
<div class="left_foot"></div>
<!-- 热门菜单 结束-->

</div>

<div class="main_right_container">
	<br/>
	<iframe width="100%" name="m_iframe" height="500" src="homepage!noticeview.action?noticeId=${noticeId}" 
		allowtransparency="true" frameborder="0" scrolling="yes"></iframe>

</div><!-- main_right_container -->
</div>
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>

</div>
<!--wai_container-->
</body>
</html>

