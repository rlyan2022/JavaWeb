package com.sanqing.bll;

import HibernateDao.Itemgeneral;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class BItemGeneral {

    private HibernateDao.Itemgeneral M_ITEM = new HibernateDao.Itemgeneral();
    private HibernateDao.ItemgeneralDAO D_ITEM = new HibernateDao.ItemgeneralDAO();


    public BItemGeneral() {
    }

    /**
     * 将物料转换成HTML
     *
     * @return
     */
    public String ItemsConvertToHTMLTable() {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao = D_ITEM.findAll();
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
                this.M_ITEM = (Itemgeneral) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_ITEM.getItemId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_ITEM.getItemDesc());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_ITEM.getClasscode().getProdDesc());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_ITEM.getColor());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append("￥" + this.M_ITEM.getSalePic());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_ITEM.getTaxRate());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_ITEM.getStatus().equals("0") ? "正常" : "停用");
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }


    /**
     * @param VendID
     * @return
     */
    public String Item_ConvertModelToJson(String VendID) {
        Itemgeneral MyType = D_ITEM.findById(VendID);
        com.sanqing.common.json MyJson = new com.sanqing.common.json();
        MyJson.Reset();
        MyJson.set_success(true);
        MyJson.AddItem("color", MyType.getColor());
        MyJson.AddItem("prod_code", MyType.getClasscode().getProdId());
        MyJson.AddItem("item_desc", MyType.getItemDesc());
        MyJson.AddItem("item_id", MyType.getItemId());
        MyJson.AddItem("status", MyType.getStatus());
        MyJson.AddItem("po_emp_id", MyType.getClasscode().getProdId());
        MyJson.AddItem("sale_emp_id", MyType.getEmployeeBySaleEmpId().getEmpId());
        MyJson.AddItem("po_emp_id", MyType.getEmployeeByPoEmpId().getEmpId());
        MyJson.AddItem("sale_pic", String.valueOf(MyType.getSalePic()));
        MyJson.AddItem("whol_pic", String.valueOf(MyType.getWholPic()));
        MyJson.AddItem("um_id", MyType.getUmmaster().getUmId());
        MyJson.AddItem("tax_rate", String.valueOf(MyType.getTaxRate()));
        MyJson.ItemOk();
        return MyJson.toString();
    }


    /**
     * @param MyType
     * @return
     */
    public boolean Item_Add(Itemgeneral MyType) {
        try {
            Transaction MyAction = D_ITEM.getSession().beginTransaction();
            D_ITEM.save(MyType);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    /**
     * �޸�����
     *
     * @return
     */
    public boolean Item_Edit(Itemgeneral MyType) {
        try {
            Transaction MyAction = D_ITEM.getSession().beginTransaction();
            Itemgeneral Temp = D_ITEM.findById(MyType.getItemId());
            Temp.setClasscode(MyType.getClasscode());
            Temp.setColor(MyType.getColor());
            Temp.setEceipts(MyType.getEceipts());
            Temp.setEmployeeByPoEmpId(MyType.getEmployeeByPoEmpId());
            Temp.setEmployeeBySaleEmpId(MyType.getEmployeeBySaleEmpId());
            Temp.setItemDesc(MyType.getItemDesc());
            Temp.setSalePic(MyType.getSalePic());
            Temp.setStatus(MyType.getStatus());
            Temp.setTaxRate(MyType.getTaxRate());
            Temp.setUmmaster(MyType.getUmmaster());
            Temp.setWholPic(MyType.getWholPic());
            D_ITEM.save(Temp);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    /**
     * �޸�����
     *
     * @return
     */
    public boolean Item_Delete(String ID) {
        try {
            Transaction MyAction = D_ITEM.getSession().beginTransaction();
            Itemgeneral MyType = D_ITEM.findById(ID);
            D_ITEM.delete(MyType);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }
}
