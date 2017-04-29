package com.sanqing.bean;

import java.util.Date;

public class Criticism {
    private int criticismID;        //批复ID
    private String criticismContent;//批复内容
    private int employeeID;            //批复人ID
    private Date criticismTime;        //批复时间
    private int messageID;            //消息ID

    public int getCriticismID() {
        return criticismID;
    }

    public void setCriticismID(int criticismID) {
        this.criticismID = criticismID;
    }

    public String getCriticismContent() {
        return criticismContent;
    }

    public void setCriticismContent(String criticismContent) {
        this.criticismContent = criticismContent;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Date getCriticismTime() {
        return criticismTime;
    }

    public void setCriticismTime(Date criticismTime) {
        this.criticismTime = criticismTime;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }
}
