package com.sanqing.dao;

import com.sanqing.hb.HibernateSessionFactory;
import com.sanqing.po.Stipend;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author BWeiMing
 */
public class StipendDao {

    /**
     * 计算工资总数
     **/
    private Stipend getCountTotalize(Stipend e) {
        float count = 0;
        count = count + e.getBasic().longValue();
        count = count + e.getDuty().longValue();
        count = count + e.getEat().longValue();
        count = count + e.getHouse().longValue();
        count = count + e.getOther().longValue();
        count = count - e.getPunishment().longValue();
        count = count - e.getScot().longValue();
        e.setTotalize(new Float(count));
        return e;
    }

    public void addStipend(Stipend e) throws HibernateException {
        e = getCountTotalize(e);
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.save(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public void deleteStipend(Stipend e) throws HibernateException {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.delete(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }

    public Stipend loadStipend(long id) throws HibernateException {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Stipend e = (Stipend) session.get(Stipend.class, new Long(id));
        tx.commit();
        HibernateSessionFactory.closeSession();
        return e;
    }

    public List listStipend() throws HibernateException {
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("select e from Stipend as e order by e.granttime");
        List list = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
        return list;
    }

    public void updateStipend(Stipend stipend) throws HibernateException {
        Stipend e = this.loadStipend(stipend.getId().longValue());
        if (stipend.getBasic() != null) {
            e.setBasic(stipend.getBasic());
        }
        if (stipend.getDuty() != null) {
            e.setDuty(stipend.getDuty());
        }
        if (stipend.getEat() != null) {
            e.setEat(stipend.getEat());
        }
        if (stipend.getHouse() != null) {
            e.setHouse(stipend.getHouse());
        }
        if (stipend.getGranttime() != null) {
            e.setGranttime(stipend.getGranttime());
        }
        if (stipend.getName() != null) {
            e.setName(stipend.getName());
        }
        if (stipend.getOther() != null) {
            e.setOther(stipend.getOther());
        }
        if (stipend.getPunishment() != null) {
            e.setPunishment(stipend.getPunishment());
        }
        if (stipend.getScot() != null) {
            e.setScot(stipend.getScot());
        }
        e = getCountTotalize(e);
        Session session = HibernateSessionFactory.getSession();
        Transaction tx = session.beginTransaction();
        session.update(e);
        tx.commit();
        HibernateSessionFactory.closeSession();
    }


}
