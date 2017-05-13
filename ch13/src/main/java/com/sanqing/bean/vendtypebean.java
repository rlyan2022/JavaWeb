package com.sanqing.bean;

import HibernateDao.Vendtype;
import org.apache.struts.util.LabelValueBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class vendtypebean {
    public vendtypebean() {

    }

    /**
     *
     */
    private Collection beanCollection;

    public Collection getBeanCollection() {

        HibernateDao.Vendtype M_Vendtype = new HibernateDao.Vendtype();
        HibernateDao.VendtypeDAO D_Vendtype = new HibernateDao.VendtypeDAO();

        List MyDao = D_Vendtype.findAll();
        ArrayList arrData = new ArrayList();
        Iterator Temp = MyDao.iterator();
        while (Temp.hasNext()) {
            M_Vendtype = (Vendtype) Temp.next();
            arrData.add(new LabelValueBean(M_Vendtype.getVendtypeId() + "-" + M_Vendtype.getVendtypeDesc(),
                    M_Vendtype.getVendtypeId()));
        }
        this.beanCollection = arrData;
        return beanCollection;
    }
}
