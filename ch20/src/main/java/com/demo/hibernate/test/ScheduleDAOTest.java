package com.demo.hibernate.test;

import com.demo.hibernate.beans.Schedule;
import com.demo.hibernate.dao.IScheduleDAO;
import com.demo.hibernate.dao.ScheduleDAO;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class ScheduleDAOTest extends TestCase {

    public IScheduleDAO scheduleDAO;

    public ScheduleDAOTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        scheduleDAO = new ScheduleDAO();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "WebRoot/WEB-INF/applicationContext.xml");
        scheduleDAO = (IScheduleDAO) ctx.getBean("scheduleDAO");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInsert() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setUsername("admin");
        schedule.setYear(new Integer(2007));
        schedule.setMonth(new Integer(8));
        schedule.setDay(new Integer(30));
        schedule.setPlan("JavaWeb");
        scheduleDAO.insert(schedule);

        schedule = scheduleDAO.findById(AllTest.ID);
        assertNotNull(schedule);
    }

    public void testFindPagerByUsername() throws Exception {
        List list = scheduleDAO.findPagerByUsername("admin", 10, 0).getResultList();
        assertTrue(list.size() > 0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Schedule schedule = (Schedule) it.next();
            assertNotNull(schedule);
        }
    }

    public void testFindById() throws Exception {
        Schedule Schedule = scheduleDAO.findById(AllTest.ID);
        assertNotNull(Schedule);
    }

    public void testUpdate() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setId(new Integer(AllTest.ID));
        schedule.setUsername("admin");
        schedule.setYear(new Integer(2007));
        schedule.setMonth(new Integer(6));
        schedule.setDay(new Integer(30));
        schedule.setPlan("JavaWeb");
        scheduleDAO.update(schedule);

        Schedule schedule2 = scheduleDAO.findById(AllTest.ID);
        assertTrue(schedule2.getMonth().intValue() == 6);
    }

    public void testDelete() throws Exception {
        scheduleDAO.delete(AllTest.ID);

        Schedule schedule = scheduleDAO.findById(AllTest.ID);
        assertNull(schedule);
    }

}
