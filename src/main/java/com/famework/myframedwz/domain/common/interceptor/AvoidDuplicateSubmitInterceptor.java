/**
* 标题: AvoidDuplicateSubmitInterceptor.java
* 包名： com.yuanjing.myweb.domain.common.interceptor
* 功能描述：TODO
* 作者： john
* 创建时间： 2014年8月3日 上午12:01:55
* @version V1.0   
*/
package com.famework.myframedwz.domain.common.interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.famework.myframedwz.domain.common.annotation.AvoidDuplicateSubmission;



/**
 *项目名称：myweb
 *包名： com.famework.myframedwz.domain.common.interceptor
 *类名称：AvoidDuplicateSubmitInterceptor
 *类描述：
 *创建人：张斌龙
 *创建时间：2014年8月3日上午12:01:55
 *修改人：张斌龙
 *修改时间：2014年8月3日上午12:01:55
 *修改备注：
 *@version
 */
public class AvoidDuplicateSubmitInterceptor extends HandlerInterceptorAdapter {
	private static final Logger LOG = Logger.getLogger(AvoidDuplicateSubmitInterceptor.class);
	 
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            AvoidDuplicateSubmission annotation = method.getAnnotation(AvoidDuplicateSubmission.class);
            if (annotation != null) {
                boolean needSaveSession = annotation.needSaveToken();
                if (needSaveSession) {
                	String token = UUID.randomUUID().toString();
                	SecurityUtils.getSubject().getSession().setAttribute("token", token);
                	//SecurityUtils.getSubject().getSession().setAttribute("tokenAjax", token);
                }
                boolean needRemoveSession = annotation.needRemoveToken();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        LOG.warn("please don't repeat submit,[url:"+ request.getServletPath() + "]");
                        String forwardUrl = request.getParameter("forwardUrl");//重复提交时，获取跳转url,此参数在提交页面中设置
                        if (forwardUrl==null || "".equals(forwardUrl)) {
                        	request.getRequestDispatcher("pages/polo/index.jsp").forward(request,response);
						}else {
							request.getRequestDispatcher(forwardUrl).forward(request,response);
						}
                        return false;
                    }
                    SecurityUtils.getSubject().getSession().removeAttribute("token");
                }
            }
            return true;
        }else {
            return super.preHandle(request, response, handler);
        }
    }
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) SecurityUtils.getSubject().getSession().getAttribute("token");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("token");
        if (clinetToken == null) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}
