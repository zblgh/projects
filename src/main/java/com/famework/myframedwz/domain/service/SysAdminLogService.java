package com.famework.myframedwz.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.famework.myframedwz.domain.dao.SysAdminLogMapper;
import com.famework.myframedwz.domain.model.SysAdminLog;
/**
 * ------------------------------------------------------------------
 * 版权所有 Copyright (C) 2014 杭州热讯电子商务有限公司
 *
 *项目名称：wxpservice
 *
 *  包 名：com.famework.myframedwz.domain.service
 *
 * 类名称：SysAdminLogService
 *
 * 类描述：用户日志处理
 *
 * 文件名：SysAdminLogService.java
 * 
 * 创建者： 张斌龙（zblvip123@163.com）
 * 
 * 创建时间：2014年11月17日-下午3:21:37
 * 
 * 描述：TODO
 *-------------------------------------------------------------------
 *修改人：
 *
 *修改时间：
 *
 *修改备注：
 *-------------------------------------------------------------------
 */
@Service("sysAdminLogService")
@Transactional
public class SysAdminLogService {

	@Autowired
	private SysAdminLogMapper sysAdminLogMapper;
	
	//添加用户日志
	public int addAdminLog(SysAdminLog sysAdminLog){
		return sysAdminLogMapper.insert(sysAdminLog);
	}
}
