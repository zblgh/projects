package com.famework.myframedwz.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.famework.myframedwz.domain.dao.SysAdminPermissionMapper;
import com.famework.myframedwz.domain.dao.SysRoleMapper;
import com.famework.myframedwz.domain.dao.SysRolePermissionMapper;
import com.famework.myframedwz.domain.model.SysAdminPermission;
import com.famework.myframedwz.domain.model.SysRole;

@Service("sysRoleService")
@Transactional
public class SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysAdminPermissionMapper sysAdminPermissionMapper;
	
	@Autowired
	private SysRolePermissionMapper sysRolePermissionMapper;
	
	//查询所有角色
	public List<SysRole> queryAllRolelistPage(SysRole sysRole){
		return sysRoleMapper.queryAllRolelistPage(sysRole);
	}
	//添加角色
	public int insert(SysRole sysRole){
		return sysRoleMapper.insert(sysRole);
	}
	//根据角色id查询该角色信息
	public SysRole selectByPrimaryKey(Long rid){
		return sysRoleMapper.selectByPrimaryKey(rid);
	}
	//根据主键修改该角色信息
	public int updateByPrimaryKeySelective(SysRole sysRole){
		return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
	}
	//根据角色id查询用户是否使用这些角色
	public int chkAllUserRole(String[] roleids){
		return sysAdminPermissionMapper.chkAllUserRole(roleids);
	}
	//批量删除角色
	public int batchDelRole(String[] idsArr){
		return sysRoleMapper.batchDelRole(idsArr);
	}
	//批量删除角色下的权限
	public int batchDelRolePer(String[] idsArr){
		return sysRolePermissionMapper.batchDelRolePer(idsArr);
	}
	//根据用户账号查询已有角色
	public List<SysAdminPermission> queryUserRole(String cardid){
		return sysAdminPermissionMapper.queryUserRole(cardid);
	}
	//批量添加用户角色信息
	public int insertBatchUserRole(List<SysAdminPermission> listSp){
		return sysAdminPermissionMapper.insertBatchUserRole(listSp);
	}
	//删除原来用户的角色
	public int delUserRole(String cardid){
		return sysAdminPermissionMapper.delUserRole(cardid);
	}
}
