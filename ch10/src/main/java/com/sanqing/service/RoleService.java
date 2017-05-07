package com.sanqing.service;

import com.sanqing.dao.ISysRightDAO;
import com.sanqing.dao.ISysRoleDAO;
import com.sanqing.dao.ISysRoleRightDAO;
import com.sanqing.po.SysRight;
import com.sanqing.po.SysRole;
import com.sanqing.po.SysRoleRight;
import com.sanqing.util.PageResult;
import com.sanqing.util.RightList;

import java.util.List;
import java.util.Map;

public class RoleService {
    private ISysRoleDAO roleDao = null;
    private ISysRightDAO rightDao = null;
    private ISysRoleRightDAO srrDao = null;

    public ISysRoleRightDAO getSrrDao() {
        return srrDao;
    }

    public void setSrrDao(ISysRoleRightDAO srrDao) {
        this.srrDao = srrDao;
    }

    public ISysRightDAO getRightDao() {
        return rightDao;
    }

    public void setRightDao(ISysRightDAO rightDao) {
        this.rightDao = rightDao;
    }

    public ISysRoleDAO getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(ISysRoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    // 创建角色信息
    public void add(SysRole sysRole) {
        roleDao.save(sysRole);
    }

    // 修改角色信息
    public void update(SysRole sysRole) {
        roleDao.merge(sysRole);
    }

    // 找出所有的权限
    public RightList findAllRight() {
        return rightDao.findAllRight();
    }

    // 根据角色找出它有或没有的权限
    public RightList findRightByRoleId(Long roleId) {
        return srrDao.findRightByRoleId(roleId);
    }

    // 判断该角色的权限是否存在
    public boolean findRightExist(Long roleId, String rightCode) {
        boolean exist;
        if (srrDao.findRightExist(roleId, rightCode) != null) {
            exist = true;
        } else {
            exist = false;
        }
        return exist;
        // boolean exist1;
        // boolean exist2;
        // if(srrDao.findByProperty("SysRole.roleId",roleId)!=null){
        // exist1=true;
        // }else{
        // exist1=false;
        // }
        // if(srrDao.findByProperty("sysRight.rightCode",rightCode)!=null){
        // exist2=true;
        // }else{
        // exist2=false;
        // }
        // if(exist1==true||exist2==true){
        // exist=true;
        // }else{
        // exist=false;
        // }
    }

    // 根据角色编号找出SysRoleRight中的主键
    public List<SysRoleRight> findIdByRoleId(Long roleId) {
        return srrDao.findByProperty("sysRole.roleId", roleId);
    }

    // 删除该角色的所有权限
    public void deleteRight(SysRoleRight srr) {
        srrDao.delete(srr);
    }

    // 保存权限
    public void updateRight(SysRoleRight srr) {
        srrDao.save(srr);
    }

    // 查询角色信息
    public PageResult findAllRole(Map paramMap) {
        return roleDao.findAllRole(paramMap);
    }

    public SysRole findByRoleId(Long roleId) {
        return roleDao.findById(roleId);
    }

    // 删除角色信息
    public void del(SysRole sysRole) {
        roleDao.delete(sysRole);
    }

    // 查询所有权限名称
    public List<SysRight> findAllRightName() {
        return rightDao.findAll();
    }
}
