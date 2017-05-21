package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Schedule;
import com.demo.struts.util.Pager;

public interface IScheduleDAO {

    public Pager findPagerByUsername(final String username, final int pageNo,
                                     final int pageSize);

    public Schedule findById(String id);

    public void insert(Schedule schedule);

    public void update(Schedule schedule);

    public void delete(String id);
}
