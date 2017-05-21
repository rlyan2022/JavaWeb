<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title}</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />	
    <script type="text/javascript" src="${ctx}/js/jquery_hp.js"></script> 
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	 
</head>
<body>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<form id="inputForm" name="inputForm" action="healthdrink!view.action?id=${id }" method="post" >
	<input type="hidden" name="id" value="${id}" />
     <table style="margin-top:7px;" width="100%" align="center" cellpadding="5" cellspacing="5">
      <tr>
        <td> 您所在的位置：慧鼎订餐 >健康饮食 >
            <!--<s:if test="healthdrinktype != null"><a href="healthdrink!query.action?healthdrinktypeid=${healthdrinktype.id }">${healthdrinktype.typeName } ></a></s:if>-->
            <s:if test="healthdrinktype != null">${healthdrinktype.typeName } ></s:if>
          	正文 </td>
      </tr>
      <tr>
        <td align="center"><h2><font color="#FF6600">${title}</font></h2></td>
      </tr>
      <tr>
        <td >
        	<fmt:formatDate value="${issueTime}" pattern="yyyy年MM月  HH:mm"/>
          	<s:if test="source != null">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;来源:${source}</s:if>         　
          	<s:if test="expert != null"><font color="#FF9900">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;作者：${expert}</font></s:if>
          </td>
      </tr> 
      <tr>
        <td style="border-top:1px #cccccc dashed;"><br/>&nbsp;&nbsp;${content}</td>
      </tr>
      <s:if test="sign!=null"> 
		<tr>
			<td align="center" height="30">				
				<input type="button" value="返　回" onclick="history.back();" />					
			</td>
		</tr>
		</s:if>
    </table>
</form>
</body>
</html>