package com.sanqing.po;

import java.util.HashSet;
import java.util.Set;

/**
 * SysRight entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SysRight implements java.io.Serializable {

    // Fields

    private Long rightCode;
    private String rightText;
    private String rightUrl;
    private Set sysRoleRights = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public SysRight() {
    }

    /**
     * full constructor
     */
    public SysRight(String rightText, String rightUrl, Set sysRoleRights) {
        this.rightText = rightText;
        this.rightUrl = rightUrl;
        this.sysRoleRights = sysRoleRights;
    }

    // Property accessors

    public Long getRightCode() {
        return this.rightCode;
    }

    public void setRightCode(Long rightCode) {
        this.rightCode = rightCode;
    }

    public String getRightText() {
        return this.rightText;
    }

    public void setRightText(String rightText) {
        this.rightText = rightText;
    }

    public String getRightUrl() {
        return this.rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public Set getSysRoleRights() {
        return this.sysRoleRights;
    }

    public void setSysRoleRights(Set sysRoleRights) {
        this.sysRoleRights = sysRoleRights;
    }

}