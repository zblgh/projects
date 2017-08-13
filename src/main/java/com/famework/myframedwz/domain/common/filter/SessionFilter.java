package com.famework.myframedwz.domain.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

/**
 * 
*项目名称：fqmall
*包名： com.yuanjing.fqmall.domain.common.filter
*类名称：OnlineFilter
*类描述：负责用户免重复登录，只针对登录页面过滤
*创建人：张斌龙
*创建时间：2014年7月30日下午2:46:56
*修改人：张斌龙
*修改时间：2014年7月30日下午2:46:56
*修改备注：
*@version
 */
public class SessionFilter extends HttpServlet implements Filter{
	private static Logger logger = LoggerFactory.getLogger(SessionFilter.class);
	public SessionFilter(){
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		//RequestDispatcher dispatcher = null;
		if (!validate(httpServletRequest)) {
			httpServletResponse.setContentType("text/html");
			httpServletResponse.setHeader("Cache-Control","no-cache");
			httpServletResponse.setCharacterEncoding("UTF-8");
			if("XMLHttpRequest".equalsIgnoreCase(httpServletRequest.getHeader("X-Requested-With")) || request.getParameter("ajax") != null) {
				JSONObject jsonObj = new JSONObject();
    			jsonObj.put("message", "会话超时");
    			jsonObj.put("statusCode", "301");
    			httpServletResponse.getWriter().print(jsonObj.toJSONString());
			}else {
				// 获取uri地址    
//		         String uri = httpServletRequest.getRequestURI();    
//		         String ctx=httpServletRequest.getContextPath();
//		         //String query = httpServletRequest.getQueryString();
//		         uri = uri.substring(ctx.length());
//		        if (uri.startsWith("/login.html")) {
//		        	chain.doFilter(request, response);
//				}else {
//					StringBuilder builder = new StringBuilder();
//			        builder.append("<script type=\"text/javascript\">");
//	                builder.append("window.top.location.href='");
//	                builder.append("index.jsp");
//	                builder.append("';");
//	                builder.append("</script>");
//	                httpServletResponse.getWriter().print(builder.toString());
//				}
				chain.doFilter(request, response);
			}
			return;
		}else {
			chain.doFilter(request, response);
		}
	}
	private boolean validate(HttpServletRequest httpServletRequest){
		HttpSession httpSession = httpServletRequest.getSession();
		if (httpSession==null) {
			return false;
		}
		if (httpSession.getAttribute("user")!=null) {
			return true;
		}
		return false;
	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
