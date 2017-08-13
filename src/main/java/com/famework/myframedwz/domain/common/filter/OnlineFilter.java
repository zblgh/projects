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

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class OnlineFilter extends HttpServlet implements Filter{
	private static Logger logger = LoggerFactory.getLogger(OnlineFilter.class);
	public OnlineFilter(){
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("login filtering...");
		SysContent.setRequest((HttpServletRequest) request);
		SysContent.setResponse((HttpServletResponse) response);
		HttpServletRequest httpRequest = SysContent.getRequest();
		HttpServletResponse httpResponse = SysContent.getResponse();
		Subject currentUser =  SecurityUtils.getSubject();
		RequestDispatcher dispatcher = null;
		if(currentUser.isAuthenticated()){
			Object principal = currentUser.getPrincipal();
			 if (null != principal) {
				 try {
				     //httpResponse.sendRedirect(httpRequest.getContextPath()+"/manager_main.html");
					 dispatcher = request.getRequestDispatcher("manager_main.html");
					 dispatcher.forward(request,response);
					 System.out.println("Is logged in");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(ExceptionUtils.getFullStackTrace(e));
					//httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.html");
					dispatcher = request.getRequestDispatcher("index.jsp");
					dispatcher.forward(request,response);
				}
			 }else {
				 //httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.html");
				 dispatcher = request.getRequestDispatcher("index.jsp");
				 dispatcher.forward(request,response);
			}
		}else{
			 System.out.println(" no currentUser");
			 //httpResponse.sendRedirect(httpRequest.getContextPath()+"/login.html");
			 dispatcher = request.getRequestDispatcher("index.jsp");
			 dispatcher.forward(request,response);
		}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
