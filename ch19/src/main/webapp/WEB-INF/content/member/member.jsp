<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page import="cn.com.tienting.modules.web.struts2.Struts2Utils" %>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title><%=ConstantUtils.restaurantName %>-会员中心</title>
	<%@ include file="/common/meta.jsp"%>
	<script src="${ctx}/js/form.js" type="text/javascript"></script>
	<link href="${ctx}/css/default_hp.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/effects/jquery.messager.js"></script>
	<link href="${ctx}/css/sp_lee.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/main_hp.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript">
	
		function loginSubmit(){	 
			var loginCode = document.loginForm.elements["loginCode"].value;
			if (loginCode=="") {
				alert("抱歉！帐号不能为空，请输入帐号！");
				return false;
			}
			
			var password = document.loginForm.elements["password"].value;	
			if (password=="") {
				alert("抱歉！密码不能为空，请输入密码！");
				return false;
			}
			
			document.loginForm.action = "member!loginCenter.action";
			document.loginForm.submit();
		}
	</script>
	 
    <style type="text/css">
		<!--
		.STYLE1 {
			color: #FFFFFF;
			font-weight: bold;
		}
		
		.STYLE2 {
					color: #DD5800;
					//font-weight: bold;
				}
		//-->
    </style>
</head>
<body > 
<%@ include file="/common/hander_item.jsp"%>
<div class="main_container">
<div class="main_left_container">
<div class="left_column"><div class="ico_jpg" style="background-image:url(../images/jpg_login.jpg); width:27px; height:33px;"></div>
<li>会员中心</li>
</div>
<div class="left_content">
	<div style="WIDTH: 210px;  font-size:14px; color:#000000;">
		<div id="message" align="center"><s:actionmessage theme="mytheme"/></div>
		<!-- 会员未登录 ,显示登录界面--> 
		<%
		String sessionMemberId = Struts2Utils.getSession().getAttribute("memberId")==null?"":Struts2Utils.getSession().getAttribute("memberId").toString();
		//System.out.println("jsp sessionMemberId="+sessionMemberId);		
		if ("".equals(sessionMemberId)) {
		%>
		<form id="loginForm" name="loginForm" action="member.action" method="post">
			<input type="hidden" id="id" name="id" value="${id}" />
			<table>
				<tr>
					<td align="right">帐号：</td>
					<td><input type="text" id="loginCode" name="loginCode" style="width:140px;"/></td>
				</tr>
				<tr>
					<td align="right">密码：</td>
					<td><input type="password" id="password" name="password" style="width:140px;"/></td>
				</tr>
				<tr>
					<td align="center">&nbsp;</td> 
				    <td align="left"><input type="button" onclick="return loginSubmit();" value="登 录" style="border:1px #f25203 solid; background-color:#f1700a; color:#FFFFFF; line-height:23px; height:23px;"/>
			        &nbsp;&nbsp; <a href="#">忘记密码?</a></td>
				</tr>
			</table>			
		</form>
        
		<%} else { %>
		<!--会员已登录，显示会员的相关信息  -->
		<table align="center">
			<!-- 帐号 -->
			<tr>
				<td align="center">
					<a href="member!view.action?id=${id }" target="m_iframe">
						<span class="STYLE2">${loginCode }</span></a>，欢迎您！
				</td>
			</tr>
			<tr>
				<td align="center">
					<!-- 会员未上传头像 -->
					<c:choose>
						<c:when test="${empty photo}">
							<!-- 会员未上传头像,显示默认的头像 -->
							<img id="menu_img" width="120" height="90"
											src="${ctx}/upload/member/defaultphoto.jpg"/>
						</c:when>
						<c:otherwise>
							<img id="menu_img" width="120" height="90"
											src="${ctx}/upload/member/${photo}"/>
						</c:otherwise>
					</c:choose>					
				</td>
			</tr>
			<!-- 上次登录时间 -->
			<tr height="20">
				<td style="border-top:1px #cccccc dashed;">
					<s:if test="lastTime==null">
						<!-- 未登录过 -->
						<font color="red">您第一次登录，欢迎您！</font>
					</s:if>
					<s:else>
						您上次登录时间是：<fmt:formatDate value="${lastTime}" pattern="MM-dd HH:mm"/>
					</s:else>
				</td>
			</tr>
			<!--<tr height="20">
				<td style="border-top:1px #cccccc dashed;">
					您的总登录次数：${loginCount }
				</td>
			</tr>-->
			
			<!-- 控制面板 -->
			<tr height="30">
				<td height="25" align="center" bgcolor="#DD5800" style="border-top:1px #cccccc dashed;">
					<span class="STYLE1">控制面板</span> </td>
		  </tr>
			<tr height="20" >
				<td style="border-top:1px #cccccc dashed;">
					<img src="${ctx }/images/member_ico.gif" alt="" />
					<a href="member!input.action?id=${id }" target="m_iframe">个人设置</a>
				</td>
			</tr>
			<tr height="20" >
				<td style="border-top:1px #cccccc dashed;">
					<img src="${ctx }/images/member_ico.gif" alt="" />
					<a href="member!password.action?id=${id }" target="m_iframe">密码修改</a>
				</td>
			</tr>
			<tr height="20" >
				<td style="border-top:1px #cccccc dashed;">
					<img src="${ctx }/images/member_ico.gif" alt="" />
					<a href="member!order_init.action" target="m_iframe">我的订餐车</a>
				</td>
			</tr>
			<tr height="20" >
				<td style="border-top:1px #cccccc dashed;">
					<img src="${ctx }/images/member_ico.gif" alt="" />
					<a href="${ctx }/integral/integralnote.action" target="m_iframe">积分兑餐币</a>
				</td>
			</tr>
			<!--<tr height="20" >
				<td style="border-top:1px #cccccc dashed;">
					<img src="${ctx }/images/member_ico.gif" alt="" />
					<a href="${ctx }/help/memberhelp.html" target="blank">新手指南</a>
				</td>
			</tr>-->
			<tr height="20" >
				<td style="border-top:1px #cccccc dashed;">
					<img src="${ctx }/images/member_ico.gif" alt="" />
					<a href="member!loginOutCenter.action">安全退出</a>
				</td>
			</tr>
		</table>
		<%} %>
	</div> 
