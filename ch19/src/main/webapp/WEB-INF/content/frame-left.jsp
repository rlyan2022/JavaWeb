<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="cn.com.tienting.modules.security.springsecurity.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<%@ include file="/common/meta.jsp"%>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(${ctx}/admin/images/left.gif);
	
}
-->
</style>
<link href="${ctx}/admin/css/css.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">	
function tupian(idt){
    var nametu="xiaotu"+idt;
    var tp = document.getElementById(nametu);
    tp.src="${ctx}/admin/images/ico05.gif";//图片ico04为白色的正方形
	
	for(var i=1;i<30;i++)
	{
	  
	  var nametu2="xiaotu"+i;
	  if(i!=idt*1)
	  {
	    var tp2=document.getElementById('xiaotu'+i);
		if(tp2!=undefined)
	    {tp2.src="${ctx}/admin/images/ico06.gif";}//图片ico06为蓝色的正方形
	  }
	}
}

function list(idstr){
	var name1="subtree"+idstr;
	var name2="img"+idstr;
	var objectobj=document.getElementById(name1);
	var imgobj=document.getElementById(name2);
	
	
	//alert(imgobj);
	
	if(objectobj.style.display=="none"){
		for(i=1;i<10;i++){
			var name3="img"+i;
			var name="subtree"+i;
			var o=document.getElementById(name);
			if(o!=undefined){
				o.style.display="none";
				var image=document.getElementById(name3);
				//alert(image);
				image.src="${ctx}/admin/images/ico04.gif";
			}
		}
		objectobj.style.display="block";
		imgobj.src="${ctx}/admin/images/ico03.gif";
	}
	else{
		objectobj.style.display="none";
		imgobj.src="${ctx}/admin/images/ico04.gif";
	}
}

	function loginout() {
		alert("您已成功退出订餐系统后台管理模块！");
		window.top.location.reload();
	}

</SCRIPT>

