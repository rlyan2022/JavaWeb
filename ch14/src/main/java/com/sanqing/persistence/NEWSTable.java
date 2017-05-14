/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.persistence;


public class NEWSTable {

    public NEWSTable() {
        headTitle = null;
        content = null;
        connect = null;
        author = null;
        editor = null;
        newsFrom = null;
        newsTime = null;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getClassId() {
        return classId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public int getKindId() {
        return kindId;
    }

    public void setMyOther(int myOther) {
        this.myOther = myOther;
    }

    public int getMyOther() {
        return myOther;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public String getConnect() {
        return connect;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getEditor() {
        return editor;
    }

    public void setNewsFrom(String newsFrom) {
        this.newsFrom = newsFrom;
    }

    public String getNewsFrom() {
        return newsFrom;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getTop() {
        return top;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getHits() {
        return hits;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public int newsId;
    public int myOther;
    public int classId;
    public int kindId;
    public String headTitle;
    public String content;
    public String connect;
    public String author;
    public String editor;
    public String newsFrom;
    public String newsTime;
    public int hits;
    public int top;
    public int state;
    public int tag;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 717 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/