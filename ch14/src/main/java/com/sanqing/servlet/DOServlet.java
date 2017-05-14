/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.servlet;

import com.sanqing.common.CodeFilter;
import com.sanqing.common.Debug;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DOServlet {

    public DOServlet() {
    }

    public String requestStr(HttpServletRequest request, String varStr) {
        try {
            str = CodeFilter.toHtml(request.getParameter(varStr));
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("DOServlet RequestStr(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        return str;
    }

    public int requestInt(HttpServletRequest request, String varStr) {
        try {
            itr = Integer.parseInt(request.getParameter(varStr));
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("DOServlet RequestStr(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
        return itr;
    }

    public static void responseUrl(HttpServletResponse response, String url) {
        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            Debug.writeLog("DOServlet responseUrl(), Exception Occured ! Info :" + e.getLocalizedMessage());
        }
    }

    private String str;
    private int itr;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 765 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/