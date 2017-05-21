<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>${title }</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />	
    <script type="text/javascript" src="${ctx}/js/jquery_hp.js"></script> 
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	 
</head>
<body> 
<%@ include file="/common/hander.jsp"%>
<div class="main_container">
<div class="main_left_container">
<div class="left_column"><div class="ico_jpg" style="background-image:url(images/jpg_active.jpg); width:45px; height:33px;  "></div>
<li>关于我们</li>
</div>
<div class="left_content">

	<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
		<form id="mainForm" action="aboutme!query.action" method="post">
		  	<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}"/>
			<!-- <input type="hidden" name="page.pageNo" id="pageNo" value="${page.pageNo}"/>
			<input type="hidden" name="page.orderBy" id="orderBy" value="${page.orderBy}"/>
			<input type="hidden" name="page.order" id="order" value="${page.order}" />-->
		  	<table>
				<s:iterator id="am" value="aboutmelist">
	  				<tr>
	  					<td><a href="aboutme!view.action?id=${am.id }" target="v_iframe">${am.title }</a></td>
	  					<td align="right"><fmt:formatDate value="${am.createTime}" pattern="MM-dd"/></td>
	  				</tr>
	  			</s:iterator>
			</table>
		  </form>
	</div>
</div>
<div class="left_foot"></div>
</div>
<br/>
<div class="main_right_container">

	<iframe width="100%" name="v_iframe" height="100%" src="aboutme!view.action?id=${id }" frameborder="0" scrolling="no"></iframe>
<!-- 
	<form id="inputForm" name="inputForm" action="notice!query.action?id=${id }" method="post" >
	<input type="hidden" name="id" value="${id}" />
	<table align="center" width="95%">
  		<tr>
  			<td>
  				您所在的位置：<%ConstantUtils.restaurantName %>- >关于我们 >正文
  			</td>
  		</tr>
		<tr>
			<td align="center"><h2><font color="#FF6600">${title}</font></h2></td>
		</tr>
		<tr>
			<td><fmt:formatDate value="${createTime}" pattern="yyyy年MM月  HH:mm"/></td>
		</tr>
		<tr>
			<td style="border-top:1px #cccccc dashed;"> <br/>			
				${content}
			</td>
		</tr>
	</table>	
</form>
 -->
</div><!-- main_right_container -->
</div>
<div style="clear:both;"></div>

<%@ include file="/common/footer_item.jsp"%>

</div>
<!--wai_container-->
</body>
</html>

