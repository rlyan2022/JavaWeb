package com.sanqing.dao;

import com.sanqing.po.BlogInfo;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BlogInfoDAOImpl extends HibernateDaoSupport implements BlogInfoDAO {

    public BlogInfo get(String username) {
        List list = this.getHibernateTemplate().find("select bloginfo from BlogInfo bloginfo where bloginfo.username=?", username);
        if (list.size() == 0) {
            return null;
        } else {
            return (BlogInfo) list.get(0);
        }
    }

    public void save(BlogInfo info) {
        this.getHibernateTemplate().saveOrUpdate(info);
    }

}
