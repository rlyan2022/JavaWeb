/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

public class EnvironmentConfig {

    private EnvironmentConfig() {
    }

    public static EnvironmentConfig getInstance() {
        if (ec == null)
            ec = new EnvironmentConfig();
        return ec;
    }

    public Properties getProperties(String fileName) {
        InputStream is = null;
        Properties p = null;
        try {
            p = (Properties) register.get(fileName);
            if (p == null) {
                try {
                    is = new FileInputStream(fileName);
                } catch (Exception e) {
                    if (fileName.startsWith("/"))
                        is = (EnvironmentConfig.class).getResourceAsStream(fileName);
                    else
                        is = (EnvironmentConfig.class).getResourceAsStream("/" + fileName);
                }
                p = new Properties();
                p.load(is);
                register.put(fileName, p);
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return p;
    }

    public String getPropertyValue(String fileName, String strKey) {
        try {
            Properties p = getProperties(fileName);
            return (String) p.get(strKey);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    static EnvironmentConfig ec;
    private static Hashtable register = new Hashtable();

}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 203 ms
	Jad reported messages/errors:
Couldn't fully decompile method getPropertyValue
Couldn't resolve all exception handlers in method getPropertyValue
	Exit status: 0
	Caught exceptions:
*/