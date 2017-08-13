<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.famework.myframedwz.utils.BaseCodeParam" %>
<%@ include file="../../base/taglib.jsp"%>
<%@ include file="../../base/js_uiframe.jsp"%>
<div class="pageContent">
	<form method="post" id="pullyform" action="manager_menu_update.html">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dd>
					<label>父菜单代码：</label>
					<input style="float:left;" readonly="readonly" name="parendid" id="parendid" type="text" size="30" placeholder="输入菜单代码" class="validate[required,maxSize[30]]" value="${sysPermission.parendid }"/>
				</dd>
			</dl>
			<dl>
				<dd>
					<label>菜单代码：</label>
					<input style="float:left;" readonly="readonly" name="pid" id="pid" type="text" size="30" placeholder="输入菜单代码" class="validate[required,maxSize[30]]" value="${sysPermission.pid }"/>
				</dd>
			</dl>
			<dl>
				<dd>
					<label>菜单名称：</label>
					<input style="float:left;" name="permissionName" id="permissionName" type="text" size="30" placeholder="输入菜单名称" class="validate[required,maxSize[30]]" value="${sysPermission.permissionName }"/>
				</dd>
			</dl>
			<dl>
				<dd>
					<label>菜单顺序：</label>
					<input style="float:left;" name="sortindex" id="sortindex" type="text" size="30" placeholder="输入菜单顺序" class="validate[required,custom[integer]]" value="${sysPermission.sortindex }"/><label style="color:green;width: 200px;text-align:left;">注：数字越小越靠前</label>
				</dd>
			</dl>
			<dl>
				<dd>
					<label>菜单链接地址：</label>
					<input style="float:left;" name="pageUrl" id="pageUrl" type="text" size="30" placeholder="输入连接地址，如果该菜单没有连接可不填" value="${sysPermission.pageUrl }"/><span style="color:green;">注：如果该菜单没有地址可不填</span>
				</dd>
			</dl>
		</div>
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="manager_resp_menuadd.html?pid=${sysPermission.pid }"><span>添加子菜单</span></a></li>
				<li><a class="edit" href="javascript:;" onclick="fullsub()"><span>确认修改</span></a></li>
				<c:if test="${isChildNodes }" var="fState">
					<li><a title="请确保角色和用户没有使用这些权限，确实要删除该菜单及子菜单吗?" href="javascript:;" onclick="menuBatchDel()" class="delete"><span>批量删除</span></a></li>
				</c:if>
				<li><a title="请确保角色和用户没有使用这些权限，确实要删除该菜单吗?" href="javascript:;" onclick="menuDel()" class="delete"><span>删除</span></a></li>
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
});
function fullsub(){
	if($("#pullyform").validationEngine("validate")){//验证通过
		$("#pullyform").submit();
	}
}
function menuBatchDel(){
	alertMsg.confirm("请确保角色和用户没有使用这些权限，确实要删除该菜单及子菜单吗?", {
		okCall: function(){
			var changeUrl = "manager_menu_batchdel.html?pid=${sysPermission.pid }";
			$.get(changeUrl,function(str){
		    	if(str == '1'){
		    		alertMsg.info('请确保角色没有使用这些权限！');
		    	}else if(str=="2"){
		    		alertMsg.info('请确保用户没有使用这些权限！');
		    	}else if(str=="3"){
		    		alertMsg.error('系统出错，请稍后重试！');
		    	}else{
		    		alertMsg.info('操作成功！');
		    		window.location.href = "manager_add_rootmenu.html";
		    	}
			});
		}
	});
}
function menuDel(){
	alertMsg.confirm("请确保角色和用户没有使用这些权限，确实要删除该菜单吗?", {
		okCall: function(){
			var changeUrl = "manager_menu_del.html?pid=${sysPermission.pid }";
			$.get(changeUrl,function(str){
		    	if(str == '1'){
		    		alertMsg.info('请确保角色没有使用这些权限！');
		    	}else if(str=="2"){
		    		alertMsg.info('请确保用户没有使用这些权限！');
		    	}else if(str=="3"){
		    		alertMsg.info('请先删除该菜单下的全部子菜单！');
		    	}else if(str=="4"){
		    		alertMsg.error('系统出错，请稍后重试！');
		    	}else{
		    		alertMsg.info('操作成功！');
		    		window.location.href = "manager_add_rootmenu.html";
		    	}
			});
		}
	});
}
</script>