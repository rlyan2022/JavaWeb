/*改变页面显示的记录数*/
function changePageSize(pageSize){	
	$("#pageSize").val(pageSize);
	$("#mainForm").submit();
}
/*跳转去的页*/
function jumpPage(pageNo){
	$("#pageNo").val(pageNo);
	$("#mainForm").submit();
}
/*排序*/
function sorting(orderBy,defaultOrder){
	if($("#orderBy").val()==orderBy){
		if($("#order").val()==""){
		$("#order").val(defaultOrder);}
		else if($("#order").val()=="desc"){
		$("#order").val("asc");}
		else if($("#order").val()=="asc"){
		$("#order").val("desc");}
	}
	else{
		$("#orderBy").val(orderBy);
		$("#order").val(defaultOrder);
	}

	$("#mainForm").submit();
}
/*查看*/
function viewItem(id){
	$("#id").val(id);
	$("#mainForm").attr("action",viewAction);
	$("#mainForm").submit();
}
/*修改*/
function modifyItem(id){
	$("#id").val(id);
	$("#mainForm").attr("action",modifyAction);
	$("#mainForm").submit();
}
/*删除*/
function deleteItem(id){
	$("#id").val(id);
	$("#mainForm").attr("action",deleteAction);
	$("#mainForm").submit();
}