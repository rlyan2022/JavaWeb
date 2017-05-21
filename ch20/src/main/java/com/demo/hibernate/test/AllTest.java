package com.demo.hibernate.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTest extends TestCase {

    public static String ID = "1";

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for test");
        //suite.addTest(new TestSuite(AddressDAOTest.class));

        //suite.addTest(new TestSuite(ScheduleDAOTest.class));
        //suite.addTest(new TestSuite(WorklogDAOTest.class));
        //suite.addTest(new TestSuite(SmsDAOTest.class));
        //suite.addTest(new TestSuite(NoticeDAOTest.class));
        //suite.addTest(new TestSuite(MeetingDAOTest.class));

        suite.addTest(new ScheduleDAOTest("testInsert"));
        suite.addTest(new ScheduleDAOTest("testFindPagerByUsername"));
        suite.addTest(new ScheduleDAOTest("testFindById"));
        suite.addTest(new ScheduleDAOTest("testUpdate"));
        suite.addTest(new ScheduleDAOTest("testDelete"));

        suite.addTest(new WorklogDAOTest("testInsert"));
        suite.addTest(new WorklogDAOTest("testFindPagerByUsername"));
        suite.addTest(new WorklogDAOTest("testFindById"));
        suite.addTest(new WorklogDAOTest("testUpdate"));
        suite.addTest(new WorklogDAOTest("testDelete"));

        suite.addTest(new SmsDAOTest("testInsert"));
        suite.addTest(new SmsDAOTest("testFindPagerByUsername"));
        suite.addTest(new SmsDAOTest("testFindById"));
        suite.addTest(new SmsDAOTest("testUpdate"));
        suite.addTest(new SmsDAOTest("testDelete"));

        suite.addTest(new NoticeDAOTest("testInsert"));
        suite.addTest(new NoticeDAOTest("testFindPager"));
        suite.addTest(new NoticeDAOTest("testFindById"));
        suite.addTest(new NoticeDAOTest("testUpdate"));
        suite.addTest(new NoticeDAOTest("testDelete"));

        suite.addTest(new MeetingDAOTest("testInsert"));
        suite.addTest(new MeetingDAOTest("testFindPager"));
        suite.addTest(new MeetingDAOTest("testFindById"));
        suite.addTest(new MeetingDAOTest("testUpdate"));
        suite.addTest(new MeetingDAOTest("testDelete"));
        return suite;
    }
}
