<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<jsp:directive.page import="com.sanqing.bean.ummasterBean"/>
<jsp:directive.page import="com.sanqing.bean.employeeBean"/>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
<head>
<title>JSP for ItemeditorForm form</title>
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
		$("#itemeditor").ajaxSubmit(
		function(data)
		{
			MyWait.hidden();
			$("#Data").empty();
			try{
				$(data).appendTo("#Data");
				$("#itemeditor").clearForm();
			}catch(e){}
		});
	}
	function clickTR(Row)
	{
		var ModelID = Row.cells[0].innerHTML;
		ResetFocus("#Data",Row);
		GetSingle(ModelID);
	}

	function GetSingle(ModelID)
	{
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$.post($("#itemeditor").attr("action"),
            {
                oper: "model",
                item_id: ModelID
            },
            function(MyData) {
                BuildModel(MyData);
                MyWait.hidden();
            }
        );
	}

	function BuildModel(MyData)
	{
		try {
			var json = eval('(' + MyData + ')');
			$("#item_id").val(json.data[0].item_id);
			$("#item_desc").val(json.data[0].item_desc);
			$("#um_id").val(json.data[0].um_id);
			$("#prod_code").val(json.data[0].prod_code);
			$("#color").val(json.data[0].color);
			$("#status").val(json.data[0].status);
			$("#tax_rate").val(json.data[0].tax_rate);
			$("#sale_pic").val(json.data[0].sale_pic);
			$("#whol_pic").val(json.data[0].whol_pic);
			$("#po_emp_id").val(json.data[0].po_emp_id);
			$("#sale_emp_id").val(json.data[0].sale_emp_id);
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
<html:form action="/itemeditor" styleId="itemeditor">
  <div class="command">
    <input type="button" name="btn_add" id="btn_add" value="添加" onClick="btn_add_onclick();">
    <input type="button" name="btn_edit" id="btn_edit" value="修改" onClick="btn_edit_onclick();">
    <input type="button" name="btn_delete" id="btn_delete" value="删除" onClick="btn_delete_onclick();">
  </div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td width="13%">&nbsp;物料ID:</td>
      <td width="20%">&nbsp;
        <html:text property="item_id" styleId="item_id"/>
        <html:errors property="item_id"/></td>
      <td width="13%">&nbsp;物料名称:</td>
      <td width="20%">&nbsp;
        <html:text property="item_desc" styleId="item_desc"/>
        <html:errors property="item_desc"/></td>
      <td width="14%">&nbsp;计量单位:</td>
      <td width="20%">&nbsp;
	  	<jsp:useBean id="ummasterbean" scope="page" class="com.sanqing.bean.ummasterBean"></jsp:useBean>
        <html:select property="um_id" styleId="um_id">
        	<html:option value=""></html:option>
        	<html:optionsCollection name="ummasterbean" property="beanCollection"/>
        </html:select>
        <html:errors property="um_id"/>
		<input type="hidden" value="getList" name="oper" id="oper">
		</td>
    </tr>
    <tr>
      <td>&nbsp;类别:</td>
      <td>&nbsp;
      	<jsp:useBean id="prodcodebean" scope="page" class="com.sanqing.bean.classCodeBean"></jsp:useBean>
        <html:select property="prod_code" styleId="prod_code">
        	<html:option value=""></html:option>
        	<html:optionsCollection name="prodcodebean" property="beanCollection"/>
        </html:select>
        <html:errors property="prod_code"/></td>
      <td>&nbsp;颜色:</td>
      <td>&nbsp;
        <html:text property="color" styleId="color"/>
        <html:errors property="color"/></td>
      <td>&nbsp;状态:</td>
      <td>&nbsp;
        <html:select property="status" styleId="status">
        	<html:option value="0">正常</html:option>
        	<html:option value="1">停用</html:option>
        </html:select>
        <html:errors property="status"/></td>
    </tr>
    <tr>
      <td>&nbsp;税率:</td>
      <td>&nbsp;
        <html:text property="tax_rate" styleId="tax_rate"/>
        <html:errors property="tax_rate"/></td>
      <td>&nbsp;单价:</td>
      <td>&nbsp;
        <html:text property="sale_pic" styleId="sale_pic"/>
        <html:errors property="sale_pic"/></td>
      <td>&nbsp;全价:</td>
      <td>&nbsp;
        <html:text property="whol_pic" styleId="whol_pic"/>
        <html:errors property="whol_pic"/></td>
    </tr>
    <tr>
      <td>&nbsp;采购员:</td>
      <td>&nbsp;
      	<jsp:useBean id="employeeBean" scope="page" class="com.sanqing.bean.employeeBean"></jsp:useBean>
        <html:select property="po_emp_id" styleId="po_emp_id">
        	<html:option value=""></html:option>
        	<html:optionsCollection name="employeeBean" property="beanCollection"/>
        </html:select>
        <html:errors property="po_emp_id"/></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;销售员:</td>
      <td>&nbsp;
        <html:select property="sale_emp_id" styleId="sale_emp_id">
        	<html:option value=""></html:option>
        	<html:optionsCollection name="employeeBean" property="beanCollection"/>
        </html:select>
        <html:errors property="sale_emp_id"/></td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
    <tr class="fixedHeaderTr" id="Header">
      <th width="10%">物料ID</th>
      <th width="25%">物料名称</th>
      <th width="15%">类型</th>
      <th width="15%">颜色</th>
      <th width="12%">单价</th>
      <th width="12%">税率</th>
      <th width="11%">状态</th>
    </tr>
    <tbody id="Data">
      <tr>
        <td>&nbsp;</td>
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
