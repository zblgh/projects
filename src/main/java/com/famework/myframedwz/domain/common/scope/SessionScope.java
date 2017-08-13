package com.famework.myframedwz.domain.common.scope;

import org.apache.shiro.SecurityUtils;
/**
 * 
*项目名称：fqmall
*包名： com.yuanjing.fqmall.domain.common.scope
*类名称：SessionScope
*类描述：负责获取session中的用户对象，实际投入使用时将Object统一替换成项目实际的Userbean,或者可以不用替换，使用时强转
*创建人：张斌龙
*创建时间：2014年7月30日下午2:32:15
*修改人：张斌龙
*修改时间：2014年7月30日下午2:32:15
*修改备注：
*@version
 */
public class SessionScope {
	
	public static Object getUserInfo() {
		Object user = SecurityUtils.getSubject().getSession().getAttribute("user");
		return user;
	}
	public static void setUserInfo(Object user){
		SecurityUtils.getSubject().getSession().setAttribute("user", user);
	}
}