<body>
<table width="198" border="0" cellpadding="0" cellspacing="0" class="left-table01">
  <tr>
    <TD>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="100%" height="55" background="${ctx}/admin/images/nav01.gif">
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					<td width="25%" rowspan="2"><img src="${ctx}/admin/images/ico02.gif" width="35" height="35" /></td>
					<td width="75%" height="22" class="left-font01">您好，<span class="left-font02">
						<%=SpringSecurityUtils.getCurrentUserName()%></span></td>
				  </tr>
				  <tr>
					<td height="22" class="left-font01">
						[&nbsp;<a href="${ctx}/j_spring_security_logout" onclick="return loginout();">退出</a>&nbsp;]&nbsp;
						[&nbsp;<a href="${ctx}/security/user!editPassword.action" class="changePassword" target="mainFrame">修改密码</a>&nbsp;]
					</td>
				  </tr>
				</table>
			</td>
		  </tr>
		</table>
		
		<!--  基本信息管理开始    -->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img1" id="img1" src="${ctx}/admin/images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('1');" >基本信息管理</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree1" style="DISPLAY: block;" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu1" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
						<a href="${ctx}/restaurant/restaurant.action" target="mainFrame" class="left-font03" onClick="tupian('1');">餐店信息</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu2" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%">
					<a href="${ctx}/restaurant/aboutme.action" target="mainFrame" class="left-font03" onClick="tupian('2');">简介管理</a></td>
				</tr>
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu3" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="${ctx}/notice/notice.action" target="mainFrame" class="left-font03" onClick="tupian('3');">公告管理</a></td>
				</tr>
				 <tr>
		           <td width="9%" height="20" ><img id="xiaotu4" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
		           <td width="91%"><a href="${ctx}/eatguide/eatguide.action" target="mainFrame" class="left-font03" onClick="tupian('4');">订餐指南</a></td>
		         </tr>
		        <tr>
		           <td width="9%" height="20" ><img id="xiaotu9" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
		           <td width="91%"><a href="${ctx}/restaurant/fetchtime.action" target="mainFrame" class="left-font03" onClick="tupian('9');">送餐时间</a></td>
		        </tr>
      </table>
		<!--  结束    -->
		
		<!--  系统管理开始    -->
		<security:authorize ifAnyGranted="SYSTEM_MANAGE">
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img2" id="img2" src="${ctx}/admin/images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('2');" >系统管理</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
               
		<table id="subtree2" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
			<security:authorize ifAnyGranted="USER_MANAGE">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu5" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="${ctx}/security/user.action" target="mainFrame" class="left-font03" onClick="tupian('5');">用户管理</a></td>
				</tr>
			</security:authorize>
			<security:authorize ifAnyGranted="AUTHORITY">
				<tr>
				  <td width="9%" height="20" ><img id="xiaotu7" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="${ctx}/security/role.action" target="mainFrame" class="left-font03" onClick="tupian('7');">权限管理</a></td>
				</tr> 
			</security:authorize>
      </table>
       </security:authorize>
		<!--  系统管理结束    -->	 

	  <!--  菜单管理开始    -->
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="8%" height="12"><img name="img3" id="img3" src="${ctx}/admin/images/ico04.gif" width="8" height="11" /></td>
                  <td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('3');" >菜单管理</a></td>
                </tr>
            </table></td>
          </tr>
      </table>
	  
	  <table id="subtree3" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
        
		<tr>
          <td width="9%" height="20" ><img id="xiaotu8" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/menu/menutype.action" target="mainFrame" class="left-font03" onClick="tupian('8');">菜单类型</a></td>
        </tr>
		<tr>
          <td width="9%" height="20" ><img id="xiaotu23" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/menu/submenu.action" target="mainFrame" class="left-font03" onClick="tupian('23');">菜单管理</a></td>
        </tr>
        <tr>
          <td width="9%" height="20" ><img id="xiaotu24" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/menu/menuevaluate.action" target="mainFrame" class="left-font03" onClick="tupian('24');">菜单评价</a></td>
        </tr>
        <tr>
          <td width="9%" height="20" ><img id="xiaotu25" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/frame!viewDefault.action" target="mainFrame" class="left-font03" onClick="tupian('25');">今日菜单销售情况</a></td>
        </tr>
      </table>

	  <!--  菜单管理结束    -->

	  <!--  订单管理开始    -->
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="8%" height="12"><img name="img4" id="img4" src="${ctx}/admin/images/ico04.gif" width="8" height="11" /></td>
                  <td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('4');" >订单管理</a></td>
                </tr>
            </table></td>
          </tr>
      </table>
	  
	  <table id="subtree4" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
        <tr>
          <td width="9%" height="20" ><img id="xiaotu11" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/orderform/orderformnote.action" target="mainFrame" class="left-font03" onClick="tupian('11');">今日订单打印</a></td>
        </tr>
		<tr>
          <td width="9%" height="20" ><img id="xiaotu12" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/restaurant/assignman.action" target="mainFrame" class="left-font03" onClick="tupian('12');">送餐员管理</a></td>
        </tr>
      </table>
	  <!--  订单管理结束    -->

	  <!-- 统计管理开始 -->
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="8%"><img name="img5" id="img5" src="${ctx}/admin/images/ico04.gif" width="8" height="11" /></td>
                  <td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('5');">统计管理</a></td>
                </tr>
            </table></td>
          </tr>
      </table>

	  <table id="subtree5" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
        <tr>
          <td width="9%" height="20"><img id="xiaotu13" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/statistics/salestatistics.action" target="mainFrame" class="left-font03" onClick="tupian('13');">销售统计</a></td>
        </tr>
      </table>
	  <!-- 统计管理结束-->

		
     <!-- 健康饮食开始 -->
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
        <tr>
          <td height="29"><table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td width="8%"><img name="img6" id="img6" src="${ctx}/admin/images/ico04.gif" width="8" height="11" /></td>
                <td width="92%"><a href="javascript:" target="mainFrame" class="left-font03" onClick="list('6');">健康饮食</a></td>
              </tr>
          </table></td>
        </tr>
      </table>
	  <table id="subtree6" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
        <tr>
          <td width="9%" height="20"><img id="xiaotu14" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td width="91%"><a href="${ctx}/healthdrink/healthdrinktype.action" target="mainFrame" class="left-font03" onClick="tupian('14');">饮食类别</a></td>
        </tr>
        <tr>
          <td height="20"><img id="xiaotu15" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
          <td><a href="${ctx}/healthdrink/healthdrink.action" target="mainFrame" class="left-font03" onClick="tupian('15');">饮食管理</a></td>
        </tr>
      </table>
	 <!-- 健康饮食结束-->

	 <!--会员管理开始-->
		<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img7" id="img7" src="${ctx}/admin/images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('7');" >会员管理</a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>

		<table id="subtree7" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
			<tr>
				  <td width="9%" height="23" ><img id="xiaotu16" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="${ctx}/member/member_operate.action" target="mainFrame" class="left-font03" 
						onClick="tupian('16');">会员管理</a></td>
			</tr>
			<tr>
				  <td width="9%" height="23" ><img id="xiaotu17" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="${ctx}/integral/integralset.action" target="mainFrame" class="left-font03" 
						onClick="tupian('17');">积分兑换设置</a></td>
			</tr>
			<tr>
				  <td width="9%" height="23" ><img id="xiaotu18" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="${ctx}/integral/integralnote!mamageQuery.action" target="mainFrame" class="left-font03" 
						onClick="tupian('18');">兑换记录查看</a></td>
			</tr>
			<tr>
				  <td width="9%" height="23" ><img id="xiaotu19" src="${ctx}/admin/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="${ctx}/member/leaveword.action" target="mainFrame" class="left-font03" 
						onClick="tupian('19');">留言管理</a></td>
			</tr>
      </table>
		<!--  会员管理结束    -->
	  </TD>
  </tr>
  <tr/>
  	  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="left-table02">
	  		<tr>
				<td height="30">欢迎使用网上订餐系统！</td>
			</tr>
			<tr>
				<td>
					技术支持：<a href="http://www.tienting.com" target="_blank">tienting.com</a>
				</td>
			</tr>
			<tr>
				<td>
					
				</td>
			</tr>
      </table>
  </tr>
</table>
</body>
</html>
