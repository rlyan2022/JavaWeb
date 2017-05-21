package com.demo.hibernate.dao;

import com.demo.hibernate.beans.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class UserDAO extends HibernateDaoSupport implements IUserDAO {

    public boolean isValid(final String username, final String password) {
        List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                List result = session.createCriteria(User.class).add(
                        Restrictions.eq("username", username)).add(
                        Restrictions.eq("password", password)).list();
                return result;
            }
        });
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExist(final String username) {
        List list = (List) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException {
                List result = session.createCriteria(User.class).add(
                        Restrictions.eq("username", username)).list();
                return result;
            }
        });
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void insertUser(User user) {
        getHibernateTemplate().saveOrUpdate(user);
    }

    public User getUser(String userid) {
        return (User) getHibernateTemplate().get(User.class,
                new Integer(userid));
    }

    public List getUsers() {
        return getHibernateTemplate().find("from User");
    }

    public void deleteUser(String userid) {
        Object p = getHibernateTemplate().load(User.class, new Integer(userid));
        getHibernateTemplate().delete(p);
    }
}
