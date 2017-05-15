package com.sanqing.dao;

import com.sanqing.po.User;

import java.util.List;

public interface UserDAO {

    public abstract void save(User transientInstance);

    public abstract void delete(User persistentInstance);

    public abstract User findById(java.lang.Integer id);

    public abstract List findByExample(User instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByUsername(Object username);

    public abstract List findByPassword(Object password);

    public abstract List findAll();

    public abstract List findAll(int start, int limit);

    public abstract User merge(User detachedInstance);

    public abstract void attachDirty(User instance);

    public abstract void attachClean(User instance);

}