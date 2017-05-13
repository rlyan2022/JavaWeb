package com.ibeifeng.dao;

import com.ibeifeng.po.User;

import java.util.List;

public interface UserDAO {

    public abstract void save(User transientInstance);

    public abstract void delete(User persistentInstance);

    public abstract User findById(java.lang.String id);

    public abstract List findByExample(User instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByPassword(Object password);

    public abstract List findByQuanxian(Object quanxian);

    public abstract List findAll();

    public abstract User merge(User detachedInstance);

    public abstract void attachDirty(User instance);

    public abstract void attachClean(User instance);

}