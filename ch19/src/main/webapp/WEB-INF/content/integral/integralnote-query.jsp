<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/meta.jsp"%>
<head>
	<title>积分兑换记录查询</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	 		
 
	</script>
</head>
<body>
<fieldset>
	<legend>积分兑换记录查询</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="integralnote!mamageQuery.action" method="post">
	<input type="hidden" name="id" id="id" /> 
<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
<input type="hidden" name="page.order" id="order" value="${page.order}" />

	<table align= center bgcolor="#CCCCCC">	
		<tr>
			<td nowrap="nowrap" align="right">会员状态：
				<select name="memeberStatus">
					<option value="" >全部</option>
					<option value="1" <c:if test="${memeberStatus=='1'}">selected</c:if>>正常可用</option>
					<option value="2" <c:if test="${memeberStatus=='2'}">selected</c:if>>暂停停用</option>
				</select> &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				
				会员帐号：<input type="text" name="memberCode" size="12" value="${memberCode }"/>&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
				 
				 <input type="submit" value="查 询" />
			</td>
		</tr>
	</table>
<br/><br/>
<div id="listContent" align="center">
<display:table id="row" name="page.result" requestURI="integralnote!mamageQuery.action" pagesize="${pageSize}" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column property="memberCode" title="会员帐号" sortable="true" />
		<display:column property="memberName" title="会员名称" sortable="true" /> 
		<display:column property="changeIntegral" title="兑换积分（个）" sortable="true" />
		<display:column property="mealCurrency" title="生成餐币（元）" sortable="true" /> 	 
		<display:column property="changeTime" title="兑换时间" sortable="true" format="{0,date,yyyy-MM-dd HH:mm}"/>	
		<%@ include file="/common/pagingbanner_displayTag.jsp"%>
	</display:table>
</div> 
</form>
 
</fieldset>
</body>
</html>