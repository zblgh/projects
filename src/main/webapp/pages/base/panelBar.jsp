<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="targetType" value="${empty targetType ? 'navTab' : targetType}"/>
<div class="panelBar">
	<div class="pages">
		<span>显示</span>
		<select class="combox" name="numPerPage" onchange="dwzPageBreak({targetType:'${targetType}',data:{numPerPage:this.value}})">
			<c:forEach begin="10" end="40" step="10" varStatus="s">
				<option value="${s.index}" ${page.numPerPage eq s.index ? 'selected="selected"' : ''}>${s.index}</option>
			</c:forEach>
		</select>
		<span>条，共${page.totalResult}条</span>
	</div>
	<div class="pagination" targetType="${targetType}" totalCount="${page.totalResult}" numPerPage="${page.numPerPage }" pageNumShown="10" currentPage="${page.pageNum}"></div>
</div>