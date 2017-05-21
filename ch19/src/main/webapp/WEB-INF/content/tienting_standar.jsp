<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 

<title>网上订餐系统--首页</title>

<script type="text/javascript" src="js/jquery_hp.js"></script> 
<script type="text/javascript" src="js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="js/jquery-tool/tools.tooltip-1.0.2.js"></script>

<link type="text/css" href="css/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
<link href="css/sp_lee.css" rel="stylesheet" type="text/css" />
<link href="css/main_hp.css" rel="stylesheet" type="text/css" />

<style type="text/css">
/* simple css-based tooltip */ 
div.tooltip { 
    background-color:#FFEEEE; 
    outline:1px solid #669; 
    border:1px solid #000; 
    padding:10px 15px; 
    width:200px; 
    display:none; 
    text-align:left; 
    font-size:12px; 
    z-index: 100;
    zoom: 100;
    position:absolute;
 
    /* outline radius for mozilla/firefox only */ 
    outline-radius:5px; 
    -moz-outline-radius:5px; 
    -webkit-outline-radius:5px; 
}

.remove {
cursor: pointer;
}

.booking {
	cursor: pointer;
}
.popular {
	cursor: pointer;
	font-size: 11px;
}

#fp:first-letter {
	font-size : 300%;
	/*font-weight : bold;*/
	float : left;
	margin-right: 3px;
}

