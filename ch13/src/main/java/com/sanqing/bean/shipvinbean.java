package com.sanqing.bean;

import HibernateDao.Shipvin;
import org.apache.struts.util.LabelValueBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class shipvinbean {
    public shipvinbean() {

    }

    private Collection beanCollection;

    public Collection getBeanCollection() {

        HibernateDao.Shipvin M_Vendtype = new HibernateDao.Shipvin();
        HibernateDao.ShipvinDAO D_Vendtype = new HibernateDao.ShipvinDAO();

        List MyDao = D_Vendtype.findAll();
        ArrayList arrData = new ArrayList();
        Iterator Temp = MyDao.iterator();
        while (Temp.hasNext()) {
            M_Vendtype = (Shipvin) Temp.next();
            arrData.add(new LabelValueBean(M_Vendtype.getShipviaId() + "-" + M_Vendtype.getShipviaDesc(),
                    M_Vendtype.getShipviaId()));
        }
        this.beanCollection = arrData;
        return beanCollection;
    }
}
