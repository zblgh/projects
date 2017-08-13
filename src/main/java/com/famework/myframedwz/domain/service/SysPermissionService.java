/**
* 标题: SysPermissionMapper.java
* 包名： com.famework.myframedwz.domain.service
* 功能描述：TODO
* 作者： tech
* 创建时间： 2014年9月3日 上午11:32:37
* @version V1.0   
*/
package com.famework.myframedwz.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.famework.myframedwz.domain.dao.SysAdminPermissionMapper;
import com.famework.myframedwz.domain.dao.SysPermissionMapper;
import com.famework.myframedwz.domain.dao.SysRolePermissionMapper;
import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.model.SysAdminPermission;
import com.famework.myframedwz.domain.model.SysPermission;
import com.famework.myframedwz.domain.model.SysRolePermission;

/**
 * ------------------------------------------------------------------
 * 版权所有 Copyright (C) 2014 杭州热讯电子商务有限公司
 *
 * 项目名称：myframedwz
 *
 * 包名：com.famework.myframedwz.domain.service
 *
 * 类名称：SysPermissionMapper
 *
 * 文件名：SysPermissionMapper.java
 * 
 * 创建者： 张斌龙（zblvip123@163.com）
 * 
 * 创建时间：2014年9月3日-上午11:32:37
 * 
 * 描述：TODO
 *-------------------------------------------------------------------
 * 修改人：
 *
 * 修改时间：
 *
 * 修改备注：
 *-------------------------------------------------------------------
 */
@Service("sysPermissionService")
@Transactional
public class SysPermissionService {

	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	
	@Autowired
	private SysAdminPermissionMapper sysAdminPermissionMapper;
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	
	//加载用户角色权限和特定权限
	public List<SysPermission> userSysPermissions(SysAdminList userList){
		return sysPermissionMapper.userSysPermissionList(userList);
	}
	
	//加载所有权限
	public List<SysPermission> sysPermissions(){
		return sysPermissionMapper.sysPermissionList();
	}
	//根据用户账号查询已有特定权限
	public List<SysAdminPermission> queryUserPer(String cardid){
		return sysAdminPermissionMapper.queryUserPer(cardid);
	}
	//批量添加用户特定权限
	public int insertBatchUserPer(List<SysAdminPermission> listSp){
		return sysAdminPermissionMapper.insertBatchUserPer(listSp);
	}
	//删除原来用户的特定角色
	public int delUserPer(String cardid){
		return sysAdminPermissionMapper.delUserPer(cardid);
	}
	//根据角色id查询角色下的权限
	public List<SysRolePermission> queryRolePer(Long rid){
		return sysRolePermissionMapper.queryRolePer(rid);
	}
	//删除原来角色的权限
	public int delRolePer(Long rid){
		return sysRolePermissionMapper.delRolePer(rid);
	}
	//批量添加角色的权限
	public int insertBatchRolePer(List<SysRolePermission> listRp){
		return sysRolePermissionMapper.insertBatchRolePer(listRp);
	}
	//添加菜单
	public int insertMenu(SysPermission sysPermission){
		return sysPermissionMapper.insertMenu(sysPermission);
	}
	//根据pid查询菜单
	public SysPermission MenuForPid(String pid){
		return sysPermissionMapper.MenuForPid(pid);
	}
	//验证菜单是否存在
	public int checkMenuPid(String pid){
		return sysPermissionMapper.checkMenuPid(pid);
	}
	//修改菜单
	public int menuUpdateForPid(SysPermission sysPermission){
		return sysPermissionMapper.menuUpdateForPid(sysPermission);
	}
	//删除菜单
	public int menuDel(String pid){
		return sysPermissionMapper.menuDel(pid);
	}
	//批量删除菜单
	public int menuBatchDel(String[] pidArr){
		return sysPermissionMapper.menuBatchDel(pidArr);
	}
	//判断批量菜单是否被角色使用
	public int chkPidsForRole(String[] pidArr){
		return sysPermissionMapper.chkPidsForRole(pidArr);
	}
	//判断批量菜单是否被用户使用
	public int chkPidsForUser(String[] pidArr){
		return sysPermissionMapper.chkPidsForUser(pidArr);
	}
	//判断单个菜单是否被角色使用
	public int chkPidForRole(String pid){
		return sysPermissionMapper.chkPidForRole(pid);
	}
	//判断单个菜单是否被用户使用
	public int chkPidForUser(String pid){
		return sysPermissionMapper.chkPidForUser(pid);
	}
}
