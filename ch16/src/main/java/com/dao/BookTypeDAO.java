package com.dao;

import com.actionForm.BookTypeForm;
import com.core.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class BookTypeDAO {
    private ConnDB conn = new ConnDB();

    // 查询数据
    public Collection query(String strif) {
        BookTypeForm bookTypeForm = null;
        Collection bookTypeColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from tb_bookType where " + strif + "";
        } else {
            sql = "select * from tb_bookType";
        }
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                bookTypeForm = new BookTypeForm();
                bookTypeForm.setId(Integer.valueOf(rs.getString(1)));
                bookTypeForm.setTypeName(rs.getString(2));
                bookTypeForm.setDays(rs.getInt(3));
                bookTypeColl.add(bookTypeForm);
                System.out.print(bookTypeForm.getTypeName());
            }
        } catch (SQLException ex) {
        }
        conn.close();
        return bookTypeColl;
    }

    // 用于修改的查询
    public BookTypeForm queryM(BookTypeForm bookTypeForm) {
        BookTypeForm bookTypeForm1 = null;
        String sql = "select * from tb_bookType where id="
                + bookTypeForm.getId() + "";
        System.out.println("修改时的SQL：" + sql);
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                bookTypeForm1 = new BookTypeForm();
                bookTypeForm1.setId(Integer.valueOf(rs.getString(1)));
                bookTypeForm1.setTypeName(rs.getString(2));
                bookTypeForm1.setDays(rs.getInt(3));
            }
        } catch (SQLException ex) {
        }
        conn.close();
        return bookTypeForm1;
    }

    // 添加数据图书类别信息
    public int insert(BookTypeForm bookTypeForm) {
        /*首先查询该图书类别名称是否已经存在*/
        String sql1 = "SELECT * FROM tb_bookType WHERE typename='"
                + bookTypeForm.getTypeName() + "'";
        ResultSet rs = conn.executeQuery(sql1);
        String sql = "";
        int falg = 0;
        try {
            if (rs.next()) {/*如果该图书类别名称存在,返回2*/
                falg = 2;
            } else {
                sql = "Insert into tb_bookType (typename,days) values('"
                        + bookTypeForm.getTypeName() + "',"
                        + bookTypeForm.getDays() + ")";
                falg = conn.executeUpdate(sql);
                System.out.println("添加图书类型的SQL：" + sql);
                conn.close();
            }
        } catch (SQLException ex) {
            falg = 0;
        }
        System.out.println("falg:" + falg);
        return falg;
    }

    // 修改数据
    public int update(BookTypeForm bookTypeForm) {
        String sql = "Update tb_bookType set typename='"
                + bookTypeForm.getTypeName() + "',days="
                + bookTypeForm.getDays() + " where id=" + bookTypeForm.getId()
                + "";
        int falg = conn.executeUpdate(sql);
        System.out.println("修改数据时的SQL：" + sql);
        conn.close();
        return falg;
    }

    // 删除数据
    public int delete(BookTypeForm bookTypeForm) {
        String sql_1 = "SELECT * FROM tb_bookinfo WHERE typeid="
                + bookTypeForm.getId() + "";
        ResultSet rs = conn.executeQuery(sql_1);
        int falg = 0;
        try {
            if (!rs.next()) {
                String sql = "Delete from tb_bookType where id="
                        + bookTypeForm.getId() + "";
                falg = conn.executeUpdate(sql);
                System.out.println("删除时的SQL：" + sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }
}
