/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.persistence;


public class NOTEAdmin {

    public NOTEAdmin() {
        adminName = null;
        adminPasswd = null;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminPasswd(String adminPasswd) {
        this.adminPasswd = adminPasswd;
    }

    public String getAdminPasswd() {
        return adminPasswd;
    }

    public String adminName;
    public String adminPasswd;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 202 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/