

package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;


public class SchedDao {
    // inset 将Sched对象中的值插入到航班信息表中
    public int inset(Connection connection, Sched sch) {
        int value = -1;
        PreparedStatement statement = null;
        String sql = "insert into sch(Hao,Qifei,Mudi,Jiage,Piaosu) value(?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, sch.getHao());
            statement.setString(2, sch.getQifei());
            statement.setString(3, sch.getMudi());
            statement.setInt(4, sch.getJiage());
            statement.setInt(5, sch.getPiaosu());
            value = statement.executeUpdate();
            return value;
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ee) {
            }
        }
        return value;
    }

    // check 修改该航班号的日期时间
    public int check(Connection connection, Anpai anpai) {
        int value = -1;
        PreparedStatement statement = null;
        String sql = "update sch set Rqi=? where Hao=?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, anpai.getRqi());
            statement.setString(2, anpai.getHao());
            value = statement.executeUpdate();
            return value;
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ee) {
            }
        }
        return value;
    }

    // quest 获得所有航班的信息
    public ArrayList quest(Connection connection) {
        ArrayList array = new ArrayList();
        //查询所有定制航班的信息
        // 将查询出的值放入ArrayList动态数组中返回
        String sql = "select Hao,Qifei,Rqi,Mudi,Jiage,Piaosu from sch ";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Sched sch = new Sched();
                sch.setHao(resultset.getString("Hao"));
                sch.setRqi(resultset.getString("Rqi"));
                sch.setQifei(resultset.getString("Qifei"));
                sch.setMudi(resultset.getString("Mudi"));
                sch.setJiage(resultset.getInt("Jiage"));
                sch.setPiaosu(resultset.getInt("Piaosu"));
                array.add(sch);
            }
            return array;

        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
                if (resultset != null) resultset.close();
            } catch (SQLException ee) {

            }
        }
        return array;

    }

    // descry 查询已经安排日期的航班
    // 将航班信息放入 ArrayList动态数组中
    public ArrayList descry(Connection connection) {
        ArrayList array = new ArrayList();
        String sql = "select Hao,Qifei,Rqi,Mudi,Jiage,Piaosu from sch where Rqi is not null";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                Sched sch = new Sched();
                // 获得服务器当前日期
                Calendar day = Calendar.getInstance();
                Date date = Date.valueOf(day.get(Calendar.YEAR) + "-" + (day.get(Calendar.MONTH) + 1) + "-" + day.get(Calendar.DATE));
                Date rqi = Date.valueOf(resultset.getString("Rqi"));
                // 比较航班日期和当前日期
                boolean i = rqi.after(date);
                // 如果航班日期在当前服务器日期之后 将查询处的值放入Sched对象中
                if (i == true) {
                    sch.setHao(resultset.getString("Hao"));
                    sch.setRqi(resultset.getString("Rqi"));
                    sch.setQifei(resultset.getString("Qifei"));
                    sch.setMudi(resultset.getString("Mudi"));
                    sch.setJiage(resultset.getInt("Jiage"));
                    sch.setPiaosu(resultset.getInt("Piaosu"));
                    array.add(sch);
                }
                //如果航班日期在当前服务器日期之后 则不将查询的值放入Sched对象中
                else {
                }


            }
            return array;

        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
                if (resultset != null) resultset.close();
            } catch (SQLException ee) {

            }
        }
        return array;

    }


}
