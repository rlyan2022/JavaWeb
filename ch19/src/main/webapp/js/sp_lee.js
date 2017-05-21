$(function(){
//alert("加载成功")
var menuID;//用来存储导航的ID
var subID ;//用来存储导航ID中的数字,数字是显示对应子菜单的关键
var current = 1;//定义当前鼠标值,默认显示第一个
for(i=1;i<=7;i++){//寻找所有menu
$("#subMenu"+current).show();//显示第一个
$("#menu"+i).mousemove(function(){//加入鼠标事件
      //alert(this.id);
    menuID = this.id;//将当前这个导航的名称赋给变量menuID，现在的menuID就是鼠标移上去的那个导航
   delete current;//删除当前值,因为我们要重新为current赋值
   //alert(menuID);
   subID = menuID.charAt(4);//取对应的数字,在这里取各个menu后面的数字
   current = subID;//将对应代码的顺序赋给当前变量
   //alert(current)
   for(i=1;i<=7;i++){//循环，先让所有的都隐藏
     $("#subMenu"+i).hide();
   }
   $("#subMenu"+current).show(); //找出对应的显示出来
   })
   };
})

//////////////////////////////////////////////

$(function(){
var speed=30;   
var show_count = 5;
var css_margin_top = 5;
var demo = $(".Sp_content");   
var demo1 = $("#Sp_sub_1");   
var demo2 = $("#Sp_sub_2");
var demo_li = $("#Sp_sub_1 li");
var Icon_Button_up = $(".Icon_Button_up");
var Icon_Button_dowm = $(".Icon_Button_dowm");
var ctrl_num= demo1.height()-show_count*(demo_li.height()+css_margin_top)+css_margin_top; //计算div到底的时候的Y值
demo2.html(demo1.html());  

function Marquee(){    
    if(demo.scrollTop()>=demo1.height())   
		{
		demo.scrollTop(0); 
		}
    else{

		if(demo.scrollTop() >= ctrl_num){$(".Icon_Button_dowm").addClass("Icon_Button_dowm_end");}
		else{
		$(".Icon_Button_up").removeClass("Icon_Button_up_end");
		$(".Icon_Button_dowm").removeClass("Icon_Button_dowm_end");
		var i=demo_li.height()+css_margin_top; 
		while(i>0){   
        demo.scrollTop(demo.scrollTop()+1);
		i-=1;
		}}
		//return;
    }   
}  
function Marquee_dowm(){    
    if(demo.scrollTop()== 0 )   
       {$(".Icon_Button_up").addClass("Icon_Button_up_end");}
    else{
		$(".Icon_Button_up").removeClass("Icon_Button_up_end");
		$(".Icon_Button_dowm").removeClass("Icon_Button_dowm_end");
		var i=demo_li.height()+css_margin_top; 
		while(i>0){   
        demo.scrollTop(demo.scrollTop()-1);
		i-=1;
		}
		//return;
    }   
}    
var MyMar;
function G_up(){
//clearInterval(MyMar);
Marquee();
//MyMar = setInterval(Marquee,speed);
} 
function G_stop(){
Marquee_dowm();   
}
Icon_Button_up.click(function(){
Marquee_dowm();
});
Icon_Button_dowm.click(function(){
Marquee();
});
demo_li.mouseover(function() {
	demo_li.each(function(){
	$(this).removeClass('Sp_sub_1_li_bold');
	});
    $(this).addClass('Sp_sub_1_li_bold');  
} )   
/*demo_li.mouseout(function() {   
    $(this).css('fontWeight','normal'); 
} )   */
  
function fun1(){   
    alert(demo.scrollTop());   
}  
function fun3(){   
    alert(ctrl_num);   
}   

function fun2(){   
    alert(demo1.height());   
}   
});