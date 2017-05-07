package com.sanqing.dao.impl;

import com.sanqing.dao.ISysRoleDAO;
import com.sanqing.po.SysRole;
import com.sanqing.struts.form.RoleForm;
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

public class SysRoleDAO extends HibernateDaoSupport implements ISysRoleDAO {
    private static final Log log = LogFactory.getLog(SysRoleDAO.class);

    protected void initDao() {
    }

    public PageResult findAll() {
        PageResult pgr = new PageResult();
        try {
            Criteria c = getSession().createCriteria(SysRole.class);
            // 把查询到的数据放到List<CstLinkmanForm>里面,方便在CustomerAction中转换成json数据
            List<SysRole> list = c.list();
            List<RoleForm> fList = new ArrayList<RoleForm>();
            RoleForm roleForm = null;
            for (SysRole role : list) {
                roleForm = new RoleForm();
                roleForm.setRoleId(role.getRoleId());
                roleForm.setRoleName(role.getRoleName());
                fList.add(roleForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            throw re;
        }
        return pgr;
    }

    public List<SysRole> findAllRoles() {
        List<SysRole> roles = null;
        try {
            Criteria c = getSession().createCriteria(SysRole.class);
            roles = c.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;

    }

    public PageResult findAllRole(Map paramMap) {
        PageResult pgr = new PageResult();
        // 参数
        String start = (String) paramMap.get("start");
        String limit = (String) paramMap.get("limit");
        String roleName = (String) paramMap.get("roleName");
        System.out.println("第一条" + start);
        System.out.println("最后一条" + limit);
        try {
            Criteria c = getSession().createCriteria(SysRole.class);

            if (StringUtils.isNotEmpty(roleName)) {
                c.add(Expression.like("roleName", "%" + roleName + "%"));// 概要
            }

            System.out.println("角色名称：" + roleName);
            // 总记录条数
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
            // 把查询到的数据放到List<cstServiceForm>里面,方便在CustomerAction中转换成json数据
            List<SysRole> list = c.list();
            List<RoleForm> fList = new ArrayList<RoleForm>();
            RoleForm roleForm = null;
            for (SysRole sysRole : list) {
                roleForm = new RoleForm();
                roleForm.setRoleId(sysRole.getRoleId());
                roleForm.setRoleName(sysRole.getRoleName());
                roleForm.setRoleDesc(sysRole.getRoleDesc());
                roleForm.setRoleFlag(sysRole.getRoleFlag());
                fList.add(roleForm);
            }
            pgr.setData(fList);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
        return pgr;
    }

    public void save(SysRole transientInstance) {
        log.debug("saving SysRole instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(SysRole persistentInstance) {
        log.debug("deleting SysRole instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public List<SysRole> findByroleName(String name) {
        Criteria ctr = getSession().createCriteria(SysRole.class);
        ctr.add(Expression.eq("roleName", name));
        return ctr.list();
        // 根据用户名
    }

    public SysRole findById(Long id) {
        log.debug("getting SysRole instance with id: " + id);
        try {
            SysRole instance = (SysRole) getHibernateTemplate().get(
                    "com.sanqing.po.SysRole", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SysRole instance) {
        log.debug("finding SysRole instance by example");
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

    public List findByProperty(String propertyName, Object value) {
        log.debug("finding SysRole instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from SysRole as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByRoleName(Object roleName) {
        return findByProperty(ROLE_NAME, roleName);
    }

    public List findByRoleDesc(Object roleDesc) {
        return findByProperty(ROLE_DESC, roleDesc);
    }

    public List findByRoleFlag(Object roleFlag) {
        return findByProperty(ROLE_FLAG, roleFlag);
    }

    public SysRole merge(SysRole detachedInstance) {
        log.debug("merging SysRole instance");
        try {
            SysRole result = (SysRole) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SysRole instance) {
        log.debug("attaching dirty SysRole instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(SysRole instance) {
        log.debug("attaching clean SysRole instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static ISysRoleDAO getFromApplicationContext(ApplicationContext ctx) {
        return (ISysRoleDAO) ctx.getBean("SysRoleDAO");
    }
}