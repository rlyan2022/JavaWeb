package com.demo.hibernate.test;

import com.demo.hibernate.beans.Sms;
import com.demo.hibernate.dao.ISmsDAO;
import com.demo.hibernate.dao.SmsDAO;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class SmsDAOTest extends TestCase {

    public ISmsDAO smsDAO;

    public SmsDAOTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        smsDAO = new SmsDAO();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "WebRoot/WEB-INF/applicationContext.xml");
        smsDAO = (ISmsDAO) ctx.getBean("smsDAO");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInsert() throws Exception {
        Sms sms = new Sms();
        sms.setUsername("admin");
        sms.setSender("admin");
        sms.setMessage("JavaWeb");
        sms.setSendtime("2008-10-10");
        sms.setIsread("0");
        smsDAO.insert(sms);

        sms = smsDAO.findById(AllTest.ID);
        assertNotNull(sms);
    }

    public void testFindPagerByUsername() throws Exception {
        List list = smsDAO.findPagerByUsername("admin", 10, 0).getResultList();
        assertTrue(list.size() > 0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Sms sms = (Sms) it.next();
            assertNotNull(sms);
        }
    }

    public void testFindById() throws Exception {
        Sms Sms = smsDAO.findById(AllTest.ID);
        assertNotNull(Sms);
    }

    public void testUpdate() throws Exception {
        Sms sms = new Sms();
        sms.setId(new Integer(AllTest.ID));
        sms.setUsername("admin");
        sms.setSender("admin");
        sms.setMessage("JavaWeb");
        sms.setSendtime("2008-10-10");
        sms.setIsread("1");
        smsDAO.update(sms);

        Sms sms2 = smsDAO.findById(AllTest.ID);
        assertTrue(sms2.getIsread().equals("1"));
    }

    public void testDelete() throws Exception {
        smsDAO.delete(AllTest.ID);

        Sms sms = smsDAO.findById(AllTest.ID);
        assertNull(sms);
    }

}
