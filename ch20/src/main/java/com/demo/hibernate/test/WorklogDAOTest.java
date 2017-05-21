package com.demo.hibernate.test;

import com.demo.hibernate.beans.Worklog;
import com.demo.hibernate.dao.IWorklogDAO;
import com.demo.hibernate.dao.WorklogDAO;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class WorklogDAOTest extends TestCase {

    public IWorklogDAO worklogDAO;

    public WorklogDAOTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        worklogDAO = new WorklogDAO();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "WebRoot/WEB-INF/applicationContext.xml");
        worklogDAO = (IWorklogDAO) ctx.getBean("worklogDAO");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInsert() throws Exception {
        Worklog worklog = new Worklog();
        worklog.setUsername("admin");
        worklog.setYear(new Integer(2007));
        worklog.setMonth(new Integer(8));
        worklog.setDay(new Integer(30));
        worklog.setTitle("JavaWeb");
        worklog.setDescription("JavaWeb");
        worklog.setLogtime("2008-10-10");
        worklogDAO.insert(worklog);

        worklog = worklogDAO.findById(AllTest.ID);
        assertNotNull(worklog);
    }

    public void testFindPagerByUsername() throws Exception {
        List list = worklogDAO.findPagerByUsername("admin", 10, 0).getResultList();
        assertTrue(list.size() > 0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Worklog worklog = (Worklog) it.next();
            assertNotNull(worklog);
        }
    }

    public void testFindById() throws Exception {
        Worklog Worklog = worklogDAO.findById(AllTest.ID);
        assertNotNull(Worklog);
    }

    public void testUpdate() throws Exception {
        Worklog worklog = new Worklog();
        worklog.setId(new Integer(AllTest.ID));
        worklog.setUsername("admin");
        worklog.setYear(new Integer(2007));
        worklog.setMonth(new Integer(6));
        worklog.setDay(new Integer(30));
        worklog.setTitle("JavaWeb");
        worklog.setDescription("JavaWeb");
        worklog.setLogtime("2008-10-10");
        worklogDAO.update(worklog);

        Worklog worklog2 = worklogDAO.findById(AllTest.ID);
        assertTrue(worklog2.getMonth().intValue() == 6);
    }

    public void testDelete() throws Exception {
        worklogDAO.delete(AllTest.ID);

        Worklog worklog = worklogDAO.findById(AllTest.ID);
        assertNull(worklog);
    }

}
