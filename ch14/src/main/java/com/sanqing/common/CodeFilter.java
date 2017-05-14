/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.common;


public class CodeFilter {

    public CodeFilter() {
    }

    public static String toHtml(String s) {
        if (s == null) {
            s = "";
            return s;
        } else {
            s = Replace(s.trim(), "&", "&amp;");
            s = Replace(s.trim(), "<", "&lt;");
            s = Replace(s.trim(), ">", "&gt;");
            s = Replace(s.trim(), "\t", "    ");
            s = Replace(s.trim(), "\r\n", "\n");
            s = Replace(s.trim(), "\n", "<br>");
            s = Replace(s.trim(), "  ", " &nbsp;");
            s = Replace(s.trim(), "'", "&#39;");
            s = Replace(s.trim(), "\\", "&#92;");
            return s;
        }
    }

    public static String toUbbHtml(String s) {
        if (s == null) {
            s = "";
            return s;
        } else {
            s = Replace(s, "\n", "<br>");
            s = Replace(s, "  ", " &nbsp;");
            s = Replace(s, "'", "&#39;");
            s = Replace(s, "\\", "&#92;");
            return s;
        }
    }

    public static String unHtml(String s) {
        s = Replace(s, "<br>", "\n");
        s = Replace(s, "&nbsp;", " ");
        return s;
    }

    public static String Replace(String source, String oldString, String newString) {
        StringBuffer output = new StringBuffer();
        int lengthOfsource = source.length();
        int lengthOfold = oldString.length();
        int posStart;
        int i;
        for (posStart = 0; (i = source.indexOf(oldString, posStart)) >= 0; posStart = i + lengthOfold) {
            output.append(source.substring(posStart, i));
            output.append(newString);
        }

        if (posStart < lengthOfsource)
            output.append(source.substring(posStart));
        return output.toString();
    }
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 218 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/