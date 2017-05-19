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
		
		function change() {
			init();
			var url = "getCourses.jsp?id=" 
							+ escape(document.courseScheduleForm.teamId.options[document.courseScheduleForm.teamId.selectedIndex].value);
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

	function addItem() {
		window.self.location = "mark.do?p=edit&pageNo=${markForm.pageNo}";
	}
	
	function modifyItem() {
		var count = 0;
		var j = 0;
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			if (document.getElementsByName("selectFlag")[i].checked) {
				j = i;
				count++;
			}
		}
		if (count == 0) {
			alert("请选择需要修改的成绩！");
			return;
		}
		if (count > 1) {
			alert("一次只能修改一个成绩！");
			return;
		}
		if (count == 1) {
			window.self.location = "mark.do?p=edit&id=" +
			                        document.getElementsByName("selectFlag")[j].value +
			                        "&pageNo=${markForm.pageNo}";
		}
	}

	function deleteItem() {
		var flag = false;
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			if (document.getElementsByName("selectFlag")[i].checked) {
				flag = true;
			}
		}
		if (!flag) {
			alert("请选择需要删除的成绩！");
			return;
		}
		if (window.confirm("确认删除吗？")) {
			with (document.getElementById("markForm")) {
				method = "post";
				action = "mark.do?p=delete&pageNo=${markForm.pageNo}";
				submit();
			}
		}
	}


	function queryItem() {
		with (document.getElementById("courseScheduleForm")) {
			method = "post";
			action = "courseSchedule.do?p=input";
			submit();
		}
	}

	function resetItem() {
		document.getElementsByName("query").value = "";
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
        <TD width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14">&nbsp;<b>成绩录入</B></TD>
      </TR>
      </TBODY>
    </TABLE>

    <hr width="80%">

	<%@ include file="/commons/messages.jsp" %>

    <form name="courseScheduleForm" id="courseScheduleForm" >

    <input type="hidden" name="flag" id="flag" value="true">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableData align=center>
            <font color="#FF0000">班级:</font>
            <select name="teamId" class="select1" id="teamId" onchange="change()">
          		<option value="">--请选择--</option>
         		<c:forEach items="${teams}" var="item" >
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
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="queryItem()">
            <input name="btnReset" type="button" class="BigButton" id="btnReset"  value="重置" onClick="resetItem()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>
    </form>

    <p>

    <form name="markForm" action="mark.do" method="post" onsubmit="return validateForm(this);">

     <input type="hidden" name="p" value="confirm">
     <input type="hidden" name="courseId" value="${requestScope.courseSchedule.course.id}">
	 <input type="hidden" name="num" value="${num }">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
      	  <TD class="TableSeparator" width="15%" align="center"><strong>课程</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>学号</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>学生</strong></TD>
      	  <TD class="TableSeparator" width="15%" align="center"><strong>班级</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>学期</strong></TD>
      	  <TD class="TableSeparator" width="10%" align="center"><strong>学分</strong></TD>
      	  <TD class="TableSeparator" width="5%" align="center"><strong>分数</strong></TD>
      </TR>

   <%
   		int i=0;
   %>

      <c:forEach items="${requestScope.courseSchedule.team.students}" var="item">

      	<input type="hidden" name="studentId<%=++i %>" value="${item.id }">

      <TR>
	      <TD class=TableData align="center">${ requestScope.courseSchedule.course.name }</td>
	      <TD class=TableData align="center">${ item.code }</td>
	      <TD class=TableData align="center">${ item.name }</td>
	      <TD class=TableData align="center">${ requestScope.courseSchedule.team.name }</td>
		  <TD class=TableData align="center">${ requestScope.courseSchedule.semester }</td>
		  <TD class=TableData align="center">${ requestScope.courseSchedule.score }</td>
	      <TD class=TableData align="center"><input type="text" name="score<%=i %>" id="score" size="10" value="0"></TD>
      </TR>

      </c:forEach>
      </TBODY>
    </TABLE>
    <logic:present name="exist" scope="request">
    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" >
      <TBODY>
      <TR>
          <TD class=TableButton colspan=2 align=center>
            <input type="submit" value="保存" class="BigButton" />
			<input type="button"  value="返回" onClick="history.back(-1);" class="BigButton">
          </TD>
      </TR>
     </TBODY>
    </TABLE>
    </logic:present>
	</form>
  </body>
</html>

