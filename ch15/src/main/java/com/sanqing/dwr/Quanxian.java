package com.sanqing.dwr;

import javax.servlet.http.HttpSession;

public class Quanxian {
    public int getQuanxian(HttpSession session) {
        return (Integer) session.getAttribute("quanxian");
    }
}
