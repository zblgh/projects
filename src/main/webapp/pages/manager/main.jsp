<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../base/taglib.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link href="<c:url value='/uiframe/dwzui/themes/default/style.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/uiframe/dwzui/themes/css/core.css'/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value='/uiframe/validationEngine/css/validationEngine.jquery.css'/>" />
<link rel="stylesheet" href="<c:url value='/uiframe/zTree_v3/css/zTreeStyle/zTreeStyle.css'/>" />
<!--[if IE]>
<link href="../../js/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
<style>
</style>
<script src="<c:url value='/uiframe/dwzui/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/speedup.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/jquery.cookie.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/jquery.validate.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/jquery.bgiframe.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/bin/dwz.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/dwz.regional.zh.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/zTree_v3/js/jquery.ztree.core-3.5.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/zTree_v3/js/jquery.ztree.excheck-3.5.js'/>" type="text/javascript"></script>
<!-- 图片js -->
<script type="text/javascript">
$(function(){
	DWZ.init("<c:url value='/uiframe/dwzui/dwz.frag.xml'/>", {
		loginUrl:"index.jsp",	// 跳到登录页面
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<c:url value='/uiframe/dwzui/themes'/>"});
		}
	});
	getTime();
	window.setInterval(getTime, 1000);
});
function getTime(){
	var date = new Date();
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	var h = date.getHours();
	var i = date.getMinutes();
	var s = date.getSeconds();
	$("#sysTime").html(y+"年"+(m>9?m:("0"+m))+"月"+(d>9?d:("0"+d))+"日 "+(h>9?h:("0"+h))+":"+(i>9?i:("0"+i))+":"+(s>9?s:("0"+s)));
}
function onLoadMenu(){//ajax加载菜单
	var changeUrl = "menu_load.html";
	$.post(changeUrl,function(str){
		if(str!="" && str!=null){
			$("#menu_node").empty();
			$("#menu_node").html(str);
		}
	});
}
</script>
</head>
<body scroll="no">
		<div id="layout">
		<div id="header">
			<div class="head">
		    	<div class="headerNav">
					<a class="logo" href="javascript:;">系统logo</a>
					<ul class="nav">
						<li><span id="sysTime" style="color:#FFF;"></span>
						</li>
						<li><a href="javascript:;">
							<c:choose>
						  		<c:when test="${user!=null }">
						  			Hi!&nbsp;${user.userName }
						  		</c:when>
						  		<c:otherwise>
						  			Hi!
						  		</c:otherwise>
						  	</c:choose>
						  	</a>
						</li>
						<li><a href="manager_resp_changepwd.html" target="dialog" rel="user_update_pwd" mask="true" width="600">设置</a></li>
						<li><a href="logout.html">退出</a></li>
					</ul>
					<ul class="themeList" id="themeList">
						<li theme="default"><div class="selected">蓝色</div></li>
						<li theme="green"><div>绿色</div></li>
						<li theme="purple"><div>紫色</div></li>
						<li theme="silver"><div>银色</div></li>
						<li theme="azure"><div>天蓝</div></li>
					</ul>
				</div>
		    </div>
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
				<div class="accordion" fillSpace="sideBar" id="menu_node"><!--这里加载菜单  -->
				${strNodes }
				</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
					<!-- 主页代码  start-->
						<div class="accountInfo">
							<div class="alertInfo">
								<p><a href="https://code.csdn.net/dwzteam/dwz_jui/tree/master/doc" target="_blank" style="line-height:19px"><span>DWZ框架使用手册</span></a></p>
								<p><a href="http://pan.baidu.com/s/18Bb8Z" target="_blank" style="line-height:19px">DWZ框架开发视频教材</a></p>
							</div>
							<div class="right">
								<p style="color:red">DWZ官方微博 <a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a></p>
							</div>
							<p><span>DWZ富客户端框架</span></p>
							<p>DWZ官方微博:<a href="http://weibo.com/dwzui" target="_blank">http://weibo.com/dwzui</a></p>
						</div>
						<!-- 主页代码  end-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">中科远景网络有限公司版权所有&nbsp;V1.0</div>
</body>
</html>
