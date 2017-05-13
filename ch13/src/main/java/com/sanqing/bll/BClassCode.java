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
public class BClassCode {
    private HibernateDao.Classcode M_L = new HibernateDao.Classcode();
    private HibernateDao.ClasscodeDAO D_L = new HibernateDao.ClasscodeDAO();

    public BClassCode() {

    }

    public String ClasscodeConvertToHTMLTable() {
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
                this.M_L = (HibernateDao.Classcode) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_L.getProdId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getProdDesc());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean ClasscodeAdd(com.sanqing.struts.form.ClassCodeEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L.setProdId(LEF.getProd_id());
            M_L.setProdDesc(LEF.getProd_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ClasscodeEdit(com.sanqing.struts.form.ClassCodeEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(LEF.getProd_id());
            M_L.setProdId(LEF.getProd_id());
            M_L.setProdDesc(LEF.getProd_desc());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ClasscodeDelete(String ID) {
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
