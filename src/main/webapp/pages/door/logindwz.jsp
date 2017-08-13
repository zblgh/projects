<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>xxx管理系统</title>
<link href="<c:url value='/uiframe/dwzui/themes/css/login.css'/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value='/uiframe/validationEngine/css/validationEngine.jquery.css'/>" />
<style type="text/css">
</style>
<script src="<c:url value='/uiframe/dwzui/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine.min.js'/>" type="text/javascript"></script>
</head>
<body>
<div id="login">
	<div id="login_header">
		<h1 class="login_logo">
			<a href="javascript:;"><img src="uiframe/dwzui/themes/default/images/login_logo.gif" /></a>
		</h1>
		<div class="login_headerContent">
			<div class="navList">
				<ul>
					<li><a href="#">设为首页</a></li>
					<li><a href="#">反馈</a></li>
					<li><a href="#" target="_blank">帮助</a></li>
				</ul>
			</div>
			<h2 class="login_title"><img src="uiframe/dwzui/themes/default/images/login_title.png" /></h2>
		</div>
	</div>
	<div id="login_content">
		<div class="loginForm">
			<div style="padding-left:80px;"><font color="red">${message}</font></div>
			<form action="login.html" method="post" id="pullyform" name="pullyform">
				<p>
					<label>用户名：</label>
					<input type="text" name="cardid" size="20" class="login_input validate[required]"  placeholder="用户名"/>
				</p>
				<p>
					<label>密码：</label>
					<input type="password" name="pwd" size="20" class="login_input validate[required]" placeholder="密码"/>
				</p>
				<p>
					<label>验证码：</label>
					<input class="code validate[required]" name="randCode" type="text" size="5" placeholder="验证码" autocomplete="off"/>
					<span><img alt="验证码" width="75" height="24" src="images/kaptcha.jpg" title="点击更换" id="img_captcha" onclick="javascript:refreshCaptcha();"></span>
				</p>
				<div class="login_bar">
					<input class="sub" type="submit" value=" " />
				</div>
			</form>
		</div>
		<div class="login_banner"><img src="uiframe/dwzui/themes/default/images/login_banner.jpg" /></div>
		<div class="login_main">
			<ul class="helpList">
				<li><a href="#">下载驱动程序</a></li>
				<li><a href="#">如何安装密钥驱动程序？</a></li>
				<li><a href="#">忘记密码怎么办？</a></li>
				<li><a href="#">为什么登录失败？</a></li>
			</ul>
			<div class="login_inner">
				<p>您可以使用 网易网盘 ，随时存，随地取</p>
				<p>您还可以使用 闪电邮 在桌面随时提醒邮件到达，快速收发邮件。</p>
				<p>在 百宝箱 里您可以查星座，订机票，看小说，学做菜…</p>
			</div>
		</div>
	</div>
	<div id="login_footer">
		Copyright &copy; 2014 www.jojin.com Inc. All Rights Reserved.
	</div>
</div>
<script type="text/javascript">
	$(function(){
		$("#pullyform").validationEngine("attach",{
	    	promptPosition:"centerRight",
	    	autoHidePrompt:true,
	    	autoHideDelay:2000,
	    	addPromptClass:"formError-text"
	    });	
	});
	function refreshCaptcha(){  
	    document.getElementById("img_captcha").src="images/kaptcha.jpg?t=" + Math.random();  
	}
	if (top.location != self.location) {top.location=self.location;}
</script>
</body>
</html>