#preview{border:1px solid #cccccc; color:#fff;  display:none; position:absolute;}
</style>
<script language="javascript">
var f_val;
var min_val;
var max_val;
$(function(){
	$("#search_btn").css({"cursor":"pointer"});
	$("#search_btn").click(function(){
	f_val = $("#textfield4").val(); //lee
	min_val = $("#textfield5").val(); //lee
	max_val = $("#textfield6").val(); //lee
	if(f_val==''&& min_val==''&& max_val=='')
	{ alert("搜索内容不能为空");}
	else{
	$("#search_result_list").load("tienting_standar!search.action?menuName="+f_val+"&startPrice="+min_val+"&endPrice="+max_val);
	$("#search_result").slideDown("fast");
	}
	});

	$("#search_close").click(function(){
	$("#search_result").slideUp("fast");
	
	});
	
	$("#real_name").blur(function(){
		$("#real_name + span").html("<img src='images/loading_left.gif'/>"); 
		var real_namr_reg = /^[\u4e00-\u9fa5]{2,4}$/;
		var real_name_val = $(this).val();
		if(!real_namr_reg.test(real_name_val))
		{          
			$("#real_name + span").html("<img src='images/unchecked.gif'/>"); 
		}
		else
		{
			$("#real_name + span").html("<img src='images/checked.gif'/>"); 
		}
	});
	
	$("#telephone").blur(function(){
		$("#telephone + span").html("<img src='images/loading_left.gif'/>"); 
		//telephone_reg=/^(\d{3,4}\-?)?[1-9]\d{7,11}$/;
		telephone_reg=/^(\d{3,4}\-?)?[1-9]\d{6,10}$/;
		var telephone_val = $(this).val();  
        if(!telephone_reg.test(telephone_val))    
		{          
			$("#telephone + span").html("<img src='images/unchecked.gif'/>"); 
		}
		else
		{
			$("#telephone + span").html("<img src='images/checked.gif'/>"); 
		}
	});
	
	$("#assignAddress").blur(function(){
		$("#assignAddress + span").html("<img src='images/loading_left.gif'/>"); 
		assignAddress_reg=/^([^\x00-\xff])|([a-zA-Z])/;
		var assignAddress_val = $(this).val();  
        if(!assignAddress_reg.test(assignAddress_val))    
		{          
			$("#assignAddress + span").html("<img src='images/unchecked.gif'/>"); 
		}
		else
		{
			$("#assignAddress + span").html("<img src='images/checked.gif'/>"); 
		}
	});

   var now= new Date();
   var hour=now.getHours();
   var minute=now.getMinutes();
   
   	$("#time_select").change(function(){
		var time_slelct_val = $("#time_select").val();
		var time_slelct_arr = time_slelct_val.split(":");   
		var time_sum = (time_slelct_arr[0]-hour)*60+(time_slelct_arr[1]-minute);
		if(time_sum<20)
		{
			//alert("送餐时间必须大于当前时间20分钟");
			$("#time_select ~ span").html("送到时间需在订餐20分钟后");
		}
		else
		{
			$("#time_select ~ span").html("");
		}
	});

	$(".simple_shopcar_btn_ok").css({"cursor":"pointer"});
	$(".simple_shopcar_btn_ok").click(function(){
		$("#hidden_total").val($(".trade-amount").text());
		var errot_sum = 0;
		if($("#hidden_total").val() == 0){
			alert("您还没有选择菜单，请先选择菜单再提交您的订餐信息！");
			errot_sum++;
		}
		if($("#buy_change option:selected").text() == "餐币付款"){
			if(parseInt($("#mealCurrency").val()) - parseInt($(".trade-amount").text()) <0){
			alert("您的餐币不够付款，请选择上门收费！");
			errot_sum++;
			}
		}
		var real_namr_reg = /^[\u4e00-\u9fa5]{2,4}$/;
		var real_name_val = $("#real_name").val();
		if(!real_namr_reg.test(real_name_val))
		{          
			$("#real_name + span").html("<img src='images/unchecked.gif'/>");
			errot_sum++;
		}
		else
		{
			$("#real_name + span").html("<img src='images/checked.gif'/>"); 
			
		}
		$("#telephone + span").html("<img src='images/loading_left.gif'/>"); 
		//telephone_reg=/^(\d{3,4}\-?)?[1-9]\d{7,11}$/;
		telephone_reg=/^(\d{3,4}\-?)?[1-9]\d{6,10}$/;
		var telephone_val = $("#telephone").val();  
        if(!telephone_reg.test(telephone_val))    
		{          
			$("#telephone + span").html("<img src='images/unchecked.gif'/>"); 
			errot_sum++;
		}
		else
		{
			$("#telephone + span").html("<img src='images/checked.gif'/>"); 
		}

		assignAddress_reg=/^([^\x00-\xff])|([a-zA-Z])/;
		var assignAddress_val = $("#assignAddress").val();  
        if(!assignAddress_reg.test(assignAddress_val))    
		{          
			$("#assignAddress + span").html("<img src='images/unchecked.gif'/>"); 
			errot_sum++;
		}
		else
		{
			$("#assignAddress + span").html("<img src='images/checked.gif'/>"); 
		}
		var time_slelct_val = $("#time_select").val();
		var time_slelct_arr = time_slelct_val.split(":");   
		var time_sum = (time_slelct_arr[0]-hour)*60+(time_slelct_arr[1]-minute);
		if(time_sum<20)
		{
			//alert("送餐时间必须大于当前时间20分钟");
			$("#time_select ~ span").html("送到时间需在订餐20分钟后");
			errot_sum++;
		}
		else
		{
			$("#time_select ~ span").html("");
		}
		
		if(errot_sum == 0){
		    var cache_1 = "您的订单如下\n\r------------------------\n\r\n\r";
			var i=0;
			$(".name").each(function(){			
				cache_1 += $(this).text()+"      数量:"+$(".count_sum_val").eq(i).val()+"      单价:"+$(".price").eq(i).text()+"\n\r";
				i++;
			});
			cache_1 += "------------------------------\n\r";
			cache_1 += "\n\r联系人:"+$("#real_name").val();
			cache_1 += "\n\r送餐电话:"+$("#telephone").val();
			cache_1 += "\n\r送餐地址:"+$("#assignAddress").val();
			cache_1 += "\n\r送餐时间:"+$("#time_select").val();
			cache_1 += "\n\r收费方式:"+$("#buy_change option:selected").text();
			cache_1 += "\n\r总价:￥"+$("#hidden_total").val();
					
			if(window.confirm(cache_1)){		
				var ordertag = "<%=request.getSession().getAttribute("ordertag")==null?"":request.getSession().getAttribute("ordertag")%>";
				if (ordertag=="Y") {
					removeId();	
				} 
				document.inputForm.action="tienting_standar!save.action";				
				document.inputForm.submit();
			}
		}
		
	});
/*------------------dislog---------------------*/
		$("#dialog").dialog({
		autoOpen:false,
		//buttons:{"确 定":function(){$(this).dialog("close");}},
		closeOnEscape:true,
		//hide:"slide",
		//show:"slide",
		modal:true,
		bgiframe:true,
		title:"用户登录",
		minWidth:350,
		width:350
		});
	$("#btn_login").click(function(){
		$("#dialog").dialog("open");
	});
	$("#btn_submit").click(function(){
		var get_loginCode = $("#get_loginCode").val();
		var get_password = $("#get_password").val();
		$("#dialog_cache").load($("#url_cache").text()+"/member/member!login.action?loginCode="+get_loginCode+"&password="+get_password+"&t="+Math.random());
	});
	$("#btn_cancle").click(function(){
		$("#dialog").dialog("close");
	});
	$("#btn_loginOut").click(function(){
		$("#dialog_cache").load($("#url_cache").text()+"/member/member!loginOut.action?t="+Math.random());
		
		$("#login_name").text("您好");
		$("#btn_login").show();
		$("#btn_reg").show();
		$("#btn_loginOut").hide();	
		//alert($("#url_cache").text());
	});
	//alert($("#dialog_cache").html());
	
}); 

