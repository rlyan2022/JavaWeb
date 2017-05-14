<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="dbc" class="sanqing.database.DBConnect"/>
<jsp:useBean id="abc" class="com.sanqing.abc"/>

<%
		//if (sdbo==null)
		//sdbo = SQLDBOperator.getInstance("Connection");
	//DBConnect dbc=new DBConnect();
	String sql = "select myorothers,classid,id,title,content,connection,author,editor,articlefrom,hits,top,state,tag,time from article;";
	ResultSet rs=dbc.executeQuery(sql);
	while(rs.next()){
		out.print(rs.getString("title"));
		abc.insSql(rs.getInt("myorothers"),rs.getInt("classid"),rs.getInt("id"),rs.getString("title"),rs.getString("content"),rs.getString("connection"),rs.getString("author"),rs.getString("editor"),rs.getString("articlefrom"),rs.getInt("hits"),rs.getInt("top"),rs.getInt("state"),rs.getInt("tag"),rs.getString("time"));
	}

	String sql1 = "select user,passwd,sex,question,answer,email,qq,http,loadtime from usr";
	ResultSet rt=dbc.executeQuery(sql1);
	while(rt.next()){
		abc.insSql1(rt.getString("user"),rt.getString("passwd"),rt.getInt("sex"),rt.getString("question"),rt.getString("answer"),rt.getString("email"),rt.getString("qq"),rt.getString("http"),rt.getString("loadtime"));
	}



%>