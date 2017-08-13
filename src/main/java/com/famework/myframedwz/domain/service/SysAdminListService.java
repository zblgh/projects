package com.famework.myframedwz.domain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.famework.myframedwz.domain.dao.SysAdminListMapper;
import com.famework.myframedwz.domain.dao.SysAdminPermissionMapper;
import com.famework.myframedwz.domain.model.SysAdminList;

@Service("sysAdminListService")
@Transactional
public class SysAdminListService {

	@Autowired
	private SysAdminListMapper sysAdminListMapper;
	
	@Autowired
	private SysAdminPermissionMapper sysAdminPermissionMapper;
	
	//添加用户信息
	public int insert(SysAdminList record) {
		return sysAdminListMapper.insert(record);
	}
	//验证用户信息
	public SysAdminList checkUser(String cardid) {
		return sysAdminListMapper.checkUser(cardid);
	}
	public int updateUserList(Map<String, Object> param){
		return sysAdminListMapper.updateByPrimaryKeyMap(param);
	}
	//查询所有的用户
	public List<SysAdminList> queryUserList(SysAdminList sysAdminList){
		return sysAdminListMapper.queryAllUserlistPage(sysAdminList);
	}
	//批量删除用户
	public int batchDel(String[] ids){
		return sysAdminListMapper.batchDelUser(ids);
	}
	//批量删除用户下的角色和权限
	public int userRoleAndPerDel(String[] ids){
		return sysAdminPermissionMapper.userRoleAndPerDel(ids);
	}
	//根据主键查询用户信息
	public SysAdminList selectByPrimaryKey(String cardid){
		return sysAdminListMapper.selectByPrimaryKey(cardid);
	}
	//修改用户信息
	public int updateByPrimaryKeySelective(SysAdminList sysAdminList){
		return sysAdminListMapper.updateByPrimaryKeySelective(sysAdminList);
	}
	//验证用户是否存在
	public int checkUserCard(String cardid){
		return sysAdminListMapper.checkUserCard(cardid);
	}
}
