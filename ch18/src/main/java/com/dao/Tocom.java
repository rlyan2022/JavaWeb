


package com.dao;


import java.io.UnsupportedEncodingException;

public class Tocom {
    public static String toCN(String value) {
        String cn = null;
        try {
            // 将传递进来的字符串转换成byte数组，并确定其编码
            byte[] b = value.getBytes("ISO-8859-1");
            // 用byte数组构造一个新的字符串，并指定其编码
            cn = new String(b, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        // 将转换成功的字符串返回
        return cn;
    }
}
