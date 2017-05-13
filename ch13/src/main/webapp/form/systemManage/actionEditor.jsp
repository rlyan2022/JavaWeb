<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
<head>
<title>JSP for ActionEditorForm form</title>
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
		getGroup();
	}
	function btn_edit_onclick(){
		$("#oper").val("edit");
		submitForm();
		getGroup();
	}
	function btn_delete_onclick(){
		$("#oper").val("delete");
		submitForm();
		getGroup();
	}
	function submitForm(){
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$("#actionEditor").ajaxSubmit(
		function(data)
		{
			MyWait.hidden();
			$("#Data").empty();
			try{
				$(data).appendTo("#Data");
				$("#actionEditor").clearForm();
				if($("#oper").val()!="getListByGroup"){
					getGroup();
				}
			}catch(e){}
		});
	}

	function getGroup(){
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$.post($("#actionEditor").attr("action"),
            {
                oper: "group"
            },
            function(MyData) {
                MyWait.hidden();
                $("#DataGroup").empty();
				try{
					$(MyData).appendTo("#DataGroup");
				}catch(e){}
            }
        );
	}

	function clickTR(Row){
		ResetFocus("#Data",Row);
		$("#action_id").val(Row.cells[0].innerHTML);
		$("#action_desc").val(Row.cells[1].innerHTML);
		$("#action_group").val(Row.cells[2].innerHTML);
	}
	//点击组
	function clickTRGroup(Row){
		ResetFocus("#DataGroup",Row);
		$("#group").val(Row.cells[0].innerHTML);
		$("#oper").val("getListByGroup");
		submitForm();
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
<html:form action="/actionEditor" styleId="actionEditor">
  <div class="command">
    <input type="button" name="btn_add" id="btn_add" value="添加" onClick="btn_add_onclick();">
    <input type="button" name="btn_edit" id="btn_edit" value="修改" onClick="btn_edit_onclick();">
    <input type="button" name="btn_delete" id="btn_delete" value="删除" onClick="btn_delete_onclick();">
  </div>
  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="160" valign="top" style="border-right:#CCCCCC 1px solid">
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
        <tr class="fixedHeaderTr" id="HeaderGounp">
          <th>操作组</th>
        </tr>
        <tbody id="DataGroup">
          <tr>
            <td>&nbsp;</td>
          </tr>
        </tbody>
      </table></td>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1">
          <tr>
            <td width="10%">&nbsp;操作ID:</td>
            <td width="23%">&nbsp;
              <html:text property="action_id" styleId="action_id"/>
              <html:errors property="action_id"/></td>
            <td width="10%">&nbsp;操作名称:</td>
            <td width="23%">&nbsp;
              <html:text property="action_desc" styleId="action_desc"/>
              <html:errors property="action_desc"/></td>
            <td width="10%">&nbsp;操作组</td>
            <td width="24%">&nbsp;
              <html:text property="action_group" styleId="action_group"/>
              <html:errors property="action_group"/>
              <input type="hidden" value="getList" name="oper" id="oper">
              <input type="hidden" value="" name="group" id="group">
              </td>
          </tr>
        </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
          <tr class="fixedHeaderTr" id="Header">
            <th width="30%">操作ID</th>
            <th width="30%">操作名称</th>
            <th>操作组</th>
          </tr>
          <tbody id="Data">
            <tr>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
              <td>&nbsp;</td>
            </tr>
          </tbody>
        </table></td>
    </tr>
  </table>
</html:form>
</body>
</html>
