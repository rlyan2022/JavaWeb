package com.dao;

import com.actionForm.PublishingForm;
import com.core.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class PublishingDAO {
    private ConnDB conn = new ConnDB();

    //查询数据
    public Collection query(String strif) {
        PublishingForm pubForm = null;
        Collection pubColl = new ArrayList();
        String sql = "";
        if (strif != "all" && strif != null && strif != "") {
            sql = "select * from tb_publishing where " + strif + "";
        } else {
            sql = "select * from tb_publishing";
        }
        ResultSet rs = conn.executeQuery(sql);
        try {
            while (rs.next()) {
                pubForm = new PublishingForm();
                pubForm.setIsbn(rs.getString(1));
                pubForm.setPubname(rs.getString(2));
                pubColl.add(pubForm);
            }
        } catch (SQLException ex) {
        }
        conn.close();
        return pubColl;
    }

}
