

package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DinDao {

    // inset 插入用户所选择航班的信息
    public int inset(Connection connection, Sched sch, int dal, String id) {
        int value = -1;
        int var = -1;
        int i = 0;
        PreparedStatement statement = null;
        PreparedStatement stat = null;
        PreparedStatement state = null;
        //查询数据库中是否有该航班记录
        String sql2 = "select Hao,Piao from din where Hao=? and id=?";
        // 插入用户所选择航班的信息
        String sql = "insert into din(Id,Hao,Qifei,Mudi,Rqi,Jiage,Piao) value(?,?,?,?,?,?,?)";
        //  根据用户名和航班号修改票数
        String sql3 = "update din set Piao=? where Hao=? and id=?";
        ResultSet result = null;

        try {
            // 进行数据库查询
            stat = connection.prepareStatement(sql2);
            stat.setString(1, sch.getHao());
            stat.setString(2, id);
            result = stat.executeQuery();
            //判断是否有该航班号的记录
            while (result.next()) {
                // 判断是否有该航班记录,如果有,var=1;

                var = 1;

                i = result.getInt("Piao");
            }
            if (var == -1) {
                // 如果var=-1则表示没有改航班的记录,则将用户所订航班信息插入数据库中
                statement = connection.prepareStatement(sql);
                statement.setString(1, id);
                statement.setString(2, sch.getHao());
                statement.setString(3, sch.getQifei());
                statement.setString(4, sch.getMudi());
                statement.setString(5, sch.getRqi());
                statement.setInt(6, sch.getJiage());
                statement.setInt(7, dal);
                value = statement.executeUpdate();
                return value;
            }
            if (var == 1) {
                //如果var=1则表示有改航班记录,得到记录中的票数+当此用户所订票数
                // 进行数据库的修改
                int j = i + dal;
                state = connection.prepareStatement(sql3);
                state.setInt(1, j);
                state.setString(2, sch.getHao());
                state.setString(3, id);
                value = state.executeUpdate();
                return value;
            }

        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ee) {
            }
        }
        return value;
    }

    // 修改管理员所制定改航班的票数
    public int update(Connection connection, Sched sch, int dal) {
        int wat = -1;
        int i = 0;
        int j = 0;
        PreparedStatement statement = null;
        PreparedStatement state = null;
        // 获得航班号 , 查询该航班的票数
        String sql = "select Piaosu from sch where Hao=?";
        // 修改该航班的票数
        String sql2 = "update sch set Piaosu=? where Hao=?";
        ResultSet resultset = null;
        try {
            //查询操作
            statement = connection.prepareStatement(sql);
            statement.setString(1, sch.getHao());
            resultset = statement.executeQuery();
            if (resultset.next()) {
                // 获得航班票数
                i = resultset.getInt("Piaosu");
            }
            // 航班票数减用户所订购票数 得到剩余票数
            j = i - dal;
            // 修改该航班的可定票数
            state = connection.prepareStatement(sql2);

            state.setInt(1, j);
            state.setString(2, sch.getHao());
            wat = state.executeUpdate();
            return wat;
        } catch (SQLException e) {
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {

            }
        }
        return wat;
    }

    //check 获得航班号 进行查询航班号信息
    public Sched check(Connection connection, String str) {
        Sched sch = new Sched();
        String sql = "select Hao,Rqi, Qifei,Mudi,Jiage,Piaosu from sch where Hao=? ";
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, str);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                sch.setHao(resultset.getString("Hao"));
                sch.setRqi(resultset.getString("Rqi"));
                sch.setQifei(resultset.getString("Qifei"));
                sch.setMudi(resultset.getString("Mudi"));
                sch.setJiage(resultset.getInt("Jiage"));
                sch.setPiaosu(resultset.getInt("Piaosu"));
            }
            return sch;
        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
                if (resultset != null) resultset.close();
            } catch (SQLException ee) {

            }
        }
        return sch;
    }

    // quest 根据用户名来获得该用户所订购的所有航班
    public ArrayList quest(Connection connection, String id) {
        ArrayList ary = new ArrayList();
        // 查询用户订票信息
        String sql = "select * from din where Id=?";
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                Sched sch = new Sched();
                sch.setHao(resultset.getString("Hao"));
                sch.setQifei(resultset.getString("Qifei"));
                sch.setMudi(resultset.getString("Mudi"));
                sch.setRqi(resultset.getString("Rqi"));
                sch.setJiage(resultset.getInt("Jiage"));
                sch.setPiaosu(resultset.getInt("Piao"));
                //如果所订航班票数等于0 那么则不将该航班信息放入ArrayList动态数组中
                //在JSP页面中省略了票数为0的航班
                if (resultset.getInt("Piao") != 0) {
                    ary.add(sch);
                }

            }
            return ary;
        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
                if (resultset != null) resultset.close();
            } catch (SQLException ee) {

            }
        }
        return ary;
    }


    //examine 查询所用航班的航班号
    public ArrayList examine(Connection connection) {
        String sql = "select Hao from sch";
        ArrayList tes = new ArrayList();
        Statement statement = null;
        ResultSet result = null;
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            if (result.next()) {
                Anpai anpai = new Anpai();
                anpai.setHao(result.getString("Hao"));
                tes.add(anpai);
            }
            return tes;
        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ee) {
            }
        }
        return tes;
    }

    // reced 获得用户所要退票的航班信息
    public Sched reced(Connection connection, String hao, String id) {
        Sched sch = new Sched();
        String sql = "select Hao,Rqi, Qifei,Mudi,Jiage,Piao from din where Hao=?  and id=?";
        PreparedStatement statement = null;
        ResultSet resultset = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, hao);
            statement.setString(2, id);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                sch.setHao(resultset.getString("Hao"));
                sch.setRqi(resultset.getString("Rqi"));
                sch.setQifei(resultset.getString("Qifei"));
                sch.setMudi(resultset.getString("Mudi"));
                sch.setJiage(resultset.getInt("Jiage"));
                sch.setPiaosu(resultset.getInt("Piao"));
            }
            return sch;
        } catch (SQLException e) {

        } finally {
            try {
                if (statement != null) statement.close();
                if (resultset != null) resultset.close();
            } catch (SQLException ee) {

            }
        }
        return sch;
    }

    // amendReced 获得用户的id 和所选择的航班号 修改用户所要退的票数
    public int amendReced(Connection connection, Sched sch, int rec, String id) {
        int value = -1;
        PreparedStatement statement = null;
        String sql = "update din set Piao=? where Hao=? and id=?";

        int i = 0, j = 0;

        try {
            // 将用户原来的所订购的票数放入i中
            i = sch.getPiaosu();
            // 用i减去用户所要退的票数 得到的结果放入j中
            j = i - rec;
            statement = connection.prepareStatement(sql);
            statement.setInt(1, j);
            statement.setString(2, sch.getHao());
            statement.setString(3, id);
            value = statement.executeUpdate();
            return value;
        } catch (SQLException e) {
        } finally {
            try {

                if (statement != null) statement.close();
            } catch (SQLException e) {

            }
        }
        return value;
    }

    // Upreced 用户退票后 修改该航班的总票数
    public int Upreced(Connection connection, Sched sch, int dal) {
        int wat = -1;
        int i = 0;
        int j = 0;
        PreparedStatement statement = null;
        PreparedStatement state = null;
        //  先查询用户所要退票的该航班的票数
        String sql = "select Piaosu from sch where Hao=?";
        // 修改该航班的票数
        String sql2 = "update sch set Piaosu=? where Hao=?";
        ResultSet resultset = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, sch.getHao());
            resultset = statement.executeQuery();
            if (resultset.next()) {
                // 获得用户退票前该航班的票数
                i = resultset.getInt("Piaosu");
            }
            // 航班的票数加上用户所退的票数 得到退票后该航班的票数
            j = i + dal;

            state = connection.prepareStatement(sql2);

            state.setInt(1, j);
            state.setString(2, sch.getHao());
            wat = state.executeUpdate();
            return wat;
        } catch (SQLException e) {
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {

            }
        }
        return wat;
    }

    //获得客户端当前日期时间
    public Date getDate(Connection connection) {
        Statement statement = null;
        Date date = null;
        ResultSet result = null;
        // 获取数据库当前日期时间
        String sql = "select current_date() as newdate";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                date = result.getDate("newdate");
                return date;
            }
        } catch (SQLException e) {
        } finally {
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {

            }
        }
        return date;
    }

    // compareDate比较所服务器日期是否在所订购的航班日期前一天
    public boolean compareDate(String str) {
        // 获得服务器日期
        Calendar day = Calendar.getInstance();
        Date date = Date.valueOf(day.get(Calendar.YEAR) + "-" + (day.get(Calendar.MONTH) + 1) + "-" + (day.get(Calendar.DATE)));
        Date rqi = Date.valueOf(str);
        // 判断航班日期是否在服务器日期之后
        boolean i = rqi.after(date);
        return i;

    }

}
