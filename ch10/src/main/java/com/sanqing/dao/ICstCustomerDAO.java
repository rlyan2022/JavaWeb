package com.sanqing.dao;

import com.sanqing.po.CstCustomer;
import com.sanqing.util.PageResult;

import java.util.List;
import java.util.Map;

public interface ICstCustomerDAO {

    // property constants
    public static final String CUST_NAME = "custName";
    public static final String CUST_REGION = "custRegion";
    public static final String CUST_MANAGER_NAME = "custManagerName";
    public static final String CUST_LEVEL = "custLevel";
    public static final String CUST_SATISFY = "custSatisfy";
    public static final String CUST_CREDIT = "custCredit";
    public static final String CUST_ADDR = "custAddr";
    public static final String CUST_ZIP = "custZip";
    public static final String CUST_TEL = "custTel";
    public static final String CUST_FAX = "custFax";
    public static final String CUST_WEBSITE = "custWebsite";
    public static final String CUST_LICENCE_NO = "custLicenceNo";
    public static final String CUST_CHIEFTAIN = "custChieftain";
    public static final String CUST_BANKROLL = "custBankroll";
    public static final String CUST_TURNOVER = "custTurnover";
    public static final String CUST_BANK = "custBank";
    public static final String CUST_BANK_ACCOUNT = "custBankAccount";
    public static final String CUST_LOCAL_TAX_NO = "custLocalTaxNo";
    public static final String CUST_NATIONAL_TAX_NO = "custNationalTaxNo";
    public static final String CUST_STATUS = "custStatus";

    public List<CstCustomer> findAllCustomer();

    public abstract PageResult findAll(Map paramMap);

    public abstract PageResult finCustStructure(String type);

    public abstract void save(CstCustomer transientInstance);

    public abstract void delete(CstCustomer persistentInstance);

    public abstract CstCustomer findById(String id);

    public abstract List findByExample(CstCustomer instance);

    public abstract List findByProperty(String propertyName, Object value);

    public abstract List findByCustName(Object custName);

    public abstract List findByCustRegion(Object custRegion);

    public abstract List findByCustManagerName(Object custManagerName);

    public abstract List findByCustLevel(Object custLevel);

    public abstract List findByCustSatisfy(Object custSatisfy);

    public abstract List findByCustCredit(Object custCredit);

    public abstract List findByCustAddr(Object custAddr);

    public abstract List findByCustZip(Object custZip);

    public abstract List findByCustTel(Object custTel);

    public abstract List findByCustFax(Object custFax);

    public abstract List findByCustWebsite(Object custWebsite);

    public abstract List findByCustLicenceNo(Object custLicenceNo);

    public abstract List findByCustChieftain(Object custChieftain);

    public abstract List findByCustBankroll(Object custBankroll);

    public abstract List findByCustTurnover(Object custTurnover);

    public abstract List findByCustBank(Object custBank);

    public abstract List findByCustBankAccount(Object custBankAccount);

    public abstract List findByCustLocalTaxNo(Object custLocalTaxNo);

    public abstract List findByCustNationalTaxNo(Object custNationalTaxNo);

    public abstract List findByCustStatus(Object custStatus);

    public abstract CstCustomer merge(CstCustomer detachedInstance);

    public abstract void attachDirty(CstCustomer instance);

    public abstract void attachClean(CstCustomer instance);

}