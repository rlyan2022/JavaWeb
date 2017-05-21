package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Notice;
import com.demo.struts.util.Pager;

public interface INoticeDAO {

    public Pager findPager(final int pageNo, final int pageSize);

    public Notice findById(String id);

    public void insert(Notice notice);

    public void update(Notice notice);

    public void delete(String id);
}
