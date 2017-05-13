package com.sanqing.bll;

import org.hibernate.Transaction;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;

public class BRoleAction {
    private String AllGroup = "所有操作组";

    private HibernateDao.Roleaction M_RA = new HibernateDao.Roleaction();
    private HibernateDao.RoleactionDAO B_RA = new HibernateDao.RoleactionDAO();

    private HibernateDao.Actionmaster M_L = new HibernateDao.Actionmaster();
    private HibernateDao.ActionmasterDAO D_L = new HibernateDao.ActionmasterDAO();

    private HibernateDao.Rolemaster M_R = new HibernateDao.Rolemaster();
    private HibernateDao.RolemasterDAO D_R = new HibernateDao.RolemasterDAO();

    public String ActionmasterConvertToHTMLTableWithCheckBox(String GroupName, String RoleID) {

        this.M_R = this.D_R.findById(RoleID);

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
                HTML.append("<input type='checkbox' value='1' name='" + this.M_L.getActionId() + "' id='" + this.M_L.getActionId() + "' " + ((IsRoleInAction(this.M_L, this.M_R)) ? "checked='checked' " : "") + "/>");
                HTML.append("</td>");

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

    public boolean IsRoleInAction(HibernateDao.Actionmaster P_Action, HibernateDao.Rolemaster P_Role) {
        try {
            List TempList = this.B_RA.findByProperty("rolemaster", P_Role);
            Iterator Temp = TempList.iterator();
            while (Temp.hasNext()) {
                HibernateDao.Roleaction T_RA = (HibernateDao.Roleaction) Temp.next();
                if (T_RA.getActionmaster().getActionId().equals(P_Action.getActionId())) {
                    return true;
                }
            }
        } catch (Exception eee) {
            return false;
        }
        return false;
    }

    /*
     *
     */
    public boolean RoleActionAdd(HibernateDao.Roleaction P_RC) {
        try {
            Transaction MyAction = this.B_RA.getSession().beginTransaction();
            HibernateDao.Roleaction M_Temp = new HibernateDao.Roleaction();
            M_Temp.setActionmaster(P_RC.getActionmaster());
            M_Temp.setRolemaster(P_RC.getRolemaster());
            this.B_RA.save(M_Temp);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean RoleActionDelete(int ID) {
        try {
            Transaction MyAction = this.B_RA.getSession().beginTransaction();
            this.M_RA = this.B_RA.findById(ID);
            B_RA.delete(M_RA);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    //编辑roleaction
    public void EditRoleActions(HttpServletRequest MyRequest) {

        try {

            String GroupName = MyRequest.getParameter("group");
            String RoleID = MyRequest.getParameter("role");
            //get Role
            this.M_R = this.D_R.findById(RoleID);

            List MyDao;
            if (GroupName.equals(AllGroup) || GroupName.equals("")) {
                MyDao = D_L.findAll();
            } else {
                MyDao = D_L.findByActionGroup(GroupName);
            }
            Iterator Temp = MyDao.iterator();
            while (Temp.hasNext()) {

                this.M_L = (HibernateDao.Actionmaster) Temp.next();

                HibernateDao.Roleaction T_Model = new HibernateDao.Roleaction();
                T_Model.setActionmaster(this.M_L);
                T_Model.setRolemaster(this.M_R);

                List MyList = this.B_RA.findByExampleTrue(T_Model);

                if (MyRequest.getParameter(this.M_L.getActionId()) != null) {

                    String MyTempPara = MyRequest.getParameter(this.M_L.getActionId());

                    if (MyTempPara.equals("1")) {
                        if (MyList.size() <= 0) {
                            this.RoleActionAdd(T_Model);
                        }
                    }
                } else {
                    Iterator InnerIterator = MyList.iterator();
                    while (InnerIterator.hasNext()) {
                        this.M_RA = (HibernateDao.Roleaction) InnerIterator.next();
                        this.RoleActionDelete(this.M_RA.getRoleActionId());
                    }
                }
            }
        } catch (Exception eee) {
            eee.printStackTrace();
        }
    }
}
