<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
<head>
<title>JSP for WarehouseForm form</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../STYLE/AJAXSTYLE.css" type="text/css" />
<link rel="stylesheet" href="../../STYLE/Styles.css" type="text/css" />
<script type="text/javascript" src="../../JS/jquery.js"></script>
<script type="text/javascript" src="../../JS/iutil.js"></script>
<script type="text/javascript" src="../../JS/idrag.js"></script>
<script type="text/javascript" src="../../JS/jquery.form.js"></script>
<script type="text/javascript" src="../../JS/waiting.js"></script>
<script type="text/javascript" src="../../JS/FelyTools.js"></script>
<script type="text/javascript">
	function btn_add_onclick(){
		$("#oper").val("add");
		submitForm();
	}
	function btn_edit_onclick(){
		$("#oper").val("edit");
		submitForm();
	}
	function btn_delete_onclick(){
		$("#oper").val("delete");
		submitForm();
	}
	function submitForm(){
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$("#warehouse").ajaxSubmit(
		function(data)
		{
			MyWait.hidden();
			$("#Data").empty();
			try{
				$(data).appendTo("#Data");
				$("#warehouse").clearForm();
			}catch(e){}
		});
	}
	function clickTR(Row)
	{
		var ModelID = Row.cells[0].innerHTML;
		ResetFocus("#Data",Row);
		GetSingle(Row);
	}

	function GetSingle(Row)
	{
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		/*MyWait.show();
		$.post($("#itemeditor").attr("action"),
            {
                oper: "model",
                item_id: ModelID
            },
            function(MyData) {
                BuildModel(MyData);
                MyWait.hidden();
            }
        );*/
        BuildModel(Row);
	}

	function BuildModel(MyData)
	{
		try {
			$("#ware_id").val(MyData.cells[0].innerHTML);
			$("#ware_desc").val(MyData.cells[1].innerHTML);
			$("#val_emp").val(MyData.cells[3].innerHTML);
			$("#phone").val(MyData.cells[5].innerHTML);
			$("#fax").val(MyData.cells[4].innerHTML);
			$("#ware_adrr").val(MyData.cells[2].innerHTML);
		}
		catch(e){

		}
	}

	//页面加载时执行
	$(
		function(){
			submitForm();
		}
	)
</script>
</head>
<body>
<html:form action="/warehouse" styleId="warehouse">
  <div class="command">
    <input type="button" name="btn_add" id="btn_add" value="添加" onClick="btn_add_onclick();">
    <input type="button" name="btn_edit" id="btn_edit" value="修改" onClick="btn_edit_onclick();">
    <input type="button" name="btn_delete" id="btn_delete" value="删除" onClick="btn_delete_onclick();">
  </div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td width="13%">&nbsp;仓库ID:</td>
      <td width="20%">&nbsp;
        <html:text property="ware_id" styleId="ware_id"/>
        <html:errors property="ware_id"/></td>
      <td width="13%">&nbsp;仓库名称:</td>
      <td width="20%">&nbsp;
        <html:text property="ware_desc" styleId="ware_desc"/>
        <html:errors property="ware_desc"/></td>
      <td width="14%">&nbsp;联系人:</td>
      <td width="20%">&nbsp;
        <html:text property="val_emp" styleId="val_emp"/>
        <html:errors property="val_emp"/>
        <input type="hidden" value="getList" name="oper" id="oper">      </td>
    </tr>
    <tr>
      <td>&nbsp;联系电话:</td>
      <td>&nbsp;
        <html:text property="phone" styleId="phone"/>
        <html:errors property="phone"/></td>
      <td>&nbsp;传真:</td>
      <td>&nbsp;
        <html:text property="fax" styleId="fax"/>
        <html:errors property="fax"/></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;地址:</td>
      <td colspan="5">&nbsp;
        <html:text property="ware_adrr" style="width:98%" styleId="ware_adrr"/>
        <html:errors property="ware_adrr"/></td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
    <tr class="fixedHeaderTr" id="Header">
      <th width="10%">仓库ID</th>
      <th width="15%">仓库名称</th>
      <th width="25%">地址</th>
      <th width="10%">联系人</th>
      <th width="15%">传真</th>
      <th width="15%">联系电话</th>
    </tr>
    <tbody id="Data">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
    </tbody>
  </table>
</html:form>
</body>
</html>
