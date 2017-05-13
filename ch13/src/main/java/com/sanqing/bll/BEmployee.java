/**
 *
 */
package com.sanqing.bll;

import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Administrator
 */
public class BEmployee {
    /**
     * 用户数据访问
     */
    private HibernateDao.EmployeeDAO D_EP = new HibernateDao.EmployeeDAO();

    private SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");

    /**
     *
     *
     */
    public BEmployee() {

    }

    public String EmployeeConvertToHTMLTable() {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao = D_EP.findAll();
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
                HibernateDao.Employee M_L = (HibernateDao.Employee) Temp.next();

                HTML.append("<td>");
                HTML.append(M_L.getEmpId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(M_L.getEmpDesc());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(M_L.getFolk());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(M_L.getCollage());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(M_L.getSpeci());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(GetEmployeeRoles(M_L));
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(M_L.getWage());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        return HTML.toString();
    }

    /**
     * 取得用户所有的角色
     *
     * @return
     */
    public String GetEmployeeRoles(HibernateDao.Employee MyEmployee) {
        StringBuilder RolesText = new StringBuilder("");
        //如何不这样，可能会取不到最新的值
        List TempList = new HibernateDao.EmployeeroleDAO().findByProperty("employee", MyEmployee);
        Iterator Temp = TempList.iterator();
        while (Temp.hasNext()) {
            HibernateDao.Employeerole TempModel = (HibernateDao.Employeerole) Temp.next();
            if (RolesText.toString().equals("")) {
                RolesText.append(TempModel.getRolemaster().getRoleName());
            } else {
                RolesText.append("," + TempModel.getRolemaster().getRoleName());
            }
        }
        return (RolesText.toString().equals("")) ? "&nbsp;" : RolesText.toString();
    }

    public String EmployeeConvertToJSON(String EmployeeID) {
        HibernateDao.Employee M_L = D_EP.findById(EmployeeID);
        com.sanqing.common.json MyJson = new com.sanqing.common.json();
        MyJson.Reset();
        MyJson.set_success(true);
        MyJson.AddItem("emp_id", M_L.getEmpId());
        MyJson.AddItem("emp_desc", M_L.getEmpDesc());
        MyJson.AddItem("folk", M_L.getFolk());
        MyJson.AddItem("birth", this.dateFm.format(M_L.getBirth()));
        MyJson.AddItem("marri", M_L.getMarri());
        MyJson.AddItem("family", M_L.getFamily());
        MyJson.AddItem("collage", M_L.getCollage());
        MyJson.AddItem("speci", M_L.getSpeci());
        MyJson.AddItem("wage", "" + M_L.getWage());
        MyJson.AddItem("Roles", GetEmployeeRoles(M_L));
        MyJson.ItemOk();
        String ReturnString = MyJson.toString();
        return ReturnString;
    }

    public boolean ActionmasterAdd(com.sanqing.struts.form.UserEditorForm LEF) {
        try {
            Transaction MyAction = D_EP.getSession().beginTransaction();
            HibernateDao.Employee M_E = new HibernateDao.Employee();
            //M_E.setBirth((java.text.DateFormat.getInstance().parse(LEF.getBirth())));
            Date MyDate = this.dateFm.parse(LEF.getBirth());
            M_E.setBirth(MyDate);
            M_E.setCollage(LEF.getCollage());
            M_E.setEmpDesc(LEF.getEmp_desc());
            M_E.setEmpId(LEF.getEmp_id());
            M_E.setFamily(LEF.getFamily());
            M_E.setFolk(LEF.getFolk());
            M_E.setMarri(LEF.getMarri());
            //初始密码
            M_E.setPassword("flyfeeling");
            M_E.setSpeci(LEF.getSpeci());
            M_E.setWage(Long.parseLong(LEF.getWage()));
            this.D_EP.save(M_E);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ActionmasterEdit(com.sanqing.struts.form.UserEditorForm LEF) {
        try {
            Transaction MyAction = D_EP.getSession().beginTransaction();
            HibernateDao.Employee M_E = D_EP.findById(LEF.getEmp_id());
            Date MyDate = this.dateFm.parse(LEF.getBirth());
            M_E.setBirth(MyDate);
            M_E.setCollage(LEF.getCollage());
            M_E.setEmpDesc(LEF.getEmp_desc());
            //M_E.setEmpId(LEF.getEmp_id());
            M_E.setFamily(LEF.getFamily());
            M_E.setFolk(LEF.getFolk());
            M_E.setMarri(LEF.getMarri());
            //这里更改不涉及密码
            //M_E.setPassword("flyfeeling");
            M_E.setSpeci(LEF.getSpeci());
            M_E.setWage(Long.parseLong(LEF.getWage()));
            this.D_EP.save(M_E);
            MyAction.commit();
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public boolean ActionmasterDelete(String ID) {
        try {
            Transaction MyAction = D_EP.getSession().beginTransaction();
            HibernateDao.Employee M_L = D_EP.findById(ID);
            D_EP.delete(M_L);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }
}
