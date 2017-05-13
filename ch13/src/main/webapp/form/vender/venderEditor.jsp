<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.sanqing.bean.shipvinbean"/>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
<head>
<title>JSP for VenderEditorForm form</title>
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
		$("#venderEditor").ajaxSubmit(
		function(data)
		{
			MyWait.hidden();
			$("#Data").empty();
			try{
				$(data).appendTo("#Data");
				$("#venderEditor").clearForm();
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
		$.post($("#venderEditor").attr("action"),
            {
                oper: "model",
                vend_id: ModelID
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
			$("#vend_id").val(json.data[0].Vend_id);
			$("#vend_desc").val(json.data[0].Vend_desc);
			$("#vend_type").val(json.data[0].Vend_type);
			//$("#vend_type").attr("value",json.data[0].Vend_type)
			$("#vend_addr").val(json.data[0].Vend_addr);
			$("#vend_phone").val(json.data[0].Vend_phone);
			$("#vend_nati").val(json.data[0].Vend_nati);
			$("#vend_city").val(json.data[0].Vend_city);
			$("#vend_post").val(json.data[0].Vend_post);
			$("#cont_man").val(json.data[0].Cont_man);
			$("#vend_email").val(json.data[0].Vend_Email);
			$("#vend_shipvia").val(json.data[0].Vend_Shipvia);
			$("#tradeamount").val(json.data[0].TradeAmount);
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
<html:form action="/venderEditor" styleId="venderEditor">
  <div class="command">
    <input type="button" name="btn_add" id="btn_add" value="添加" onClick="btn_add_onclick();">
    <input type="button" name="btn_edit" id="btn_edit" value="修改" onClick="btn_edit_onclick();">
    <input type="button" name="btn_delete" id="btn_delete" value="删除" onClick="btn_delete_onclick();">
  </div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td width="13%">&nbsp;供应商ID:</td>
      <td width="20%">&nbsp;
        <html:text property="vend_id" styleId="vend_id"/>
        <html:errors property="vend_id"/></td>
      <td width="13%">&nbsp;供应商名称:</td>
      <td width="20%">&nbsp;
        <html:text property="vend_desc" styleId="vend_desc"/>
        <html:errors property="vend_desc"/></td>
      <td width="14%">&nbsp;类型</td>
      <td width="20%">&nbsp;
      	<jsp:useBean id="Vend_type_Data" class="com.sanqing.bean.vendtypebean" scope="page"/>
        <html:select property="vend_type" styleId="vend_type">
        	<html:option value=""></html:option>
        	<html:optionsCollection name="Vend_type_Data" property="beanCollection"/>
        </html:select>
        <html:errors property="vend_type"/>
        <input type="hidden" value="getList" name="oper" id="oper"></td>
    </tr>
    <tr>
      <td>&nbsp;联系地址:</td>
      <td colspan="3">&nbsp;
        <html:text property="vend_addr" styleId="vend_addr" style="width:90%"/>
        <html:errors property="vend_addr"/></td>
      <td>&nbsp;联系电话:</td>
      <td>&nbsp;
        <html:text property="vend_phone" styleId="vend_phone"/>
        <html:errors property="vend_phone"/></td>
    </tr>
    <tr>
      <td>&nbsp;国家</td>
      <td>&nbsp;
        <html:text property="vend_nati" styleId="vend_nati"/>
        <html:errors property="vend_nati"/></td>
      <td>&nbsp;城市</td>
      <td>&nbsp;
      	<html:text property="vend_city" styleId="vend_city"/>
        <html:errors property="vend_city"/></td>
      <td>&nbsp;邮编</td>
      <td>&nbsp;
        <html:text property="vend_post" styleId="vend_post"/>
        <html:errors property="vend_post"/></td>
    </tr>
    <tr>
      <td>&nbsp;联系人:</td>
      <td>&nbsp;
        <html:text property="cont_man" styleId="cont_man"/>
        <html:errors property="cont_man"/></td>
      <td>&nbsp;邮箱:</td>
      <td>&nbsp;
        <html:text property="vend_email" styleId="vend_email"/>
        <html:errors property="vend_email"/></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;运输方式:</td>
      <td>&nbsp;
      <jsp:useBean id="ShipVin_Data" class="com.sanqing.bean.shipvinbean" scope="page"/>
        <html:select property="vend_shipvia" styleId="vend_shipvia">
        	<html:option value=""></html:option>
        	<html:optionsCollection name="ShipVin_Data" property="beanCollection"/>
        </html:select>
        <html:errors property="vend_shipvia"/></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
      <td>&nbsp;交易额:</td>
      <td>&nbsp;
        <html:text property="tradeamount" styleId="tradeamount"/>
        <html:errors property="tradeamount"/></td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
    <tr class="fixedHeaderTr" id="Header">
      <th width="10%">供应商ID</th>
      <th width="20%">供应商名称</th>
      <th width="10%">城市</th>
      <th width="15%">联系电话</th>
      <th width="10%">联系人</th>
      <th width="20%">类型</th>
      <th width="15%">交易额</th>
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
