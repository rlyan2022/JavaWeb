package com.demo.hibernate.test;

import com.demo.hibernate.beans.Notice;
import com.demo.hibernate.dao.INoticeDAO;
import com.demo.hibernate.dao.NoticeDAO;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class NoticeDAOTest extends TestCase {

    public INoticeDAO noticeDAO;

    public NoticeDAOTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        noticeDAO = new NoticeDAO();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "WebRoot/WEB-INF/applicationContext.xml");
        noticeDAO = (INoticeDAO) ctx.getBean("noticeDAO");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testInsert() throws Exception {
        Notice notice = new Notice();
        notice.setSender("admin");
        notice.setTitle("JavaWeb");
        notice.setContent("JavaWeb");
        notice.setSendtime("2008-10-10");
        noticeDAO.insert(notice);

        notice = noticeDAO.findById(AllTest.ID);
        assertNotNull(notice);
    }

    public void testFindPager() throws Exception {
        List list = noticeDAO.findPager(10, 0).getResultList();
        assertTrue(list.size() > 0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Notice notice = (Notice) it.next();
            assertNotNull(notice);
        }
    }

    public void testFindById() throws Exception {
        Notice Notice = noticeDAO.findById(AllTest.ID);
        assertNotNull(Notice);
    }

    public void testUpdate() throws Exception {
        Notice notice = new Notice();
        notice.setId(new Integer(AllTest.ID));
        notice.setSender("admin");
        notice.setTitle("JavaWeb Develop");
        notice.setContent("JavaWeb");
        notice.setSendtime("2008-10-10");
        noticeDAO.update(notice);

        Notice notice2 = noticeDAO.findById(AllTest.ID);
        assertTrue(notice2.getTitle().equals("JavaWeb Develop"));
    }

    public void testDelete() throws Exception {
        noticeDAO.delete(AllTest.ID);

        Notice notice = noticeDAO.findById(AllTest.ID);
        assertNull(notice);
    }

}
