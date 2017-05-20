package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
    // insert方法 向数据库中插入用户信息
    public int insert(Connection connection, User user) {

        int value = -1;
        PreparedStatement statement = null;
        String sql = "insert into user(Username,Password,Name,Sex,Tel,Email) values(?,?,?,?,?,?)";
        try {

            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSex());
            statement.setString(5, user.getTel());
            statement.setString(6, user.getEmail());
            value = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ex) {
            }
        }
        return value;
    }

    //check方法 根据登录界面传输的用户名和密码与数据库进行比较
    // 如果有这用户名则返回真,每有责返回假
    public boolean check(Connection connection, String username, String password) {
        boolean value = false;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select Username from user where Username = ? and Password = ?";
        try {
            // 获得PreparedStatement对象，并填充
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                value = true;
            }
            return value;
        } catch (SQLException e) {
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
            }
        }
        return value;
    }

    // getUser方法  根据username 进行数据库查询操作
    // 将查询出的值放入User对象中 并返回该对象
    public User getUser(Connection connection, String username) {
        User user = new User();
        String sql = "select Username,Password,Name,Sex,Tel,Email from user where Username=?";
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            result = statement.executeQuery();
            while (result.next()) {
                user.setUsername(result.getString("Username"));
                user.setPassword(result.getString("Password"));
                user.setName(result.getString("Name"));
                user.setSex(result.getString("Sex"));
                user.setTel(result.getString("Tel"));
                user.setEmail(result.getString("Email"));
            }
            return user;
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) statement.close();
                if (result != null) result.close();
            } catch (SQLException e) {
            }
        }
        return user;
    }

    // Update 获得用户修改后的信息 进行数据库的修改操作
    public int Update(Connection connection, User user) {
        int value = -1;
        PreparedStatement statement = null;
        String sql = "update user set  Name = ?, Sex = ?, Tel = ?, Email = ? where Username = ?";

        try {
            // 获得PreparedStatement对象
            statement = connection.prepareStatement(sql);
            // 填充statement的参数

            statement.setString(1, user.getName());
            statement.setString(2, user.getSex());
            statement.setString(3, user.getTel());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getUsername());
            // 执行修改操作
            value = statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) statement.close();
                //if(connection != null) connection.close();
            } catch (SQLException e) {
            }
        }
        return value;
    }

    // UpdatePass  根据用户输入的密码 进行输入密码修改操作
    public int UpdatePass(Connection connection, String Password, String username) {
        int value = -1;
        PreparedStatement statement = null;
        String sql = "update user set Password = ? where Username = ?";

        try {
            // 获得PreparedStatement对象
            statement = connection.prepareStatement(sql);
            // 填充statement的参数
            statement.setString(1, Password);
            statement.setString(2, username);
            // 执行修改操作
            value = statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            try {
                if (statement != null) statement.close();
                //if(connection != null) connection.close();
            } catch (SQLException e) {
            }
        }
        return value;
    }

}