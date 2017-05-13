package com.sanqing.bll;

import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class BWareHouse {
    private HibernateDao.Warehouse M_W = new HibernateDao.Warehouse();
    private HibernateDao.WarehouseDAO D_W = new HibernateDao.WarehouseDAO();

    public BWareHouse() {
    }

    public String WareHouseConvertToHTMLTable() {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao = D_W.findAll();
        Iterator Temp = MyDao.iterator();
        while (Temp.hasNext()) {
            HTML.append("<tr");
            if (CreateOnclick) {
                HTML.append(" onclick='clickTR(this)'");
            }
            if (CreateOnmouseOver) {
                HTML.append(" onmouseover='overTR(this)'");
            }
            HTML.append(">");
            try {
                this.M_W = (HibernateDao.Warehouse) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_W.getWareId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_W.getWareDesc());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_W.getWareAdrr());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_W.getValEmp());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_W.getFax());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_W.getPhone());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean WareHouseAdd(com.sanqing.struts.form.WarehouseForm WHF) {
        try {
            Transaction MyAction = D_W.getSession().beginTransaction();
            M_W.setFax(WHF.getFax());
            M_W.setPhone(WHF.getPhone());
            M_W.setValEmp(WHF.getVal_emp());
            M_W.setWareAdrr(WHF.getWare_adrr());
            M_W.setWareDesc(WHF.getWare_desc());
            M_W.setWareId(WHF.getWare_id());
            D_W.save(M_W);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean WareHouseEdit(com.sanqing.struts.form.WarehouseForm WHF) {
        try {
            Transaction MyAction = D_W.getSession().beginTransaction();
            M_W = D_W.findById(WHF.getWare_id());
            M_W.setFax(WHF.getFax());
            M_W.setPhone(WHF.getPhone());
            M_W.setValEmp(WHF.getVal_emp());
            M_W.setWareAdrr(WHF.getWare_adrr());
            M_W.setWareDesc(WHF.getWare_desc());
            D_W.save(M_W);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean WareHouseDelete(String ID) {
        try {
            Transaction MyAction = D_W.getSession().beginTransaction();
            M_W = D_W.findById(ID);
            D_W.delete(M_W);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }
}
