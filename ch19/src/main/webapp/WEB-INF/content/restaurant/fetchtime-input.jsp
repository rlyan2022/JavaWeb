<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<title>增加送餐时间</title>
	<%@ include file="/common/meta.jsp"%>
	<link href="${ctx}/css/default.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/validate/jquery.validate.css" type="text/css" rel="stylesheet" />
	<script src="${ctx}/js/jquery.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	
	<script type="text/javascript">

		$(document).ready(function(){ 
			$("#inputForm").validate({
				 rules: {
	    			a_hour: {
	    				required: true
	    			},
	    			a_minute: {
	    				required: true
	    			}     		
				}
			});
		});
	</script>
</head>
<body>
<fieldset style="width: 90%;margin-left: 50px;">
	<legend>增加送餐时间</legend>
<div id="message"><s:actionmessage theme="mytheme"/></div>
<div id="listContent">
	<form id="inputForm" name="inputForm" action="fetchtime!save.action" method="post" >
	<input type="hidden" id="id" name="id" value="${id}" /> 
	<table class="inputView" style="margin-left: 100px;" width="300">	
		<tr>
			<td>&nbsp;</td>
			<td>小时　 ：　分钟</td>
		</tr>
		<tr>
			<td align="right">送餐时间：</td>
			<td>
				<select name="a_hour">
					<option value="00" >00</option>
					<option value="01" >01</option>
					<option value="02" >02</option>
					<option value="03" >03</option>
					<option value="04" >04</option>
					<option value="05" >05</option>
					<option value="06" >06</option>
					<option value="07" >07</option>
					<option value="08" >08</option>
					<option value="09" >09</option>
					<option value="10" selected="selected">10</option>
					<option value="11" >11</option>
					<option value="12" >12</option>
					<option value="13" >13</option>
					<option value="14" >14</option>
					<option value="15" >15</option>
					<option value="16" >16</option>
					<option value="17" >17</option>
					<option value="18" >18</option>
					<option value="19" >19</option>
					<option value="20" >20</option>
					<option value="21" >21</option>
					<option value="22" >22</option>
					<option value="23" >23</option>
				</select>&nbsp;&nbsp;：			
				<select name="a_minute">
					<option value="00" >00</option>
					<option value="05" >05</option>
					<option value="10" >10</option>
					<option value="15" >15</option>
					<option value="20" >20</option>
					<option value="25" >25</option>
					<option value="30" >30</option>
					<option value="35" >35</option>
					<option value="40" >40</option>
					<option value="45" >45</option>
					<option value="50" >50</option>
					<option value="55" >55</option>					
				</select>　
			</td>
		</tr>		
		  
		<tr>
			<td colspan="2" align="center" height="50">
				<s:if test="id == null"><input type="submit" value=" 提 交 " /></s:if><s:else><input type="submit" value=" 修 改 " /></s:else>&nbsp;&nbsp;
				<input type="button" value=" 取 消 " onclick="history.back()" />	
			</td>
		</tr>
	</table>	
	</form>
</div>
</fieldset>
</body>
</html>