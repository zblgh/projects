package com.famework.myframedwz.domain.common.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.famework.myframedwz.domain.common.authc.CaptchaUsernamePasswordToken;
import com.famework.myframedwz.domain.common.authc.IncorrectCaptchaException;
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.service.SysAdminListService;

/**
 * 
*项目名称：fqmall
*包名： com.yuanjing.fqmall.domain.common.realm
*类名称：MonitorRealm
*类描述：安全认证最主要的实现类
*创建人：张斌龙
*创建时间：2014年7月30日下午2:40:05
*修改人：张斌龙
*修改时间：2014年7月30日下午2:40:05
*修改备注：
*@version
 */
@Service("monitorRealm")
public class MonitorRealm extends AuthorizingRealm {
	@Autowired
	private SysAdminListService sysAdminListService;//用户服务类，用于查询用户，初始框架中注释掉
	public MonitorRealm() {
		super();
	}
	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		return null;
	}
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException,IncorrectCaptchaException {
		CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;
		
		// 增加判断验证码逻辑
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
		    throw new IncorrectCaptchaException("验证码错误");
		}
		SysAdminList user = sysAdminListService.checkUser(token.getUsername());//获取用户信息
		SecurityUtils.getSubject().getSession().setAttribute("user", user);//放入session,key必须是user,因为在获得时使用user
		//SecurityUtils.getSubject().getSession().setTimeout(60000);
		AuthenticationInfo authcInfo =new SimpleAuthenticationInfo(user.getCardid(), user.getPwd(), getName());//进行认证操作
		return authcInfo;
	}
	//清空用户关联权限认证，待下次使用时重新加载
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
	//向当前session会话中添加属性的方法
	@SuppressWarnings("unused")
	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}
