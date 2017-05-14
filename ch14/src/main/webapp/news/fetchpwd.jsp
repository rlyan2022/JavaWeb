<%@ page contentType="text/html;charset=UTF-8"%>

<link rel="stylesheet" href="../css/text.css" type="text/css">
<script language="JavaScript">
	function check(){
		if(fetch.userName.value==""){
			alert("用户名不为空！");
			return false;
		}
		if(fetch.userName.value.length>20){
			alert("用户名为数不超过20！");
			return false;
		}
		return true;
	}
</script>

<body topMargin=0>
<br>
<form action="fthname.jsp" method="post" name="fetch" onSubmit="return check();">
<table width="300" height="80" border="0" align="center" cellpadding="0" cellspacing="0">

  <tr>
    <td align="center" valign="top"><p>请输入你的用户名:
        <input type="text" size="16" name="userName" class="text">
      </p>
      <p>
          <input type="submit" value="下一步">
          &nbsp; </p></td>
  </tr>
 
</table>
 </form>
</body>
