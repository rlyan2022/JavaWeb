<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="drp" uri="http://www.sanqing.com/drp/functions"%> 
<%@ include file="/commons/taglibs.jsp" %>

<html>
  <head>
    <title>${CompanyName}--${ProjectName}</title>
    <%@ include file="/commons/meta.jsp" %>
    
    <script src="script/client_validate.js"></script>
	<script type="text/javascript">
	
	function addItem() {
		if (trim(document.getElementById("name").value) == "") {
			alert("用户名称不能为空!");
			document.getElementById("name").focus();
			return;
		}

		if (trim(document.getElementById("password").value) == "") {
			alert("用户密码不能为空!");
			document.getElementById("password").focus();
			return;
		}

		with (document.getElementById("userForm")) {
			method = "post";
			action = "user.do?p=save&pageNo=${userForm.pageNo}";
			submit();
		}
	}

	function goBack() {
		window.self.location = "user.do?p=list&pageNo=${userForm.pageNo}";
	}


</script>
  </head>

  <body>

    <div id="content">

    <TABLE cellSpacing="1" cellPadding="2" width="30%" align="center" border="0">
      <TBODY>
      <tr>
        <td width="522" class="p1" height="25" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14">&nbsp;<b>用户管理&gt;&gt;增加用户</b></td>
      </tr>
      </TBODY>
    </TABLE>
    <hr width="30%">

    <%@ include file="/commons/messages.jsp" %>

    <form name="userForm" id="userForm">

  	<input type="hidden" name="id" value="${requestScope.user.id }">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="30%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableLabel width="35%">用户名：</TD>
          <TD class=TableData><input id="name" name="name" size="24" value="${requestScope.user.name }" class="BigInput" /></TD>
      </TR>
      <TR>
          <TD class=TableLabel>密码：</TD>
          <TD class=TableData><input id="password" name="password" size="24" value="${requestScope.user.password }" class="BigInput" /></TD>
      </TR>
 	  <TR>
          <TD class=TableLabel width="35%">教师:</TD>
          <TD class=TableData>
          	<select name="teacherId" class="select1" id="teacherId" >
          		<option value="" >--请选择--</option>
         		<c:forEach items="${drp:getTeacherList()}" var="item" >
         			<c:set var="select" value=""/>
         			<c:if test="${ item.id eq user.teacher.id }">
         				<c:set var="select" value="selected"/>
         			</c:if>
         			<option value="${item.id}" ${select}>${item.name}</option>
        		</c:forEach>
        	</select>
         </TD>
      </TR>

      <TR>
          <TD class=TableButton colspan=2 align=center>
            <input type="button" value="保存" class="BigButton" onclick="addItem()">
			<input type="button" value="返回" class="BigButton" onClick="goBack()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>
    
    </form>
   </div>    
  </body>
</html>
