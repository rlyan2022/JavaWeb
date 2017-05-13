package com.sanqing.common;

public class Sql_in {

    public static boolean sql_in_method(String str) {
        String sql_in_str = "'~and~exec~insert~select~delete~update~count~*~%~chr~mid~master~truncate~char~declare~;~or~-~+~,";
        String sql_in_str_stra[] = sql_in_str.split("~");
        for (int i = 0; i < sql_in_str_stra.length; i++) {
            if (str.indexOf(sql_in_str_stra[i]) >= 0) {
                return true;
            }
        }
        return false;
    }

}
