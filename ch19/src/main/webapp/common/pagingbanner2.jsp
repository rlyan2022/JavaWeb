<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
<s:if test="page.totalCount>0">
有${page.totalCount}条记录, 显示${(page.pageNo-1)*page.pageSize+1}到<s:if test="page.totalCount-page.pageNo*page.pageSize>page.pageSize">${page.pageNo*page.pageSize}</s:if><s:else>${page.totalCount}</s:else>条记录<br/>
第${page.pageNo}页, 共${page.totalPages}页 
每页显示
<select name="pageSizeSelect" onchange="changePageSize(this.options[this.options.selectedIndex].value)">
	<option value="10" <s:if test="page.pageSize==10"> selected </s:if>>10</option>
	<option value="20" <s:if test="page.pageSize==20"> selected </s:if>>20</option>
	<option value="30" <s:if test="page.pageSize==30"> selected </s:if>>30</option>
</select>条<br>
<s:if test="page.totalPages>1">	
	<br/>	
	<s:if test="page.hasPre">[<a href="javascript:jumpPage(1)">首页</a>/<a href="javascript:jumpPage(${page.prePage})">前一页</a>]</s:if>
	<s:elseif test="1">[<b>首页</b>/前一页]</s:elseif>		
	<s:if test="page.pageNo-3>0">...&nbsp;</s:if>
	<s:if test="page.pageNo-2>0"><a href="javascript:jumpPage(${page.pageNo-2})">${page.pageNo-2}</a></s:if>
	<s:if test="page.pageNo-1>0"><a href="javascript:jumpPage(${page.pageNo-1})">${page.pageNo-1}</a></s:if>
	<s:if test="page.pageNo>0"><b>${page.pageNo}</b></s:if>
	<s:if test="page.totalPages>=page.pageNo+1"><a href="javascript:jumpPage(${page.pageNo+1})">${page.pageNo+1}</a></s:if>
	<s:if test="page.totalPages>=page.pageNo+2"><a href="javascript:jumpPage(${page.pageNo+2})">${page.pageNo+2}</a></s:if>
	<s:if test="page.totalPages>=page.pageNo+3"><a href="javascript:jumpPage(${page.pageNo+3})">${page.pageNo+3}</a></s:if>
	<s:if test="page.totalPages>=page.pageNo+4"><a href="javascript:jumpPage(${page.pageNo+4})">${page.pageNo+4}</a></s:if>
	<s:if test="page.totalPages>=page.pageNo+5">...&nbsp;</s:if>	
	<s:if test="page.hasNext">[<a href="javascript:jumpPage(${page.nextPage})">后一页</a>/<a href="javascript:jumpPage(${page.totalPages})">尾页</a>]</s:if>
	<s:elseif test="page.hasNext-1">[后一页/<b>尾页</b>]</s:elseif>	  
</s:if>
</s:if>
</div>