<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="drp" uri="http://www.sanqing.com/drp/functions"%> 
<%@ include file="/commons/taglibs.jsp" %>


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
			var url = "getChilds.jsp?id=" + escape(document.courseScheduleForm.courseId.options[document.courseScheduleForm.courseId.selectedIndex].value);
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
    
    <script src="script/client_validate.js"></script>
	<script type="text/javascript">
	
	function addItem() {
		if (trim(document.getElementById("courseId").value) == "") {
			alert("课程名称不能为空!");
			document.getElementById("courseId").focus();
			return;
		}
		if (trim(document.getElementById("teacherId").value) == "") {
			alert("教师名称不能为空!");
			document.getElementById("teacherId").focus();
			return;
		}
		if (trim(document.getElementById("semester").value) == "") {
			alert("学期不能为空!");
			document.getElementById("semester").focus();
			return;
		}
		if (trim(document.getElementById("score").value) == "") {
			alert("学分不能为空!");
			document.getElementById("score").focus();
			return;
		}
		with (document.getElementById("courseScheduleForm")) {
			method = "post";
			action = "courseSchedule.do?p=save";
			submit();
		}
	}

	function goBack() {
		window.self.location = "team.do?p=detail&id=${team.id}";
	}


</script>
  </head>

  <body>

    <div id="content">

    <TABLE cellSpacing="1" cellPadding="2" width="30%" align="center" border="0">
      <TBODY>
      <tr>
        <td width="522" class="p1" height="25" nowrap><img src="images/mark_arrow_03.gif" width="14" height="14">&nbsp;<b>班级管理&gt;&gt;班级课程维护&gt;&gt;增加课程</b></td>
      </tr>
      </TBODY>
    </TABLE>
    <hr width="30%">

    <%@ include file="/commons/messages.jsp" %>

    <form name="courseScheduleForm" id="courseScheduleForm">

  	<input type="hidden" name="id" value="${courseSchedule.id }">
  	<input type="hidden" name="teamId" value="${team.id }">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="30%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableLabel>班级：</TD>
          <TD class=TableData>${team.name }</TD>
      </TR>

      <TR>
          <TD class=TableLabel width="35%">课程名:</TD>
          <TD class=TableData>
          	<select name="courseId" class="select1" id="courseId" onchange="change()">
          		<option value="" >--请选择--</option>
         		<c:forEach items="${drp:getCourseList()}" var="item" >
         			<c:set var="select" value=""/>
         			<c:if test="${ item.id eq courseSchedule.course.id }">
         				<c:set var="select" value="selected"/>
         			</c:if>
         			<option value="${item.id}" ${ select }>${item.name}</option>
        		</c:forEach>
        	</select>
         </TD>
      </TR>

      <TR>
          <TD class=TableLabel width="35%">教师名:</TD>
          <TD class=TableData>
          	<select name="teacherId" class="select1" id="teacherId">
          		<option value=""><font color="#FF0000">--课程还没选择--</font></option>
        	</select>
         </TD>
      </TR>

      <TR>
          <TD class=TableLabel width="35%">学期:</TD>
          <TD class=TableData>
          	<select name="semester" class="select1" id="semester">
          		<option value="" >--请选择--</option>
          		<option value="1" >第一学期</option>
          		<option value="2" >第一学期</option>
          		<option value="3" >第一学期</option>
          		<option value="4" >第一学期</option>
          		<option value="5" >第一学期</option>
          		<option value="6" >第一学期</option>
        	</select>
         </TD>
      </TR>

      <TR>
          <TD class=TableLabel>学分：</TD>
          <TD class=TableData><input id="score" name="score" size="24" value="${courseSchedule.score }" class="BigInput" /></TD>
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
