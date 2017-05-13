<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
<head>
<title>JSP for RoleActionForm form</title>
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
	function btn_comfirm_click(){
		$("#oper").val("comfirm");
		submitForm();
	}
	function btn_cancel_click(){
		$("#oper").val("cancel");
		submitForm();
	}
	function GetRoleList(){
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$.post(
			$("#roleAction").attr("action"),
			{
	            oper: "role"
	        },
			function(datafromServer){
				MyWait.hidden();
				$("#DataRole").empty();
				try{
					$(datafromServer).appendTo("#DataRole");
				}catch(e){}
			}
		);
	}
	function getGroup(){
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$.post($("#roleAction").attr("action"),
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
	function submitForm(){
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$("#roleAction").ajaxSubmit(
		function(data){
			MyWait.hidden();
			$("#DataAction").empty();
			try{
				$(data).appendTo("#DataAction");
			}catch(e){}
		});
	}

	function clickTR(Row){
		ResetFocus("#DataAction",Row);
	}

	function clickTRGroup(Row){
		ResetFocus("#DataGroup",Row);
		$("#group").val(Row.cells[0].innerHTML);
		$("#oper").val("getListByGroup");
		submitForm();
	}

	function clickTRRole(RoleID,Row){
		ResetFocus("#DataRole",Row);
		$("#role").val(RoleID);
		$("#oper").val("getListByRole");
		submitForm();
	}

	//页面加载时执行
	$(
		function(){
			GetRoleList();
			getGroup();
			submitForm();
		}
	)
</script>
</head>
<body>
<html:form action="/roleAction" styleId="roleAction">
  <div class="command">
    <input type="button" name="btn_Comfirm" id="btn_Comfirm" value="确定" onClick="btn_comfirm_click();">
    <input type="button" name="btn_delete" id="btn_delete" value="取消" onClick="btn_cancel_click();">
    <input type="hidden" value="getList" name="oper" id="oper">
    <input type="hidden" value="" name="group" id="group">
    <input type="hidden" value="" name="role" id="role">
  </div>
  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td width="160" valign="top" style="border-right:#CCCCCC 1px solid"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
          <tr class="fixedHeaderTr" id="HeaderRole">
            <th>角色</th>
          </tr>
          <tbody id="DataRole">
            <tr>
              <td>&nbsp;</td>
            </tr>
          </tbody>
        </table></td>
      <td width="160" valign="top" style="border-right:#CCCCCC 1px solid"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
          <tr class="fixedHeaderTr" id="HeaderGounp">
            <th>操作组</th>
          </tr>
          <tbody id="DataGroup">
            <tr>
              <td>&nbsp;</td>
            </tr>
          </tbody>
        </table></td>
      <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
          <tr class="fixedHeaderTr" id="Header">
          	<th width="25px">&nbsp;</th>
            <th width="30%">操作ID</th>
            <th width="30%">操作名称</th>
            <th>操作组</th>
          </tr>
          <tbody id="DataAction">
            <tr>
              <td>&nbsp;</td>
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
