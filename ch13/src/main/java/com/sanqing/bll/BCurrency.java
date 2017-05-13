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
public class BCurrency {
    private HibernateDao.Currency M_L = new HibernateDao.Currency();
    private HibernateDao.CurrencyDAO D_L = new HibernateDao.CurrencyDAO();

    public BCurrency() {

    }

    public String CurrencyConvertToHTMLTable() {
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
                this.M_L = (HibernateDao.Currency) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_L.getCurId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getCurDesc());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean CurrencyAdd(com.sanqing.struts.form.CurrencyEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L.setCurId(LEF.getCur_id());
            M_L.setCurDesc(LEF.getCur_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean CurrencyEdit(com.sanqing.struts.form.CurrencyEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(LEF.getCur_id());
            M_L.setCurId(LEF.getCur_id());
            M_L.setCurDesc(LEF.getCur_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean CurrencyDelete(String ID) {
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
