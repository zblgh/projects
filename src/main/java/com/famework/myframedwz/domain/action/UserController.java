package com.famework.myframedwz.domain.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.alibaba.fastjson.JSONObject;
import com.famework.myframedwz.domain.common.Page;
import com.famework.myframedwz.domain.common.scope.SessionScope;
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.service.SysAdminListService;
import com.famework.myframedwz.utils.DateUtil;


@Controller
@RequestMapping(produces = {"text/html;charset=UTF-8"})
public class UserController{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private SysAdminListService sysAdminListService;
	//查询所有的用户
	@RequestMapping("manager_user_list")
	public String userList(SysAdminList sysAdminList,HttpServletRequest request,Model model){
		String url = "manager/sysmanage/user_list";
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
			sysAdminList.setPage(page);
			List<SysAdminList> userList = sysAdminListService.queryUserList(sysAdminList);
			model.addAttribute("userList", userList);
			model.addAttribute("sysAdminList", sysAdminList);
			model.addAttribute("page", sysAdminList.getPage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//添加用户信息
	@RequestMapping("manager_user_add")
	@ResponseBody
	public String addUser(SysAdminList sysAdminList,HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功！";
		String statusCode = "200";
		Date addTime = new Date();//更新时间
		try {
			String birthday = request.getParameter("bday");
			String pwd = request.getParameter("pwd");
			sysAdminList.setBirthday(DateUtil.toDate(birthday, "yyyy-MM-dd"));
			sysAdminList.setPwd(DigestUtils.md5Hex(pwd));
			sysAdminList.setAddTime(addTime);
			sysAdminList.setIsEnabledTime(addTime);
			sysAdminList.setUpdateTime(addTime);
			int i = sysAdminListService.insert(sysAdminList);
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
	//批量删除用户
	@RequestMapping("manager_user_batchdel")
	@ResponseBody
	public String batchDelUser(String ids,HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功！";
		String statusCode = "200";
		try {
			System.out.println("ids--"+ids);
			String[] idsArr = ids.split(",");
			userRoleAndPerDel(idsArr);
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
	public void userRoleAndPerDel(String[] idsArr) throws Exception{
		try {
			sysAdminListService.batchDel(idsArr);//批量删除用户
			sysAdminListService.userRoleAndPerDel(idsArr);//批量删除用户下的角色和权限
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw e;
		}
	}
	//根据主键查询用户信息
	@RequestMapping("manager_query_userforid")
	public String queryUserForId(String cardid,HttpSession session, HttpServletRequest request,Model model){
		String url = "manager/sysmanage/user_update";
		try {
			System.out.println("cardid--"+cardid);
			SysAdminList sysAdminList = sysAdminListService.selectByPrimaryKey(cardid);
			model.addAttribute("sysAdminList", sysAdminList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtils.getFullStackTrace(e));
			url = "base/error";
		}
		return url;
	}
	//修改用户信息
	@RequestMapping("manager_user_update")
	@ResponseBody
	public String userUpdate(SysAdminList sysAdminList,HttpSession session, HttpServletRequest request,Model model){
		JSONObject jsonObj = new JSONObject();
		String message = "操作成功！";
		String statusCode = "200";
		Date addTime = new Date();//更新时间
		try {
			String birthday = request.getParameter("bday");
			String pwd = request.getParameter("pwd");
			sysAdminList.setBirthday(DateUtil.toDate(birthday, "yyyy-MM-dd"));
			sysAdminList.setPwd(DigestUtils.md5Hex(pwd));
			sysAdminList.setIsEnabledTime(addTime);
			sysAdminList.setUpdateTime(addTime);
			int i = sysAdminListService.updateByPrimaryKeySelective(sysAdminList);
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
	//验证用户是否存在
	@RequestMapping("manager_user_chk")
	@ResponseBody
	public String chkUser(String cardid,HttpSession session, HttpServletRequest request,Model model){
		String flag = "0";//状态，0：不存在；1：已存在；2:验证出错
		try {
			int count = sysAdminListService.checkUserCard(cardid);
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
}
