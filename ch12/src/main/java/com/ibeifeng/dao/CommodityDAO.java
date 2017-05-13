package com.ibeifeng.dao;

import com.ibeifeng.po.Commodity;

import java.util.List;

public interface CommodityDAO {

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#save(com.ibeifeng.po.Commodity)
     */
    public abstract void save(Commodity transientInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#delete(com.ibeifeng.po.Commodity)
     */
    public abstract void delete(Commodity persistentInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findById(java.lang.Integer)
     */
    public abstract Commodity findById(java.lang.Integer id);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByExample(com.ibeifeng.po.Commodity)
     */
    public abstract List findByExample(Commodity instance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByProperty(java.lang.String, java.lang.Object)
     */
    public abstract List findByProperty(String propertyName, Object value);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByCommodityName(java.lang.Object)
     */
    public abstract List findByCommodityName(Object commodityName);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByPrice(java.lang.Object)
     */
    public abstract List findByPrice(Object price);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findByAgio(java.lang.Object)
     */
    public abstract List findByAgio(Object agio);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#findAll()
     */
    public abstract List findAll();

    public abstract List findAll(int start, int limit);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#merge(com.ibeifeng.po.Commodity)
     */
    public abstract Commodity merge(Commodity detachedInstance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#attachDirty(com.ibeifeng.po.Commodity)
     */
    public abstract void attachDirty(Commodity instance);

    /* (non-Javadoc)
     * @see com.ibeifeng.dao.CommodityDAO#attachClean(com.ibeifeng.po.Commodity)
     */
    public abstract void attachClean(Commodity instance);

}