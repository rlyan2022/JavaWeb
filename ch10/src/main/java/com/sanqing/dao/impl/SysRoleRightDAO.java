package com.sanqing.dao.impl;

import com.sanqing.dao.ISysRoleRightDAO;
import com.sanqing.po.SysRight;
import com.sanqing.po.SysRoleRight;
import com.sanqing.struts.form.RightForm;
import com.sanqing.util.RightList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class SysRoleRightDAO extends HibernateDaoSupport implements
        ISysRoleRightDAO {
    private static final Log log = LogFactory.getLog(SysRoleRightDAO.class);

    protected void initDao() {

    }

    // 根据用户角色查询用户权限

    public String[] findRight(Long roleId) {
        Session session = getSession();
        List<SysRoleRight> sysRoleRightList = findByProperty("sysRole.roleId",
                roleId);
        String[] rightUrl = new String[sysRoleRightList.size()];
        for (int i = 0; i < rightUrl.length; i++) {
            rightUrl[i] = sysRoleRightList.get(i).getSysRight().getRightUrl();
        }
        return rightUrl;

    }

    // 根据角色找出它有或没有的权限
    public RightList findRightByRoleId(Long roleId) {
        RightList rl = new RightList();
        try {
            // ///////////////////////////////////
            Criteria c = getSession().createCriteria(SysRoleRight.class);
            c.add(Expression.eq("sysRole.roleId", roleId));
            List<SysRoleRight> list = c.list();
            List<RightForm> fLists = new ArrayList<RightForm>();

            // 找有的权限
            List<RightForm> fList = new ArrayList<RightForm>();
            RightForm rightForm = null;
            Long[] rightCode = new Long[list.size()];
            for (int i = 0; i < list.size(); i++) {
                rightCode[i] = list.get(i).getSysRight().getRightCode();
                rightForm = new RightForm();
                rightForm.setRightCode(list.get(i).getSysRight().getRightCode());
                rightForm
                        .setRightText(list.get(i).getSysRight().getRightText());
                fList.add(rightForm);
            }
            rl.setHasRight(fList);

            // 找没有的权限
            Criteria cc = getSession().createCriteria(SysRight.class);
            for (int n = 0; n < rightCode.length; n++) {
                cc.add(Expression.ne("rightCode", rightCode[n]));
            }
            List<SysRight> lists = cc.list();
            RightForm rightForms = null;

            for (int j = 0; j < lists.size(); j++) {
                rightForms = new RightForm();
                System.out.println("没的权限：" + lists.get(j).getRightCode());
                rightForms.setRightCode(lists.get(j).getRightCode());
                rightForms.setRightText(lists.get(j).getRightText());
                fLists.add(rightForms);
            }
            rl.setHasNotRight(fLists);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rl;
    }

    public List<SysRoleRight> findRightExist(Long roleId, String rightCode) {
        List<SysRoleRight> list = getSession().createQuery(
                "select * from SysRoleRight srr where srr.sysRole.roleId='"
                        + roleId + "' and srr.sysRight.rightCode='" + rightCode
                        + "'").list();
        return list;
    }

    /*
     * (non-Javadoc)
     *
     */
    public void save(SysRoleRight transientInstance) {
        log.debug("saving SysRoleRight instance");
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
    public void delete(SysRoleRight persistentInstance) {
        log.debug("deleting SysRoleRight instance");
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
     */
    public SysRoleRight findById(Integer id) {
        log.debug("getting SysRoleRight instance with id: " + id);
        try {
            SysRoleRight instance = (SysRoleRight) getHibernateTemplate().get(
                    "com.sanqing.po.SysRoleRight", id);
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
    public List findByExample(SysRoleRight instance) {
        log.debug("finding SysRoleRight instance by example");
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
     */
    public List findByProperty(String propertyName, Object value) {
        log.debug("finding SysRoleRight instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from SysRoleRight as model where model."
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
     */
    public List findAll() {
        log.debug("finding all SysRoleRight instances");
        try {
            String queryString = "from SysRoleRight";
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
    public SysRoleRight merge(SysRoleRight detachedInstance) {
        log.debug("merging SysRoleRight instance");
        try {
            SysRoleRight result = (SysRoleRight) getHibernateTemplate().merge(
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
    public void attachDirty(SysRoleRight instance) {
        log.debug("attaching dirty SysRoleRight instance");
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
    public void attachClean(SysRoleRight instance) {
        log.debug("attaching clean SysRoleRight instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ISysRoleRightDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (ISysRoleRightDAO) ctx.getBean("SysRoleRightDAO");
    }
}