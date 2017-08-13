package com.famework.myframedwz.domain.dao;

import java.util.List;

import com.famework.myframedwz.domain.model.SysAdminPermission;
import com.famework.myframedwz.domain.model.SysPermission;
import com.famework.myframedwz.domain.model.SysRolePermission;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Long rpid);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Long rpid);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
    
    int batchDelRolePer(String[] idsArr);
    
    List<SysRolePermission> queryRolePer(Long rid);
    
    int delRolePer(Long rid);
    
    int insertBatchRolePer(List<SysRolePermission> listRp);
}