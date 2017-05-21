/*
title
message
filter
listContent
listView
inputContent
inputView
footer
cellspacing是相邻单元格和单元格之间的距离
cellpadding是单元格与其内填充的元素（如文字，图片）之间的距离
odd奇数；even偶数
*/
$(function() {
	$(".listView,.tableView").attr("cellspacing","1").attr("border","0").attr("cellpadding","0");
	$(".listView tr,.tableView tr").mouseover(function(){
		$(this).addClass("listView_mouseover");
	});
	$(".listView tr,.tableView tr").mouseout(function(){
		$(this).removeClass("listView_mouseover");
	});
	$(".listView tr:odd").addClass("listView_odd");
	$(".listView tr:even").addClass("listView_even");
	
	
	
	$("#title .text").prepend("※").append("※<hr />");
	$help = $("#title .help").after("<div class='help-icon'></div>");
	$("#title .help-icon").click(function(){
		displayBox.show();
	});
	
	displayBox = {
		init:function(){
			$("<iframe class='displayBox'></iframe><div class='displayBox'></div>").appendTo("body").click(function(){
				$(".displayBox").hide();
				$("#title .help").hide();
			});
		},
		show:function(){
			$(".displayBox").show();
			$("#title .help").show();
		},
		hide:function(){
			$(".displayBox").hide();
			$("#title .help").hide();
		}
	}
	displayBox.init();
		
	$(".default_text").focus(function(){
		$(this).css("border-color","#ffae44");
	});
	$(".default_text").blur(function(){
		$(this).css("border-color","#cccccc");
	});
	
});