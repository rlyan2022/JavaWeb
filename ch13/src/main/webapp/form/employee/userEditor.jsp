<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<html>
<head>
<title>JSP for UserEditorForm form</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../STYLE/AJAXSTYLE.css" type="text/css" />
<link rel="stylesheet" href="../../STYLE/Styles.css" type="text/css" />
<script type="text/javascript" src="../../JS/jquery.js"></script>
<script type="text/javascript" src="../../JS/iutil.js"></script>
<script type="text/javascript" src="../../JS/idrag.js"></script>
<script type="text/javascript" src="../../JS/jquery.form.js"></script>
<script type="text/javascript" src="../../JS/waiting.js"></script>
<script type="text/javascript" src="../../JS/FelyTools.js"></script>
<script type="text/javascript" src="../../JS/WebCalendar.js"></script>
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
	function btn_passwordset_onclick(){
		$("#oper").val("passwordSet");
		submitForm();
	}
	function btn_roleset_onclick(){
		$("#oper").val("roleSet");
		submitForm();
	}
	function submitForm(){
		var MyWait = new wait(150,30,'请稍候,正在等待数据','../../IMAGES/processing.gif');
		MyWait.show();
		$("#userEditor").ajaxSubmit(
		function(data){
			MyWait.hidden();
			$("#Data").empty();
			try{
				$(data).appendTo("#Data");
				$("#userEditor").clearForm();
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
		$.post($("#userEditor").attr("action"),
            {
                oper: "model",
                employID: ModelID
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
			$("#emp_id").val(json.data[0].emp_id);
			$("#emp_desc").val(json.data[0].emp_desc);
			$("#folk").val(json.data[0].folk);
			$("#birth").val(json.data[0].birth);
			$("#marri").val(json.data[0].marri);
			$("#family").val(json.data[0].family);
			$("#collage").val(json.data[0].collage);
			$("#speci").val(json.data[0].speci);
			$("#wage").val(json.data[0].wage);
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
<html:form action="/userEditor" styleId="userEditor">
  <div class="command">
    <input type="button" name="btn_add" id="btn_add" value="添加" onClick="btn_add_onclick();">
    <input type="button" name="btn_edit" id="btn_edit" value="修改" onClick="btn_edit_onclick();">
    <input type="button" name="btn_delete" id="btn_delete" value="删除" onClick="btn_delete_onclick();">
  </div>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table1">
    <tr>
      <td width="13%">&nbsp;用户名:</td>
      <td width="20%">&nbsp;
        <html:text property="emp_id" styleId="emp_id"/>
        <html:errors property="emp_id"/></td>
      <td width="13%">&nbsp;姓名:</td>
      <td width="20%">&nbsp;
        <html:text property="emp_desc" styleId="emp_desc"/>
        <html:errors property="emp_desc"/></td>
      <td width="14%">&nbsp;民族:</td>
      <td width="20%">&nbsp;
        <html:text property="folk" styleId="folk"/>
        <html:errors property="folk"/>
        <input type="hidden" value="getList" name="oper" id="oper"></td>
    </tr>
    <tr>
      <td>&nbsp;出生日期:</td>
      <td>&nbsp;
        <html:text property="birth" styleId="birth" onclick="new Calendar().show(this);"/>
        <html:errors property="birth"/></td>
      <td>&nbsp;婚姻状况:</td>
      <td>&nbsp;
        <html:text property="marri" styleId="marri"/>
        <html:errors property="marri"/></td>
      <td>&nbsp;家庭成员:</td>
      <td>&nbsp;
        <html:text property="family" styleId="family"/>
        <html:errors property="family"/></td>
    </tr>
    <tr>
      <td>&nbsp;毕业院校:</td>
      <td>&nbsp;
        <html:text property="collage" styleId="collage"/>
        <html:errors property="collage"/></td>
      <td>&nbsp;专业:</td>
      <td>&nbsp;
        <html:text property="speci" styleId="speci"/>
        <html:errors property="speci"/></td>
      <td>&nbsp;目前薪水:</td>
      <td>&nbsp;
        <html:text property="wage" styleId="wage"></html:text>
        <html:errors property="wage"/></td>
    </tr>
    <tr>
      <td>&nbsp;当前角色:</td>
      <td>&nbsp;</td>
      <td colspan="4" style="text-align:right">
        <input type="button" name="btn_PasswordSet" id="btn_add2" value="设置密码" onClick="btn_passwordset_onclick();">

        <input type="button" name="btn_RoleSet" id="btn_add3" value="设置角色" onClick="btn_roleset_onclick();">
        </td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table2">
    <tr class="fixedHeaderTr" id="Header">
      <th width="10%">用户名</th>
      <th width="15%">姓名</th>
      <th width="5%">民族</th>
      <th width="20%">毕业院校</th>
      <th width="15%">专业</th>
      <th width="20%">角色</th>
      <th width="15%">薪水</th>
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
  validpassword :
  <html:text property="validpassword"/>
  <html:errors property="validpassword"/>
  <br/>
  password :
  <html:password property="password"/>
  <html:errors property="password"/>
  <br/>
</html:form>
</body>
</html>