//移除session（ordertag）
function removeId(){
	$.ajax({
		type: "POST",
		url: "tienting_standar!removeId.action"
	});			
}
</script>

<script language="javascript">
	$(document).ready(function(){
		window.dinner = {
			//订购主菜
			bookMain:function(name,price,count,menuId){
				$(".trade-content").data("bookMain")(name,price,count,menuId);
			},
			//删除主菜
			removeMain:function(){
				$(this).removeAttr("onClick");
				$(this).parents(".trade-main:last").fadeOut(function()  { 
					$(this).remove();
					//$(this).data("bookEvent")();
					$(".trade-content").data("price_sum")();
				});
			},
			//清空购物车
			clear:function(){
				$(".trade-content").data("clear")();
				$(".trade-amount").html("0");
				$("#hidden_total").val("0");				
			},
			
			price_sum:function(count_sum,price_change){
				//$(".trade-content").data("price_sum")();
				var count_sum_change = $(this).parents(".trade-main").children(".count").children(".count_sum_val").val();
				//alert(count_sum_change);
				var $price_change = $(this).parents(".trade-main").children(".price");
				//alert($price_change);
				$(this).parents(".trade-main").data("count_change")(count_sum_change,$price_change);
			}
			/*,
			//隐藏配菜
			hideCostar:function(){		
			},
			//显示配菜
			showCostar:function(){			
			}*/
		}

		$(".trade-content").each(function(){
			var $tr;
			var _this = this;
			var $trade = $(this);
			
			//订购主菜
			$(this).data("bookMain",function(name,price,count,menuId){
			var add_sum=0;
			var name_num=0;
			var i=0;
			
				/*--------------lee---------------*/
				$(".name").each(function(){
					i++;
					if($(this).text()== name)
					{
						add_sum=1;
						name_num=i;
					}					
				})
				//alert(add_sum);
				/*--------------lee---------------*/
				if(add_sum==0){
				$tr = $("<tr class='trade-main'></tr>").appendTo(_this);
				$tr.append("<td width='39%' class='name'>"+name+"<input type='hidden' name='mainMenuIds' value='"+menuId+"'/></td>")
				   .append("<td width='19%' align='center' class='count'><input name='count' type='text' class='count_sum_val' size='2' maxlength='2' value='"+count+"' onkeyup=\"dinner.price_sum.call(this)\"  style=\"ime-modeisabled\" onkeydown=\"if(event.keyCode==13)event.keyCode=9\" onKeyPress=\"if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false\" /></td>")
				   .append("<td width='22%' align='center' class='price'>￥"+price+"</td>")
				   .append("<td width='20%' align='left' onclick=\"dinner.removeMain.call(this)\" class=\"remove\">删除</label></td>");
				
				
				$tr.data("count",parseInt(count)).data("name",name).data("price",parseFloat(price));
				$tr.data("length",0);

				var $amount = $("<tr class='trade-fore' style=\"display:none\"></tr>").insertAfter($tr);
				$amount.append("<td colspan='4'>预计:<span class='number'></span></td>");

				$tr.hide();
				$tr.fadeIn();
							
				//$(".speak_for_dialog").data("bookMain_event")($tr);

				//删除主菜
				//$tr.data("remove",function(){
					
				//	$trade.data("bookEvent")();
				//});
				$tr.data("count_change",function(count_sum,price_change){
					price_change.html("￥" + (count_sum * price));
					$(".trade-content").data("price_sum")();
				})
			
				//订购事件 计算总计
				$trade.data("bookEvent",function(){
					$(".trade-content").data("price_sum")();
				});
				$trade.data("bookEvent")();
				}
				if(add_sum==1)
				{
				 	var count_txt = $(".count_sum_val").eq(name_num-1).val();
					$(".count_sum_val").eq(name_num-1).val(parseInt(count_txt)+1);
					
					var price_txt = price*(parseInt(count_txt)+1);
					$(".price").eq(name_num-1).html("￥"+price_txt);
					
					var all_price = $(".trade-amount").text();
					all_price = parseInt(all_price) + parseInt(price);
					$(".trade-amount").text(all_price);	
				}
			});
		});
	
		$(".trade-content").data("clear",function(){
			$(".trade-content").empty(); 
			
		});
		$(".trade-content").data("price_sum",function(){
			num = 0;
			$(".trade-main").each(function(){
			var price_txt = $(this).children(".price").text().split("￥");
			num = parseInt(num) +  parseInt(price_txt[1]);
			});		
			$(".trade-amount").html(num);
			$("#hidden_total").val(num);
		})
});
</script>
 
