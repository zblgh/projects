package com.famework.myframedwz.domain.dao;

import java.util.List;

import com.famework.myframedwz.domain.model.SysAdminPermission;

public interface SysAdminPermissionMapper {
    int deleteByPrimaryKey(Long upid);

    int insert(SysAdminPermission record);

    int insertSelective(SysAdminPermission record);

    SysAdminPermission selectByPrimaryKey(Long upid);

    int updateByPrimaryKeySelective(SysAdminPermission record);

    int updateByPrimaryKey(SysAdminPermission record);
    
    List<SysAdminPermission> queryUserRole(String cardid);
    
    int insertBatchUserRole(List<SysAdminPermission> listSp);
    
    int delUserRole(String cardid);
    
    List<SysAdminPermission> queryUserPer(String cardid);
    
    int insertBatchUserPer(List<SysAdminPermission> listSp);
    
    int delUserPer(String cardid);
    
    int chkAllUserRole(String[] roleids);
    
    int userRoleAndPerDel(String[] ids);
}