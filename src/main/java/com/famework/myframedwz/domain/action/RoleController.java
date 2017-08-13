package com.famework.myframedwz.domain.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.model.SysAdminPermission;
import com.famework.myframedwz.domain.model.SysRole;
import com.famework.myframedwz.domain.service.SysRoleService;
import com.famework.myframedwz.utils.DateUtil;
import com.famework.myframedwz.utils.Tools;


@Controller
@RequestMapping(produces = {"text/html;charset=UTF-8"})
public class RoleController{
	private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private SysRoleService sysRoleService;
	
	//查询用户的角色信息
	@RequestMapping("manager_user_role")
	public String queryUserRole(String cardid,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/user_role";
		try {
			System.out.println("cardid--"+cardid);
			//1.查询所有角色
			SysRole sysRole = new SysRole();
			List<SysRole> roleList = sysRoleService.queryAllRolelistPage(sysRole);
			//2.根据用户账号查询已有角色
			List<SysAdminPermission> userRole = sysRoleService.queryUserRole(cardid);
			//3.拼接成json格式的数据
//			var zNodesZdy =[
//    			{id:1,pId:0,name:"角色1"},
//    			{id:2,pId:0,name:"角色2"},
//    			{id:3,pId:0,name:"角色3"},
//    			{id:4,pId:0,name:"角色4"},
//    			{id:5,pId:0,name:"角色5"},
//    			{id:6,pId:0,name:"角色6"},
//    			{id:7,pId:0,name:"角色7", checked:true}
//    		];
			JSONArray jsonArray = new JSONArray();
			if (!roleList.isEmpty()) {
				Map<Long, Long> rmap = new HashMap<Long, Long>();
				if (!userRole.isEmpty()) {
					for (int i = 0; i < userRole.size(); i++) {
						SysAdminPermission sysAdminPermission = userRole.get(i);
						Long ridLong = sysAdminPermission.getRid();
						rmap.put(ridLong,ridLong);
					}
				}
				SysRole sRole = null;
				JSONObject jsonObject = null;
				for (int i = 0; i < roleList.size(); i++) {
					sRole = roleList.get(i);
					jsonObject = new JSONObject();
					jsonObject.put("id", sRole.getRid());
					jsonObject.put("pId", 0);
					jsonObject.put("name", sRole.getRoleName());
					jsonObject.put("des", sRole.getRoleDescribe());
					if (rmap.containsKey(sRole.getRid())) {
						jsonObject.put("checked", true);
					}
					jsonArray.add(jsonObject);
				}
			}
			//4.数据返回前台
			model.addAttribute("jsonArray", jsonArray.toJSONString());
			model.addAttribute("cardid", cardid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//修改用户的角色信息
	@RequestMapping("manager_user_roleupdate")
	@ResponseBody
	public String userRoleUpdate(HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功";
		String statusCode = "200";
		try {
			String cardid = request.getParameter("cardid");
			String roleIds = request.getParameter("roleIds");
			this.userRoleUpdate(cardid, roleIds);//修改用户角色
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
	@Transactional(rollbackFor = Exception.class)
	public void userRoleUpdate(String cardid,String roleIds) throws Exception{
		String[] idsArr = Tools.str2StrArray(roleIds);
		try {
			//删除原来用户的角色
			sysRoleService.delUserRole(cardid);
			//添加新角色
			if (idsArr!=null) {
				List<SysAdminPermission> listSp = new ArrayList<SysAdminPermission>();
				SysAdminPermission sp = null;
				String rid = "";
				for (int i = 0; i < idsArr.length; i++) {
					sp = new SysAdminPermission();
					rid = idsArr[i];//角色id
					sp.setRid(Long.parseLong(rid));
					sp.setCardid(cardid);
					listSp.add(sp);
				}
				if(listSp.size()>0){
					sysRoleService.insertBatchUserRole(listSp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw e;
		}
	}
	//查询所有的角色
	@RequestMapping("manager_role_list")
	public String roleList(SysRole sysRole,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/role_list";
		try {
			String pageNum = request.getParameter("pageNum");
			String numPerPage = request.getParameter("numPerPage");
			Page page = new Page();
			if (pageNum!=null && !"".equals(pageNum)) {
				page.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
			}
			if (numPerPage!=null && !"".equals(numPerPage)) {
				page.setNumPerPage(Integer.parseInt(request.getParameter("numPerPage")));
			}
			sysRole.setPage(page);
			List<SysRole> roleList = sysRoleService.queryAllRolelistPage(sysRole);
			model.addAttribute("roleList", roleList);
			model.addAttribute("sysRole", sysRole);
			model.addAttribute("page", sysRole.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//添加角色信息
	@RequestMapping("manager_role_add")
	@ResponseBody
	public String addRole(SysRole sysRole,HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功！";
		String statusCode = "200";
		Date addTime = new Date();//更新时间
		try {
			sysRole.setAddTime(addTime);
			int i = sysRoleService.insert(sysRole);
			if (i==0) {
				message = "操作失败:请检查录入信息！";
				statusCode = "300";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			message = "操作失败！";
			statusCode = "300";
		}
		jsonObj.put("statusCode", statusCode);
		jsonObj.put("message", message);
		jsonObj.put("callbackType", "closeCurrent");
		return jsonObj.toJSONString();
	}
	//批量删除角色
	@RequestMapping("manager_role_batchdel")
	@ResponseBody
	public String batchDelrole(String role_ids,HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功！";
		String statusCode = "200";
		try {
			System.out.println("role_ids--"+role_ids);
			String[] idsArr = role_ids.split(",");
			//1.查询用户是否使用这些角色
			int count =  sysRoleService.chkAllUserRole(idsArr);
			if (count==0) {
				try {
					RoleAndPerDel(idsArr);//删除角色及角色下的权限
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(ExceptionUtils.getFullStackTrace(e));
					message = "操作失败！";
					statusCode = "300";
				}
			}else {
				message = "操作失败:请确保用户没有使用这些角色！";
				statusCode = "300";
			}
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
	//角色以及角色下的权限删除的方法
	@Transactional(rollbackFor = Exception.class)
	public void RoleAndPerDel(String[] idsArr) throws Exception{
		try {
			sysRoleService.batchDelRole(idsArr);//删除角色
			sysRoleService.batchDelRolePer(idsArr);//删除角色下的权限
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw e;
		}
	}
	//根据主键查询角色信息
	@RequestMapping("manager_query_roleforid")
	public String queryRoleForId(String rid,HttpSession session, HttpServletRequest request,Model model){
		String url = "manager/sysmanage/role_update";
		try {
			System.out.println("rid--"+rid);
			SysRole sysRole = sysRoleService.selectByPrimaryKey(Long.parseLong(rid));
			model.addAttribute("sysRole", sysRole);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//修改角色信息
	@RequestMapping("manager_role_update")
	@ResponseBody
	public String roleUpdate(SysRole sysRole,HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功！";
		String statusCode = "200";
		try {
			int i = sysRoleService.updateByPrimaryKeySelective(sysRole);
			if (i==0) {
				message = "操作失败:请检查修改信息！";
				statusCode = "300";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			message = "操作失败！";
			statusCode = "300";
		}
		jsonObj.put("statusCode", statusCode);
		jsonObj.put("message", message);
		jsonObj.put("callbackType", "closeCurrent");
		return jsonObj.toJSONString();
	}
}