</div>
<div class="left_foot"></div>
<%if ("".equals(sessionMemberId)) { %>
<!-- 热门菜单显示方式1 -->
<div class="left_column">
<div class="ico_jpg" style="background-image:url(images/jpg_caishi.jpg); width:45px; height:44px; margin-bottom:-5px; margin-right:-9px;"></div>
<li>热门菜单排行</li></div>
<div class="left_content">
<table width="100%">
	<tr>
		<th>菜单名称</th>
		<th>单价(元)</th>
	</tr>
	<%int i=1; %>
	<s:iterator id="pmenu" value="submenulist">
		<tr>
			<td>
				<font class="STYLE2"><%=i %>.</font>
				<a href="${ctx }/homepage/homepage!menuview.action?submenuId=${pmenu.id }" target="m_iframe">${pmenu.name }</a></td>
			<td><font class="STYLE2">${pmenu.price }</font></td>
		</tr>		
		<%i++; %>
	</s:iterator>
</table> 
</div>
<div class="left_foot"></div>
<!-- 热门菜单显示方式1 -->
<%} else { %>
<!-- 热门菜单 -->
<div class="left_column">
<div class="ico_jpg" style="background-image:url(images/jpg_caishi.jpg); width:45px; height:44px; margin-bottom:-5px; margin-right:-9px;"></div>
<li>热门菜单排行</li></div>
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
<%} %>

</div>
<div class="main_right_container"> 
	<br/>
	<!-- 默认显示前１０热门菜单 -->
	<iframe width="100%" height="570" src="member!initMember.action" name="m_iframe" 
		allowtransparency="true" frameborder="0" scrolling="yes"></iframe>	
</div>
<br/>
<div class="clear"></div>
 <%@ include file="/common/footer_item.jsp"%>
</body>
</html>

