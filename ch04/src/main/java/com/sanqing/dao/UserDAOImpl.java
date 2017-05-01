package com.sanqing.dao;

import com.sanqing.po.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {

    public void add(User user) {
        this.getHibernateTemplate().save(user);
    }

    public void delete(User user) {

    }

    public List queryAll() {
        return null;
    }

    public User queryByID(String username) {
        List list = this.getHibernateTemplate().find("select user from User user where user.username = '" + username + "'");

        if (list.size() == 0) {
            return null;
        } else {
            return (User) list.get(0);
        }
    }

    public void update(User user) {

    }

}
