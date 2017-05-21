<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>健康饮食</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />	
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx}/js/jquery_hp.js"></script> 
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	 
</head>
<body> 
<%@ include file="/common/hander.jsp"%>
<div class="main_container">
<div class="main_left_container">
<div class="left_column"><div class="ico_jpg" style="background-image:url(images/jpg_active.jpg); width:45px; height:33px;  "></div>
<li>健康饮食</li>
</div>
<div class="left_content">

	<div style="WIDTH: 210px;  font-size:14px; color:#000000;">
		<form id="mainForm" action="healthdrink!query.action" method="post">
		  	<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
			<input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
			<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
			<input type="hidden" name="page.order" id="order" value="${page.order}" />
		  	<table>
				<s:iterator id="eg" value="page.result">
	  				<tr>
	  					<td><s:if test="healthdrinktype != null">[<a href="healthdrink!query.action?healthdrinktypeid=${eg.healthdrinktype.id }">${eg.healthdrinktype.typeName }</a>]</s:if>
	  						<a href="healthdrink!view.action?id=${eg.id }&healthdrinktypeid=${healthdrinktypeid }" target="v_iframe">${eg.title }</a></td>
	  					<td align="right"><fmt:formatDate value="${eg.issueTime}" pattern="dd/MM"/></td>
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

	<iframe width="100%" height="100%" name="v_iframe" src="healthdrink!view.action?id=${id }" frameborder="0" scrolling="no"></iframe>

<!-- 	<form id="inputForm" name="inputForm" action="healthdrink!view.action?id=${id }" method="post" >
	<input type="hidden" name="id" value="${id}" />
    <table style="margin-top:7px;" width="100%" align="center" cellpadding="5" cellspacing="5">
      <tr>
        <td> 您所在的位置：慧鼎订餐 ><a href="healthdrink!query.action">健康饮食</a> >
            <s:if test="healthdrinktype != null"><a href="healthdrink!query.action?healthdrinktypeid=${healthdrinktype.id }">${healthdrinktype.typeName } ></a></s:if>
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
        <td style="border-top:1px #cccccc dashed;"><br/>${content}</td>
      </tr>
    </table>
</form> -->

</div><!-- main_right_container -->

</div>
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>

</div>
<!--wai_container-->
</body>
</html>

