/**
 * 2008.04
 */
package com.sanqing.bll;

import HibernateDao.Roleaction;
import HibernateDao.RoleactionDAO;
import HibernateDao.Rolemaster;
import HibernateDao.RolemasterDAO;

import java.util.List;
import java.util.Set;

/*
 * �����û�Ȩ������
 */
public class BUserRight {

    private HibernateDao.Employee _UserObject = new HibernateDao.Employee();
    private HibernateDao.EmployeeDAO _UserControler = new HibernateDao.EmployeeDAO();


    private HibernateDao.Actionmaster _ActionObject = new HibernateDao.Actionmaster();
    private HibernateDao.ActionmasterDAO _ActionControler = new HibernateDao.ActionmasterDAO();


    private HibernateDao.Employeerole _UserRoleObject = new HibernateDao.Employeerole();
    private HibernateDao.EmployeeroleDAO _UserRoleControler = new HibernateDao.EmployeeroleDAO();


    private HibernateDao.Rolemaster _RoleObject = new Rolemaster();
    private HibernateDao.RolemasterDAO _RoleControler = new RolemasterDAO();


    private HibernateDao.Roleaction _RoleActionObject = new Roleaction();
    private HibernateDao.RoleactionDAO _RoleActionControler = new RoleactionDAO();

    public BUserRight() {
    }

    public BUserRight(String UserName) {
        this._UserObject = this._UserControler.findById(UserName);
    }

    public BUserRight(HibernateDao.Employee UserObject) {

        this._UserObject = UserObject;
    }

    /**
     * ��֤�û����
     * @param UserName
     * @param Password
     * @return
     * @throws Exception
     */
    public boolean ValidUser(String UserName, String Password)
            throws Exception {
        try {
            this._UserObject = this._UserControler.findById(UserName);
        } catch (Exception e) {
            throw new Exception("��ݿ�无法连接到数据库，请稍候重试");
        }
        if (this._UserObject == null) {
            throw new Exception("用户名不存在");
        }
        if (!this._UserObject.getPassword().equals(Password)) throw new Exception("密码不正确");
        return true;
    }

    /**
     * �û���ĳ�����Ƿ���Ȩ��
     * @param UserID
     * @param ActionID
     * @return
     */
    public boolean IsUserInRole(String UserID, String ActionID) {
        this._UserObject = this._UserControler.findById(UserID);
        //�鿴�û����Ķ������˳�
        while (this._UserObject.getEmployeeroles().iterator().hasNext()) {
            HibernateDao.Rolemaster TmpRole = (HibernateDao.Rolemaster) this._UserObject.getEmployeeroles().iterator().next();
            //�ӽ�ɫ������
            while (TmpRole.getRoleactions().iterator().hasNext()) {
                HibernateDao.Actionmaster TmpAction = (HibernateDao.Actionmaster) TmpRole.getRoleactions().iterator().next();

                if (TmpAction.getActionId().equals(ActionID)) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * �����û������н�ɫ
     * @param UserName
     * @return
     * @throws Exception
     */
    public Set GetUserRoles(String UserName)
            throws Exception {
        try {
            this._UserObject = this._UserControler.findById(UserName);
        } catch (Exception e) {
            throw new Exception("");
        }
        return this._UserObject.getEmployeeroles();
    }

    /**
     * ���û���ӽ�ɫ
     * @param UserID
     * @param ActionID
     */
    public void AddRoleToUser(String UserID, String RoleID) {
        //��Ҫ���������
        this._UserRoleObject = new HibernateDao.Employeerole();
        this._UserRoleObject.setEmployee(this._UserControler.findById(UserID));
        this._UserRoleObject.setRolemaster(this._RoleControler.findById(RoleID));
        this._UserRoleControler.attachDirty(this._UserRoleObject);

    }

    /**
     * ���û�ɾ���ɫ
     * @param UserID
     * @param ActionID
     */
    public void DeleteRoleFromUser(String UserID, String RoleID) {
        this._UserRoleObject = new HibernateDao.Employeerole();
        this._UserRoleObject.setEmployee(this._UserControler.findById(UserID));
        this._UserRoleObject.setRolemaster(this._RoleControler.findById(RoleID));
        List UserRoleTmpList = this._UserRoleControler.findByExample(this._UserRoleObject);
        while (UserRoleTmpList.iterator().hasNext()) {
            HibernateDao.Employeerole tmpobj = (HibernateDao.Employeerole) UserRoleTmpList.iterator().next();
            this._UserRoleControler.delete(tmpobj);
        }
    }
}
