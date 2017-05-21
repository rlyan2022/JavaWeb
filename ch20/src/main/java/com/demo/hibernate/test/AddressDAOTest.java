package com.demo.hibernate.test;

import com.demo.hibernate.beans.Address;
import com.demo.hibernate.dao.AddressDAO;
import com.demo.hibernate.dao.IAddressDAO;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class AddressDAOTest extends TestCase {

    public IAddressDAO addressDAO;

    public AddressDAOTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        addressDAO = new AddressDAO();
        ApplicationContext ctx = new FileSystemXmlApplicationContext(
                "WebRoot/WEB-INF/applicationContext.xml");
        addressDAO = (IAddressDAO) ctx.getBean("addressDAO");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testFindAllByUsername() throws Exception {
        List list = addressDAO.findAllByUsername("admin");
        assertTrue(list.size() == 0);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Address address = (Address) it.next();
            assertNotNull(address);
        }
    }

    public void testFindById() throws Exception {
        Address address = addressDAO.findById("1");
        assertNotNull(address);
    }

    public void testIsExist() throws Exception {
        boolean b = addressDAO.isExist("admin", "lzb");
        assertTrue(b);
    }

    public void testInsert() throws Exception {
        Address address = new Address();
        address.setUsername("admin");
        address.setName("andy");
        address.setSex("2");
        address.setMobile("13888886666");
        address.setEmail("andy@163.com");
        address.setQq("12345678");
        address.setCompany("Intel");
        address.setAddress("上海市");
        address.setPostcode("200089");
        addressDAO.insert(address);

        address = addressDAO.findById("2");
        assertNotNull(address);
    }

    public void testUpdate() throws Exception {
        Address address = new Address();
        address.setId(new Integer(2));
        address.setUsername("admin");
        address.setName("andy");
        address.setSex("2");
        address.setMobile("13888886666");
        address.setEmail("andy@163.com");
        address.setQq("12345678");
        address.setCompany("Microsoft");
        address.setAddress("上海市");
        address.setPostcode("200089");
        addressDAO.update(address);

        Address address2 = addressDAO.findById("2");
        assertTrue(address2.getCompany().equals("Microsoft"));
    }

    public void testDelete() throws Exception {
        addressDAO.delete("2");

        Address address = addressDAO.findById("2");
        assertNull(address);
    }

}
