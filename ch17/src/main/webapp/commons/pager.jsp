<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>

<TABLE border="0">
      <TBODY>
      <TR>
      	  <TD class=TableData align="center">
      	    <html:form action="${requestName }.do" method="post" > 
	      	    共${pageModel.totalRecords }条记录&nbsp;&nbsp;共${pageModel.totalPages }页&nbsp;&nbsp;第${pageModel.pageNo }页&nbsp;&nbsp;&nbsp;&nbsp;
	      	  	<a href="${requestName }.do?p=list&pageNo=${pageModel.topPageNo }&pageSize=3"> 首页 </a> &nbsp;&nbsp;
				<a href="${requestName }.do?p=list&pageNo=${pageModel.previousPageNo}&pageSize=3"> 上一页 </a> &nbsp;&nbsp;
				<a href="${requestName }.do?p=list&pageNo=${pageModel.nextPageNo}&pageSize=3"> 下一页 </a> &nbsp;&nbsp;
				<a href="${requestName }.do?p=list&pageNo=${pageModel.bottomPageNo }&pageSize=3"> 尾页 </a> &nbsp;&nbsp;
		    	
		   	    <html:hidden property="p" value="list"/>
		    	
	     		<input type=text size=4 name="pageNo" />
	     		<input type="submit" value="go" />
		 	</html:form>
      	  </TD>
      </TR>
     </TBODY>
</TABLE>