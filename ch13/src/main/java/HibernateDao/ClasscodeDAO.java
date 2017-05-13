package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Classcode.
 *
 * @author MyEclipse Persistence Tools
 * @see Classcode
 */

public class ClasscodeDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(ClasscodeDAO.class);

    // property constants
    public static final String PROD_DESC = "prodDesc";

    public void save(Classcode transientInstance) {
        log.debug("saving Classcode instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Classcode persistentInstance) {
        log.debug("deleting Classcode instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Classcode findById(String id) {
        log.debug("getting Classcode instance with id: " + id);
        try {
            Classcode instance = (Classcode) getSession().get(
                    "HibernateDao.Classcode", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Classcode instance) {
        log.debug("finding Classcode instance by example");
        try {
            List results = getSession()
                    .createCriteria("HibernateDao.Classcode").add(
                            Example.create(instance)).list();
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding Classcode instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Classcode as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByProdDesc(Object prodDesc) {
        return findByProperty(PROD_DESC, prodDesc);
    }

    public List findAll() {
        log.debug("finding all Classcode instances");
        try {
            String queryString = "from Classcode";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Classcode merge(Classcode detachedInstance) {
        log.debug("merging Classcode instance");
        try {
            Classcode result = (Classcode) getSession().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Classcode instance) {
        log.debug("attaching dirty Classcode instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Classcode instance) {
        log.debug("attaching clean Classcode instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}