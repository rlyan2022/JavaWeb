<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="javascript">
function User()
{
	var f=document.form1;
	if(f.Password.value=="")
        {
               alert("原密码不能为空");
               f.Password.focus();
                f.Password.select();
		return false;
        }
        if(f.Password.value.length>16 || f.Password.value.length<6)
	{
		alert("原密码长度不正确");
                f.Password.focus();
                f.Password.select();
		return false;

	}
        if(isNaN(f.Password.value))
        {
                alert("原密码必须为数字");
                f.Password.focus();
                f.Password.select();
                return false;

        }


      if(f.Password1.value=="")
        {
               alert("新密码不能为空");
               f.Password1.focus();
                f.Password1.select();
		return false;
        }
        if(f.Password1.value.length>16 || f.Password.value.length<6)
	{
		alert("新密码长度不正确");
                f.Password1.focus();
                f.Password1.select();
		return false;

	}
        if(isNaN(f.Password1.value))
        {
                alert("新密码必须为数字");
                f.Password1.focus();
                f.Password1.select();
                return false;
        }
      if(f.Password1.value!=f.Password2.value)
	{
		alert("密码不一致!");
                f.Password2.focus();
                f.Password2.select();
		return false;
	}



}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><style type="text/css">
<!--
body {
	background-image: url(inmage/12d.JPG);
}
.style1 {
	font-size: 36px;
	color: #FF0000;
}
-->
</style><body>
<p align="center" class="style1">个人信息管理</p>
<hr>
<p>&nbsp;</p>
<table width="644" height="26" border="2" align="center" cellpadding="1" cellspacing="0" bordercolor="#FFFFFF" bgcolor="#CDE9FE">
  <tr>
    <td><div align="center"><a href="index.html">首页</a></div></td>
    <td><div align="center"><a href="ModifySerlvet?param=0">修改个人信息</a></div></td>

    <td><div align="center">密码修改</a></div></td>
    <td><div align="center"><a href="DestineServlet?param=1">预订机票</a></div></td>
    <td><div align="center"><a href="ExamineSerlvet">查看购物车</a></div></td>
    <td><div align="center"><a href="index.html">退出</a></div></td>
  </tr>
</table>
<p>&nbsp;</p>
<form name="form1" method="post" action="PassServlet" onsubmit="return User()">
    <p>&nbsp;</p>
    <table width="300" border="1" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="94" height="38"><div align="center"><p>原密码</p> </div></td>
        <td width="200"><div align="center"><input name="Password" type="password"  size="20" />
        </div></td>
      </tr>
      <tr>
        <td height="38"><div align="center">新密码</div></td>
        <td><div align="center"><input name="Password1" type="password"  size="20" >
        </div></td>
      </tr>
      <tr>
        <td height="38"><div align="center">确认密码</div></td>
        <td><div align="center"> <input name="Password2" type="password" size="20" >
        </div></td>
      </tr>
      <tr>

    </table>
    <p align="center">
      <input type="submit" name="Submit" value="修改"/>&nbsp;&nbsp;&nbsp;
      <input type="reset" name="reset" value="重置"/>
   
    
</p>
  </form>
</body>
</html>

