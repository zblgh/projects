<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<div class="pageContent">
	<form id="role_update_form" method="post" action="manager_role_update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="rid" value="${sysRole.rid}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>角色名称：</label>
				<input name="roleName" type="text" size="30" placeholder="输入角色名称" class="validate[required,maxSize[30]]" value="${sysRole.roleName }"/>
			</p>
			<p>
				<label>角色描述：</label>
				<input type="text" name="roleDescribe" size="30" class="validate[maxSize[50]]" placeholder="输入角色描述" value="${sysRole.roleDescribe }"/>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="roleupdatesub()">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script language="JavaScript" type="text/JavaScript">
$(function () {
	$("#role_update_form").validationEngine("attach",{
    	promptPosition:"centerRight",
    	autoHidePrompt:true,
    	autoHideDelay:2000,
    	addPromptClass:"formError-text"
    });
	
	var sexCode='${sysAdminList.sex }';
	$("#sex_id").find("option[value='"+sexCode+"']").attr("selected",true);
	
	var isECode='${sysAdminList.isEnabled }';
	$("#isEnabled_id").find("option[value='"+isECode+"']").attr("selected",true);
	
});
function roleupdatesub(){
	if($("#role_update_form").validationEngine("validate")){//验证通过
		$("#role_update_form").submit();
	}
}
</script>