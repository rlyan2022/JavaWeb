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
			var url = "getStudents.jsp?id=" + escape(document.markForm.teamId.options[document.markForm.teamId.selectedIndex].value);
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

	function checkAll() {
		for (var i = 0; i < document.getElementsByName("selectFlag").length; i++) {
			document.getElementsByName("selectFlag")[i].checked = document.getElementById("ifAll").checked;
		}
	}

	function queryItem() {
		with (document.getElementById("markForm")) {
			method = "post";
			action = "mark.do?p=list&pageNo=1";
			submit();
		}
	}

	function resetItem() {
		window.self.location = "mark.do?p=analyse";
	}

	function myOnkeypress() {
		if (window.event.keyCode == 13) {
			queryItem();
		}
	}

	function topPage() {
		window.self.location = "mark.do?p=list&pageNo=${pageModel.topPageNo}"
	}

	function previousPage() {
		window.self.location = "mark.do?p=list&pageNo=${pageModel.previousPageNo}"
	}

	function nextPage() {
		window.self.location = "mark.do?p=list&pageNo=${pageModel.nextPageNo}"
	}

	function bottomPage() {
		window.self.location = "mark.do?p=list&pageNo=${pageModel.bottomPageNo}"
	}

	</script>
  </head>

  <body>

    <div id="content">

    <TABLE cellSpacing="1" cellPadding="2" width="60%" align="center" border="0">
      <TBODY>
      <TR>
        <TD width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14">&nbsp;<b>成绩管理</B></TD>
      </TR>
      </TBODY>
    </TABLE>

    <hr width="80%">

	<%@ include file="/commons/messages.jsp" %>

    <form name="markForm" id="markForm" >

    <input type="hidden" name="flag" id="flag" value="true">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
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
          		<option value="">--请选择--</option>
         		<c:forEach items="${drp:getCourseList()}" var="item" >
         			<option value="${item.id}">${item.name}</option>
        		</c:forEach>
        	</select>
        	<font color="#FF0000">学生:</font>
            <select name="studentId" class="select1" id="studentId">
          		<option value="">--请先选择班级--</option>
        	</select>
        	<font color="#FF0000">学期:</font>&nbsp;&nbsp;
            <select name="semester" class="select1" id="semester">
          		<option value="">--请选择--</option>
        	</select>&nbsp;&nbsp;
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="queryItem()">
            <input name="btnReset" type="button" class="BigButton" id="btnReset"  value="汇总分析" onClick="resetItem()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>

    <p>

    <TABLE class=small cellSpacing="1" cellPadding="2" width="80%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
      	  <TD class="TableSeparator" width="5%" align="center"><input type="checkbox" name="ifAll" onClick="checkAll()" ></TD>
      	  <TD class="TableSeparator" width="25%" align="center"><strong>课程</strong></TD>
      	  <TD class="TableSeparator" width="25%" align="center"><strong>班级</strong></TD>
      	  <TD class="TableSeparator" width="15%" align="center"><strong>学号</strong></TD>
      	  <TD class="TableSeparator" width="15%" align="center"><strong>学生</strong></TD>
      	  <TD class="TableSeparator" width="20%" align="center"><strong>分数</strong></TD>
      </TR>

      <c:forEach items="${pageModel.list}" var="item">
      <TR>
      	  <TD class=TableData align="center"><input type="checkbox" name="selectFlag" id="selectFlag" class="checkbox1" value="${item.id }"></TD>
	      <TD class=TableData align="center">${ item.course.name }</TD>
	      <TD class=TableData align="center">${ item.student.team.name }</TD>
	      <TD class=TableData align="center">${ item.student.code }</TD>
	      <TD class=TableData align="center">${ item.student.name }</TD>
	      <TD class=TableData align="center">${ item.score }</TD>

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
        	<input name="btnDelete" type="button" class="BigButton" id="btnDelete" value="删除" onClick="deleteItem()">
        	<input name="btnModify" type="button" class="BigButton" id="btnModify" value="修改"  onClick="modifyItem()">
      	  </TD>
      </TR>
     </TBODY>
	</TABLE>
	</form>
  </body>
</html>


