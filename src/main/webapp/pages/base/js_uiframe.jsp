<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="<c:url value='/uiframe/dwzui/themes/default/style.css'/>" rel="stylesheet" type="text/css" />
<link href="<c:url value='/uiframe/dwzui/themes/css/core.css'/>" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<c:url value='/uiframe/validationEngine/css/validationEngine.jquery.css'/>" />
<link rel="stylesheet" href="<c:url value='/uiframe/zTree_v3/css/zTreeStyle/zTreeStyle.css'/>" />
<!--[if IE]>
<link href="../../js/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" />
<![endif]-->
<style>
</style>
<script src="<c:url value='/uiframe/dwzui/js/jquery-1.7.2.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/speedup.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/jquery.cookie.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/jquery.validate.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/jquery.bgiframe.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/bin/dwz.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/dwzui/js/dwz.regional.zh.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine-zh_CN.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/validationEngine/jquery.validationEngine.min.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/zTree_v3/js/jquery.ztree.core-3.5.js'/>" type="text/javascript"></script>
<script src="<c:url value='/uiframe/zTree_v3/js/jquery.ztree.excheck-3.5.js'/>" type="text/javascript"></script>
<!-- 图片js -->
<script type="text/javascript">
$(function(){
	DWZ.init("<c:url value='/uiframe/dwzui/dwz.frag.xml'/>", {
		loginUrl:"index.jsp",	// 跳到登录页面
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<c:url value='/uiframe/dwzui/themes'/>"});
		}
	});
});
</script>