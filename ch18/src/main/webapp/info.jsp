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
	
       
       if(f.Name.value=="")
       {
            alert("姓名不能为空");
            f.Name.focus();
            f.Name.select();
            return false;
        }

        if(isNaN(f.Name.value))
        {
             if(f.Name.value.length<2 || f.Name.value.length>10)
            {
                alert("您输入的姓名长度有误");
                f.Name.focus();
                f.Name.select();
                return false;
            }
         }
        else
        {
                alert("姓名不能为数字");
                f.Name.focus();
                f.Name.select();
                return false;
        }



        if(f.Tel.value=="")
        {
            alert("电话不能为空");
            f.Tel.focus();
            f.Tel.select();
            return false;
        }
        if(f.Tel.value.length<8 || f.Tel.value.length>14)
        {
            alert("电话号码长度不正确");
            f.Tel.focus();
            f.Tel.select();
            return false;
        }
        if(isNaN(f.Tel.value))
        {
            alert("电话必须为数字");
            f.Tel.focus();
            f.Tel.select();
            return false;
        }
        if(f.Email.value=="")
        {
            return true;
        }
        else
        {
            if(f.Email.value.indexOf("@",0)==-1)
            {
                            alert("地址有误,把@补上");
                             f.Email.focus();
                            f.Email.select();
                            return false;
            }
            if(f.Email.value.indexOf(".",0)==-1)
            {
                            alert("地址有误,把.补上");
                            f.Email.focus();
                            f.Email.select();
                            return false;
            }
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
    <td><div align="center">修改个人信息</div></td>

    <td><div align="center"><a href="passwordamend.jsp">密码修改</a></div></td>
    <td><div align="center"><a href="DestineServlet?param=1">预订机票</a></div></td>
    <td><div align="center"><a href="ExamineSerlvet">查看购物车</a></div></td>
    <td><div align="center"><a href="index.html">退出</a></div></td>
  </tr>
</table>
<p>&nbsp;</p>
<form name="form1" method="post" action="NoparamServlet?param=1" onsubmit="return User()">
    <p>&nbsp;</p>
    <table width="443" border="1" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="94" height="38"><div align="center">
          <p>用&nbsp;户 名:</p>
          </div></td>
        <td width="400">          <div align="center">
           <input name="Username" type="text" id="yonghu" size="20" readonly="true" value="${user.username}"/>
        </div></td>
      </tr>
      <tr>
        <td height="38"><div align="center">真实姓名:</div></td>
        <td><div align="center">
          <input name="Name" type="text" id="xingbin" size="20" value="${user.name}">
        </div></td>
      </tr>
      <tr>
        <td height="38"><div align="center">性 &nbsp;&nbsp;&nbsp;别:</div></td>
        <td><div align="center">

            <select name="Sex" >
               <option value="${ho}" >${ho}</option>
               <option value="${mo}">${mo}</option>

            </select>


            </div></td>
      </tr>
      <tr>
        <td height="38"><div align="center">电话号码:</div></td>
        <td><div align="center">
          <input name="Tel" type="text" id="zhengjian" size="20" value="${user.tel}">
        </div></td>
      </tr>
      <tr>
        <td height="38"><div align="center">电子邮件:</div></td>
        <td><div align="center">
          <input name="Email" type="text" id="Emil" size="20" value="${user.email}">
        </div></td>
      </tr>
    </table>
    <p align="center">
      <input type="submit" name="Submit" value="修改">
   
    
</p>
  </form>
</body>
</html>

