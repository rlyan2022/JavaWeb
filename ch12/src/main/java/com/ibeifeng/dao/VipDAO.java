package com.ibeifeng.dao;

import com.ibeifeng.po.Vip;

import java.util.List;

public interface VipDAO {

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#save(com.ibeifeng.po.Vip)
     */
    public abstract void save(Vip transientInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#delete(com.ibeifeng.po.Vip)
     */
    public abstract void delete(Vip persistentInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findById(java.lang.Integer)
     */
    public abstract Vip findById(java.lang.Integer id);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByExample(com.ibeifeng.po.Vip)
     */
    public abstract List findByExample(Vip instance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByProperty(java.lang.String, java.lang.Object)
     */
    public abstract List findByProperty(String propertyName, Object value);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByName(java.lang.Object)
     */
    public abstract List findByName(Object name);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByAge(java.lang.Object)
     */
    public abstract List findByAge(Object age);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findByProfession(java.lang.Object)
     */
    public abstract List findByProfession(Object profession);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#findAll()
     */
    public abstract List findAll();

    public abstract List findAll(int start, int limit);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#merge(com.ibeifeng.po.Vip)
     */
    public abstract Vip merge(Vip detachedInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#attachDirty(com.ibeifeng.po.Vip)
     */
    public abstract void attachDirty(Vip instance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.VipDAO#attachClean(com.ibeifeng.po.Vip)
     */
    public abstract void attachClean(Vip instance);

}