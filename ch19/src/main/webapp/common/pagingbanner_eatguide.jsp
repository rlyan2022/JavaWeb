<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<div style="WIDTH: 210px;  font-size:12px; color:#000000;">
<s:if test="eatguidePage.totalCount>0">
有${eatguidePage.totalCount}条记录, 显示${(eatguidePage.pageNo-1)*eatguidePage.pageSize+1}到<s:if test="eatguidePage.totalCount-eatguidePage.pageNo*eatguidePage.pageSize>eatguidePage.pageSize">${eatguidePage.pageNo*eatguidePage.pageSize}</s:if><s:else>${eatguidePage.totalCount}</s:else>条记录<br/>
第${eatguidePage.pageNo}页, 共${eatguidePage.totalPages}页 
每页显示
<select name="pageSizeSelect" onchange="changePageSize(this.options[this.options.selectedIndex].value)">
	<option value="10" <s:if test="eatguidePage.pageSize==10"> selected </s:if>>10</option>
	<option value="20" <s:if test="eatguidePage.pageSize==20"> selected </s:if>>20</option>
	<option value="30" <s:if test="eatguidePage.pageSize==30"> selected </s:if>>30</option>
</select>条<br>
<s:if test="eatguidePage.totalPages>1">	
	<br/>	
	<s:if test="eatguidePage.hasPre">[<a href="javascript:jumpPage(1)">首页</a>/<a href="javascript:jumpPage(${eatguidePage.prePage})">前一页</a>]</s:if>
	<s:elseif test="1">[<b>首页</b>/前一页]</s:elseif>		
	<s:if test="eatguidePage.pageNo-3>0">...&nbsp;</s:if>
	<s:if test="eatguidePage.pageNo-2>0"><a href="javascript:jumpPage(${eatguidePage.pageNo-2})">${eatguidePage.pageNo-2}</a></s:if>
	<s:if test="eatguidePage.pageNo-1>0"><a href="javascript:jumpPage(${eatguidePage.pageNo-1})">${eatguidePage.pageNo-1}</a></s:if>
	<s:if test="eatguidePage.pageNo>0"><b>${eatguidePage.pageNo}</b></s:if>
	<s:if test="eatguidePage.totalPages>=eatguidePage.pageNo+1"><a href="javascript:jumpPage(${eatguidePage.pageNo+1})">${eatguidePage.pageNo+1}</a></s:if>
	<s:if test="eatguidePage.totalPages>=eatguidePage.pageNo+2"><a href="javascript:jumpPage(${eatguidePage.pageNo+2})">${eatguidePage.pageNo+2}</a></s:if>
	<s:if test="eatguidePage.totalPages>=eatguidePage.pageNo+3"><a href="javascript:jumpPage(${eatguidePage.pageNo+3})">${eatguidePage.pageNo+3}</a></s:if>
	<s:if test="eatguidePage.totalPages>=eatguidePage.pageNo+4"><a href="javascript:jumpPage(${eatguidePage.pageNo+4})">${eatguidePage.pageNo+4}</a></s:if>
	<s:if test="eatguidePage.totalPages>=eatguidePage.pageNo+5">...&nbsp;</s:if>	
	<s:if test="eatguidePage.hasNext">[<a href="javascript:jumpPage(${eatguidePage.nextPage})">后一页</a>/<a href="javascript:jumpPage(${eatguidePage.totalPages})">尾页</a>]</s:if>
	<s:elseif test="eatguidePage.hasNext-1">[后一页/<b>尾页</b>]</s:elseif>	  
</s:if>
</s:if>
</div>