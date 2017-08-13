package com.famework.myframedwz.domain.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Node;

import org.apache.commons.codec.digest.DigestUtils;
import org.codehaus.plexus.util.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.famework.myframedwz.domain.common.Page;
import com.famework.myframedwz.domain.common.scope.SessionScope;
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.model.SysAdminPermission;
import com.famework.myframedwz.domain.model.SysPermission;
import com.famework.myframedwz.domain.model.SysRole;
import com.famework.myframedwz.domain.model.SysRolePermission;
import com.famework.myframedwz.domain.service.SysPermissionService;
import com.famework.myframedwz.utils.BaseCodeParam;
import com.famework.myframedwz.utils.Tools;
import com.famework.myframedwz.utils.nodeutil.NodeUtil;


@Controller
@RequestMapping(produces = {"text/html;charset=UTF-8"})
public class MenuController{
	private static Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private SysPermissionService sysPermissionService;
	
	//查询用户权限信息
	@RequestMapping("manager_user_menu")
	public String queryUserMenu(String cardid,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/user_menu";
		try {
			//1.查询所有权限
			List<SysPermission> spList = sysPermissionService.sysPermissions();
			//2.根据用户账号查询已有权限
			List<SysAdminPermission> userSpList = sysPermissionService.queryUserPer(cardid);
			Map<String, String> rmap = new HashMap<String, String>();
			if (!userSpList.isEmpty()) {
				for (int i = 0; i < userSpList.size(); i++) {
					SysAdminPermission sysAdminPermission = userSpList.get(i);
					String pidLong = sysAdminPermission.getPid();
					rmap.put(pidLong,pidLong);
				}
			}
			//3.拼接成json格式的数据
			String nodeStr = NodeUtil.getAllChildNodes(spList,rmap);
			//4.数据返回前台
			model.addAttribute("jsonArray", nodeStr);
			model.addAttribute("cardid", cardid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//修改用户权限信息
	@RequestMapping("manager_user_menuupdate")
	@ResponseBody
	public String menuUpdate(HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功";
		String statusCode = "200";
		try {
			String cardid = request.getParameter("cardid");
			String menuIds = request.getParameter("menuIds");
			this.userMenuUpdate(cardid, menuIds);//修改用户权限信息
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			message = "操作失败！";
			statusCode = "300";
		}
		jsonObj.put("statusCode", statusCode);
		jsonObj.put("message", message);
		return jsonObj.toJSONString();
	}
	//修改用户权限方法
	@Transactional(rollbackFor = Exception.class)
	public void userMenuUpdate(String cardid,String menuIds) throws Exception{
		String[] idsArr = Tools.str2StrArray(menuIds);
		try {
			//删除原来用户的角色
			sysPermissionService.delUserPer(cardid);
			//添加新角色
			if (idsArr!=null) {
				List<SysAdminPermission> listSp = new ArrayList<SysAdminPermission>();
				SysAdminPermission sp = null;
				String pid = "";
				for (int i = 0; i < idsArr.length; i++) {
					sp = new SysAdminPermission();
					pid = idsArr[i];//权限id
					sp.setPid(pid);
					sp.setCardid(cardid);
					sp.setPermissionSource((byte)1);
					listSp.add(sp);
				}
				if(listSp.size()>0){
					sysPermissionService.insertBatchUserPer(listSp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw e;
		}
	}
	//查询角色权限信息
	@RequestMapping("manager_role_menu")
	public String queryRoleMenu(String rid,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/role_menu";
		try {
			//1.查询所有权限
			List<SysPermission> spList = sysPermissionService.sysPermissions();
			//2.根据角色id查询已有权限
			List<SysRolePermission> rpList = sysPermissionService.queryRolePer(Long.parseLong(rid));
			Map<String, String> rmap = new HashMap<String, String>();
			if (!rpList.isEmpty()) {
				for (int i = 0; i < rpList.size(); i++) {
					SysRolePermission sysRolePermission = rpList.get(i);
					String pidLong = sysRolePermission.getPid();
					rmap.put(pidLong,pidLong);
				}
			}
			//3.拼接成json格式的数据
			String nodeStr = NodeUtil.getAllChildNodes(spList,rmap);
			//4.数据返回前台
			model.addAttribute("jsonArray", nodeStr);
			model.addAttribute("rid", rid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	@RequestMapping("manager_role_menuupdate")
	@ResponseBody
	public String roelMenuUpdate(HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功";
		String statusCode = "200";
		try {
			String rid = request.getParameter("rid");
			String menuIds = request.getParameter("menuIds");
			this.roleMenuUpdate(rid, menuIds);//修改角色权限信息
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			message = "操作失败！";
			statusCode = "300";
		}
		jsonObj.put("statusCode", statusCode);
		jsonObj.put("message", message);
		return jsonObj.toJSONString();
	}
	//修改用户权限方法
	@Transactional(rollbackFor = Exception.class)
	public void roleMenuUpdate(String rid,String menuIds) throws Exception{
		String[] idsArr = Tools.str2StrArray(menuIds);
		try {
			//删除原来角色的权限
			sysPermissionService.delRolePer(Long.parseLong(rid));
			//添加新权限
			if (idsArr!=null) {
				List<SysRolePermission> listSp = new ArrayList<SysRolePermission>();
				SysRolePermission rp = null;
				String pid = "";
				for (int i = 0; i < idsArr.length; i++) {
					rp = new SysRolePermission();
					pid = idsArr[i];//权限id
					rp.setPid(pid);
					rp.setRid(Long.parseLong(rid));
					listSp.add(rp);
				}
				if(listSp.size()>0){
					sysPermissionService.insertBatchRolePer(listSp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw e;
		}
	}
	//查询所有的菜单
	@RequestMapping("manager_menu_left")
	public String roleList(HttpServletRequest request,Model model){
		String url = "manager/sysmanage/menu_list_left";
		try {
			//1.查询所有权限
			List<SysPermission> spList = sysPermissionService.sysPermissions();
			//3.拼接成json格式的数据
			String nodeStr = NodeUtil.getAllNodes(spList);
			//4.数据返回前台
			model.addAttribute("jsonArray", nodeStr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//添加菜单
	@RequestMapping("manager_save_rootmenu")
	public String roleList(SysPermission sysPermission,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/add_root_menu";
		try {
			//1.添加菜单
			sysPermissionService.insertMenu(sysPermission);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//节点连接
	@RequestMapping("manager_menu_pid")
	public String roleList(String pid,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/menu_pid";
		try {
			//1.根据pid查看菜单
			SysPermission sysPermission = sysPermissionService.MenuForPid(pid);
			
			//1.判断该菜单是否有子菜单
			List<SysPermission> spList = sysPermissionService.sysPermissions();
			boolean isChildNodes = NodeUtil.isChildNodes(spList, sysPermission.getPid());
			model.addAttribute("sysPermission", sysPermission);
			model.addAttribute("isChildNodes", isChildNodes);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//验证菜单是否存在
	@RequestMapping("manager_menu_chk")
	@ResponseBody
	public String chkUser(String pid,HttpSession session, HttpServletRequest request,Model model){
		String flag = "0";//状态，0：不存在；1：已存在；2:验证出错
		try {
			int count = sysPermissionService.checkMenuPid(pid);
			if (count!=0) {
				flag = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			flag = "2";
		}
		return flag;
	}
	//修改菜单
	@RequestMapping("manager_menu_update")
	public String menuUpdate(SysPermission sysPermission,HttpServletRequest request,Model model){
		String url = "redirect:"+BaseCodeParam.menuUrl+"?pid="+sysPermission.getPid();
		try {
			//1.根据pid修改菜单
			sysPermissionService.menuUpdateForPid(sysPermission);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//添加菜单
	@RequestMapping("manager_save_menu")
	public String menuBatcsshDel(SysPermission sysPermission,HttpServletRequest request,Model model){
		String url = "redirect:"+BaseCodeParam.menuUrl+"?pid="+sysPermission.getPid();
		try {
			sysPermissionService.insertMenu(sysPermission);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//批量删除菜单
	@RequestMapping("manager_menu_batchdel")
	@ResponseBody
	public String menuBatchDel(String pid,HttpServletRequest request,Model model){
		String flag = "0";//状态，0：正常；1：角色在使用；2:用户在使用；3：系统出错
		try {
			//1.查询所有权限,得到该节点和该节点下的子节点
			List<SysPermission> spList = sysPermissionService.sysPermissions();
			String pids= NodeUtil.getMenuPidArray(spList,pid);
			String[] pidArr = pids.split("`");
			//2.判断这些权限是否被角色和用户使用
			int countR = sysPermissionService.chkPidsForRole(pidArr);
			if (countR!=0) {
				flag = "1";
				return flag;
			}
			int countU = sysPermissionService.chkPidsForUser(pidArr);
			if (countU!=0) {
				flag = "2";
				return flag;
			}
			//3.根据pidArr删除这些菜单
			sysPermissionService.menuBatchDel(pidArr);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			flag = "3";
		}
		return flag;
	}
	//删除单个菜单
	@RequestMapping("manager_menu_del")
	@ResponseBody
	public String menuDel(String pid,HttpSession session, HttpServletRequest request,Model model){
		String flag = "0";//状态，0：正常；1：角色在使用；2:用户在使用；3：有子菜单；4：系统出错
		try {
			//2.判断这些权限是否被角色和用户使用
			int countR = sysPermissionService.chkPidForRole(pid);
			if (countR!=0) {
				flag = "1";
				return flag;
			}
			int countU = sysPermissionService.chkPidForUser(pid);
			if (countU!=0) {
				flag = "2";
				return flag;
			}
			//1.判断该菜单是否有子菜单
			List<SysPermission> spList = sysPermissionService.sysPermissions();
			boolean isChildNodes = NodeUtil.isChildNodes(spList, pid);
			if (isChildNodes) {
				flag = "3";
			}else {//删除该菜单
				sysPermissionService.menuDel(pid);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			flag = "4";
		}
		return flag;
	}
}
