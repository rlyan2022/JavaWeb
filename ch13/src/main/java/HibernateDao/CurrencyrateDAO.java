package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Currencyrate.
 *
 * @author MyEclipse Persistence Tools
 * @see Currencyrate
 */

public class CurrencyrateDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(CurrencyrateDAO.class);

    // property constants

    public void save(Currencyrate transientInstance) {
        log.debug("saving Currencyrate instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Currencyrate persistentInstance) {
        log.debug("deleting Currencyrate instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Currencyrate findById(CurrencyrateId id) {
        log.debug("getting Currencyrate instance with id: " + id);
        try {
            Currencyrate instance = (Currencyrate) getSession().get(
                    "HibernateDao.Currencyrate", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Currencyrate instance) {
        log.debug("finding Currencyrate instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Currencyrate").add(Example.create(instance))
                    .list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Currencyrate instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from Currencyrate as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Currencyrate instances");
        try {
            String queryString = "from Currencyrate";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Currencyrate merge(Currencyrate detachedInstance) {
        log.debug("merging Currencyrate instance");
        try {
            Currencyrate result = (Currencyrate) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Currencyrate instance) {
        log.debug("attaching dirty Currencyrate instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Currencyrate instance) {
        log.debug("attaching clean Currencyrate instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}