/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.servlet;

import javax.servlet.http.HttpSession;

public class SessionManager {

    public SessionManager() {
    }

    public void setSession(HttpSession session, String str, String username) {
        session.setAttribute(str, username);
    }

    public String getSession(HttpSession session, String str) {
        String user = null;
        user = (String) session.getAttribute(str);
        return user;
    }

    public static void removeSession(HttpSession session, String user) {
        session.removeAttribute(user);
    }
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 234 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/