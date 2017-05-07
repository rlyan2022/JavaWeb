package com.sanqing.dao;

import com.sanqing.po.SysRoleRight;
import com.sanqing.util.RightList;

import java.util.List;

public interface ISysRoleRightDAO {

    public List<SysRoleRight> findRightExist(Long roleId, String rightCode);

    // 根据用户角色查询用户权限
    public abstract String[] findRight(Long roleId);


    public RightList findRightByRoleId(Long roleId);

    public abstract void save(SysRoleRight transientInstance);

    public abstract void delete(SysRoleRight persistentInstance);

    public abstract SysRoleRight findById(Integer id);

    public abstract List findByExample(SysRoleRight instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findAll();

    public abstract SysRoleRight merge(SysRoleRight detachedInstance);

    public abstract void attachDirty(SysRoleRight instance);

    public abstract void attachClean(SysRoleRight instance);

}