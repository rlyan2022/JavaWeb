// JScript 文件
//Jquery based
function ChangeView(obj)
{
    var MyUl = $(obj).next("li");
    if(MyUl.css("display")=="none")
    {
        MyUl.slideDown("fast");
    }
    else
    {
        MyUl.slideUp("fast");
    }
}
