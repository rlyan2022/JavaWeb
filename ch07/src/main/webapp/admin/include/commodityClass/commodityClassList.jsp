<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>欢迎进入系统后台</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<LINK href="<%=basePath %>admin/images/syscome.files/Admin.css" rel=stylesheet>
<SCRIPT language=javascript src="<%=basePath %>images/syscome.files/Admin.js"></SCRIPT>

<STYLE type=text/css>.STYLE1 {
	FONT-WEIGHT: bold; COLOR: #0099ff
}
</STYLE>

<META content="MSHTML 6.00.2900.5726" name=GENERATOR></HEAD>
<BODY><BR>
<TABLE class=tableborder cellSpacing=1 cellPadding=3 width="95%" align=center
border=0>
</TABLE><BR>
<TABLE cellSpacing=1 cellPadding=3 width="95%" align=center bgColor=#6ab6b6
border=0>
  <TBODY>
  <TR>
    <TH colSpan=2 height=24>【商品种类列表】</TH>  </TR>
  <TR>
    <TD class=forumrow width="30%" height=24><div align="center">商品种类编号</div></TD>
  <TD class=forumrowhighlight width="70%"
      height=24><div align="center">商品种类名称</div></TD>
  </TR>
  <s:iterator value="commodityClasses" var="commodityClass" >
  <TR>
    <TD class=forumrow height=24><div align="center">${commodityClass.commodityClassId}</div></TD>
    <TD class=forumrowhighlight
      height=24><div align="center">${commodityClass.commodityClassName}</div></TD>
  </TR>
  </s:iterator>
  <TR>
    <TD class=forumrow height=24 colspan="2" align="center">
    <s:if test="page.hasPrePage">
		<a href="findAllCommodityClass.action?currentPage=1">首页</a>
		<a href="findAllCommodityClass.action?currentPage=${currentPage - 1}">上一页</a>
	</s:if>
	<s:else>
		<EM>首页</EM>
		<EM>上一页</EM>
	</s:else>
	<s:if test="page.hasNextPage">
		<a href="findAllCommodityClass.action?currentPage=${currentPage + 1}">下一页</a>
		<a href="findAllCommodityClass.action?currentPage=${page.totalPage}">尾页</a>
	</s:if>
	<s:else>
		<EM>下一页</EM>
		<EM>尾页</EM>
	</s:else>
    </TD>
  </TR>
  </TBODY></TABLE>
<BR>
<TABLE class=tableborder cellSpacing=1 cellPadding=3 width="95%" align=center 
border=0>
</TABLE><BR>
</BODY></HTML>
