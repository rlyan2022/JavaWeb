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
		if (trim(document.getElementById("code").value) == "") {
			alert("学号不能为空!");
			document.getElementById("code").focus();
			return;
		}

		if (trim(document.getElementById("name").value) == "") {
			alert("姓名不能为空!");
			document.getElementById("name").focus();
			return;
		}

		if (trim(document.getElementById("sex").value) == "") {
			alert("性别不能为空!");
			document.getElementById("sex").focus();
			return;
		}
		with (document.getElementById("studentForm")) {
			method = "post";
			action = "student.do?p=save&pageNo=${studentForm.pageNo}";
			submit();
		}
	}

	function goBack() {
		window.self.location = "student.do?p=list&pageNo=${studentForm.pageNo}";
	}


</script>
  </head>

  <body>

    <div id="content">

    <TABLE cellSpacing="1" cellPadding="2" width="30%" align="center" border="0">
      <TBODY>
      <tr>
        <td width="522" class="p1" height="25" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14">&nbsp;<b>学生管理&gt;&gt;增加学生</b></td>
      </tr>
      </TBODY>
    </TABLE>
    <hr width="30%">

    <%@ include file="/commons/messages.jsp" %>

    <form name="studentForm" id="studentForm">

  	<input type="hidden" name="id" value="${student.id }">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="30%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableLabel width="35%">学号：</TD>
          <TD class=TableData><input id="code" name="code" size="24" value="${student.code }" class="BigInput" /></TD>
      </TR>
      <TR>
          <TD class=TableLabel>姓名：</TD>
          <TD class=TableData><input id="name" name="name" size="24" value="${student.name }" class="BigInput" /></TD>
      </TR>
      <TR>
          <TD class=TableLabel>入学时间：</TD>
          <TD class=TableData><input id="enrollDate" name="enrollDate" size="24" value="${student.enrollDate }" class="BigInput" /></TD>
      </TR>
      <TR>
          <TD class=TableLabel>出生年月：</TD>
          <TD class=TableData><input id="birthday" name="birthday" size="24" value="${student.birthday }" class="BigInput" /></TD>
      </TR>
      <TR>
          <TD class=TableLabel>性别：</TD>
          <TD class=TableData>
 			<input type="radio" name="sex" value="男" checked>男
          	<input type="radio" name="sex" value="女" >女
          </TD>
      </TR>
      <TR>
          <TD class=TableLabel width="35%">班级:</TD>
          <TD class=TableData>
          	<select name="teamId" class="select1" id="teamId">
          		<option value="" >--请选择--</option>
         		<c:forEach items="${drp:getTeamList()}" var="item" >
         			<c:set var="select" value=""/>
         			<c:if test="${ item.id eq student.team.id }">
         				<c:set var="select" value="selected"/>
         			</c:if>
         			<option value="${item.id}" ${ select }>${item.name}</option>
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
