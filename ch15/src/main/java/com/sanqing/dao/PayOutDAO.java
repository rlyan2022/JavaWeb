package com.sanqing.dao;

import com.sanqing.po.PayOut;

import java.util.List;

public interface PayOutDAO {

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#save(com.sanqing.po.PayOut)
     */
    /* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#save(com.sanqing.po.PayOut)
	 */
    public abstract void save(PayOut transientInstance);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#delete(com.sanqing.po.PayOut)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#delete(com.sanqing.po.PayOut)
	 */
    public abstract void delete(PayOut persistentInstance);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findById(java.lang.Integer)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findById(java.lang.Integer)
	 */
    public abstract PayOut findById(java.lang.Integer id);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findByExample(com.sanqing.po.PayOut)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByExample(com.sanqing.po.PayOut)
	 */
    public abstract List findByExample(PayOut instance);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    public abstract List findByProperty(String propertyName, Object value);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findByProperty(java.lang.String, java.lang.Object, int, int)
     */
    public abstract List findByProperty(String propertyName, Object value,
                                        int start, int limit);

    public abstract List findByProperties(String propertyName, Object value,
                                          String propertyName2, Object value2, int start, int limit);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findByPayOutName(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByPayOutName(java.lang.Object)
	 */
    public abstract List findByPayOutName(Object payOutName);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findByPayOutMoney(java.lang.Object)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findByPayOutMoney(java.lang.Object)
	 */
    public abstract List findByPayOutMoney(Object payOutMoney);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#findAll()
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#findAll()
	 */
    public abstract List findAll();

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#merge(com.sanqing.po.PayOut)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#merge(com.sanqing.po.PayOut)
	 */
    public abstract PayOut merge(PayOut detachedInstance);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#attachDirty(com.sanqing.po.PayOut)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachDirty(com.sanqing.po.PayOut)
	 */
    public abstract void attachDirty(PayOut instance);

    /* (non-Javadoc)
     * @see com.sanqing.dao.PayOutDAO#attachClean(com.sanqing.po.PayOut)
     */
	/* (non-Javadoc)
	 * @see com.sanqing.dao.PayOutDAO#attachClean(com.sanqing.po.PayOut)
	 */
    public abstract void attachClean(PayOut instance);

}