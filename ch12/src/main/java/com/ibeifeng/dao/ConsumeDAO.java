package com.ibeifeng.dao;

import com.ibeifeng.po.Consume;

import java.util.List;

public interface ConsumeDAO {

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#save(com.ibeifeng.po.Consume)
     */
    public abstract void save(Consume transientInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#delete(com.ibeifeng.po.Consume)
     */
    public abstract void delete(Consume persistentInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findById(java.lang.Integer)
     */
    public abstract Consume findById(java.lang.Integer id);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByExample(com.ibeifeng.po.Consume)
     */
    public abstract List findByExample(Consume instance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByProperty(java.lang.String, java.lang.Object)
     */
    public abstract List findByProperty(String propertyName, Object value);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByName(java.lang.Object)
     */
    public abstract List findByName(Object name);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByCommodityName(java.lang.Object)
     */
    public abstract List findByCommodityName(Object commodityName);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByPrice(java.lang.Object)
     */
    public abstract List findByPrice(Object price);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findByPracticePrice(java.lang.Object)
     */
    public abstract List findByPracticePrice(Object practicePrice);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#findAll()
     */
    public abstract List findAll();

    public abstract List findAll(int start, int limit);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#merge(com.ibeifeng.po.Consume)
     */
    public abstract Consume merge(Consume detachedInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#attachDirty(com.ibeifeng.po.Consume)
     */
    public abstract void attachDirty(Consume instance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.ConsumeDAO#attachClean(com.ibeifeng.po.Consume)
     */
    public abstract void attachClean(Consume instance);

}