</head>
<body>
<!-- 页头 -->
<%@ include file="/common/hander.jsp"%>

<div class="main_container">
	<!-- 左侧界面 start -->
	<div class="main_left_container">
		<!--公告、指南 start-->
		<div class="left_column">
			<div class="ico_jpg" style="background-image:url(images/jpg_active.jpg); width:45px; height:33px;  "></div>
			<li>最新公告</li>
		</div>
		<div class="left_content">
			<div style="WIDTH: 210px;  font-size:12px; color:#000000;" >
				<!-- <a href="#"><b>${notice.title }</b><br/>${notice.summary }</a> -->
				<table width="100%" summary="网上订餐系统">
					<!-- 餐店公告 -->
					<s:iterator id="nlist" value="noticelist">
						<tr>
							<td align="left" valign="top">
								<a href="${ctx }/homepage/homepage!noticequery.action?noticeId=${nlist.id }" target="_blank">
								<c:choose>
									<c:when test="${fn:length(nlist.title)>=12}">
										${fn:substring(nlist.title,0,12)}...
									</c:when>
									<c:otherwise>
										${nlist.title }
									</c:otherwise>
								</c:choose>														
								</a>
							</td> 
							<td align="right" valign="top"><fmt:formatDate value="${nlist.recordTime }" pattern="MM-dd"/></td>
						</tr>
					</s:iterator> 
					
					<!-- 订餐指南 -->
					<tr>
						<td colspan="2" align="left"><img src="images/index_dczn.jpg" /></td>
					</tr>	
					<s:iterator id="egid" value="eatguidelist">
						<tr>
							<td>
								<a href="${ctx }/homepage/homepage!eatguidequery.action?eatguideId=${egid.id }" target="_blank">
								<c:choose>
									<c:when test="${fn:length(egid.title)>=12}">
										${fn:substring(egid.title,0,12)}...
									</c:when>
									<c:otherwise>
										${egid.title }
									</c:otherwise>
								</c:choose>														
								</a>
							</td> 
							<td align="right" valign="top"><fmt:formatDate value="${egid.recordTime }" pattern="MM-dd"/></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
		<div class="left_foot"></div>
		<!--公告、指南 end-->

		<!--订餐车-->
		<div class="left_column">
			<div class="ico_jpg" style="background-image:url(images/jpg_gouwu.jpg);width:45px; height:36px; margin-bottom:-5px; margin-right:-9px;">
			</div>
			<li>订餐车</li>
		</div>
		<div class="left_content" style="font-size:12px;">
			 <form id="inputForm" name="inputForm" action="tienting_standar.action" method="post">
				  <table width="210" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
					  <td width="39%" align="left"><strong>菜单名称</strong></td>
					  <td width="19%" align="center"><strong>数量</strong></td>
					  <td width="22%" align="center"><strong>单价</strong></td>
					  <td width="20%"><strong><span onclick="dinner.clear();" style="cursor: pointer;">清空</span></strong></td>
					</tr>
				  </table>
				  <div class="car_flow">	
					<table class="trade-content" width="210" border="0" align="center" cellpadding="0" cellspacing="0">
					
					</table>
				  </div>
				  <div style="margin-top:10px; ">
						<div style=" font-weight:bold; margin:12px 0 0 130px; ">
							总价：￥<label class="trade-amount" >0</label>
							<input id="hidden_total" type="hidden" name="total" value="0"/>
						</div>
				   </div>
				   <hr style="height:1px; color:#999999;"/>
				   <table width="100%" height="150" border="000" cellpadding="0" cellspacing="0">
					 <tr>
					   <td width="29%" align="center" valign="middle">联 系 人:</td>
					   <td width="71%"><label> 
						 <input id="real_name" name="relationMan" value="${relationMan }" type="text" size="18" maxlenght="20" style="width:120px;"/>&nbsp;<span></span>
					   </label></td>
					 </tr>
					 <tr>
					   <td align="center" valign="middle">联系电话:</td> 
					   <td><input name="relationPhone" value="${relationPhone }" type="text" size="18" maxlenght="20" id="telephone" style="width:120px;"/>&nbsp;<span></span></td>
					 </tr>
					 <tr>
					   <td align="center" valign="middle">送餐地址:</td>
					   <td><input name="assignAddress"  value="${assignAddress }" type="text" id="assignAddress" size="18" maxlenght="30" style="width:120px;" />&nbsp;<span></span></td>
					 </tr>
					 <tr>
					   <td align="center" valign="middle">其它备注:</td>
					   <td><textarea name="remark" value="${remark }" cols="15" rows="2"  title="请输入您的其它要求"  style="width:140px;"></textarea></td>
					 </tr>
					 <tr>
					   <td align="center" valign="middle">送到时间:</td>
					   <td>
							<select id="time_select" name="fetchTime" style="width:60;height:18;">			  
								<c:forEach items="${fecthTimeList}" var="ftm">
									<option value="${ftm }">${ftm }</option>
								</c:forEach>                
							</select>
							<select id="buy_change" name="payMode" style="width:75;height:18;"  >
								<option value="1" selected="selected">上门收费</option>
								<option value="2" >餐币付款</option>
							</select>
							<input id="mealCurrency" type="hidden" value="${mealCurrency }" />
							<br/>
							<span style="color:#FF0000;"></span>
						 </td>
					 </tr>
				   </table>
				   <div>
						<div class="simple_shopcar_btn_ok">确认购物</div> 
				   </div>
			   </form>
		</div>
		<div class="left_foot"></div>
		<!--订餐车结束-->

		<!--积分排行 start-->
		<div class="left_column">
			<div class="ico_jpg" style="background-image:url(images/jpg_paihang.jpg); width:27px; height:33px;  "></div>
			<li>积分排行榜<img src="images/left_more.jpg" width="46" height="18" /></li>
		</div>
		<div class="left_content">
		  <table width="100%" height="100" border="000" cellpadding="0" cellspacing="0">
			<tr>
			  <td align="center"><strong>排名</strong></td>
			  <td align="center"><strong>会员帐号</strong></td>
			  <td align="center"><strong>积分</strong></td>
			</tr>
			<%int top_number=1;%>
			<s:iterator id="top_c" value="customerlist">
			<tr>
				<td style="border-bottom:1px #cccccc dashed;" align="center"><%=top_number%></td>
				<td style="border-bottom:1px #cccccc dashed;" align="center">
					${top_c.loginCode }
				</td>
				<td style="border-bottom:1px #cccccc dashed;" align="center">
					<font color="#DD5800">${top_c.totalIntegral }</font>
				</td>
			</tr>	
			<%top_number++;%>
			</s:iterator>
		  </table>
		</div>
		<div class="left_foot"></div>
		<!--积分排行 end-->
	</div>
	<!-- 左侧界面 end -->

	<!-- 右侧界面 start -->
	<div class="main_right_container">
	<!--<div class="ad_main"><img src="images/ad/ad_1.jpg" width="735" height="184" /></div>-->
		<!-- 推荐菜单 start -->
		<div class="right_column">
		  <li>推荐菜单<a href="#"><img src="images/right_more.jpg" alt="" width="43" height="13" /></a></li>
		</div>
		<div class="right_new_content" style="height:340px;">
			<% int c_count = 0;%>
			<s:iterator value="commendMenus" id="cmenus">
				<div class="right_new_list">
					<c:choose>
						<c:when test="${empty cmenus.photo}"><img src="images/new/1.jpg" width="120" height="80"/></c:when>
						<c:otherwise><img src="${ctx}/upload/submenu/${cmenus.photo }" width="120" height="80"/></c:otherwise>
					</c:choose>
					<div class="list_word">${cmenus.name}</div> 
					<div class="list_money"><img src="images/ico_bi.jpg" width="12" height="10" /> 价格：￥${cmenus.price }</div>
					<div class="list_money"><img src="images/btn_buy.jpg" width="82" height="20" class="booking" onclick="dinner.bookMain('${cmenus.name}',${cmenus.price},1,${cmenus.id})" /></div>
				</div>
				<%
					++c_count;
					
					if (c_count%5==0) {
				%>
					<div class="clear"></div>
				<%}%>
			</s:iterator>
		</div>
		<div class="clear"></div>
		<!-- 推荐菜单 end -->

		<!--热门菜单top 10-->
		<div class="popular_main">
			<table width="100%">
				<tr>
					<td width="100%" height="20" bgcolor="#e3ecf1" colspan="8" valign="baseline">
						<font style="font-size:14px; color:#DD5800; font-weight:bold;">热门菜单 top 10</font> 
					</td>
				</tr>
				<tr>
					<th>菜单名称</th>
					<th>价格</th>
					<th>已销售</th>
					<th>操作</th>
					<th>菜单名称</th>
					<th>价格</th>
					<th>已销售</th>
					<th>操作</th>
				</tr>
				<%int count_p=0; %>
				<s:iterator id="pmenu" value="submenulist">
					<%if (count_p%2==0){ %>
					<tr>
					<%}%> 
						<td style="border-bottom:1px #cccccc dashed;" class="preview" onmouseover="m1('${pmenu.photo}')">
							<font style="font-size:14px; color:#DD5800; font-weight:bold;"><%=count_p+1 %>.</font> &nbsp;&nbsp;${pmenu.name }
						</td>
						<td style="border-bottom:1px #cccccc dashed;" align="center">
							<font class="STYLE2">￥${pmenu.price }</font>	
						</td>
						<td style="border-bottom:1px #cccccc dashed;" align="center">
							<font class="STYLE2">${pmenu.sellNumber }</font>份	
						</td>
						<td style="border-bottom:1px #cccccc dashed;" align="center">
							<img src="images/btn_buy2.gif" width="39" height="16" class="booking" onclick="dinner.bookMain('${pmenu.name}',${pmenu.price},1,${pmenu.id})" />
						</td>
					<%if (count_p%2==1){ %>
					</tr>
					<%} count_p++;%>		
				</s:iterator>
				<%//菜单为奇数时补全html
				if (count_p%2 > 0) { %>
					<td style="border-bottom:1px #cccccc dashed;" colspan="4"> 
				</tr>
				<%} %>
			</table>
		</div>
		<!--热门菜单top 10 end-->

		<!--菜单搜索 start-->
		<div class="search_main">
			<div class="search">
				美食搜索：&nbsp;&nbsp;
				 菜单名称
				  <input name="textfield4" type="text" id="textfield4" size="20" /> 
				  价格范围
				  <input name="textfield5" type="text" id="textfield5" size="3" maxlength="3"  style="ime-modeisabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false" />
				  至
				  <input name="textfield6" type="text" id="textfield6" size="3" maxlength="3"  style="ime-modeisabled" onkeydown="if(event.keyCode==13)event.keyCode=9" onKeyPress="if ((event.keyCode<48 || event.keyCode>57)) event.returnValue=false"/>
				  元  <img id="search_btn" src="images/btn_search.jpg" width="62" height="22" align="absmiddle" />
			</div>
		</div>
		<div id="search_result">
			<div id="search_result_list"></div>
			<div id="search_close" style="cursor:pointer;"><img id="search_btn" src="images/btn_search_close.jpg"/></div>
		</div>
		<!--菜单搜索 end-->

		<!-- 按类型显示菜单信息 -->
		<div class="sp_wai"><!--sp_wai start-->
			<div class="Sp_left"><!--Sp_left start-->
				<div class="Icon_Button_up" align="center"></div>
				<div class="Sp_content">
					<div id="Sp_sub_1">
						<%  
							int mt_row = 1;
							int count = 0;
						%>
						<ul>
							<s:iterator id="mt" value="menutypes">
								<li id="menu<%=mt_row%>" class="Sp_left_content">${mt.name }</li>
								<%++mt_row; %>
							</s:iterator>
						</ul>					
					</div>
					
					<%if (mt_row<7){%>
						<div id="Sp_sub_1"> 
							<table>
								<tr><td height="<%=(8-mt_row)*23%>">&nbsp; </td></tr>
							</table>
						</div>
					<%} else {%> 
						<div id="Sp_sub_1"> 
							<table>
								<tr><td height="5">&nbsp;</td></tr>
							</table>
						</div>
					<%}%>
					<%mt_row = 1; %>
					
					<div id="Sp_sub_2"></div>
				</div>  
				<div class="Icon_Button_dowm"></div>
			</div><!--Sp_left end-->

			<div class="content"><!--content start-->
				<s:iterator id="mt2" value="menutypes">
				<%if (mt_row == 1) {%>
					<div id="subMenu<%=mt_row %>" class="Sp_right_content sp_index_table" style="display:block;" >
				<%} else { %>
					<div id="subMenu<%=mt_row %>" class="Sp_right_content sp_index_table">
				<%} %>
					  <table width="630" height="207" border="000" align="center" cellpadding="0" cellspacing="0">
						<tr>
						  <td width="185"><strong>名称</strong></td>
						  <td width="70"><strong>价格</strong></td>
						  <td width="60"><strong>操作</strong></td>
						  <td width="185"><strong>名称</strong></td>
						  <td width="70"><strong>价格</strong></td>
						  <td width="60"><strong>操作</strong></td>
						</tr>
						<s:iterator id="smid" value="#mt2.submenus">
							<%if (count%2 == 0) { %>
								<tr>
							<%}%>
									<td class="preview" onmouseover="m1('${smid.photo}')">${smid.name}</td>
									<td>￥${smid.price }</td>
									<td><img src="images/btn_buy2.gif" width="39" height="16" class="booking" onclick="dinner.bookMain('${smid.name}',${smid.price},1,${smid.id})" /></td>
							<%if (count%2 == 1) { %>
								</tr>
							<%}
								++count; 	
							%>
						</s:iterator>
							<!--单列记录则补全列数-->
							<%if (count%2 > 0){ %>
									<td colspan="3">&nbsp;</td>　 
								</tr>
							<%} %>
							
							<%if (count<11){%>
							<tr>
							  <td colspan="6" height="<%=(9-count)*17%>">&nbsp;</td> 
							</tr>
							<%}%>
					  </table>
					</div>	
					<%++mt_row; 
					  count = 0; //下一个循环
					%>
				 </s:iterator>		 
			</div><!--content end-->
			<script type="text/javascript" src="js/sp_lee.js"></script> 
		</div><!--sp_wai end-->
		<div class="clear"></div>
		<!-- 按类型显示菜单信息 结束-->

		<!--服务指南　start-->
		<div class="index_foot_news">
			<h5><div>&nbsp;</div>
			温馨提示</h5>
			<div class="clear"></div> 	
			<ul>
				<%if (null == ConstantUtils.service || ConstantUtils.service.length()<=5) { %>
					<p id="fp">提</p>醒各位街坊,为了能让您在用餐时间能准时的享受到我们提供的餐食,请您提前半个小时订餐!
				<%} else {
					if (ConstantUtils.service.substring(0,3).equals("<p>")) {
				%>
						<p id="fp"><%=ConstantUtils.service.substring(3,4) %></p><%=ConstantUtils.service.substring(4) %>
					<%} else { %>
						<p id="fp"><%=ConstantUtils.service.substring(0,1) %></p><%=ConstantUtils.service.substring(1) %>
					<%} 
				}			
				%>
			</ul> 
		</div>
		<!--服务指南　end-->

		<!--健康饮食　start-->
		<div class="index_foot_news" style="margin-left:20px;">
			<h5><div><a href="${ctx }/homepage/homepage!healthquery.action" target="_blank"><font color="#DD5800">MORE...</font></a></div>
			健康饮食</h5>
			<div class="clear"></div>
			<s:iterator id="hdid" value="healthdrinklist">
			<ul>
				<li><div><fmt:formatDate value="${hdid.issueTime }" pattern="MM-dd"/></div>
					<s:if test="typeId != null">[<a href="${ctx }/homepage/homepage!healthquery.action?healthdrinktypeid=${hdid.typeId }" target="_blank">${hdid.typeName }</a>]</s:if>
					<a href="${ctx }/homepage/homepage!healthquery.action?healthdrinkId=${hdid.id }" target="_blank">${hdid.title }</a>
				</li>
			</ul>
			</s:iterator>
		</div>
		<!--健康饮食　end-->

		<div class="clear"></div>
	</div><!-- main_right_container -->
	<!-- 右侧界面 end -->
	<div style="clear:both;"></div>

	<!-- 页脚 -->
	<%@ include file="/common/footer.jsp"%>
