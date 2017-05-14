/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.pool;

import com.sanqing.common.Debug;
import com.sanqing.common.EnvironmentConfig;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class DBConnectionManager {
    class DBConnectionPool {

        public synchronized void freeConnection(Connection Conn) {
            freeConnections.addElement(Conn);
            checkedOut--;
            notifyAll();
        }

        public synchronized Connection getConnection() {
            Connection con = null;
            if (freeConnections.size() > 0) {
                con = (Connection) freeConnections.firstElement();
                freeConnections.removeElementAt(0);
                try {
                    if (con.isClosed()) {
                        con = getConnection();
                    }
                } catch (SQLException e) {
                    Debug.writeLog("\u4ECE\u8FDE\u63A5\u6C60" + name + "\u5220\u9664\u4E00\u4E2A\u65E0\u6548\u8FDE\u63A5");
                    con = getConnection();
                }
            } else if (maxConn == 0 || checkedOut < maxConn)
                con = newConnection();
            if (con != null)
                checkedOut++;
            if (con == null)
                Debug.writeLog("DBConnectionPool getConnection(), The Returned Con is null");
            return con;
        }

        public synchronized Connection getConnection(long timeout) {
            long startTime = new Date().getTime();
            Connection con;
            while ((con = getConnection()) == null) {
                try {
                    wait(timeout);
                } catch (InterruptedException e) {
                }
                if ((new Date()).getTime() - startTime >= timeout)
                    return null;
            }
            return con;
        }

        private void log(String msg) {
            log.println(new Date() + ": " + msg);
        }

        private void log(Throwable e, String msg) {
            log.println(new Date() + ": " + msg);
            e.printStackTrace(log);
        }

        private Connection newConnection() {
            Connection con = null;
            try {
                if (user == null)
                    con = DriverManager.getConnection(URL);
                else
                    con = DriverManager.getConnection(URL, user, password);
                Debug.writeLog("\u8FDE\u63A5\u6C60" + name + "\u521B\u5EFA\u4E00\u4E2A\u65B0\u7684\u8FDE\u63A5");
            } catch (SQLException e) {
                Debug.writeLog("\u65E0\u6CD5\u521B\u5EFA\u4E0B\u5217URL\u7684\u8FDE\u63A5: " + URL);
                e.printStackTrace(System.out);
                return null;
            }
            if (con == null)
                Debug.writeLog("DBConnectionPool newConnection(), The Returned Con is null");
            return con;
        }

        public synchronized void release() {
            for (Enumeration allConnections = freeConnections.elements(); allConnections.hasMoreElements(); ) {
                Connection con = (Connection) allConnections.nextElement();
                try {
                    con.close();
                    Debug.writeLog("\u5173\u95ED\u8FDE\u63A5\u6C60" + name + "\u4E2D\u7684\u4E00\u4E2A\u8FDE\u63A5");
                } catch (SQLException e) {
                    Debug.writeLog("\u65E0\u6CD5\u5173\u95ED\u8FDE\u63A5\u6C60" + name + "\u4E2D\u7684\u8FDE\u63A5");
                    e.printStackTrace(System.out);
                }
            }

            freeConnections.removeAllElements();
        }

        private int checkedOut;
        private Vector freeConnections;
        private int maxConn;
        private String name;
        private String user;
        private String password;
        private String URL;
        private PrintWriter log;

        public DBConnectionPool(String name, String url, String user, String password, int maxConn) {
            freeConnections = new Vector();
            this.name = name;
            URL = url;
            this.user = user;
            this.password = password;
            this.maxConn = maxConn;
            log = new PrintWriter(System.err);
            Debug.writeLog("poolname" + this.name);
            Debug.writeLog("URL: " + URL);
            Debug.writeLog("user: " + this.user);
            Debug.writeLog("password: " + this.password);
            Debug.writeLog("maxConn: " + this.maxConn);
        }
    }


    private DBConnectionManager() {
        drivers = new Vector();
        pools = new Hashtable();
        init();
    }

    private void createPools(Properties props) {
        for (Enumeration propNames = props.propertyNames(); propNames.hasMoreElements(); ) {
            String name = (String) propNames.nextElement();
            Debug.writeLog("createPools(Properties), name is:  " + name);
            if (name.endsWith(".url")) {
                Debug.writeLog("createPools(Properties), name end with url");
                String poolName = name.substring(0, name.lastIndexOf("."));
                String url = props.getProperty(poolName + ".url");
                Debug.writeLog("createPools(Properties), url is " + url);
                if (url == null) {
                    Debug.writeLog("\u6CA1\u6709\u4E3A\u8FDE\u63A5\u6C60" + poolName + "\u6307\u5B9AURL");
                } else {
                    String user = props.getProperty(poolName + ".user");
                    String password = props.getProperty(poolName + ".password");
                    String maxconn = props.getProperty(poolName + ".maxconn", "0");
                    int max;
                    try {
                        max = Integer.valueOf(maxconn).intValue();
                    } catch (NumberFormatException e) {
                        Debug.writeLog("\u9519\u8BEF\u7684\u6700\u5927\u8FDE\u63A5\u6570\u9650\u5236: " + maxconn + " .\u8FDE\u63A5\u6C60: " + poolName);
                        max = 0;
                    }
                    DBConnectionPool pool = new DBConnectionPool(poolName, url, user, password, max);
                    pools.put(poolName, pool);
                    Debug.writeLog("\u6210\u529F\u521B\u5EFA\u8FDE\u63A5\u6C60" + poolName);
                }
            }
        }

    }

    public void freeConnection(String name, Connection con) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null)
            pool.freeConnection(con);
    }

    public Connection getConnection(String name) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null) {
            Debug.writeLog("DBConnectionManager getConnection(String) ! pool is not null !");
            return pool.getConnection();
        } else {
            return null;
        }
    }

    public Connection getConnection(String name, long time) {
        DBConnectionPool pool = (DBConnectionPool) pools.get(name);
        if (pool != null)
            return pool.getConnection(time);
        else
            return null;
    }

    public static synchronized DBConnectionManager getInstance() {
        if (instance == null)
            instance = new DBConnectionManager();
        clients++;
        return instance;
    }

    private void init() {
        Properties dbProps = EnvironmentConfig.getInstance().getProperties("/SiteConfig/db.properties");
        loadDrivers(dbProps);
        createPools(dbProps);
    }

    private void loadDrivers(Properties props) {
        String driverClasses = props.getProperty("drivers");
        for (StringTokenizer st = new StringTokenizer(driverClasses); st.hasMoreElements(); ) {
            String driverClassName = st.nextToken().trim();
            try {
                Driver driver = (Driver) Class.forName(driverClassName).newInstance();
                DriverManager.registerDriver(driver);
                Debug.writeLog("Load Driver Success !");
                drivers.addElement(driver);
                Debug.writeLog("\u6210\u529F\u6CE8\u518CJDBC\u9A71\u52A8\u7A0B\u5E8F" + driverClassName);
            } catch (Exception e) {
                Debug.writeLog("\u65E0\u6CD5\u6CE8\u518CJDBC\u9A71\u52A8\u7A0B\u5E8F: " + driverClassName + ", \u9519\u8BEF: " + e);
            }
        }

    }

    public synchronized void release() {
        if (--clients != 0)
            return;
        DBConnectionPool pool;
        for (Enumeration allPools = pools.elements(); allPools.hasMoreElements(); pool.release())
            pool = (DBConnectionPool) allPools.nextElement();

        for (Enumeration allDrivers = drivers.elements(); allDrivers.hasMoreElements(); ) {
            Driver driver = (Driver) allDrivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                Debug.writeLog("\u64A4\u9500JDBC\u9A71\u52A8\u7A0B\u5E8F " + driver.getClass().getName() + "\u7684\u6CE8\u518C");
            } catch (SQLException e) {
                Debug.writeLog("\u65E0\u6CD5\u64A4\u9500\u4E0B\u5217JDBC\u9A71\u52A8\u7A0B\u5E8F\u7684\u6CE8\u518C: " + driver.getClass().getName());
                e.printStackTrace(System.out);
            }
        }

    }

    private static DBConnectionManager instance;
    private static int clients;
    private Vector drivers;
    private Hashtable pools;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 640 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/