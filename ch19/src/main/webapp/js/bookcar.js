var menuNow;
var menuId;
var menuName;
var menuPrice;
var pos;
var total;

$(document).ready(function(){
	pos = 1;
	total = $("#total");
		
	//清空订餐车
	$("#clear").click(function(){
		//确认删除
		if(confirm("是否全部取消?")){
			//删除
			$("#cart table").remove();
			//总价归零
			$(total).val(0);
			
			pos = 1;

			//$("#cart > div").before("<div id='subSelect'><table></table></div>");
		}
	});
			
	//点击删除菜单
	$("#cart table input:button").click(function(){
		del($(this));
	});
	
	//订购主菜
	$("div table").find("input:button[name='book2']").click(function(){
		menuNow = $(this).parent().parent();
		menuId = $(menuNow).find("td[name='id']").text();
		menuName = $(menuNow).find("td[name='name']").text();
		menuPrice = $(menuNow).find("td[name='price']").text();	

		addMenu(menuId, menuName, menuPrice);
	});
	
	//订购配菜
	$("div table").find("input:button[name='book1']").click(function(){
		menuNow = $(this).parent().parent();
		menuId = $(menuNow).find("td[name='id']").text();
		menuName = $(menuNow).find("td[name='name']").text();
		menuPrice = $(menuNow).find("td[name='price']").text();	
		
		$("#subSelect").dialog("open");
	});	
});


//删除菜单
function del(obj){
	var tbNow = $(obj).parent().parent().parent().parent();
	//删除主菜
	if($(obj).attr("name") == "removeAll"){
		if(confirm("是否删菜单（此菜单若有配菜，也将全部被删除）？")){
			$("#subSelect table").find("input:checkbox[name='pos']").each(function(){
				if($(this).val() == $(tbNow).attr("id")){
					$(this).parent().parent().remove();
				}
			});			
			$(tbNow).remove();
		}
	}
	//删除配菜
	else{
		var trNow = $(obj).parent().parent();
		$(trNow).remove();
	}
	
	//总计
	calTotal();
}

//修改订购数量
function countChange(obj){
	var tbNow = $(obj).parent().parent().parent();
	var subtotal = $(tbNow).find("input:text[name='subtotal']");
	$(subtotal).val(calSubtotal(tbNow));
	$(total).val(calTotal());
}

//小计
function calSubtotal(tbNow){
	var calSub = 0;
	var trs = $(tbNow).find("tr");
	$(trs).each(function(){
		var temp = $(this).find("input:text[name='count']").val()*$(this).find("label[name='price']").text();
		if(temp > 0){
			calSub += temp;
		}
	});
	var subtotal = $(tbNow).find("input:text[name='subtotal']");
	$(subtotal).val(calSub);
	return calSub;
}

//总计
function calTotal(){
	var calTo = 0;
	$("#cart table").each(function(){
		calTo += calSubtotal($(this));
	});
	$(total).val(calTo);
	return calTo;
}

//菜单类型选择
$(function() {
	$("#tabs").tabs({selected: 0});
});

//添加主菜
function addMenu(){

	//添加到购物车
	$("#cart > div").before(""
		+ "<table id='" + pos + "'><tr><td>" + pos + "</td><td><input type='hidden' name='mainMenuIds' value='" + menuId + "'/>" + menuName + "</td>"
		+ "<td><input type='text' name='count' value='1' size='1' readonly='true'></td>"
		+ "<td>￥<label name='price'>" + menuPrice + "</label></td>"
		+ "<td>&nbsp;<input type='hidden' name='subCount' value='0'><input type='button' name='removeAll' value='删除'/></td></tr>"
		+ "<tr name='st'><td colspan='5' align='right' height='0px;'></td></tr>"
		+ "</table>");
	
	//添加到对话框
	$("#subSelect table").append("<tr><td><input type='checkbox' name='pos' value='" + pos + "'></td><td>" + pos++ + "</td><td>" + menuName + "</td><td><input type='text' name='count' value='1' size='1'></td></tr>");

	//为新添加的菜单绑定删除事件
	$("#cart table input:button").bind("click", function(){
		del($(this));
	});
	
	//为新添加的菜单绑定修改数量事件
	$("#cart table").find("input:text[name='count']").bind("change", function(){
		countChange($(this));
	});

	//总计
	calTotal();
	
	//修改可订数量
}

//添加配菜
function addSubmenu(){
	$("#subSelect table tr").find("input:checkbox[name='pos']").each(function(){
		var tempSelected = $(this).parent().parent();
		var count = $(tempSelected).find("input:text[name='count']");	
		var countVal = $(count).val();
		if($(this).attr("checked")==true){
			var tempMenuNow = "#" + $(this).val();
			var subMenus = $(tempMenuNow).find("input:hidden[name='subMenuIds']");
			var flag=true;
			if($(subMenus).length > 0){
				$(subMenus).each(function(){
					if($(this).val() == menuId){
						var countCart = $(this).parent().parent().find("input:text[name='count']");
						var countCartVal = $(countCart).val();
						$(countCart).val(parseInt(countCartVal) + parseInt(countVal));
						flag=false;
					}
				});
			}
			if(flag){
				$(tempMenuNow).find("tr[name='st']").before("<tr><td>+</td>"
				+ "<td><input type='hidden' name='subMenuIds' value='" + menuId + "'/>" + menuName + "</td>"
				+ "<td><input type='text' name='count' value='" + countVal + "' size='1'/></td>"
				+ "<td>￥<label name='price'>" + menuPrice + "</label></td>"
				+ "<td><input type='button' name='remove' value='删除'/></td></tr>"
				+ "<tr><td colspan='5' align='right'>小计:￥<input type='text' name='subtotal' value='' size='1' readonly='true'></td></tr>");
				
				//
				var tempSubCount = $(tempMenuNow).find("input:hidden[name='subCount']");
				var tempSubCountVal = $(tempSubCount).val();
				$(tempSubCount).val(++tempSubCountVal);
				
				//为新添加的菜单绑定删除事件
				$("#cart table input:button").bind("click",function(){
					del($(this));
				});
			}
			
			//为新添加的菜单绑定修改数量事件
			$("#cart table").find("input:text[name='count']").bind("change", function(){
				countChange($(this));
			});
			
			//总计
			calTotal();
		}
	});
}

//添加配菜对话框
$(function() {	
	$("#subSelect").dialog({		
		autoOpen:false,
		height:"auto",
		width:"500",
		modal:true,
		title:"添加配菜",
		buttons:{
			"作主菜":function(){
				addMenu();
				$(this).dialog("close");
			},
			"完成":function(){
				addSubmenu();
				$(this).dialog("close");
			},
			"取消":function(){
				$(this).dialog("close");
			}
		}
	});
});
