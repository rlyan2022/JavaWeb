package com.sanqing.po;

public class SysRoleRight implements java.io.Serializable {

    // Fields

    private Integer RRId;
    private SysRole sysRole;
    private SysRight sysRight;

    // Constructors

    /**
     * default constructor
     */
    public SysRoleRight() {
    }

    /**
     * full constructor
     */
    public SysRoleRight(SysRole sysRole, SysRight sysRight) {
        this.sysRole = sysRole;
        this.sysRight = sysRight;
    }

    // Property accessors

    public Integer getRRId() {
        return this.RRId;
    }

    public void setRRId(Integer RRId) {
        this.RRId = RRId;
    }

    public SysRole getSysRole() {
        return this.sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public SysRight getSysRight() {
        return this.sysRight;
    }

    public void setSysRight(SysRight sysRight) {
        this.sysRight = sysRight;
    }

}