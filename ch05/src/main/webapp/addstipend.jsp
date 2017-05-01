<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人力资源管理系统</title>
<link rel="stylesheet" rev="stylesheet" href="css/style.css " type="text/css" media="all" />
<script type="text/javascript" src="Js/typem.js"></script>
<script type="text/javascript" src="Js/js.js"></script>
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>
<body class="ContentBody">
<form name="stipendForm" method="post" action="modifystipend.do?action=addstipend" onSubmit="return stipendValidate();" >
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >薪金数据录入</th>
  </tr>
  <tr>
    <td class="CPanel">

		<table border="0" cellpadding="0" cellspacing="0" style="width:80%" align="center">
		<tr><td align="left">
		<input type="submit"value="保存" class="button"/>　

			<input type="reset" value="返回" class="button"/>
		</td></tr>

		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>薪金数据</legend>
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					  <tr>
					    <td nowrap align="right" width="9%">员工姓名：</td>
					    <td width="36%"><input name="name" type="text" class="input">
						<span class="red">*</span></td>
					    <td width="12%"><div align="right">基本薪金：</div></td>
					    <td width="43%"><input name="basic" type="text" class="input" id="basic" />
					      <span class="red">*</span></tr>
					  <tr>
					    <td nowrap align="right" width="9%">饭补：</td>
					    <td><input name="eat" type="text" class="input" id="eat"></td>
					    <td><div align="right">房补：</div></td>
					    <td><input name="house" type="text" class="input" id="house"></td>
					  </tr>
					  <tr>
					    <td nowrap align="right">全勤奖：</td>
					    <td><input name="duty" type="text" class="input" id="duty"></td>
					    <td><div align="right">赋税：</div></td>
					    <td><input name="scot" type="text" class="input" id="scot"></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">额外补助：</td>
					    <td><input name="other" type="text" class="input" id="other"></td>
					    <td><div align="right">罚款：</div></td>
					    <td><input name="punishment" type="text" class="input" id="punishment"></td>
					    </tr>
					  <tr>
					    <td nowrap align="right">发放时间：</td>
					    <td><input name="granttime" type="text" class="input" id="granttime">
				        <span class="red">*</span></td>
					    <td colspan="2"><div align="left">注：金钱单位（元/RMB）</div></td>
					    </tr>
					  </table>
			  <br />
				</fieldset>			</TD>

		</TR>
		</TABLE>
	 </td>
  </tr>
		<TR>
			<TD colspan="2" align="center" height="50px">
			<input name="提交" type="submit" class="button" value="保存"/>　
			<input name="重置" type="reset" class="button" value="重置"/></TD>
		</TR>
		</TABLE>	
</div>
</form>
</body>
</html>

