/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.common;

import com.sanqing.persistence.NEWSTable;
import com.sanqing.persistence.NEWSUsr;
import com.sanqing.persistence.NOTEGuest;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

public class Pagination {

    public Pagination() {
        strPage = null;
    }

    public String strPage(HttpServletRequest request, String page) {
        try {
            strPage = request.getParameter(page);
        } catch (Exception e) {
            System.out.println("delcolumn" + e.getMessage());
        }
        return strPage;
    }

    public int curPages(String strPage) {
        try {
            if (strPage == null) {
                curPages = 1;
            } else {
                curPages = Integer.parseInt(strPage);
                if (curPages < 1)
                    curPages = 1;
            }
        } catch (Exception e) {
            System.out.print("curPages");
        }
        return curPages;
    }

    public void setRows(int rows) {
        m_rows = rows;
    }

    public int getPages(int rowcounts) {
        int test = rowcounts % m_rows;
        if (test == 0)
            pages = rowcounts / m_rows;
        else
            pages = rowcounts / m_rows + 1;
        return pages;
    }

    public Iterator getPageSet(Iterator rs, int curPages) {
        int i;
        if (curPages == 1)
            return rs;
        i = 1;
        while (rs.hasNext()) {
            NEWSTable tableNews = (NEWSTable) rs.next();
            i++;
            if (i > (curPages - 1) * m_rows)
                break;
        }
        return rs;
    }

    public Iterator getPageSetNote(Iterator rs, int curPages) {
        int i;
        if (curPages == 1)
            return rs;
        i = 1;
        while (rs.hasNext()) {
            NOTEGuest tableNote = (NOTEGuest) rs.next();
            i++;
            if (i > (curPages - 1) * m_rows)
                break;
        }
        return rs;
    }

    public Iterator getPageSetUsr(Iterator rs, int curPages) {
        int i;
        if (curPages == 1)
            return rs;
        i = 1;
        while (rs.hasNext()) {
            NEWSUsr tableUsr = (NEWSUsr) rs.next();
            i++;
            if (i > (curPages - 1) * m_rows)
                break;
        }
        return rs;
    }

    private String strPage;
    private int curPages;
    private int m_rows;
    private int pages;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 203 ms
	Jad reported messages/errors:
Couldn't fully decompile method getPageSet
Couldn't resolve all exception handlers in method getPageSet
Couldn't fully decompile method getPageSetNote
Couldn't resolve all exception handlers in method getPageSetNote
Couldn't fully decompile method getPageSetUsr
Couldn't resolve all exception handlers in method getPageSetUsr
	Exit status: 0
	Caught exceptions:
*/