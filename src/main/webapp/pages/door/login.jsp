<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XXX管理系统后台</title>
<link rel="stylesheet" href="<c:url value='/css/login.css'/>" />
<link rel="stylesheet" href="<c:url value='/uiframe/validationEngine/css/validationEngine.jquery.css'/>" />
<style type="text/css">
</style>
<script src="<c:url value='/uiframe/dwzui/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine.min.js'/>" type="text/javascript"></script>
</head>
<body>
<div style="width:100%;height:640px;position: absolute;top:50%;left:50%;margin-top:-320px;margin-left:-50%;">
	<div class="header"></div>
	<div class="center">
		<div class="login_right">
			<div style="width:100%;height:auto;margin-top:150px;">
			<form action="" method="post" id="pullyform" name="pullyform">
				<div class="login_title">
					管理员登录
				</div>
				<div class="login_info">
					<label>用户名：</label><input type="text" name="cardid" id="cardid" class="login_input validate[required]" value="${loginname }"/>
				</div>
				<div class="login_info">
					<label>密　码：</label><input type="password" name="pwd" id="pwd" class="login_input validate[required]" value="${password }"/>
				</div>
				<div class="login_info">
					<label>验证码：</label><input type="text" name="randCode" id="randCode" class="login_code validate[required]"/>&nbsp;&nbsp;
					<img alt="验证码" width="100" src="images/kaptcha.jpg" title="点击更换" id="img_captcha" onclick="javascript:refreshCaptcha();"><span>(看不清<a href="javascript:void(0)" onclick="javascript:refreshCaptcha()">换一张</a>)</span>
				</div>
				<div class="login_info">
					<input type="submit" name="loginBtn" value="登录" class="btn"/>
					<input type="reset" name="cancelBtn" value="取消" class="btn"/>
				</div>
			</form>
			</div>
		</div>
		<div class="login_left">
			<div style="width:100%;height:auto;margin-top:150px;">
				<div class="logo"></div>
			</div>
		</div>
	</div>
	<div class="bottom">
	Copyright &copy; 2011 xxx网络科技有限公司版权所有
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
	    document.getElementById("img_captcha").src="<%=basePath%>images/kaptcha.jpg?t=" + Math.random();  
	}
</script>
</body>
</html>