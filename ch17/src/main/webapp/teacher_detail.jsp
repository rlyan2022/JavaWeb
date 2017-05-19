<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="drp" uri="http://www.sanqing.com/drp/functions"%> 
<%@ include file="/commons/taglibs.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${CompanyName}--${ProjectName}</title>
    <%@ include file="/commons/meta.jsp" %>
	
	<script src="script/windows.js"></script>
	<script type="text/javascript">

	function addItem() {
		window.self.location = "teacher.do?p=editCourse&id=${teacher.id}";
	}
	
	function deleteItem() {
		var flag = false;
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			if (document.getElementsByName("selectFlag")[i].checked) {
				flag = true;
			}		
		}
		if (!flag) {
			alert("请选择需要删除的课程！");
			return;
		}
		if (window.confirm("确认删除吗？")) {
			with (document.getElementById("courseScheduleForm")) {
				method = "post";
				action = "teacher.do?p=deleteCourse&id=${teacher.id}";
				submit();
			}
		}
	}

	function checkAll() {
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			document.getElementsByName("selectFlag")[i].checked = document.getElementById("ifAll").checked;
		}
	}

	function queryItem() {
		window.self.location = "teacher.do?p=detail&id=${teacher.id}&teamId="
									+ escape(document.courseScheduleForm.teamId.options[document.courseScheduleForm.teamId.selectedIndex].value);
	}

	function resetItem() {
		document.getElementsByName("teamId").value = "";
	}

	function myOnkeypress() {
		if (window.event.keyCode == 13) {
			queryItem();
		}
	}

	</script>
  </head>

  <body>

    <div id="content">

    <TABLE cellSpacing="1" cellPadding="2" width="60%" align="center" border="0">
      <TBODY>
      <TR>
        <TD width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14">&nbsp;<b>教师管理&gt;&gt;<strong>${requestScope.teacher.name }</strong>详细信息</B></TD>
      </TR>
      </TBODY>
    </TABLE>

    <hr width="60%">

	<%@ include file="/commons/messages.jsp" %>

    <form name="courseScheduleForm" id="courseScheduleForm" >


    <TABLE class=small cellSpacing="1" cellPadding="2" width="60%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableData align=center>
            <font color="#FF0000">班级:</font>&nbsp;&nbsp;
            <select name="teamId" class="select1" id="teamId">
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getTeamList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="queryItem()">
            <input name="btnReset" type="button" class="BigButton" id="btnReset"  value="重置" onClick="resetItem()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>

    <p>
  	 <TABLE cellSpacing="1" cellPadding="2" width="60%" align="center" border="0">
      <TBODY>
      <TR>
        <TD width="522" align="center">目前已安排的课程</TD>
      </TR>
      </TBODY>
    </TABLE>
    <p>
    <TABLE class=small cellSpacing="1" cellPadding="2" width="60%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
		  <TD class="TableSeparator" width="25%" align="center">课程</TD>
		  <TD class="TableSeparator" width="25%" align="center">班级</TD>
		  <TD class="TableSeparator" width="25%" align="center">学期</TD>
		  <TD class="TableSeparator" width="25%" align="center">学分</TD>
      </TR>

      <c:forEach items="${courseSchedules}" var="item">
      <TR>
	      <TD class=TableData align="center">${ item.course.name }</TD>
	      <TD class=TableData align="center">${ item.team.name }</TD>
	      <TD class=TableData align="center">${ item.semester }</TD>
	      <TD class=TableData align="center">${ item.score }</TD>
      </TR>
      </c:forEach>

      </TBODY>
    </TABLE>

    <p>
        <hr width="60%">
    <p>
    <TABLE cellSpacing="1" cellPadding="2" width="60%" align="center" border="0">
      <TBODY>
      <TR>
        <TD width="522"  align="center">可教的课程</TD>
      </TR>
      </TBODY>
    </TABLE>
    <p>
    <TABLE class=small cellSpacing="1" cellPadding="2" width="60%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
      	  <TD class="TableSeparator" width="5%" align="center"><input type="checkbox" name="ifAll" onClick="checkAll()" ></TD>
		  <TD class="TableSeparator" width="25%" align="center">课程名</TD>
      </TR>

      <c:forEach items="${teacher.courses}" var="item">
      <TR>
      	  <TD class=TableData align="center" ><input type="checkbox" name="selectFlag" id="selectFlag" class="checkbox1" value="${item.id }"></td>
	      <TD class=TableData align="center">${ item.name }</TD>
      </TR>
      </c:forEach>

      </TBODY>
    </TABLE>

    <p>

    <TABLE border="0">
      <TBODY>
      <TR>
      	  <TD class=TableData align="right">
      	    <input name="btnAdd" type="button" class="BigButton" id="btnAdd"  value="添加" onClick="addItem()">
        	<input name="btnDelete" type="button" class="BigButton" id="btnDelete" value="删除" onClick="deleteItem()">
      	  </TD>
      </TR>
     </TBODY>
	</TABLE>
    
   
        
    
	</form>
  </body>
</html>
