<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=ConstantUtils.restaurantName %></title>
	<%@ include file="/common/meta.jsp"%>	
</head>  

<frameset rows="100,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${ctx}/frame!toppage.action" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="213,*" frameborder="no" border="0" framespacing="0">
    <frame src="${ctx}/frame!leftpage.action" name="leftFrame" style="overflow-x:hidden;overflow-y:auto" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="${ctx}/orderform/orderformnote.action" name="mainFrame" id="mainFrame" style="overflow-x:hidden;overflow-y:auto" noresize="noresize" />
  </frameset>
</frameset>
<noframes><body>
</body>
</noframes></html>
