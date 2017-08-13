package com.famework.myframedwz.domain.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 
*项目名称：fqmall
*包名： com.yuanjing.fqmall.domain.common.filter
*类名称：SysContent
*类描述：web系统管理request,response,session
*创建人：张斌龙
*创建时间：2014年7月30日下午2:42:43
*修改人：张斌龙
*修改时间：2014年7月30日下午2:42:43
*修改备注：
*@version
 */
public class SysContent {
	private static ThreadLocal<HttpServletRequest> requestLocal= new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal= new ThreadLocal<HttpServletResponse>();
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest)requestLocal.get();
	}
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse)responseLocal.get();
	}
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
	public static HttpSession getSession() {
		return (HttpSession)((HttpServletRequest)requestLocal.get()).getSession();
	}
}
