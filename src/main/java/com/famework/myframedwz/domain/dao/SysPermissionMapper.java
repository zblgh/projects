package com.famework.myframedwz.domain.dao;

import java.util.List;

import com.famework.myframedwz.domain.model.SysAdminList;
import com.famework.myframedwz.domain.model.SysPermission;

public interface SysPermissionMapper {
    int deleteByPrimaryKey(Long pid);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Long pid);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);
    
    List<SysPermission> userSysPermissionList(SysAdminList userList);
    
    List<SysPermission> sysPermissionList();
    
    int insertMenu(SysPermission sysPermission);
    
    SysPermission MenuForPid(String pid);
    
    int checkMenuPid(String pid);
    
    int menuUpdateForPid(SysPermission sysPermission);
        
    int menuDel(String pid);
    
    int menuBatchDel(String[] pidArr);
    
    int chkPidsForRole(String[] pidArr);
    
    int chkPidsForUser(String[] pidArr);
    
    int chkPidForRole(String pid);
    
    int chkPidForUser(String pid);
}