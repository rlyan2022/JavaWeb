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
		&nbsp;您好，欢迎来<%=ConstantUtils.restaurantName %>！&nbsp;[<a href="${ctx }/member/member.action">请登陆</a>]
		&nbsp;[<a href="${ctx }/member/register.action">免费注册</a>]
	<%} else { %>
		<a href="${ctx }/member/member.action?loginCode=${loginCode }">${loginCode}</a>，欢迎来<%=ConstantUtils.restaurantName %>!
        &nbsp;[<a href="${ctx }/member/member!loginOutCenter2.action" onclick="return loginout();">退出</a>]
	<%} %>
</div>
	 <div class="page_top_nav"> 
		<a href="javascript:void(0)" onClick=this.style.behavior='url(#default#homepage)';this.setHomePage('http://<%=request.getServerName() %>');return false;>设置首页</a>&nbsp;
		<a href="${ctx }/homepage/homepage!aboutmequery.action?loginCode=${loginCode }">联系我们</a>&nbsp;
		<img src="${ctx }/images/help2.gif" width="16" height="13"/>
		<a href="${ctx }/homepage/homepage!eatguidequery.action?loginCode=${loginCode }">帮助</a>
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
<li><a href="${ctx }/homepage/homepage!aboutmequery.action?loginCode=${loginCode }">餐店简介</a></li>
<li><a href="${ctx}/homepage/homepage!menuquery.action?loginCode=${loginCode }">美食分类</a></li>
<li><a href="${ctx}/homepage/homepage!eatguidequery.action?loginCode=${loginCode }" >订餐指南</a></li> 
<li><a href="${ctx}/member/member.action?loginCode=${loginCode }">会员中心</a></li>
<li><a href="${ctx}/homepage/homepage!orderqueryinit.action?loginCode=${loginCode }">订单查询</a></li>
<!--<li><a href="${ctx}/restaurant/aboutme!query.action" target="_blank">关于我们</a></li>-->
<li style="background:none;"><a href="${ctx}/homepage/homepage!leaveword.action?loginCode=${loginCode }">我要建议</a></li>
</div>
<script type="text/javascript">
	function loginout() {
		alert("您已成功退出会员中心！");
		window.location.reload();
	}
</script>
<input type="hidden" name="loginCode" value="${loginCode }">
