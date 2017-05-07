package com.sanqing.po;

/**
 * SysUser entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SysUser implements java.io.Serializable {

    // Fields

    private Long usrId;
    private SysRole sysRole;
    private String usrName;
    private String usrPassword;
    private Integer usrFlag;

    // Constructors

    /**
     * default constructor
     */
    public SysUser() {
    }

    /**
     * minimal constructor
     */
    public SysUser(String usrName, String usrPassword, Integer usrFlag) {
        this.usrName = usrName;
        this.usrPassword = usrPassword;
        this.usrFlag = usrFlag;
    }

    /**
     * full constructor
     */
    public SysUser(SysRole sysRole, String usrName, String usrPassword,
                   Integer usrFlag) {
        this.sysRole = sysRole;
        this.usrName = usrName;
        this.usrPassword = usrPassword;
        this.usrFlag = usrFlag;
    }

    // Property accessors

    public Long getUsrId() {
        return this.usrId;
    }

    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    public SysRole getSysRole() {
        return this.sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public String getUsrName() {
        return this.usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrPassword() {
        return this.usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public Integer getUsrFlag() {
        return this.usrFlag;
    }

    public void setUsrFlag(Integer usrFlag) {
        this.usrFlag = usrFlag;
    }

}