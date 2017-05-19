<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="drp" uri="http://www.sanqing.com/drp/functions"%> 
<%@ include file="/commons/taglibs.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${CompanyName}--${ProjectName}</title>
    <%@ include file="/commons/meta.jsp" %>
    
    <script type="text/javascript">
		var req;
		
		function init() {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		
		function change2() {
			init();
			var url = "getCoursesForm2.jsp?id=" + escape(document.form2.teamId.options[document.form2.teamId.selectedIndex].value);
			req.open("GET", url, true);
			req.onreadystatechange = callback;
			req.send(null);
		}
		
		function change3() {
			init();
			var url = "getCoursesForm3.jsp?id=" + escape(document.form3.teamId.options[document.form3.teamId.selectedIndex].value);
			req.open("GET", url, true);
			req.onreadystatechange = callback;
			req.send(null);
		}
		
		function callback() {
			if(4 == req.readyState) {
		
				if(200 == req.status) {
					//alert(req.responseText);
					eval(req.responseText);
				}
			}
			
		}
	</script>
		
	<script src="script/windows.js"></script>
	<script type="text/javascript">

	function checkAll() {
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			document.getElementsByName("selectFlag")[i].checked = document.getElementById("ifAll").checked;
		}
	}
	
	function query1() {
		with (document.getElementById("form1")) {
			method = "post";
			action = "mark.do?p=analyseTeam";
			submit();
		}
	}
	
	function query2() {
		with (document.getElementById("form2")) {
			method = "post";
			action = "mark.do?p=analyseScore";
			submit();
		}
	}
	
	function query3() {
		with (document.getElementById("form3")) {
			method = "post";
			action = "mark.do?p=analyseCourse";
			submit();
		}
	}
	
	function resetItem() {
		window.self.location = "mark.do?p=list&pageNo=1&flag=true";
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
        <TD width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14">&nbsp;<b>成绩管理&gt;&gt;汇总分析</B></TD>
      </TR>
      </TBODY>
    </TABLE>

    <hr width="80%">

	<%@ include file="/commons/messages.jsp" %>

    <form name="form1" id="form1" >
    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableData align=center>
            <font color="#FF0000">班级:</font>
            <select name="teamId" class="select1" id="teamId">
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getTeamList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
        	<font color="#FF0000">学期:</font>&nbsp;&nbsp;
            <select name="semester" class="select1" id="semester">
          		<option value="">--请选择--</option>
        	</select>&nbsp;&nbsp;
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="query1()">
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="成绩管理" onClick="resetItem()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>
    </form>

    <form name="form2" id="form2" >
    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableData align=center>
            <font color="#FF0000">班级:</font>
            <select name="teamId" class="select1" id="teamId" onchange="change2()">
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getTeamList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
        	<font color="#FF0000">课程:</font>
            <select name="courseId" class="select1" id="courseId" >
          		<option value="">--请先选择班级--</option>
        	</select>
        	<font color="#FF0000">学期:</font>&nbsp;&nbsp;
            <select name="semester" class="select1" id="semester">
          		<option value="">--请选择--</option>
        	</select>&nbsp;&nbsp;
        	成绩分数段:<input type="text" name="min" id="min" size="10" value="0">--
        			 <input type="text" name="max" id="max" size="10" value="0">
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="query2()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>
    </form>

    <form name="form3" id="form3" >
    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableData align=center>
            <font color="#FF0000">班级:</font>
            <select name="teamId" class="select1" id="teamId" onchange="change3()">
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getTeamList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
        	<font color="#FF0000">课程:</font>
            <select name="courseId" class="select1" id="courseId">
          		<option value="">--请先选择班级--</option>
        	</select>
        	<font color="#FF0000">学期:</font>&nbsp;&nbsp;
            <select name="semester" class="select1" id="semester">
          		<option value="">--请选择--</option>
        	</select>&nbsp;&nbsp;
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="query3()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>
   </form>

    <p>
    <logic:present name="marks1" scope="request">
    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>学号</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>姓名</strong></TD>
		  <c:forEach items="${courses}" var="item">
	      	<TD class="TableSeparator" width="10%" align="center"><strong>${item.name }</strong></TD>
      	  </c:forEach>
      	  <TD class="TableSeparator" width="5%" align="center"><strong>总分</strong></TD>
      </TR>

	<!--
      <c:forEach items="${marks1}" var="item">

      <TR>
	      <TD class=TableData align="center">${ item.student.code }</TD>
	      <TD class=TableData align="center">${ item.student.name  }</TD>
	      <TD class=TableData align="center">${ requestScope.courseSchedule.team.name }</TD>
		  <TD class=TableData align="center">${ requestScope.courseSchedule.semester }</TD>
		  <TD class=TableData align="center">${ requestScope.courseSchedule.score }</TD>
	      <TD class=TableData align="center">100</TD>
      </TR>

      </c:forEach>
      -->
      </TBODY>
    </TABLE>
    </logic:present>


    <logic:present name="marks2" scope="request">
     <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class="TableSeparator" width="10%" align="center"><strong>学号</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>姓名</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>课程</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>分数</strong></TD>
      </TR>

      <c:forEach items="${marks2}" var="item">

      <TR>
	      <TD class=TableData align="center">${ item.student.code }</TD>
	      <TD class=TableData align="center">${ item.student.name }</TD>
	      <TD class=TableData align="center">${ item.course.name }</TD>
		  <TD class=TableData align="center">${ item.score }</TD>
      </TR>

      </c:forEach>
      </TBODY>
    </TABLE>
    </logic:present>

    <p>

     <logic:present name="marks3" scope="request">
     <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class="TableSeparator" width="10%" align="center"><strong>学号</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>姓名</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>课程</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>分数</strong></TD>
      </TR>

      <c:forEach items="${marks3}" var="item">

      <TR>
	      <TD class=TableData align="center">${ item.student.code }</TD>
	      <TD class=TableData align="center">${ item.student.name }</TD>
	      <TD class=TableData align="center">${ item.course.name }</TD>
		  <TD class=TableData align="center">${ item.score }</TD>
      </TR>

      </c:forEach>
      </TBODY>
    </TABLE>
    </logic:present>
    
  </body>
</html>

