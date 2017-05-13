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
public class BRoleMaster {
    private HibernateDao.Rolemaster M_L = new HibernateDao.Rolemaster();
    private HibernateDao.RolemasterDAO D_L = new HibernateDao.RolemasterDAO();

    public BRoleMaster() {

    }

    public String RoleMasterConvertToHTMLTable() {
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
                this.M_L = (HibernateDao.Rolemaster) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_L.getRoleId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_L.getRoleName());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public String RoleMasterConvertToHTMLTableName() {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao = D_L.findAll();
        Iterator Temp = MyDao.iterator();
        while (Temp.hasNext()) {
            try {
                this.M_L = (HibernateDao.Rolemaster) Temp.next();
                HTML.append("<tr");
                if (CreateOnclick) {
                    HTML.append(" onclick=\"clickTRRole('" + this.M_L.getRoleId() + "',this)\"");
                }
                if (CreateOnmouseOver) {
                    HTML.append(" onmouseover='overTR(this)'");
                }
                HTML.append(">");

                HTML.append("<td>");
                HTML.append(this.M_L.getRoleId() + "--");
                HTML.append(this.M_L.getRoleName());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean RoleMasterAdd(com.sanqing.struts.form.RoleEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L.setRoleId(LEF.getRole_id());
            M_L.setRoleName(LEF.getRole_name());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean RoleMasterEdit(com.sanqing.struts.form.RoleEditorForm LEF) {
        try {
            Transaction MyAction = D_L.getSession().beginTransaction();
            M_L = D_L.findById(LEF.getRole_id());
            M_L.setRoleId(LEF.getRole_id());
            M_L.setRoleName(LEF.getRole_name());
            D_L.save(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean RoleMasterDelete(String ID) {
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
