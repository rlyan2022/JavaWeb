package com.demo.hibernate.test;

import com.demo.hibernate.beans.Meeting;
import com.demo.hibernate.dao.IMeetingDAO;
import com.demo.hibernate.dao.MeetingDAO;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class MeetingDAOTest extends TestCase {

    public IMeetingDAO meetingDAO;

    public MeetingDAOTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        meetingDAO = new MeetingDAO();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "WebRoot/WEB-INF/applicationContext.xml");
        meetingDAO = (IMeetingDAO) ctx.getBean("meetingDAO");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInsert() throws Exception {
        Meeting meeting = new Meeting();
        meeting.setSender("admin");
        meeting.setStarttime("2008-10-10");
        meeting.setEndtime("2008-10-12");
        meeting.setAddress("Beijing");
        meeting.setTitle("JavaWeb");
        meeting.setContent("JavaWeb");
        meetingDAO.insert(meeting);

        meeting = meetingDAO.findById(AllTest.ID);
        assertNotNull(meeting);
    }

    public void testFindPager() throws Exception {
        List list = meetingDAO.findPager(10, 0).getResultList();
        assertTrue(list.size() > 0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Meeting meeting = (Meeting) it.next();
            assertNotNull(meeting);
        }
    }

    public void testFindById() throws Exception {
        Meeting Meeting = meetingDAO.findById(AllTest.ID);
        assertNotNull(Meeting);
    }

    public void testUpdate() throws Exception {
        Meeting meeting = new Meeting();
        meeting.setId(new Integer(AllTest.ID));
        meeting.setSender("admin");
        meeting.setStarttime("2008-10-10");
        meeting.setEndtime("2008-10-12");
        meeting.setAddress("Shanghai");
        meeting.setTitle("JavaWeb");
        meeting.setContent("JavaWeb");
        meetingDAO.update(meeting);

        Meeting meeting2 = meetingDAO.findById(AllTest.ID);
        assertTrue(meeting2.getAddress().equals("Shanghai"));
    }

    public void testDelete() throws Exception {
        meetingDAO.delete(AllTest.ID);

        Meeting meeting = meetingDAO.findById(AllTest.ID);
        assertNull(meeting);
    }

}
