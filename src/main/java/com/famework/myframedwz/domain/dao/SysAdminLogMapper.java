package com.famework.myframedwz.domain.dao;

import com.famework.myframedwz.domain.model.SysAdminLog;

public interface SysAdminLogMapper {
    int deleteByPrimaryKey(Long lid);

    int insert(SysAdminLog record);

    int insertSelective(SysAdminLog record);

    SysAdminLog selectByPrimaryKey(Long lid);

    int updateByPrimaryKeySelective(SysAdminLog record);

    int updateByPrimaryKey(SysAdminLog record);
}