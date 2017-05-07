package com.sanqing.dao.impl;

import com.sanqing.dao.IStorageDAO;
import com.sanqing.po.Product;
import com.sanqing.po.Storage;
import com.sanqing.struts.form.StorageForm;
import com.sanqing.util.PageResult;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StorageDAO extends HibernateDaoSupport implements IStorageDAO {
    private static final Log log = LogFactory.getLog(StorageDAO.class);
    ProductDAO prodDao = null;

    public ProductDAO getProdDao() {
        return prodDao;
    }

    public void setProdDao(ProductDAO prodDao) {
        this.prodDao = prodDao;
    }

    public static Log getLog() {
        return log;
    }

    protected void initDao() {
        // do nothing
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");

        String prodName = (String) paramMap.get("prodName");
        String warehouse = (String) paramMap.get("stkWarehouse");
        List<Product> products = prodDao.findByProdName(prodName);
        // 根据产品名称查询出产品编号
        Long[] prodId = new Long[products.size()];
        for (int i = 0; i < prodId.length; i++) {
            prodId[i] = products.get(i).getProdId();
            System.out.println("产品编号为:" + prodId[i]);
        }
        System.out.println("第一条" + start);
        System.out.println("最后一条" + limit);
        try {
            Criteria c = getSession().createCriteria(Storage.class);
            if (prodId.length > 0) {
                c.add(Expression.in("product.prodId", prodId));
            }
            if (StringUtils.isNotEmpty(warehouse)) {
                c.add(Expression.like("stkWarehouse", "%" + warehouse + "%"));
            }

            // 总条数
            Projection entityProjection = ((CriteriaImpl) c).getProjection();
            c.setProjection(Projections.rowCount()).uniqueResult();
            int rowCount = ((Number) c.uniqueResult()).intValue();
            pgr.setRowCount(rowCount);
            c.setProjection(entityProjection);
            // 分页
            if (start != null) {
                c.setFirstResult(Integer.parseInt(start));
            }
            if (limit != null) {
                c.setMaxResults(Integer.parseInt(limit));
            }
            List<Storage> list = c.list();
            List<StorageForm> fList = new ArrayList<StorageForm>();
            StorageForm storageForm = null;
            for (Storage storage : list) {
                storageForm = new StorageForm();
                storageForm.setStkId(storage.getStkId());
                storageForm.setName(storage.getProduct().getProdName() + " "
                        + storage.getProduct().getProdType() + " "
                        + storage.getProduct().getProdBatch());
                storageForm.setStkWarehouse(storage.getStkWarehouse());
                storageForm.setStkWare(storage.getStkWare());
                storageForm.setStkCount(storage.getStkCount());
                storageForm.setStkMemo(storage.getStkMemo());
                fList.add(storageForm);
            }
            pgr.setData(fList);

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    /*
     * (non-Javadoc)
     *
     */
    public void save(Storage transientInstance) {
        log.debug("saving Storage instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void delete(Storage persistentInstance) {
        log.debug("deleting Storage instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#findById(java.lang.Long)
     */
    public Storage findById(Long id) {
        log.debug("getting Storage instance with id: " + id);
        try {
            Storage instance = (Storage) getHibernateTemplate().get(
                    "com.sanqing.po.Storage", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public List findByExample(Storage instance) {
        log.debug("finding Storage instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#findByProperty(java.lang.String,
     *      java.lang.Object)
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Storage instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Storage as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#findByStkWarehouse(java.lang.Object)
     */
    public List findByStkWarehouse(Object stkWarehouse) {
        return findByProperty(STK_WAREHOUSE, stkWarehouse);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#findByStkWare(java.lang.Object)
     */
    public List findByStkWare(Object stkWare) {
        return findByProperty(STK_WARE, stkWare);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#findByStkCount(java.lang.Object)
     */
    public List findByStkCount(Object stkCount) {
        return findByProperty(STK_COUNT, stkCount);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#findByStkMemo(java.lang.Object)
     */
    public List findByStkMemo(Object stkMemo) {
        return findByProperty(STK_MEMO, stkMemo);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#findAll()
     */
    public List findAll() {
        log.debug("finding all Storage instances");
        try {
            String queryString = "from Storage";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#merge(com.sanqing.po.Storage)
     */
    public Storage merge(Storage detachedInstance) {
        log.debug("merging Storage instance");
        try {
            Storage result = (Storage) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public void attachDirty(Storage instance) {
        log.debug("attaching dirty Storage instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIStorageDAO#attachClean(com.sanqing.po.Storage)
     */
    public void attachClean(Storage instance) {
        log.debug("attaching clean Storage instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static IStorageDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IStorageDAO) ctx.getBean("StorageDAO");
    }
}