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
	
	function queryItem() {
		with (document.getElementById("studentForm")) {
			method = "post";
			action = "student.do?p=count&pageNo=1";
			submit();
		}
	}	
	
	function resetItem() {
		window.self.location = "student.do?p=list&pageNo=1&flag=true";
	}
	
	function myOnkeypress() {
		if (window.event.keyCode == 13) {
			queryItem();
		}
	}
	
	function topPage() {
		window.self.location = "student.do?p=count&pageNo=${pageModel.topPageNo}"
	}
	
	function previousPage() {
		window.self.location = "student.do?p=count&pageNo=${pageModel.previousPageNo}"
	}	
	
	function nextPage() {
		window.self.location = "student.do?p=count&pageNo=${pageModel.nextPageNo}"
	}
	
	function bottomPage() {
		window.self.location = "student.do?p=count&pageNo=${pageModel.bottomPageNo}"
	}
	
	</script>
  </head>
  
  <body>

    <div id="content">
    
    <TABLE cellSpacing="1" cellPadding="2" width="60%" align="center" border="0">
      <TBODY>
      <TR>
        <TD width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14">&nbsp;<b>学生管理&gt;&gt;学生统计</B></TD>
      </TR>
      </TBODY>
    </TABLE>

    <hr width="60%">

	<%@ include file="/commons/messages.jsp" %>

    <form name="studentForm" id="studentForm" >

    <input type="hidden" name="flag" id="flag" value="true">

      <TABLE class=small cellSpacing="1" cellPadding="2" width="60%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableData align=center>
            <font color="#FF0000">班级:</font>
            <select name="teamId" class="select1" id="teamId" onchange="change()">
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getTeamList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
        	<font color="#FF0000">课程:</font>
            <select name="courseId" class="select1" id="courseId">
          		<option value="">----请选择----</option>
         		<c:forEach items="${drp:getCourseList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
        	<font color="#FF0000">教师:</font>
            <select name="teacherId" class="select1" id="teacherId">
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getTeacherList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="queryItem()">
         	<input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="管理" onClick="resetItem()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>

    <p>

    <TABLE class=small cellSpacing="1" cellPadding="2" width="60%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
      	  <TD class="TableSeparator" width="5%" align="center"><input type="checkbox" name="ifAll" onClick="checkAll()" ></TD>
      	  <TD class="TableSeparator" width="20%" align="center"><strong>学号</strong></TD>
          <TD class="TableSeparator" width="20%" align="center"><strong>姓名</strong></TD>
          <TD class="TableSeparator" width="15%" align="center"><strong>入学时间</strong></TD>
          <TD class="TableSeparator" width="20%" align="center"><strong>出生年月</strong></TD>
          <TD class="TableSeparator" width="5%" align="center"><strong>性别</strong></TD>
          <TD class="TableSeparator" width="25%" align="center"><strong>班级</strong></TD>
      </TR>

      <c:forEach items="${pageModel.list}" var="item">
      <TR>
      	  <TD class=TableData align="center" ><input type="checkbox" name="selectFlag" id="selectFlag" class="checkbox1" value="${item.id }"></td>
	      <TD class=TableData align="center">${item.code }</td>
          <TD class=TableData align="center">${item.name}</TD>
          <TD class=TableData align="center">${item.enrollDate}</TD>
          <TD class=TableData align="center">${item.birthday}</TD>
          <TD class=TableData align="center">${item.sex}</TD>
          <TD class=TableData align="center">${item.team.name}</TD>
      </TR>
      </c:forEach>

      </TBODY>
    </TABLE>

    <p>
    <TABLE border="0">
      <TBODY>
      <TR>
      	  <TD class=TableData align="right">
      	     共${pageModel.totalRecords }条记录&nbsp;&nbsp;共${pageModel.totalPages }页&nbsp;&nbsp;第${pageModel.pageNo }页&nbsp;&nbsp;&nbsp;&nbsp;
      	    <input name="btnTopPage" type="button" class="BigButton" id="btnTopPage" value="首页"  title="首页" onClick="topPage()">
            <input name="btnPreviousPage" type="button" class="BigButton" id="btnPreviousPage" value="上一页"  title="上一页" onClick="previousPage()">
            <input name="btnNext" type="button" class="BigButton" id="btnNext" value="下一页"  title="下一页" onClick="nextPage()">
            <input name="btnBottomPage" type="button" class="BigButton" id="btnBottomPage" value="尾页"  title="尾页" onClick="bottomPage()">
      	  </TD>
      </TR>
     </TBODY>
	</TABLE>
	</form>
  </body>
</html>
