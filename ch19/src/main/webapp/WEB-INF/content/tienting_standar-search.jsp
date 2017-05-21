<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<%@ include file="/common/meta.jsp"%>
	<style type="text/css">
		#preview{border:1px solid #cccccc; color:#fff;  display:none; position:absolute;}
	</style>
	<c:choose>
		<c:when test="${empty submenulist}">
			<div>没有找到符合您查询的菜单信息！</div>
		</c:when>
		<c:otherwise>
			<%int count = 0; %>
			<table width="100%" border="000" align="center" cellpadding="5" cellspacing="0">
	     		<tr style=" height:23px;">
				     <td height="23"><strong>名称</strong></td>
				     <td height="23"><strong>价格</strong></td>
				     <td height="23"><strong>操作</strong></td>
				     <td height="23"><strong>名称</strong></td>
				     <td height="23"><strong>价格</strong></td>
				     <td height="23"><strong>操作</strong></td>
				</tr>			
				<s:iterator id="slid" value="submenulist">
				    <%if (count%2 == 0) { %>
				    <tr>
					<%} %>	
					      <td class="preview" onmouseover="m1('${slid.photo}')">${slid.name }</td>
					      <td>￥${slid.price }</td>
					      <td>
					      		<c:choose>
					      			<c:when test="${stock == 0}">
					      				<font color="orange">已售完</font>
					      			</c:when>
					      			<c:otherwise>
					      				<img src="images/btn_buy2.gif" width="39" height="16" class="booking" onClick="dinner.bookMain('${slid.name}',${slid.price},1,${slid.id})" />
					      			</c:otherwise>
					      		</c:choose>
					      </td>
				     <%if (count%2 == 1) { %>					
					  </tr>
				       <%}
				          ++count; 	
				       %>
				 </s:iterator>
				  <%if (count%2 > 0){ //补齐空行%>
				       <td colspan="3">&nbsp;</td>
				     </tr>
				 <%} %>
			</table>
	  </c:otherwise>
	</c:choose>
	
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
