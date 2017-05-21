<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page import="cn.com.tienting.modules.web.struts2.Struts2Utils" %>
<div class="page_top">
<div class="page_top_width">
<div class="page_top_word"><img src="${ctx }/images/dian.gif" width="6" height="10" align="middle" />
	<%
		String memberId = Struts2Utils.getSession().getAttribute("memberId")==null?"":Struts2Utils.getSession().getAttribute("memberId").toString();
		
		//System.out.println("jsp memberId="+memberId);
		
		if ("".equals(memberId)) {
	%>
		&nbsp; <span id="login_name">您好</span>，欢迎来<%=ConstantUtils.restaurantName %>！<span id="btn_login" style="cursor:pointer;">[请登陆]</span>
		<span id="btn_reg">&nbsp;[<a href="${ctx }/member/register.action" target="_blank">免费注册</a>]</span>
        <span id="btn_loginOut" style="display:none;cursor:pointer;">&nbsp;[退出]</span>
	<%} else { %>
		<span id="login_name">您好，<a href="${ctx }/member/member.action?loginCode=${loginCode }" target="_blank">${loginCode}</a></span>，欢迎来<%=ConstantUtils.restaurantName %>！<span id="btn_login" style=" display:none;cursor:pointer;">[请登陆]</span>
		<span id="btn_reg" style="display:none;">&nbsp;[<a href="${ctx }/member/register.action?loginCode=${loginCode }" target="_blank">免费注册</a>]</span>
        <span id="btn_loginOut" style="cursor:pointer;">&nbsp;[退出]</span>
	<%} %>
</div>
	
	<div class="page_top_nav"> 
		<a href="javascript:void(0)" onClick=this.style.behavior='url(#default#homepage)';this.setHomePage('http://<%=request.getServerName() %>');return false;>设置首页</a>&nbsp;
		<a href="${ctx }/homepage/homepage!aboutmequery.action?loginCode=${loginCode }" target="_blank">联系我们</a>&nbsp;
		<img src="${ctx }/images/help2.gif" width="16" height="13"/>
		<a href="${ctx }/homepage/homepage!eatguidequery.action?loginCode=${loginCode }" target="_blank">帮助</a>
	</div>
</div>
</div>
<div class="wai_container">
<div class="top_container">
<div class="logo"></div>
<div class="pic_top"><img src="${ctx }/upload/restaurant/ad.jpg" border="0" width="630" height="80"/></div>

</div>
<div class="menu">
<li><a href="${ctx}/tienting_standar.action?loginCode=${loginCode }">首 页</a></li>
<li><a href="${ctx }/homepage/homepage!aboutmequery.action?loginCode=${loginCode }" target="_blank">餐店简介</a></li>
<li><a href="${ctx}/homepage/homepage!menuquery.action?loginCode=${loginCode }" target="_blank">美食分类</a></li>
<li><a href="${ctx}/homepage/homepage!eatguidequery.action?loginCode=${loginCode }" target="_blank">订餐指南</a></li> 
<li><a href="${ctx}/member/member.action?loginCode=${loginCode }" target="_blank">会员中心</a></li>
<li><a href="${ctx}/homepage/homepage!orderqueryinit.action?loginCode=${loginCode }" target="_blank">订单查询</a></li>
<!--<li><a href="${ctx}/restaurant/aboutme!query.action" target="_blank">关于我们</a></li>-->
<li style="background:none;"><a href="${ctx}/homepage/homepage!leaveword.action?loginCode=${loginCode }" target="_blank" >我要建议</a></li>
</div>
<input type="hidden" name="loginCode" value="${loginCode }">
