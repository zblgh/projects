package com.famework.myframedwz.domain.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.famework.myframedwz.domain.model.SysRole;

/**
 * ------------------------------------------------------------------
 * 版权所有 Copyright (C) 2014 杭州热讯电子商务有限公司
 *
 * 项目名称：fqmalladmin
 *
 * 包名：com.famework.myframedwz.domain.action
 *
 * 类名称：PageForward
 *
 * 文件名：PageForward.java
 * 
 * 创建者： 张斌龙（zblvip123@163.com）
 * 
 * 创建时间：2014年9月4日-上午10:47:28
 * 
 * 描述：TODO  此类负责页面转发
 *-------------------------------------------------------------------
 * 修改人：
 *
 * 修改时间：
 *
 * 修改备注：
 *-------------------------------------------------------------------
 */
@Controller
@RequestMapping(produces = {"text/html;charset=UTF-8"})
public class PageForward {
	//用户修改密码跳转
	@RequestMapping("manager_resp_changepwd")
	public String respChangepwd(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		String url ="manager/changepwd";
		return url;
	}
	//添加用户跳转
	@RequestMapping("manager_resp_useradd")
	public String respUserAdd(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		String url ="manager/sysmanage/user_add";
		return url;
	}
	//添加用户跳转
	@RequestMapping("manager_resp_roleadd")
	public String respRoleAdd(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		String url ="manager/sysmanage/role_add";
		return url;
	}
	//菜单管理跳转
	@RequestMapping("manager_menu_list")
	public String roleList(HttpServletRequest request,Model model){
		String url = "manager/sysmanage/menu_list";
		return url;
	}
	//添加根菜单下的子菜单跳转
	@RequestMapping("manager_add_rootmenu")
	public String addRootMenu(HttpServletRequest request,Model model){
		String url = "manager/sysmanage/add_root_menu";
		return url;
	}
	//添加子菜单
	@RequestMapping("manager_resp_menuadd")
	public String respMenuAdd(String pid,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/add_child_menu";
		model.addAttribute("pid", pid);
		return url;
	}
}
