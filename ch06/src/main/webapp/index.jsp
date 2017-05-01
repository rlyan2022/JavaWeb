<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报价管理系统</title>
<style type="text/css">
<!--
a{ color:#008EE3}
a:link  { text-decoration: none;color:#008EE3}
A:visited {text-decoration: none;color:#666666}
A:active {text-decoration: underline}
A:hover {text-decoration: underline;color: #0066CC}
A.b:link {
	text-decoration: none;
	font-size:12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}
A.b:visited {
	text-decoration: none;
	font-size:12px;
	font-family: "Helvetica,微软雅黑,宋体";
	color: #FFFFFF;
}
A.b:active {
	text-decoration: underline;
	color: #FF0000;

}
A.b:hover {text-decoration: underline; color: #ffffff}

.table1 {
	border: 1px solid #CCCCCC;
}
.font {
	font-size: 12px;
	text-decoration: none;
	color: #999999;
	line-height: 20px;


}
.input {
	font-size: 12px;
	color: #999999;
	text-decoration: none;
	border: 0px none #999999;


}

td {
	font-size: 12px;
	color: #007AB5;
}
form {
	margin: 1px;
	padding: 1px;
}
input {
	border: 0px;
	height: 26px;
	color: #007AB5;
	.unnamed1 {
	border: thin none #FFFFFF;
}
.unnamed1 {
	border: thin none #FFFFFF;
}
select {
	border: 1px solid #cccccc;
	height: 18px;
	color: #666666;

	.unnamed1 {
	border: thin none #FFFFFF;
}
body {
	background-repeat: no-repeat;
	background-color: #9CDCF9;
	background-position: 0px 0px;

}
.tablelinenotop {
	border-top: 0px solid #CCCCCC;
	border-right: 1px solid #CCCCCC;
	border-bottom: 0px solid #CCCCCC;
	border-left: 1px solid #CCCCCC;
}
.tablelinenotopdown {

	border-top: 1px solid #eeeeee;
	border-right: 1px solid #eeeeee;
	border-bottom: 1px solid #eeeeee;
	border-left: 1px solid #eeeeee;
}
.style6 {FONT-SIZE: 9pt; color: #7b8ac3; }
.STYLE9 {font-size: 24px}

-->
</style>
</head>
<body>

<table width="681" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-top:120px">
  <tr>
    <td width="122" height="259" align="center" valign="bottom">&nbsp;</td>
    <td width="441" background="images/login_2.gif">
    <form method="post" action="login.do">
    <table width="326" height="221" border="0" align="center" cellpadding="2" cellspacing="0">
            <tr>
              <td height="25" colspan="2" align="left"><div align="center"><span class="STYLE9">报价管理系统</span></div></td>
            </tr>
            <tr>
              <td height="25" colspan="2" align="left">&nbsp;</td>
            </tr>
            <tr>
              <td width="68" height="30" align="left">用户名：</td>
              <td width="279"><input name="username" type="TEXT" style="background:url(Images/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="UserName"size="25"></td>
            </tr>
            <tr>
              <td height="30" align="left">密码：</td>
              <td><input name="password" TYPE="PASSWORD" style="background:url(Images/login_6.gif) repeat-x; border:solid 1px #27B3FE; height:20px; background-color:#FFFFFF" id="Password" size="26"></td>
            </tr>
            <tr>
              <td height="40" colspan="2" align="center"><img src="images/tip.gif" width="16" height="16"> 请勿非法登录！</td>
          <tr>
              <td colspan="2" align="center"><input type="submit" name="submit" style="background:url(images/login_5.gif) no-repeat" value=" 登  录 ">
                &nbsp;
			  <input type="reset" name="Submit" style="background:url(images/login_5.gif) no-repeat" value=" 取  消 "></td>
          <tr>
              <td height="5" colspan="2"></td>
          </tr>    
    </table>
    </form>
    </td>
    <td width="118">&nbsp;</td>
  </tr>
  <tr>
    <td height="161" colspan="3"></td>
  </tr>
</table>
</body>
</html>