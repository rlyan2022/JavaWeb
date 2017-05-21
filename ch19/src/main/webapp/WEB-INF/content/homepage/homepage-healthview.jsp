<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %></title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />　
</head>
<body>
<div id="message"><s:actionmessage theme="mytheme"/></div>
  <form id="inputForm" name="inputForm" action="homepage!healthview.action?healthdrinkId=${healthdrinkId }" method="post" >
	<input type="hidden" name="healthdrinkId" value="${healthdrinkId}" />
	<table align="center" width="95%">
  		<td> 您所在的位置：<%=ConstantUtils.restaurantName %> >健康饮食 >
            <!--<s:if test="healthdrinktype != null"><a href="healthdrink!query.action?healthdrinktypeid=${healthdrinktype.id }">${healthdrinktype.typeName } ></a></s:if>-->
            <s:if test="healthdrinktype != null">${healthdrink.healthdrinktype.typeName } ></s:if>
          	正文 </td>
      </tr>
      <tr>
        <td align="center"><h3><font color="#FF6600">${healthdrink.title}</font></h3></td>
      </tr>
      <tr>
        <td >
        	<fmt:formatDate value="${healthdrink.issueTime}" pattern="yyyy年MM月  HH:mm"/>
          	<s:if test="source != null">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;来源:${healthdrink.source}</s:if>         　
          	<s:if test="expert != null"><font color="#FF9900">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${healthdrink.expert}</font></s:if>
          </td>
      </tr> 
      <tr>
        <td style="border-top:1px #cccccc dashed;"><br/>${healthdrink.content}</td>
      </tr>
	</table>
  </form>
</body>
</html>