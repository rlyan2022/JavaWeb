/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.persistence;


public class NEWSAdmin {

    public NEWSAdmin() {
        userName = null;
        passWd = null;
        lastLogin = null;
        lastLoginIp = null;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPurview(int purview) {
        this.purview = purview;
    }

    public int getPurview() {
        return purview;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public String userName;
    public String passWd;
    public int purview;
    public String lastLogin;
    public String lastLoginIp;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 483 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/