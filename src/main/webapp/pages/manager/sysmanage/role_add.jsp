<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<div class="pageContent">
	<form id="role_add_form" method="post" action="manager_role_add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名称：</label>
				<input name="roleName" type="text" size="30" placeholder="输入角色名称" class="validate[required,maxSize[30]]"/>
			</p>
			<p>
				<label>角色描述：</label>
				<input type="text" name="roleDescribe" size="30" class="validate[maxSize[50]]" placeholder="输入角色描述"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="roleaddsub()">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script language="JavaScript" type="text/JavaScript">
$(function () {
	$("#role_add_form").validationEngine("attach",{
    	promptPosition:"centerRight",
    	autoHidePrompt:true,
    	autoHideDelay:2000,
    	addPromptClass:"formError-text"
    });
});
function roleaddsub(){
	if($("#role_add_form").validationEngine("validate")){//验证通过
		$("#role_add_form").submit();
	}
}
</script>