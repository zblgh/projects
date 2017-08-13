package com.famework.myframedwz.domain.dao;

import java.util.List;
import java.util.Map;

import com.famework.myframedwz.domain.model.SysAdminList;

public interface SysAdminListMapper {
    int deleteByPrimaryKey(Long uid);

    int insert(SysAdminList record);

    int insertSelective(SysAdminList record);

    SysAdminList selectByPrimaryKey(String cardid);

    int updateByPrimaryKeySelective(SysAdminList record);

    int updateByPrimaryKey(SysAdminList record);
    
    SysAdminList checkUser(String cardid);
    
    int updateByPrimaryKeyMap(Map<String, Object> param);
    
    List<SysAdminList> queryAllUserlistPage(SysAdminList sysAdminList);
    
    int batchDelUser(String[] ids);
    
    int checkUserCard(String cardid);
}