<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<table width="100%" height="100%" align="center" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="30%" id="left" style="vertical-align: top;">
			<iframe onLoad="iFrameLeftHeight()" style="background-color: #FFFFFF;" id="ifrmLeft" name="ifrmLeft" height="100%" width="100%" scrolling="auto" frameborder="0" src="manager_menu_left.html"></iframe>
		</td>
		<td id="right" style="vertical-align: top;">
			<iframe onLoad="iFrameRightHeight()" id="ifrmRight" name="ifrmRight" height="2048" width="100%" scrolling="no" frameborder="0" src="manager_add_rootmenu.html"></iframe>
		</td>
	</tr>
</table>
<script type="text/javascript" language="javascript">
function iFrameLeftHeight() {
	var ifm= document.getElementById("ifrmLeft");
	var subWeb = document.frames ? document.frames["ifrmLeft"].document : ifm.contentDocument;
	if(ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
	}
}
function iFrameRightHeight() {
	var ifm= document.getElementById("ifrmRight");
	var subWeb = document.frames ? document.frames["ifrmRight"].document : ifm.contentDocument;
	if(ifm != null && subWeb != null) {
		ifm.height = subWeb.body.scrollHeight;
	}
}
</script> 
