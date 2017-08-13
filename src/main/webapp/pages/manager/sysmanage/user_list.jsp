<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<%@ include file="../../base/pagerForm.jsp" %>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="manager_user_list.html" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					账号：<input type="text" name="cardid" value="${sysAdminList.cardid }"/><span style="color:green;">注：支持模糊查询</span>
				</td>
				<td>
					姓名：<input type="text" name="userName" value="${sysAdminList.userName }"/><span style="color:green;">注：支持模糊查询</span>
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manager_resp_useradd.html" target="dialog" mask="true" height="400" rel="user_add"><span>添加用户</span></a></li>
			<li><a title="删除用户将会一并删除用户下的所有角色和权限，确定删除吗?" target="selectedTodo" rel="ids" href="manager_user_batchdel.html" class="delete"><span>批量删除</span></a></li>
			<li><a class="edit" href="manager_query_userforid.html?cardid={uid_user}" target="dialog" mask="true" height="400" rel="user_update" warn="请选择一个用户"><span>修改用户</span></a></li>
			<li><a class="edit" href="manager_user_role.html?cardid={uid_user}" target="dialog" rel="user_role" mask="true" width="300" warn="请选择一个用户"><span>角色</span></a></li>
			<li><a class="edit" href="manager_user_menu.html?cardid={uid_user}" target="dialog" rel="user_per" mask="true" width="300" warn="请选择一个用户"><span>权限</span></a></li>
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100" align="center">账号</th>
				<th width="100" align="center">姓名</th>
				<th width="80" align="center">电话</th>
				<th width="50" align="center">性别</th>
				<th width="80" align="center">出生日期</th>
				<th width="50" align="center">是否启用</th>
				<th width="80" align="center">启用/停用时间</th>
				<th width="80" align="center">修改时间</th>
				<th width="80" align="center">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList }" var="item" varStatus="s">
          	<tr target="uid_user" rel="${item.cardid }" id="user_${s.index+1 }">
          		<td><input name="ids" value="${item.uid }" type="checkbox"></td>
	       		<td>${item.cardid }</td>
	       		<td>${item.userName }</td>
				<td>${item.tel }</td>
				<td>
				<c:choose>
					<c:when test="${item.sex==1 }">
						男 
					</c:when>
					<c:when test="${item.sex==2 }">
						女
					</c:when>
					<c:otherwise>
						未知
					</c:otherwise>
				</c:choose>
				</td>
				<td><fmt:formatDate value="${item.birthday }" pattern="yyyy-MM-dd"/></td>
	       		<td>
		       		<c:choose>
						<c:when test="${item.isEnabled==1 }">
							启用
						</c:when>
						<c:otherwise>
							不启用
						</c:otherwise>
					</c:choose>
	       		</td>
				<td><fmt:formatDate value="${item.isEnabledTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	       		<td><fmt:formatDate value="${item.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          	</tr>
          </c:forEach>
		</tbody>
	</table>
		<%@include file="../../base/panelBar.jsp" %>
	</div>