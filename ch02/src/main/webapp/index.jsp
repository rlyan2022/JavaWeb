<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>体育赛事投票系统</title>
    <LINK HREF="style.css" TYPE="text/css" REL="stylesheet">
	<style type="text/css">
	<!--
	.STYLE14 {color: #FF0000}
	.STYLE15 {font-family: tahoma; font-size: 11px;}
	.STYLE17 {color: #003300}
	-->
	</style>
  </head>

  <body>
	 <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
	    <td>&nbsp;</td>
	    <td width="642" align="left" valign="top"><table width="642"  border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td height="37" align="left" valign="top" background="images/menu.jpg">
	        	<!--导航栏-->
		        <div style="padding-left:42px; padding-top:10px">
		        	<a href="showVoteByChannel.action?channelID=1" class="style2" style="text-decoration:none ">NBA</a>
		        	<img src="images/spacer.gif" width="74" height="1">
		        	<a href="showVoteByChannel.action?channelID=2" class="style2" style="text-decoration:none ">CBA</a>
		        	<img src="images/spacer.gif" width="55" height="1">
		        	<a href="showVoteByChannel.action?channelID=3" class="style2" style="text-decoration:none ">足球世界杯</a>
		        	<img src="images/spacer.gif" width="33" height="1">
		        	<a href="showVoteByChannel.action?channelID=4" class="style2" style="text-decoration:none ">中超</a>
		        	<img src="images/spacer.gif" width="68" height="1">
		        	<a href="showVoteByChannel.action?channelID=5" class="style2" style="text-decoration:none ">英超</a>
		        	<img src="images/spacer.gif" width="64" height="1">
		        	<a href="showVoteByChannel.action?channelID=6" class="style2" style="text-decoration:none ">F1</a>
		        </div>
	        </td>
	      </tr>
	      <tr>
	        <td align="left" valign="top"><table width="642"  border="0" cellspacing="0" cellpadding="0">
	          <tr align="left" valign="top">
	            <td width="427" height="100%" align="right"><table width="426" height="100%"  border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                <td align="left" valign="top"><table width="426"  border="0" cellpadding="0" cellspacing="0" background="images/line_1.jpg">
	                  <tr>
	                    <td height="22" align="left" valign="middle" background="images/kattle_1.jpg" class="style2">&nbsp;&nbsp;最新投票</td>
	                  </tr>
	                  <tr>
	                    <td height="518" align="left" valign="top"><div style="padding-left:18px; padding-top:16px; padding-bottom:20px">
	                     <s:actionerror/>
	                     <s:iterator value="#request.voteResultList" var="voteResult">
	                     	<p class="style2">${voteResult.vote.voteName}</p>
	                     	 <form action="doVote.action" method="post">
	                     	 <p class="style2">
	                     	<s:iterator value="#voteResult.voteOptions" var="voteOption">
	                     		<input type="radio" name="voteOptionID" value="${voteOption.voteOptionID}">${voteOption.voteOptionName}<br>
	                     	</s:iterator>
	                     		<input type="radio" name="voteOptionID" value="0">其它<br>
	                     		<input type="text" name="otherOption">
	                     		<input type="hidden" name="voteID" value="${voteResult.vote.voteID}">
	                     		<input type="hidden" name="channelID" value="${voteResult.vote.channelID}">
	                     		<a href="voteResult?voteID=${voteResult.vote.voteID}">查看投票结果</a>
	                     	</p>
	                     	<p>
							<input type="submit" value="投票">
							<input type="reset" value="重置">
							</p>
							</form>
	                     </s:iterator>
	                     <s:if test="#request.voteResultList.size == 0">
	                     	没有任何投票内容!
	                     </s:if>
						</div>
						<div align="center" style="font-size: 12px">
							<s:if test="#request.page.hasPrePage">
								<a href="showVoteByChannel?channelID=${channelID}&&currentPage=1">首页</a> |
								<a href="showVoteByChannel?channelID=${channelID}&&currentPage=${page.currentPage -1 }">上一页</a>
							</s:if>
							<s:else>
								首页 | 上一页
							</s:else>
							<s:if test="#request.page.hasNextPage">
								<a href="showVoteByChannel?channelID=${channelID}&&currentPage=${page.currentPage + 1 }">下一页</a> |
								<a href="showVoteByChannel?channelID=${channelID}&&currentPage=${page.totalPage }">尾页</a>
							</s:if>
							<s:else>
								下一页 | 尾页
							</s:else>
						</div>
						 </td>
	                  </tr>
	                  <tr>
	                    <td align="left" valign="top"><img src="images/kettle_2.jpg" width="426" height="2"></td>
	                  </tr>
	                </table></td>
	              </tr>
	            </table></td>
	            <td width="215" height="100%" align="right"><table width="203" height="100%"  border="0" cellpadding="0" cellspacing="0">
	              <tr>
	                <td align="left" valign="top"><table width="203" height="613"  border="0" cellpadding="0" cellspacing="0" background="images/line_3.jpg">
	                  <tr>
	                    <td height="22" align="left" valign="middle" background="images/kettle_5.jpg" class="style9 STYLE14">&nbsp;<span class="STYLE15">&nbsp;<span class="STYLE17">热门投票</span></span></td>
	                  </tr>
	                  <tr>
	                    <td align="left" valign="top"><div style="padding-left:19px; padding-top:14px; padding-bottom:10px"></div>
	                      <ol>
	                        <li class="style7">你最喜欢的NBA球员</li>
	                        <li class="style7">你最喜欢的NBA中锋</li>
	                      </ol></td>
	                  </tr>
	                  <tr>
	                    <td height="2" align="left" valign="top"><img src="images/bot_1.jpg" width="203" height="2"></td>
	                  </tr>
	                </table></td>
	              </tr>
	              <tr>
	                <td height="11" align="left" valign="top"><img src="images/spacer.gif" width="1" height="11"></td>
	              </tr>
	            </table></td>
	          </tr>
	        </table></td>
	      </tr>
	      <tr>
	        <td height="12" align="left" valign="top"><img src="images/spacer.gif" width="1" height="12"></td>
	      </tr>
	      <tr>
	        <td align="left" valign="top" background="images/rep_1.jpg" style="background-repeat:repeat-x; background-position:top left "><div align="center" style="padding-top:14px"></div>
	          <div style="padding-left:5px; padding-top:3px">
	            <div align="center" class="style8">体育赛事投票系统 &copy;2009-2010 </div>
	          </div></td>
	      </tr>
	    </table></td>
	    <td>&nbsp;</td>
	  </tr>
	  <tr>
	    <td>&nbsp;</td>
	    <td width="642" align="left" valign="top">&nbsp;</td>
	    <td>&nbsp;</td>
	  </tr>
	</table>
  </body>
</html>

