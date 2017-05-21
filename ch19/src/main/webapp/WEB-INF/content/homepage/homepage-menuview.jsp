<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.tienting.modules.web.struts2.Struts2Utils" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>网上订餐系统-菜单信息查看</title>
	<%@ include file="/common/meta.jsp"%>
	<%@ include file="/js/widgets/calendar/calendar.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script  src="${ctx}/js/form.js" type="text/javascript"></script>
	<style type="text/css">
		<!--
		.STYLE1 {
			color: #FFFFFF;
			font-weight: bold;
		}
		
		.STYLE2 {
					color: #DD5800;
					font-weight: bold;
				}
		//-->
    </style>
</head>

<body> 

<fieldset>
	<legend><font color="#006676"><b>菜单信息明细</b></font></legend>
	<div id="listContent">
   	  	<table width="96%" align="center"> 
			<tr bgcolor="#e3ecf1">
			 	<td rowspan="2" width="120px;">
				   	<s:if test="submenu.photo==null">
				   		<img src="${ctx }/upload/submenu/defaultphoto.jpg" width="120px;" height="100px;"/>
				   	</s:if> 
				   	<s:else>
				   		<img src="${ctx }/upload/submenu/${submenu.photo }" width="120px;" height="80px;"/>
				   	</s:else>
			    </td>
			   	<th>菜单类型</th>
			   	<th>菜单名称</th>
			   	<th>单价</th>
			   	<th>销售量</th>
			 </tr>
			 <tr>
			  	<td align="center">&nbsp;${submenu.menutype.name }</td>
			  	<td align="center">&nbsp;${submenu.name }</td>
			  	<td align="center">&nbsp;<font class="STYLE2">${submenu.price }</font> 元</td> 
				<td align="center">&nbsp;<font class="STYLE2">${submenu.sellNumber }</font> 份</td> 
			 </tr>
			 <tr bgcolor="#e3ecf1">
			  	<td colspan="5" valign="middle">
			  	&nbsp;&nbsp;<b>菜单详细描述：</b>${submenu.description }<br/><br/></td>
			 </tr>
		</table> 
	</div>
</fieldset> 
	<br />
<c:if test="${not empty submenu.menuEvaluates}">
	<fieldset>
	<legend><font color="#006676"><b>菜单评价记录</b></font></legend>
		<!-- 菜单评价内容 -->
		<div id="listContent">
			<table width="96%" align="center">
				<s:iterator id="eval" value="submenu.menuEvaluates">
					<tr>
						<td valign="top" width="140"><br/><b>会员帐号：${eval.member.loginCode }</b><br/>
							<b>评价日期：<fmt:formatDate value="${eval.evaluateTime}" pattern="yyyy-MM-dd"/></b>
						</td >
						<td valign="top">
							<br/>&nbsp;&nbsp;<b>评价内容：</b>${eval.content }<br/>
						</td>
					</tr>
				</s:iterator>
			</table> 	
		</div>			
	</fieldset>	
</c:if>	
<br/>
<div id="message" style="margin-left: 50px;"><s:actionmessage theme="mytheme"/></div>
<script type="text/javascript">
<!--
	function submitConfirm(){		
		
		var content = document.inputForm.elements["valaContent"].value;
		 
		content = content.replace(/(^\s*)|(\s*$)/g,'');		
		if (content=="") {
			alert("抱歉！请输入您的评价内容再提交！");
			return false;
		}
			
		document.inputForm.action = "homepage!evaluate.action";
		document.inputForm.submit();
	}
//-->
</script>
<fieldset>
	<legend><font color="#006676"><b>菜单评价&nbsp;-&nbsp;请输入您对该菜单：‘${submenu.name }’的意见或者建议</b></font></legend>
	<form id="inputForm" name="inputForm" action="homepage!menuview.action?submenuId=${submenuId }" method="post">
		<input type="hidden" value="${submenuId }" name="submenuId" id="submenuId"/>
		<table width="96%">
			<tr>
				<td nowrap="nowrap" width="70"><b>评价内容：</b></td>
				<td>
					<textarea id="valaContent" name="valaContent" style="width:480px;height:60px;">${valaContent }</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center" height="30">
					<%
						String memberId = Struts2Utils.getSession().getAttribute("memberId")==null?"":Struts2Utils.getSession().getAttribute("memberId").toString();		
						//System.out.println("jsp memberId="+memberId);		
						if ("".equals(memberId)) {
					%> 
						<input type="button" value="提 交" disabled="disabled"/>
						抱歉！菜单评价功能暂时只对会员开放，请您登录后再评价！
					<%} else {%>
						<input type="button" onclick="return submitConfirm();" value="提 交"/>
					<% }%>
				</td>
			</tr>
		</table>
	</form>
</fieldset>

</body>
</html>
