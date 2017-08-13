<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<div class="pageContent">
	<form id="user_update_form" method="post" action="manager_user_update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="uid" value="${sysAdminList.uid}">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>账号：</label>
				<input name="cardid" readonly="readonly" type="text" size="30" placeholder="输入账号" value="${sysAdminList.cardid }"/>
			</p>
			<p>
				<label>密码：</label>
				<input name="pwd" id="cp_newPwd" class="validate[required,custom[onlyLetterNumber],maxSize[30]]" type="password" size="30" placeholder="输入密码"/>
			</p>
			<p>
				<label>确认密码：</label>
				<input name="pwd2" class="validate[required,equals[cp_newPwd],custom[onlyLetterNumber],maxSize[30]]" type="password" size="30" placeholder="再次输入密码"/>
			</p>
			<p>
				<label>姓名：</label>
				<input type="text" name="userName" size="30" class="validate[required,maxSize[20]]" placeholder="输入姓名" value="${sysAdminList.userName }"/>
			</p>
			<p>
				<label>电话：</label>
				<input name="tel" class="validate[custom[mobileNo]]" size="30" type="text" placeholder="输入电话" value="${sysAdminList.tel }"/>
			</p>
			<p>
				<label>证件号：</label>
				<input type="text" name="cardno" class="validate[custom[chinaIdLoose]]" size="30" placeholder="输入证件号" value="${sysAdminList.cardno }"/>
			</p>
			<p>
				<label>性别：</label>
				<select name="sex" class="combox" id="sex_id">
					<option value="0">未知</option>
					<option value="1">男</option>
					<option value="2">女</option>
				</select>
			</p>
			<p>
				<label>生日：</label>
				<input type="text" name="bday" class="date" size="30" value="<fmt:formatDate value="${sysAdminList.birthday }" pattern="yyyy-MM-dd"/>"/><a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>是否启用：</label>
				<select name="isEnabled" class="combox" id="isEnabled_id">
					<option value="1">启用</option>
					<option value="0">不启用</option>
				</select>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="userupdatesub()">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script language="JavaScript" type="text/JavaScript">
$(function () {
	$("#user_update_form").validationEngine("attach",{
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
function userupdatesub(){
	if($("#user_update_form").validationEngine("validate")){//验证通过
		$("#user_update_form").submit();
	}
}
</script>