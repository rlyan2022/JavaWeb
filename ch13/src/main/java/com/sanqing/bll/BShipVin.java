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
public class BShipVin {
    private HibernateDao.Shipvin M_L = new HibernateDao.Shipvin();
    private HibernateDao.ShipvinDAO D_L = new HibernateDao.ShipvinDAO();

    public BShipVin() {

    }

    public String ShipVinConvertToHTMLTable() {
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
                this.M_L = (HibernateDao.Shipvin) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_L.getShipviaId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getShipviaDesc());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean ShipVinAdd(com.sanqing.struts.form.ShipvinEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L.setShipviaId(LEF.getShipvia_id());
            M_L.setShipviaDesc(LEF.getShipvia_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ShipVinEdit(com.sanqing.struts.form.ShipvinEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(LEF.getShipvia_id());
            M_L.setShipviaId(LEF.getShipvia_id());
            M_L.setShipviaDesc(LEF.getShipvia_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ShipVinDelete(String ID) {
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
