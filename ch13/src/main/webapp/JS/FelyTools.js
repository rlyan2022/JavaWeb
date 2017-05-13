
function ResetFocus(ContainerName,obj)
{
    var container = $(ContainerName);
    $("tr",container).removeClass("GridViewRowMouseClick");
    $(obj).addClass("GridViewRowMouseClick");
}
