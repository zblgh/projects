<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<div class="pageContent">
	<form method="post" id="user_role_form" action="manager_user_roleupdate.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="cardid" value="${cardid}">
		<input type="hidden" name="roleIds" id="roleIds">
		<div class="pageFormContent" layoutH="56">
			<div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul id="tree" class="ztree"></ul>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="userroleSub()">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
<script language="JavaScript" type="text/JavaScript">
$(function(){
	var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				},
				key: {
					title: "des"
				}
			}
		};
	var zn = '${jsonArray}';
	var zTreeNodes = eval(zn);
	$.fn.zTree.init($("#tree"), setting, zTreeNodes);
});
function userroleSub(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = "";
	for(var i=0; i<nodes.length; i++){
		tmpNode = nodes[i];
		if(i!=nodes.length-1){
			ids += tmpNode.id+",";
		}else{
			ids += tmpNode.id;
		}
	}
	$("#roleIds").val(ids);
	$("#user_role_form").submit();
	$.pdialog.closeCurrent();
}
</script>