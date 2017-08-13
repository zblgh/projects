package com.famework.myframedwz.domain.model;

public class SysAdminPermission {
    private Long upid;

    private Long rid;

    private Byte permissionSource;

    private Byte isEnabled;

    private String pid;

    private String cardid;

    public Long getUpid() {
        return upid;
    }

    public void setUpid(Long upid) {
        this.upid = upid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Byte getPermissionSource() {
        return permissionSource;
    }

    public void setPermissionSource(Byte permissionSource) {
        this.permissionSource = permissionSource;
    }

    public Byte getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Byte isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid == null ? null : cardid.trim();
    }
}