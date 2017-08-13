<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base/taglib.jsp"%>
<div class="pageContent">
	<form method="post" id="changepwd_form" action="manager_updateUser.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">

			<div class="unit">
				<label>旧密码：</label>
				<input type="password" name="pwd" size="30" class="validate[required,custom[onlyLetterNumber],maxSize[30]]" />
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input type="password" id="user_newPassword" name="newpwd" size="30" class="validate[required,custom[onlyLetterNumber],maxSize[30]]"/>
			</div>
			<div class="unit">
				<label>重复输入新密码：</label>
				<input type="password" name="newpwd2" size="30" class="validate[required,equals[user_newPassword],custom[onlyLetterNumber],maxSize[30]]"/>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="changepwdsub()">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
<script language="JavaScript" type="text/JavaScript">
$(function () {
	$("#changepwd_form").validationEngine("attach",{
    	promptPosition:"centerRight",
    	autoHidePrompt:true,
    	autoHideDelay:2000,
    	addPromptClass:"formError-text"
    });
});
function changepwdsub(){
	if($("#changepwd_form").validationEngine("validate")){//验证通过
		$("#changepwd_form").submit();
	}
}
</script>