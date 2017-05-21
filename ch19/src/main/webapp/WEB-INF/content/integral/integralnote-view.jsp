<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net/el" prefix="display" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/meta.jsp"%>
<head>
	<title>积分兑餐币记录查询</title>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">	 		
 
	</script>
</head>
<body>
<fieldset>
	<legend>积分兑餐币记录查询 &nbsp;&nbsp;&nbsp;<a href="integralnote.action">积分兑餐币</a></legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>

<form id="mainForm" action="integralnote!memberQuery.action" method="post">
	<input type="hidden" name="memberId" id="memberId" />  
<br/>
<div id="listContent" align="center">
<display:table id="row" name="integralnotelist" requestURI="integralnote!memberQuery.action" class="listView" defaultsort="4" defaultorder="ascending" style="listView">
		<display:column property="memberCode" title="您帐号" sortable="true" />
		<display:column property="memberName" title="您的姓名" sortable="true" /> 
		<display:column property="changeIntegral" title="兑换积分（个）" sortable="true" />
		<display:column property="mealCurrency" title="生成餐币（元）" sortable="true" /> 	 
		<display:column property="changeTime" title="兑换时间" sortable="true" format="{0,date,yyyy-MM-dd HH:mm}"/>	
	</display:table>
</div> 
</form>
</fieldset>
</body>
</html>