package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Meeting;
import com.demo.struts.util.Pager;

public interface IMeetingDAO {

    public Pager findPager(final int pageNo, final int pageSize);

    public Meeting findById(String id);

    public void insert(Meeting meeting);

    public void update(Meeting meeting);

    public void delete(String id);
}
