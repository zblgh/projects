package com.famework.myframedwz.domain.model;

import com.famework.myframedwz.domain.common.Page;

public class SysPermission implements Comparable {
    private String pid;

    private String permissionName;

    private String parendid;

    private Short level;

    private Integer sortindex;

    private String pageUrl;

    private Byte isChildnode;

    private Byte isDelete;

    private Page page;
    
    public int compareTo(Object o)
	{
    	SysPermission s=(SysPermission)o;
	   return sortindex>s.sortindex?1:(sortindex==s.sortindex?0:-1);
	}
    
    public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getParendid() {
        return parendid;
    }

    public void setParendid(String parendid) {
        this.parendid = parendid;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Integer getSortindex() {
        return sortindex;
    }

    public void setSortindex(Integer sortindex) {
        this.sortindex = sortindex;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? "" : pageUrl.trim();
    }

    public Byte getIsChildnode() {
        return isChildnode;
    }

    public void setIsChildnode(Byte isChildnode) {
        this.isChildnode = isChildnode;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}