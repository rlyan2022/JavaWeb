package com.demo.hibernate.dao;

import com.demo.hibernate.beans.Address;
import com.demo.struts.util.Pager;

import java.util.List;

public interface IAddressDAO {

    public List findAllByUsername(final String username);

    public Pager findPagerByUsername(final String username);

    public Pager findPagerByUsername(final String username, final int pageNo,
                                     final int pageSize);

    public Address findById(String id);

    public boolean isExist(final String username, final String name);

    public void insert(Address address);

    public void update(Address address);

    public void delete(String id);
}
