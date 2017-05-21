package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Sms;
import com.demo.struts.util.Pager;

public interface ISmsDAO {

    public Pager findPagerByUsername(final String username, final int pageNo,
                                     final int pageSize);

    public Sms findById(String id);

    public void insert(Sms sms);

    public void update(Sms sms);

    public void delete(String id);
}
