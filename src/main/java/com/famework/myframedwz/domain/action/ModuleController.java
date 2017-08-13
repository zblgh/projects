/**
* 标题: ModuleController.java
* 包名： com.famework.myframedwz.domain.action
* 功能描述：TODO
* 作者： tech
* 创建时间： 2014年9月3日 上午10:38:05
* @version V1.0   
*/
package com.famework.myframedwz.domain.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.famework.myframedwz.domain.common.scope.SessionScope;
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.model.SysPermission;
import com.famework.myframedwz.domain.service.SysPermissionService;
import com.famework.myframedwz.utils.nodeutil.NodeUtil;

/**
 * ------------------------------------------------------------------
 * 版权所有 Copyright (C) 2014 杭州热讯电子商务有限公司
 *
 * 项目名称：myframedwz
 *
 * 包名：com.famework.myframedwz.domain.action
 *
 * 类名称：ModuleController
 *
 * 文件名：ModuleController.java
 * 
 * 创建者： 张斌龙（zblvip123@163.com）
 * 
 * 创建时间：2014年9月3日-上午10:38:05
 * 
 * 描述：TODO  主要的模块加载，如：动态菜单
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
public class ModuleController {

	@Autowired
	private SysPermissionService sysPermissionService;
	
	/**
	 * 
	* @Title: menuLoad
	* @Description: TODO(加载菜单)
	* @param @param request
	* @param @param response
	* @param @param session
	* @param @param model
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	@RequestMapping("manager_menu_load")
	public String menuLoad(HttpServletRequest request,HttpServletResponse response,HttpSession session,Model model){
		String url = "manager/main";
		SysAdminList userSession = (SysAdminList)SessionScope.getUserInfo();
		List<SysPermission> spList = sysPermissionService.userSysPermissions(userSession);
		String strNodes = NodeUtil.getChildNodes(spList);
		model.addAttribute("strNodes", strNodes);
		return url;
	}
}
