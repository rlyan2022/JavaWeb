<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="dbc" class="sanqing.database.DBConnect"/>
<jsp:useBean id="abc" class="com.sanqing.abc"/>

<%
	String sql2 = "select id,username,sex,email,qq,url,title,content,time,images from guest;";
	ResultSet rb=dbc.executeQuery(sql2);
	while(rb.next()){
		out.print(rb.getInt("id"));
	abc.insql2(rb.getInt("id"),rb.getString("username"),rb.getString("email"),rb.getString("qq"),rb.getString("url"),rb.getString("title"),rb.getString("content"),rb.getString("images"),rb.getString("time"));
	}
%>