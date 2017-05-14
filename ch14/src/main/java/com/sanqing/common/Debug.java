/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.common;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

// Referenced classes of package com.sanqing.common:
//            EnvironmentConfig

public class Debug {

    public Debug() {
    }

    private static synchronized PrintWriter getLogStream(String logFileName) {
        SimpleDateFormat _dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = null;
        PrintWriter pw = null;
        try {
            File currentFile = new File(logFileName);
            if (currentFile.length() > 1000000L) {
                File backupFile = new File(logFileName + ".bak");
                if (backupFile.exists()) {
                    currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
                    File backupRenamedFile = new File(logFileName + ".bak." + currentTime);
                    try {
                        backupFile.renameTo(backupRenamedFile);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    currentFile.renameTo(backupFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            debugNo++;
            FileOutputStream os = new FileOutputStream(logFileName, true);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            pw = new PrintWriter(bos, true);
            pw.println("\n");
        } catch (Exception ex) {
            pw = new PrintWriter(System.err, true);
            _dateFormatter = new SimpleDateFormat("'In' yyyyMMdd 'at' HH a mm 'minutes' ss 'seconds'");
            currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
            pw.println("\n");
            pw.println(currentTime + ":" + " Exception Occured While Trying To Open Log File: " + logFileName + ".[" + ex.toString() + "]");
        }
        return pw;
    }

    public static synchronized void writeLog(String info) {
        String isOn = "";
        Properties p = EnvironmentConfig.getInstance().getProperties("/SiteConfig/db.properties");
        isOn = p.getProperty("isLog");
        if (isOn == null || isOn.trim() == "" || !isOn.trim().equalsIgnoreCase("on") && !isOn.trim().equalsIgnoreCase("true"))
            isOn = "off";
        String logPath = p.getProperty("logPath");
        if (logPath == null || logPath.trim().equalsIgnoreCase(""))
            logPath = "/log/";
        logPath.replace('\\', '/');
        if (!logPath.endsWith(File.separator))
            logPath = logPath + File.separator;
        String logFile = p.getProperty("logFile");
        if (logFile == null || logFile.trim().equalsIgnoreCase(""))
            logFile = "Site.log";
        String logFileName = logPath + logFile;
        SimpleDateFormat _dateFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = null;
        PrintWriter pw = null;
        if (isOn.equalsIgnoreCase("true") || isOn.trim().equalsIgnoreCase("on")) {
            pw = getLogStream(logFileName);
            _dateFormatter = new SimpleDateFormat("'On' yyyyMMdd 'at' HH:mm:ss '---'");
            currentTime = _dateFormatter.format(new Date(System.currentTimeMillis()));
            pw.println(currentTime + " Debug Starts No " + String.valueOf(debugNo) + " ************");
            pw.println("\n");
            pw.println(info);
            pw.flush();
            pw.close();
        }
    }

    private static int debugNo = 0;

}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 203 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/