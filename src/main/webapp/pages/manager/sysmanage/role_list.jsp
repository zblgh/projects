<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../base/taglib.jsp"%>
<%@ include file="../../base/pagerForm.jsp" %>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="manager_role_list.html" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					角色名称：<input type="text" name="roleName" value="${sysRole.roleName }"/><span style="color:green;">注：支持模糊查询</span>
				</td>
				<td>
					角色描述：<input type="text" name="roleDescribe" value="${sysRole.roleDescribe }"/><span style="color:green;">注：支持模糊查询</span>
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
			<li><a class="add" href="manager_resp_roleadd.html" target="dialog" mask="true" rel="role_add"><span>添加角色</span></a></li>
			<li><a title="请确保用户没有使用这些角色，删除角色将会一并删除角色下的权限，确定删除吗?" target="selectedTodo" rel="role_ids" href="manager_role_batchdel.html" class="delete"><span>批量删除</span></a></li>
			<li><a class="edit" href="manager_query_roleforid.html?rid={rid_role}" target="dialog" mask="true" rel="role_update" warn="请选择一个角色"><span>修改角色</span></a></li>
			<li><a class="edit" href="manager_role_menu.html?rid={rid_role}" target="dialog" mask="true" width="300" rel="role_per" warn="请选择一个角色"><span>权限</span></a></li>
			<!-- <li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li> -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="role_ids" class="checkboxCtrl"></th>
				<th width="100" align="center">角色名称</th>
				<th width="100" align="center">角色描述</th>
				<th width="100" align="center">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roleList }" var="item" varStatus="s">
          	<tr target="rid_role" rel="${item.rid }" id="user_${s.index+1 }">
          		<td><input name="role_ids" value="${item.rid }" type="checkbox"></td>
	       		<td>${item.roleName }</td>
	       		<td>${item.roleDescribe }</td>
	       		<td><fmt:formatDate value="${item.addTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
          	</tr>
          </c:forEach>
		</tbody>
	</table>
		<%@include file="../../base/panelBar.jsp" %>
	</div>