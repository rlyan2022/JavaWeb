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
public class BAction {
    private String AllGroup = "所有操作组";

    private HibernateDao.Actionmaster M_L = new HibernateDao.Actionmaster();
    private HibernateDao.ActionmasterDAO D_L = new HibernateDao.ActionmasterDAO();

    public BAction() {

    }

    public String ActionmasterConvertToHTMLTable(String GroupName) {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao;
        if (GroupName.equals(AllGroup) || GroupName.equals("")) {
            MyDao = D_L.findAll();
        } else {
            MyDao = D_L.findByActionGroup(GroupName);
        }
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
                this.M_L = (HibernateDao.Actionmaster) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_L.getActionId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getActionDesc());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getActionGroup());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public String ActionmasterGetAllGroup() {
        StringBuilder HTML = new StringBuilder("");
        List MyDao = D_L.findGroup();
        Iterator Temp = MyDao.iterator();

        HTML.append("<tr");
        HTML.append(" onclick='clickTRGroup(this)'");
        HTML.append(">");
        HTML.append("<td>");
        HTML.append(AllGroup);
        HTML.append("</td>");
        HTML.append("</tr>");

        while (Temp.hasNext()) {
            HTML.append("<tr");
            HTML.append(" onclick='clickTRGroup(this)'");
            HTML.append(">");
            try {

                HTML.append("<td>");
                HTML.append(Temp.next().toString());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        return HTML.toString();
    }

    public boolean ActionmasterAdd(com.sanqing.struts.form.ActionEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L.setActionId(LEF.getAction_id());
            M_L.setActionDesc(LEF.getAction_desc());
            M_L.setActionGroup(LEF.getAction_group());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ActionmasterEdit(com.sanqing.struts.form.ActionEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(LEF.getAction_id());
            M_L.setActionId(LEF.getAction_id());
            M_L.setActionDesc(LEF.getAction_desc());
            M_L.setActionGroup(LEF.getAction_group());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ActionmasterDelete(String ID) {
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
