package HibernateDao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import java.util.List;

/**
 * Data access object (DAO) for domain model class Roleaction.
 *
 * @author MyEclipse Persistence Tools
 * @see Roleaction
 */

public class RoleactionDAO extends BaseHibernateDAO {
    private static final Log log = LogFactory.getLog(RoleactionDAO.class);

    // property constants

    public void save(Roleaction transientInstance) {
        log.debug("saving Roleaction instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Roleaction persistentInstance) {
        log.debug("deleting Roleaction instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Roleaction findById(Integer id) {
        log.debug("getting Roleaction instance with id: " + id);
        try {
            Roleaction instance = (Roleaction) getSession().get(
                    "HibernateDao.Roleaction", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Roleaction instance) {
        log.debug("finding Roleaction instance by example");
        try {
            List results = getSession().createCriteria(
                    "HibernateDao.Roleaction").add(Example.create(instance))
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
        log.debug("finding Roleaction instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Roleaction as model where model."
                    + propertyName + "= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, value);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByExampleTrue(Roleaction instance) {
        log.debug("finding Roleaction instance with property: ");
        try {
            String queryString = "from Roleaction as model where model.rolemaster= ? and model.actionmaster=?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, instance.getRolemaster());
            queryObject.setParameter(1, instance.getActionmaster());
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll() {
        log.debug("finding all Roleaction instances");
        try {
            String queryString = "from Roleaction";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Roleaction merge(Roleaction detachedInstance) {
        log.debug("merging Roleaction instance");
        try {
            Roleaction result = (Roleaction) getSession().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Roleaction instance) {
        log.debug("attaching dirty Roleaction instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Roleaction instance) {
        log.debug("attaching clean Roleaction instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}