</div>
<!--wai_container-->

<div id="dialog" title="Dialog Title" >
<form method="get" action="jUI_2.html">
<table width="90%" border="000" align="center" cellpadding="0" cellspacing="5">
  <tr>
    <td align="right">帐号</td>
    <td align="center"><input type="text" name="textfield" id="get_loginCode" /></td>
    <td><a href="${ctx }/member/register.action" target="_blank">注册新帐号</a></td>
  </tr>
  <tr>
    <td align="right">密码</td>
    <td align="center"><input type="password" name="textfield2" id="get_password" /></td>
    <td>忘记密码？</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td height="40" colspan="2">　<img style="cursor:pointer;" src="images/btn_dialog_login.jpg" name="btn_submit" id="btn_submit" />　
    <img style="cursor:pointer;" src="images/btn_dialog_cancle.jpg" name="btn_submit" id="btn_cancle" /></td>
  </tr>
</table>

<div id="url_cache" style="display:none">${ctx }</div>
</form>
</div>
<div id="dialog_cache" style="display:none"></div>
<div id="orderformnoteCode_cache" style="display:none">${orderformnoteCode}</div>
<c:if test="${not empty orderformnoteCode}">
	<script type="text/javascript">
		function copyToClipboard(txt) {   
			if(window.clipboardData) {   
				window.clipboardData.clearData();   
				window.clipboardData.setData("Text", txt);   
			} else if(navigator.userAgent.indexOf("Opera") != -1) {   
				window.location = txt;   
			} else if (window.netscape) {   
				try {   
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");   
				} catch (e) {   
					alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");   
				}   
			var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);   
			if (!clip)   
				return;   
			var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);   
			if (!trans)   
				return;   
			trans.addDataFlavor('text/unicode');   
			var str = new Object();   
			var len = new Object();   
			var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);   
			var copytext = txt;   
			str.data = copytext;   
			trans.setTransferData("text/unicode",str,copytext.length*2);   
			var clipid = Components.interfaces.nsIClipboard;   
			if (!clip)   
				return false;   
			clip.setData(trans,null,clipid.kGlobalClipboard);   
			//alert("复制成功")   
			}   
		}  
	</script>
	<script type="text/javascript">
		$(function(){
			var orderformnoteCode_cache_txt = $("#orderformnoteCode_cache").text();
			copyToClipboard(orderformnoteCode_cache_txt);
			alert("订餐成功！\n您的订单号是："+ orderformnoteCode_cache_txt+"。\n\n您的订单号已成功复制到剪切板，\n您可以选择订单查询功能查询您的订餐明细信息！");
		})	
	</script>
</c:if>
</body>
</html>

<script type="text/javascript">
var pic_url = "error.jpg";

function m1(pic_u)
{
	if(pic_u == ""){}
	else{	
	pic_url=pic_u;
	}
	//alert(pic_url);
}

this.imagePreview = function(){ 
/* CONFIG */
  
   xOffset = 10;
   yOffset = 30;
  
   // 可以自己设定偏移值
  
/* END CONFIG */

$(".preview").hover(function(e){
	//pic_url = "error.jpg";
   $("body").append("<div id='preview'><img width='120' height='80' src='upload/submenu/"+pic_url+"'/></div>");         
   $("#preview")
    .css("top",(e.pageY - xOffset) + "px")
    .css("left",(e.pageX + yOffset) + "px")
    .fadeIn("slow");      
    },
function(){
   $("#preview").fadeOut("fast");
   $("#preview").remove();
   pic_url = "error.jpg";
}); 
$(".preview").mousemove(function(e){
   $("#preview")
    .css("top",(e.pageY - xOffset) + "px")
    .css("left",(e.pageX + yOffset) + "px");
}); 
};

// 页面加载完执行
$(document).ready(function(){
	imagePreview();
});
</script>
