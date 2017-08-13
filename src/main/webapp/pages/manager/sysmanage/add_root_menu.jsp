<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.famework.myframedwz.utils.BaseCodeParam" %>
<%@ include file="../../base/taglib.jsp"%>
<%@ include file="../../base/js_uiframe.jsp"%>
<div class="pageContent">
	<form method="post" id="pullyform" action="manager_save_rootmenu.html">
		<input type="hidden" name="parendid" value="<%=BaseCodeParam.rootPid%>">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dd>
					<label>菜单代码：</label>
					<input style="float:left;" name="pid" id="pid" type="text" size="30" placeholder="输入菜单代码" class="validate[required,maxSize[30]]"/>
				</dd>
			</dl>
			<dl>
				<dd>
					<label>菜单名称：</label>
					<input style="float:left;" name="permissionName" id="permissionName" type="text" size="30" placeholder="输入菜单名称" class="validate[required,maxSize[30]]"/>
				</dd>
			</dl>
			<dl>
				<dd>
					<label>菜单顺序：</label>
					<input style="float:left;" name="sortindex" id="sortindex" type="text" size="30" placeholder="输入菜单顺序" class="validate[required,custom[integer]]"/><label style="color:green;width: 200px;text-align:left;">注：数字越小越靠前</label>
				</dd>
			</dl>
			<dl>
				<dd>
					<label>菜单链接地址：</label>
					<input style="float:left;" name="pageUrl" id="pageUrl" type="text" size="30" placeholder="输入连接地址，如果该菜单没有连接可不填"/><span style="color:green;">注：如果该菜单没有地址可不填</span>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="fullsub()">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset" class="close">重置</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
<script language="JavaScript" type="text/JavaScript">
$(function () {
	$("#pullyform").validationEngine("attach",{
    	promptPosition:"centerRight",
    	autoHidePrompt:true,
    	autoHideDelay:2000,
    	addPromptClass:"formError-text"
    });
	//验证菜单是否存在
    $("#pid").blur(function(){
    	if(!$("#pid").validationEngine("validate")){//验证通过
    		var pid = $(this).val();
        	var changeUrl = "manager_menu_chk.html?pid="+pid; 
        	$.get(changeUrl,function(str){
    	    	if(str == '1'){
    	    		alertMsg.info('您输入的菜单已存在，请重新输入！');
    	    		$("#pid").val("");
    	    	}else if(str=="2"){
    	    		alertMsg.error('系统出错，请稍后重试！');
    	    		$("#pid").val("");
    	    	}
        	});
    	}
    });
});
function fullsub(){
	if($("#pullyform").validationEngine("validate")){//验证通过
		$("#pullyform").submit();
	}
}
</script>