/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.sanqing.persistence;


public class NOTEReply {

    public NOTEReply() {
        content = null;
        replyTime = null;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public int replyId;
    public int noteId;
    public String content;
    public String replyTime;
}


/*
    DECOMPILATION REPORT

	Decompiled from: D:\old\javaprj\JavaPrj_14\WebRoot\WEB-INF\lib\sanqing.jar
	Total time: 219 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/