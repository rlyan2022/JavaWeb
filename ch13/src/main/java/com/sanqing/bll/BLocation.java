/**
 *
 */
package com.sanqing.bll;

import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 */
public class BLocation {
    private HibernateDao.Location M_L = new HibernateDao.Location();
    private HibernateDao.LocationDAO D_L = new HibernateDao.LocationDAO();

    public BLocation() {

    }

    public String LocationConConvertToHTMLTable() {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao = D_L.findAll();
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
                this.M_L = (HibernateDao.Location) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_L.getLocaId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getLocaDesc());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean LocationAdd(com.sanqing.struts.form.LocationeditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L.setLocaId(LEF.getLoca_id());
            M_L.setLocaDesc(LEF.getLoca_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean LocationEdit(com.sanqing.struts.form.LocationeditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(LEF.getLoca_id());
            M_L.setLocaId(LEF.getLoca_id());
            M_L.setLocaDesc(LEF.getLoca_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean LocationDelete(String ID) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(ID);
            D_L.delete(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }
}
