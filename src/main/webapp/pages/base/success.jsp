<!-- 页面位置：pages/base/success.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>执行成功页面</title>
<script src="<c:url value='/js/jquery-1.7.1.min.js'/>" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		alert("操作完成!");
		window.location.href = '${url}';
	});
</script>
</head>
<body>
</body>
</html>