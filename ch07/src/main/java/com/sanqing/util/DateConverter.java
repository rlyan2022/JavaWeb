package com.sanqing.util;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateConverter extends DefaultTypeConverter {
    private static final DateFormat[] ACCEPT_DATE_FORMATS = {
            new SimpleDateFormat("dd/MM/yyyy"),
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("yyyy/MM/dd")}; // 支持转换的日期格式

    @Override
    @SuppressWarnings("unchecked")
    public Object convertValue(Map context, Object value, Class toType) {
        if (toType == Date.class) { // 浏览器向服务器提交时，进行String to Date的转换
            Date date = null;
            String dateString = null;
            String[] params = (String[]) value;
            dateString = params[0]; // 获取日期的字符串
            for (DateFormat format : ACCEPT_DATE_FORMATS) {
                try {
                    date = format.parse(dateString);
                    //System.err.println("------------->>" + new SimpleDateFormat("yyyy-MM-dd").format(date));
                    return date; // 遍历日期支持格式，进行转换
                } catch (Exception e) {
                    continue;
                }
            }
            return null;
        } else if (toType == String.class) { // 服务器向浏览器输出时，进行Date to
            // String的类型转换
            Date date = (Date) value;
            return new SimpleDateFormat("yyyy-MM-dd").format(date);// 输出的格式是yyyy-MM-dd
        }
        return null;
    }

}
