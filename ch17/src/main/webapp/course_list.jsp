<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>${CompanyName}--${ProjectName}</title>
    <%@ include file="/commons/meta.jsp" %>
	
	<script src="script/windows.js"></script>
	<script type="text/javascript">

	function addItem() {
		window.self.location = "course.do?p=edit&pageNo=${courseForm.pageNo}";
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
			alert("请选择需要修改的课程！");
			return;
		}
		if (count > 1) {
			alert("一次只能修改一个课程！");
			return;
		}
		if (count == 1) {
			window.self.location = "course.do?p=edit&id=" +
			                        document.getElementsByName("selectFlag")[j].value +
			                        "&pageNo=${courseForm.pageNo}";
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
			alert("请选择需要删除的课程！");
			return;
		}
		if (window.confirm("确认删除吗？")) {
			with (document.getElementById("courseForm")) {
				method = "post";
				action = "course.do?p=delete&pageNo=${courseForm.pageNo}";
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
		with (document.getElementById("courseForm")) {
			method = "post";
			action = "course.do?p=list&pageNo=1";
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

	function topPage() {
		window.self.location = "course.do?p=list&pageNo=${pageModel.topPageNo}"
	}

	function previousPage() {
		window.self.location = "course.do?p=list&pageNo=${pageModel.previousPageNo}"
	}

	function nextPage() {
		window.self.location = "course.do?p=list&pageNo=${pageModel.nextPageNo}"
	}

	function bottomPage() {
		window.self.location = "course.do?p=list&pageNo=${pageModel.bottomPageNo}"
	}

	</script>
  </head>

  <body>

    <div id="content">

    <TABLE cellSpacing="1" cellPadding="2" width="60%" align="center" border="0">
      <TBODY>
      <TR>
        <TD width="522" class="p1" height="2" nowrap><img src="images/mark_arrow_02.gif" width="14" height="14">&nbsp;<b>课程管理</B></TD>
      </TR>
      </TBODY>
    </TABLE>

    <hr width="60%">

	<%@ include file="/commons/messages.jsp" %>

    <form name="courseForm" id="courseForm" >

    <input type="hidden" name="flag" id="flag" value="true">

    <TABLE class=small cellSpacing="1" cellPadding="2" width="60%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
          <TD class=TableData align=center>
            <font color="#FF0000">课程名:</font>&nbsp;&nbsp;<input type="text" name="name" id="name" class="BigInput" onkeypress="myOnkeypress()" size="40" maxlength="40" >
            <input name="btnQuery" type="button" class="BigButton" id="btnQuery"  value="查询" onClick="queryItem()">
            <input name="btnReset" type="button" class="BigButton" id="btnReset"  value="重置" onClick="resetItem()">
          </TD>
      </TR>
      </TBODY>
    </TABLE>

    <p>

    <TABLE class=small cellSpacing="1" cellPadding="2" width="60%" align="center" bgColor="#000000" border="0">
      <TBODY>
      <TR>
      	  <TD class="TableSeparator" width="5%" align="center"><input type="checkbox" name="ifAll" onClick="checkAll()" ></TD>
      	  <TD class="TableSeparator" width="25%" align="center"><strong>课程名</strong></TD>
      	  <TD class="TableSeparator" width="25%" align="center"><strong>操作</strong></TD>
      </TR>

      <c:forEach items="${pageModel.list}" var="item">
      <TR>
      	  <TD class=TableData align="center" ><input type="checkbox" name="selectFlag" id="selectFlag" class="checkbox1" value="${item.id }"></TD>
	      <TD class=TableData align="center">${item.name }</TD>
	      <TD class=TableData align="center"><a href="course.do?p=detail&id=${item.id}">查看开课班级</a></TD>
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
      	    <input name="btnAdd" type="button" class="BigButton" id="btnAdd"  value="添加" onClick="addItem()">
        	<input name="btnDelete" type="button" class="BigButton" id="btnDelete" value="删除" onClick="deleteItem()">
        	<input name="btnModify" type="button" class="BigButton" id="btnModify" value="修改"  onClick="modifyItem()">
      	  </TD>
      </TR>
     </TBODY>
	</TABLE>
	</form>
  </body>
</html>
