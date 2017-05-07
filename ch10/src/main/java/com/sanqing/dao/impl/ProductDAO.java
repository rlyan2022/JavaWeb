package com.sanqing.dao.impl;

import com.sanqing.dao.IProductDAO;
import com.sanqing.po.Product;
import com.sanqing.struts.form.ProductForm;
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

/**
 * A data access object (DAO) providing persistence and search support for
 * Product entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 */

public class ProductDAO extends HibernateDaoSupport implements IProductDAO {
    private static final Log log = LogFactory.getLog(ProductDAO.class);

    protected void initDao() {
        // do nothing
    }

    public void save(Product transientInstance) {
        log.debug("saving Product instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public PageResult findAll(Map paramMap) {
        PageResult pgr = new PageResult();
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");

        String name = (String) paramMap.get("prod_name");
        String prodtype = (String) paramMap.get("prod_type");
        String prodBatch = (String) paramMap.get("prodBatch");

        try {
            Criteria c = getSession().createCriteria(Product.class);
            if (StringUtils.isNotEmpty(name)) {
                c.add(Expression.like("prodName", "%" + name + "%"));
            }
            if (StringUtils.isNotEmpty(prodtype)) {
                c.add(Expression.like("prodType", "%" + prodtype + "%"));
            }
            if (StringUtils.isNotEmpty(prodBatch)) {
                c.add(Expression.like("prodBatch", "%" + prodBatch + "%"));
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
            List<Product> list = c.list();
            List<ProductForm> fList = new ArrayList<ProductForm>();
            ProductForm productForm = null;
            for (Product product : list) {
                productForm = new ProductForm();
                productForm.setProdId(product.getProdId());
                productForm.setProdName(product.getProdName());
                productForm.setProdType(product.getProdType());
                productForm.setProdBatch(product.getProdBatch());
                productForm.setProdUnit(product.getProdUnit());
                productForm.setProdPrice(product.getProdPrice());
                productForm.setProdMemo(product.getProdMemo());
                fList.add(productForm);
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
     * @see com.sanqing.daoIProductDAO#delete(com.sanqing.po.Product)
     */
    public void delete(Product persistentInstance) {
        log.debug("deleting Product instance");
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
     * @see com.sanqing.daoIProductDAO#findById(java.lang.Long)
     */
    public Product findById(Long id) {
        log.debug("getting Product instance with id: " + id);
        try {
            Product instance = (Product) getHibernateTemplate().get(
                    "com.sanqing.po.Product", id);
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
    public List findByExample(Product instance) {
        log.debug("finding Product instance by example");
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
     * @see com.sanqing.daoIProductDAO#findByProperty(java.lang.String,
     *      java.lang.Object)
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Product instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Product as model where model."
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
     * @see com.sanqing.daoIProductDAO#findByProdName(java.lang.Object)
     */
    public List findByProdName(String prodName) {
        Criteria c = getSession().createCriteria(Product.class);
        c.add(Expression.like("prodName", "%" + prodName + "%"));
        return c.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIProductDAO#findByProdType(java.lang.Object)
     */
    public List findByProdType(Object prodType) {
        return findByProperty(PROD_TYPE, prodType);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIProductDAO#findByProdBatch(java.lang.Object)
     */
    public List findByProdBatch(Object prodBatch) {
        return findByProperty(PROD_BATCH, prodBatch);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIProductDAO#findByProdUnit(java.lang.Object)
     */
    public List findByProdUnit(Object prodUnit) {
        return findByProperty(PROD_UNIT, prodUnit);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIProductDAO#findByProdPrice(java.lang.Object)
     */
    public List findByProdPrice(Object prodPrice) {
        return findByProperty(PROD_PRICE, prodPrice);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIProductDAO#findByProdMemo(java.lang.Object)
     */
    public List findByProdMemo(Object prodMemo) {
        return findByProperty(PROD_MEMO, prodMemo);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.sanqing.daoIProductDAO#findAll()
     */
    public List findAll() {
        log.debug("finding all Product instances");
        try {
            String queryString = "from Product";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    /*
     * (non-Javadoc)
     *
     */
    public Product merge(Product detachedInstance) {
        log.debug("merging Product instance");
        try {
            Product result = (Product) getHibernateTemplate().merge(
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
    public void attachDirty(Product instance) {
        log.debug("attaching dirty Product instance");
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
     */
    public void attachClean(Product instance) {
        log.debug("attaching clean Product instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static IProductDAO getFromApplicationContext(ApplicationContext ctx) {
        return (IProductDAO) ctx.getBean("ProductDAO");
    }
}