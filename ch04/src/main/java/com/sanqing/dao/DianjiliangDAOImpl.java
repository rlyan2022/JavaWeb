package com.sanqing.dao;

import com.sanqing.po.Dianjiliang;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DianjiliangDAOImpl extends HibernateDaoSupport implements
        DianjiliangDAO {

    public void addJilu(Dianjiliang dianjiliang) {
        this.getHibernateTemplate().save(dianjiliang);
    }

    public List queryByAId(final int AId, final String IP, final Date time) {
        return this.getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createQuery("select djl from Dianjiliang djl where djl.AId = ? and djl.ip = ? and djl.time = ?");
                //设置参数
                query.setParameter(0, AId);
                query.setParameter(1, IP);
                query.setParameter(2, time);
                return query.list();
            }

        });
    }

}
