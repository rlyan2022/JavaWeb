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
		if (trim(document.getElementById("courseId").value) == "") {
			alert("课程名称不能为空!");
			document.getElementById("courseId").focus();
			return;
		}
		with (document.getElementById("teacherForm")) {
			method = "post";
			action = "teacher.do?p=saveCourse";
			submit();
		}
	}

	function goBack() {
		window.self.location = "teacher.do?p=detail&id=${teacher.id}";
	}


</script>
  </head>

  <body>

    <div id="content">

    <TABLE cellSpacing="1" cellPadding="2" width="30%" align="center" border="0">
      <TBODY>
      <tr>
        <td width="522" class="p1" height="25" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14">&nbsp;<b>教师管理&gt;&gt;设置课程</b></td>
      </tr>
      </TBODY>
    </TABLE>
    <hr width="30%">

    <%@ include file="/commons/messages.jsp" %>

    <form name="teacherForm" id="teacherForm">

  	<input type="hidden" name="id" value="${teacher.id }">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="30%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableLabel width="35%">教师姓名：</TD>
          <TD class=TableData>${teacher.name }
      </TR>

      <TR>
          <TD class=TableLabel width="35%">课程名:</TD>
          <TD class=TableData>
          	<select name="courseId" class="select1" id="courseId">
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getCourseList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
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
