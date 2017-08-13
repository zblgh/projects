package com.famework.myframedwz.domain.model;

import java.util.Date;

import com.famework.myframedwz.domain.common.Page;

public class SysRole {
    private Long rid;

    private String roleName;

    private Integer sortIndex;

    private String roleDescribe;
    
    private Date addTime;

    private Page page;
    
    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : "".equals(roleName)?null:roleName.trim();
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe == null ? null : "".equals(roleDescribe)?null:roleDescribe.trim();
    }
}