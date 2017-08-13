<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<link rel="stylesheet" href="<c:url value='/uiframe/zTree_v3/css/zTreeStyle/zTreeStyle.css'/>" />
<!--[if IE]>
<link href="../../js/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
<style>
</style>
<script src="<c:url value='/uiframe/dwzui/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/zTree_v3/js/jquery.ztree.core-3.5.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/zTree_v3/js/jquery.ztree.excheck-3.5.js'/>" type="text/javascript"></script>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="tree" class="ztree"></ul>
	</div>
</div>
<script language="JavaScript" type="text/JavaScript">
$(function(){
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};
	var zn = '${jsonArray}';
	var zTreeNodes = eval(zn);
	$.fn.zTree.init($("#tree"), setting, zTreeNodes);
});
</script>