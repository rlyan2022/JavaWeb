/**
 *
 */
package com.sanqing.bll;

import HibernateDao.Vendermaster;
import HibernateDao.Vendtype;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;


public class B_vender {
    HibernateDao.Vendtype M_Vendtype = new HibernateDao.Vendtype();
    HibernateDao.VendtypeDAO D_Vendtype = new HibernateDao.VendtypeDAO();

    HibernateDao.Vendermaster M_VendMaster = new HibernateDao.Vendermaster();
    HibernateDao.VendermasterDAO D_VendMaster = new HibernateDao.VendermasterDAO();

    /**
     *
     *
     */
    public B_vender() {

    }

    public String M_Vendtype_ConvertToHTMLTable() {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao = D_Vendtype.findAll();
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
                this.M_Vendtype = (Vendtype) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_Vendtype.getVendtypeId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_Vendtype.getVendtypeDesc());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public boolean M_Vendtype_Add(Vendtype MyType) {
        try {
            Transaction MyAction = D_Vendtype.getSession().beginTransaction();
            D_Vendtype.save(MyType);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }


    public boolean M_Vendtype_Edit(String ID, String Name) {
        try {
            Transaction MyAction = D_Vendtype.getSession().beginTransaction();
            Vendtype MyType = D_Vendtype.findById(ID);
            MyType.setVendtypeDesc(Name);
            D_Vendtype.save(MyType);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }


    public boolean M_Vendtype_Delete(String VendTypeID) {
        try {
            Transaction MyAction = D_Vendtype.getSession().beginTransaction();
            Vendtype MyType = D_Vendtype.findById(VendTypeID);
            D_Vendtype.delete(MyType);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    /**
     * 添加供应商
     *
     * @param Model
     * @return
     */
    public boolean M_Vend_Add(Vendermaster Model) {
        try {
            Transaction MyAction = D_VendMaster.getSession().beginTransaction();
            D_VendMaster.save(Model);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    /**
     * 修改供应商
     *
     * @param Model
     * @return
     */
    public boolean M_Vend_Edit(Vendermaster Model) {
        try {
            Transaction MyAction = D_VendMaster.getSession().beginTransaction();
            Vendermaster Temp = D_VendMaster.findById(Model.getVendId());
            Temp.setContMan(Model.getContMan());
            Temp.setShipvin(Model.getShipvin());
            Temp.setTradeAmount(Model.getTradeAmount());
            Temp.setVendAddr(Model.getVendAddr());
            Temp.setVendCity(Model.getVendCity());
            Temp.setVendDesc(Model.getVendDesc());
            Temp.setVendEmail(Model.getVendEmail());
            Temp.setVendId(Model.getVendId());
            Temp.setVendNati(Model.getVendNati());
            Temp.setVendPhone(Model.getVendPhone());
            Temp.setVendPost(Model.getVendPost());
            Temp.setVendtype(Model.getVendtype());
            D_VendMaster.save(Temp);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    /**
     * 删除记录
     *
     * @param VendID
     * @return
     */
    public boolean M_Vend_Delete(String VendID) {
        try {
            Transaction MyAction = D_VendMaster.getSession().beginTransaction();
            Vendermaster MyType = D_VendMaster.findById(VendID);
            D_VendMaster.delete(MyType);
            MyAction.commit();
        } catch (Exception eee) {
            return false;
        }
        return true;
    }

    public String M_Vend_ConvertToHTMLTable() {
        StringBuilder HTML = new StringBuilder("");
        boolean CreateOnclick = true;
        boolean CreateOnmouseOver = false;
        List MyDao = D_VendMaster.findAll();
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
                this.M_VendMaster = (Vendermaster) Temp.next();

                HTML.append("<td>");
                HTML.append(this.M_VendMaster.getVendId());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_VendMaster.getVendDesc());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_VendMaster.getVendCity());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_VendMaster.getVendPhone());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_VendMaster.getContMan());
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append(this.M_VendMaster.getVendtype().getVendtypeDesc() + "(" + this.M_VendMaster.getVendtype().getVendtypeId() + ")");
                HTML.append("</td>");

                HTML.append("<td>");
                HTML.append("￥" + this.M_VendMaster.getTradeAmount());
                HTML.append("</td>");

            } catch (Exception eee) {
                eee.printStackTrace();
            }

            HTML.append("</tr>");
        }
        String Temp1 = HTML.toString();
        return HTML.toString();
    }

    public String M_Vend_ConvertModelToJson(String VendID) {
        Vendermaster MyType = D_VendMaster.findById(VendID);
        com.sanqing.common.json MyJson = new com.sanqing.common.json();
        MyJson.Reset();
        MyJson.set_success(true);
        MyJson.AddItem("Vend_id", MyType.getVendId());
        MyJson.AddItem("Vend_desc", MyType.getVendDesc());
        MyJson.AddItem("Vend_addr", MyType.getVendAddr());
        MyJson.AddItem("Vend_city", MyType.getVendCity());
        MyJson.AddItem("Vend_nati", MyType.getVendNati());
        MyJson.AddItem("Vend_phone", MyType.getVendPhone());
        MyJson.AddItem("Vend_post", MyType.getVendPost());
        MyJson.AddItem("Vend_type", MyType.getVendtype().getVendtypeId());
        MyJson.AddItem("Vend_Shipvia", MyType.getShipvin().getShipviaId());
        MyJson.AddItem("Vend_Email", MyType.getVendEmail());
        MyJson.AddItem("TradeAmount", "" + MyType.getTradeAmount());
        MyJson.AddItem("Cont_man", MyType.getContMan());
        MyJson.ItemOk();
        return MyJson.toString();
    }
}
