package com.famework.myframedwz.domain.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
*项目名称：fqmall
*包名： com.yuanjing.fqmall.domain.common.filter
*类名称：GetContent
*类描述：负责在web项目启动后访问获得HttpServletRequest，HttpServletResponse，提供给整个项目使用
*创建人：张斌龙
*创建时间：2014年7月30日下午2:44:07
*修改人：张斌龙
*修改时间：2014年7月30日下午2:44:07
*修改备注：
*@version
 */
public class GetContent implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		SysContent.setRequest((HttpServletRequest) arg0);
		SysContent.setResponse((HttpServletResponse) arg1);
		arg2.doFilter(arg0, arg1);
	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
