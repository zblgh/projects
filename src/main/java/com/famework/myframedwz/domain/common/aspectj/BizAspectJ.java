package com.famework.myframedwz.domain.common.aspectj;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.famework.myframedwz.domain.common.filter.SysContent;
import com.famework.myframedwz.domain.common.scope.SessionScope;
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.model.SysAdminLog;
import com.famework.myframedwz.domain.service.SysAdminLogService;
/**
 * 
*项目名称：fqmall
*包名： com.yuanjing.fqmall.domain.common.aspectj
*类名称：BizAspectJ
*类描述：业务各层的切面控制
*创建人：张斌龙
*创建时间：2014年7月30日下午2:52:24
*修改人：张斌龙
*修改时间：2014年7月30日下午2:52:24
*修改备注：
*@version
 */
@Aspect
public class BizAspectJ {

	private static Logger logger = LoggerFactory.getLogger(BizAspectJ.class);
	//用来获取spring bean的应用类
	private ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
	
	@Pointcut("execution(* com.famework.myframedwz.domain..*.login*(..))")
	public void objectCallsLogin(){}
	
	@Pointcut("execution(* com.famework.myframedwz.domain..*.logout*(..))")
	public void objectCallsLogout(){}
	
	@Pointcut("execution(* com.famework.myframedwz.domain.common..*.*(..))")
	public void objectNotCalls(){}
	
	//环绕通知
//	@Around("objectCalls() && !objectNotCalls()")
//	public Object aroundLogCalls(ProceedingJoinPoint joinPoint) throws Throwable{
//		System.out.println("before invoke method:"+joinPoint.getSignature().getName());
//		Object object = joinPoint.proceed();
//		System.out.println("after invoke method:"+joinPoint.getSignature().getName());
//		return new Object();
//	}
	
	//前置通知
	@After("objectCallsLogin() && !objectNotCalls()")
	public void afterAdviceLogin(JoinPoint joinPoint){
		HttpServletRequest request = SysContent.getRequest();
//    	HttpServletResponse response = SysContent.getResponse();
//    	HttpSession session = SysContent.getSession();
    	String ip = request.getRemoteAddr();
    	String content = "invoke method:"+joinPoint.getSignature().getName();
    	//获取当前用户
		SysAdminList userSession = (SysAdminList)SessionScope.getUserInfo();
    	SysAdminLogService sysAdminLogService = (SysAdminLogService)ctx.getBean("sysAdminLogService");
    	try {
    		SysAdminLog sysAdminLog = new SysAdminLog();
    		sysAdminLog.setIpAddress(ip);
    		sysAdminLog.setAccount(userSession.getCardid());
    		sysAdminLog.setAccountName(userSession.getUserName());
    		sysAdminLog.setOperationState((byte)1);
    		sysAdminLog.setOperationType((byte)0);
    		sysAdminLog.setAddTime(new Date());
    		sysAdminLog.setContent(content);
    		sysAdminLogService.addAdminLog(sysAdminLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
		}
    } 
	//前置通知
	@Before("objectCallsLogout() && !objectNotCalls()")
	public void afterAdviceLogout(JoinPoint joinPoint){
		HttpServletRequest request = SysContent.getRequest();
//    	HttpServletResponse response = SysContent.getResponse();
//    	HttpSession session = SysContent.getSession();
    	String ip = request.getRemoteAddr();
    	String content = "invoke method:"+joinPoint.getSignature().getName();
    	//获取当前用户
		SysAdminList userSession = (SysAdminList)SessionScope.getUserInfo();
    	SysAdminLogService sysAdminLogService = (SysAdminLogService)ctx.getBean("sysAdminLogService");
    	try {
    		SysAdminLog sysAdminLog = new SysAdminLog();
    		sysAdminLog.setIpAddress(ip);
    		sysAdminLog.setAccount(userSession.getCardid());
    		sysAdminLog.setAccountName(userSession.getUserName());
    		sysAdminLog.setOperationState((byte)1);
    		sysAdminLog.setOperationType((byte)1);
    		sysAdminLog.setAddTime(new Date());
    		sysAdminLog.setContent(content);
    		sysAdminLogService.addAdminLog(sysAdminLog);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
		}
    }
}
