package com.famework.myframedwz.domain.common;

public class Page {
	private String uniqueId="";
	public String getUniqueId() {
		if (uniqueId==null)
			this.uniqueId = "";
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	private int numPerPage = 10; //每页显示记录数
	private int totalPage;		//总页数
	private int totalResult;	//总记录数
	private int pageNum;	//当前页
	private int currentResult;	//当前记录起始索引
	private boolean entityOrField;	//true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr;		//最终页面显示的底部翻页导航，详细见：getPageStr();
	private String formID;      //分页列表页面的form表单的id，用于在同一页面确定是哪个form表单进行列表查询
	public String getFormID() {
		return formID;
	}
	public void setFormID(String formID) {
		this.formID = formID;
	}
	public int getTotalPage() {
		if(totalResult%numPerPage==0)
			totalPage = totalResult/numPerPage;
		else
			totalPage = totalResult/numPerPage+1;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalResult() {
		return totalResult;
	}
	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}
	public int getPageNum() {
		if(pageNum<=0)
			pageNum = 1;
		if(pageNum>getTotalPage())
			pageNum = getTotalPage();
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if(totalResult>0){
			sb.append("	<ul>\n");
			if(pageNum==1){
				sb.append("	<li class=\"pageinfo\">首页</li>\n");
				sb.append("	<li class=\"pageinfo\">上页</li>\n");
			}else{	
				sb.append("	<li><a href=\"#@\" onclick=\"nextPage(1)\">首页</a></li>\n");
				sb.append("	<li><a href=\"#@\" onclick=\"nextPage("+(pageNum-1)+")\">上页</a></li>\n");
			}
			int showTag = 3;	//分页标签显示数量
			int startTag = 1;
			if(pageNum>showTag){
				startTag = pageNum-1;
			}
			int endTag = startTag+showTag-1;
			for(int i=startTag; i<=totalPage && i<=endTag; i++){
				if(pageNum==i)
					sb.append("<li class=\"current\">"+i+"</li>\n");
				else
					sb.append("	<li><a href=\"#@\" onclick=\"nextPage("+i+")\">"+i+"</a></li>\n");
			}
			if(pageNum==totalPage){
				sb.append("	<li class=\"pageinfo\">下页</li>\n");
				sb.append("	<li class=\"pageinfo\">尾页</li>\n");
			}else{
				sb.append("	<li><a href=\"#@\" onclick=\"nextPage("+(pageNum+1)+")\">下页</a></li>\n");
				sb.append("	<li><a href=\"#@\" onclick=\"nextPage("+totalPage+")\">尾页</a></li>\n");
			}
			sb.append("	<li class=\"pageinfo\">第"+pageNum+"页</li>\n");
			sb.append("	<li class=\"pageinfo\">共"+totalPage+"页</li>\n");
			sb.append("	<li class=\"pageinfo\">共"+totalResult+"条</li>\n");
			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append("	if(true && document.getElementById('"+formID+"')){\n");
			sb.append("		var url = document.getElementById('"+formID+"').getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"+(entityOrField?"pageNum":getUniqueId()+"page.pageNum")+"=\";}\n");
			sb.append("		else{url += \"?"+(entityOrField?"pageNum":getUniqueId()+"page.pageNum")+"=\";}\n");
			sb.append("		document.getElementById('"+formID+"').action = url+page;\n");
			sb.append("		document.getElementById('"+formID+"').submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('pageNum')>-1){\n");
			sb.append("				var reg = /pageNum=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'pageNum=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"+(entityOrField?"pageNum":getUniqueId()+"page.pageNum")+"=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"+(entityOrField?"pageNum":getUniqueId()+"page.pageNum")+"=\";}\n");
			sb.append("		document.location = url + page;\n");
			sb.append("	}\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}
	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public int getCurrentResult() {
		currentResult = (getPageNum()-1)*getNumPerPage();
		if(currentResult<0)
			currentResult = 0;
		return currentResult;
	}
	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}
	public boolean isEntityOrField() {
		return entityOrField;
	}
	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}
	
}
