<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>我的文章</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="../css/main.css" media="all" />
<!--[if IE 6]><link type="text/css" rel="stylesheet" href="css/ie6.css" media="all" /><![endif]-->
<script type="text/javascript" src="../js/mootools.js"></script>
<script type="text/javascript" src="../js/site.js"></script>
</head>
<body>
<div id="wrapper">
  <div id="container">
    <div id="scene"> c<img src="../images/scene.jpg" alt="" />
       <h1>${empty sessionScope.blogtitle ? "博客网站系统":sessionScope.blogtitle} <br/>
      		<font size="8">${empty sessionScope.idiograph ? "我的签名":sessionScope.idiograph}</font>
      		</h1>
      <div id="scale_area">
        <div id="scale_knob">&raquo; Font Size &laquo;</div>
      </div>
      <div id="menu">
         <div class="holder"> <a href="../showAllArticle.action">博客首页</a> </div>
        <div class="holder"> <a href="showUserAllArticle.action">用户首页</a> </div>
        <div class="holder"> <a href="editbloginfo.jsp">个性化设置</a> </div>
        <div class="holder"> <a href="addArticle.jsp">写日志</a> </div>
        <div class="holder"> <a href="showPhoto.action">相册</a> </div>
      </div>
    </div>
    <div id="content">
      <div id="col_left">
        <div class="post">
          <div class="meta"><a class="title" href=""><s:property value="#request.article.title"/></a>
            <div class="clear"></div>
          </div>
          <div class="comments">
            <div class="comment">
              <div class="meta">
              <span><s:property value="#request.article.content" escape="false"/>
              </span>
                <div class="clear"> </div>
              </div>
            </div>
            </div>
          </div>
        <!-- 循环输出 -->
        <s:set name="loushu" value="#request.page.beginIndex"></s:set>
        <s:iterator value="#request.allCri" id="cri">
        <s:set name="loushu" value="#loushu + 1"></s:set>
          <div class="comments">
            <div class="comment">
              <div class="meta"><span><s:property value="#cri.content" escape="false"/></span>
                <div class="clear"><span class="datetime">作者:<s:property value="#cri.username"/></span></div>
              </div>
            </div>
          </div>
		 </s:iterator>
		  <div class="comment" align="center">
		  	当前第${page.currentPage}页，共${page.totalPage}页，每页显示${page.everyPage}条记录
			<s:if test="#request.page.hasPrePage">
				<a href="showArticle.action?id=${requestScope.article.id}&currentPage=1">首页</a>
				<a href="showArticle.action?id=${requestScope.article.id}&currentPage=${page.currentPage -1 }">上一页</a>
			</s:if>
			<s:else>
				首页
				上一页
			</s:else>

			<s:if test="#request.page.hasNextPage">
				<a href="showArticle.action?id=${requestScope.article.id}&currentPage=${page.currentPage + 1 }">下一页</a>
				<a href="showArticle.action?id=${requestScope.article.id}&currentPage=${page.totalPage }">尾页</a>
			</s:if>
			<s:else>
				下一页
				尾页
			</s:else>
		  </div>
		  <div class="comment">
		  	<h2>发表评论</h2>
            <form class="h" action="addCritique.action" method="post">
            <input type="hidden" name="id" value='${requestScope.article.id }'/>
              <div>
                <FCK:editor instanceName="content" basePath="/user/fckeditor" toolbarSet="myToolbar" height="400"></FCK:editor>
              </div>
              <div>
                <label></label>
                <div class="clear"> </div>
              </div>
              <div class="button_wrapper">
                <input name="提交" type="submit" class="button" value="提交" />
              </div>
            </form>
		  </div>
        </div>
      </div>
      <div id="col_right">
        <div id="search_box">
          <form action="" method="post">
            <div>
              <input type="text" name="search" />
            </div>
            <div class="button_wrapper">
              <input type="submit" value="Search" class="button" />
            </div>
            <div class="clear"> </div>
          </form>
        </div>
        <div id="sidebar">
          <h2>页面导航</h2>
          <ul>
            <li><a href="../showAllArticle.action">博客首页</a></li>
        	<li><a href="showUserAllArticle.action">用户首页</a></li>
        	<li><a href="editbloginfo.jsp">个性化设置</a></li>
        	<li><a href="addArticle.jsp">写日志</a></li>
       		<li><a href="showPhoto.action">相册</a></li>
          </ul>
        </div>
      </div>
      <div class="clear"> </div>
    </div>
    <div id="footer">
      <div class="clear"> </div>
      <hr />
      <p class="credit">博客网站系统</p>
    </div>
  </div>
</body>
</html>
