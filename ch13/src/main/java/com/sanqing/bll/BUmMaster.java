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
public class BUmMaster {
    private HibernateDao.Ummaster M_L = new HibernateDao.Ummaster();
    private HibernateDao.UmmasterDAO D_L = new HibernateDao.UmmasterDAO();

    public BUmMaster() {

    }

    public String UmMasterConvertToHTMLTable() {
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
                this.M_L = (HibernateDao.Ummaster) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_L.getUmId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getUmDesc());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean UmMasterAdd(com.sanqing.struts.form.UmmasterForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L.setUmId(LEF.getUm_id());
            M_L.setUmDesc(LEF.getUm_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean UmMasterEdit(com.sanqing.struts.form.UmmasterForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(LEF.getUm_id());
            M_L.setUmId(LEF.getUm_id());
            M_L.setUmDesc(LEF.getUm_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean UmMasterDelete(String ID) {
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
