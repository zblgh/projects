package com.famework.myframedwz.domain.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.famework.myframedwz.domain.common.authc.CaptchaUsernamePasswordToken;
import com.famework.myframedwz.domain.common.authc.IncorrectCaptchaException;
import com.famework.myframedwz.domain.common.scope.SessionScope;
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.service.SysAdminListService;


@Controller
@RequestMapping(produces = {"text/html;charset=UTF-8"})
public class LoginController{
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private SysAdminListService sysAdminListService;
	
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value="adminlogin")
	public String adminLogin(){
		return "door/logindwz";
	}
	/**
	 * 用户登录:验证
	 * 
	 * @param user
	   *           　登录用户
	 * @return
	 */
	@RequestMapping(value="login")
	public String login(SysAdminList user,HttpSession session, HttpServletResponse response, HttpServletRequest request,Model model) {
		String url = "redirect:manager_menu_load.html";
		try {
			Subject currentUser = SecurityUtils.getSubject();
			CaptchaUsernamePasswordToken token = new CaptchaUsernamePasswordToken(
					user.getCardid(), DigestUtils.md5Hex(user.getPwd()),user.getRandCode());
			//如果该方法返回true，则Shiro 将会在整个会话中记住终端用户的身份ID
			token.setRememberMe(true);
			// 回调doGetAuthenticationInfo，进行认证
			currentUser.login(token);
			logger.info("currentUser验证成功！");
		} catch (IncorrectCaptchaException d) {
			//d.printStackTrace();
			url = "door/logindwz";
			model.addAttribute("message", "验证码错误！");
			logger.error("验证码错误!");
		} catch (AuthenticationException e) {
			//e.printStackTrace();
			url = "door/logindwz";
			model.addAttribute("message", "用户名或密码错误！");
			logger.error("用户名或密码错误！");
		} catch(Exception e){
			e.printStackTrace();
			url = "door/logindwz";
		}
		return url;
	}
	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();
		} catch (AuthenticationException e) {
			e.printStackTrace();

		}
		return "redirect:index.jsp";//重定向到一个新的视图或页面，保证任何与安全相关的Cookies都能像预期的被删除
	}
	/**
	 * 
	* @Title: bizMain
	* @Description: TODO(加载后台管理)
	* @param @param request
	* @param @param model
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("manager_main")
	public String bizMain(HttpServletRequest request,Model model,SysAdminList user){
		return "redirect:manager_menu_load.html";
	}
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("manager_updateUser")
	@ResponseBody
	public String updateUser(HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功";
		String statusCode = "200";
		String pwd = request.getParameter("pwd");
		String newpwd = request.getParameter("newpwd");
		String newpwd2 = request.getParameter("newpwd2");
		if (newpwd!=null && newpwd2!=null && pwd!=null) {
			if(newpwd.equals(newpwd2)){
				SysAdminList user = (SysAdminList) SessionScope.getUserInfo();
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("pwd", DigestUtils.md5Hex(pwd));
				param.put("uid", user.getUid());
				param.put("newpwd", DigestUtils.md5Hex(newpwd));
				int i = sysAdminListService.updateUserList(param);
				if (i==0) {
					message = "操作失败:原密码有误，请重新修改！";
					statusCode = "300";
				}
			}else {
				message = "操作失败:密码不一致，请重新修改！";
				statusCode = "300";
			}
		}else {
			message = "操作失败:输入项不可为空！";
			statusCode = "300";
		}
		jsonObj.put("statusCode", statusCode);
		jsonObj.put("message", message);
		jsonObj.put("callbackType", "closeCurrent");
		return jsonObj.toJSONString();
	}
	public static void main(String[] args) {
		System.out.println(DigestUtils.md5Hex("zbl"));
	}
}
