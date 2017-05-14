/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.persistence;


public class NEWSKind {

    public NEWSKind() {
        content = null;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public int getKindId() {
        return kindId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

    public int kindId;
    public String content;
    public int classId;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 203 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/