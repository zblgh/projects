package com.famework.myframedwz.domain.dao;

import java.util.List;

import com.famework.myframedwz.domain.model.SysAdminPermission;
import com.famework.myframedwz.domain.model.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long rid);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long rid);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> queryAllRolelistPage(SysRole sysRole);
    
    int batchDelRole(String[] ids);
}