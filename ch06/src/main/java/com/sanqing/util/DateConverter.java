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

    @SuppressWarnings("unchecked")
    public Object convertValue(Map context, Object value, Class toType) {
        if (toType == Date.class) { //进行String到Date的转换
            Date date = null;
            String dateString = null;
            String[] params = (String[]) value;    //获得参数列表
            dateString = params[0];            //获取日期的字符串
            for (DateFormat format : ACCEPT_DATE_FORMATS) {
                try {
                    date = format.parse(dateString);//对字符串进行转换
                    return date;                    //返回Date类型日期
                } catch (Exception e) {
                    continue;
                }
            }
            return null;
        } else if (toType == String.class) { //进行Date到String的转换
            Date date = (Date) value;//强制类型转换
            return new SimpleDateFormat("yyyy-MM-dd").format(date);//返回String类型日期
        }
        return null;
    }
}
