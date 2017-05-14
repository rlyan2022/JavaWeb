/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.database;

import java.sql.Connection;
import java.sql.ResultSet;

public abstract class DBOperator {

    public DBOperator() {
    }

    public abstract void Close();

    public abstract void prepareStatement(String s);

    public abstract void executeUpdate();

    public abstract ResultSet executeQuery();

    public abstract void executeUpdate(String s);

    public abstract ResultSet executeQuery(String s);

    public abstract Connection getConnection();
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 265 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/