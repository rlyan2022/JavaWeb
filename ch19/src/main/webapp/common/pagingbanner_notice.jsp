<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
<s:if test="noticePage.totalCount>0">
有${noticePage.totalCount}条记录, 显示${(noticePage.pageNo-1)*noticePage.pageSize+1}到<s:if test="noticePage.totalCount-noticePage.pageNo*noticePage.pageSize>noticePage.pageSize">${noticePage.pageNo*noticePage.pageSize}</s:if><s:else>${noticePage.totalCount}</s:else>条记录<br/>
第${noticePage.pageNo}页, 共${noticePage.totalPages}页 
每页显示
<select name="pageSizeSelect" onchange="changePageSize(this.options[this.options.selectedIndex].value)">
	<option value="10" <s:if test="noticePage.pageSize==10"> selected </s:if>>10</option>
	<option value="20" <s:if test="noticePage.pageSize==20"> selected </s:if>>20</option>
	<option value="30" <s:if test="noticePage.pageSize==30"> selected </s:if>>30</option>
</select>条<br>
<s:if test="noticePage.totalPages>1">	
	<br/>	
	<s:if test="noticePage.hasPre">[<a href="javascript:jumpPage(1)">首页</a>/<a href="javascript:jumpPage(${noticePage.prePage})">前一页</a>]</s:if>
	<s:elseif test="1">[<b>首页</b>/前一页]</s:elseif>		
	<s:if test="noticePage.pageNo-3>0">...&nbsp;</s:if>
	<s:if test="noticePage.pageNo-2>0"><a href="javascript:jumpPage(${noticePage.pageNo-2})">${noticePage.pageNo-2}</a></s:if>
	<s:if test="noticePage.pageNo-1>0"><a href="javascript:jumpPage(${noticePage.pageNo-1})">${noticePage.pageNo-1}</a></s:if>
	<s:if test="noticePage.pageNo>0"><b>${noticePage.pageNo}</b></s:if>
	<s:if test="noticePage.totalPages>=noticePage.pageNo+1"><a href="javascript:jumpPage(${noticePage.pageNo+1})">${noticePage.pageNo+1}</a></s:if>
	<s:if test="noticePage.totalPages>=noticePage.pageNo+2"><a href="javascript:jumpPage(${noticePage.pageNo+2})">${noticePage.pageNo+2}</a></s:if>
	<s:if test="noticePage.totalPages>=noticePage.pageNo+3"><a href="javascript:jumpPage(${noticePage.pageNo+3})">${noticePage.pageNo+3}</a></s:if>
	<s:if test="noticePage.totalPages>=noticePage.pageNo+4"><a href="javascript:jumpPage(${noticePage.pageNo+4})">${noticePage.pageNo+4}</a></s:if>
	<s:if test="noticePage.totalPages>=noticePage.pageNo+5">...&nbsp;</s:if>	
	<s:if test="noticePage.hasNext">[<a href="javascript:jumpPage(${noticePage.nextPage})">后一页</a>/<a href="javascript:jumpPage(${noticePage.totalPages})">尾页</a>]</s:if>
	<s:elseif test="noticePage.hasNext-1">[后一页/<b>尾页</b>]</s:elseif>	  
</s:if>
</s:if>
</div>