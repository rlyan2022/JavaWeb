package com.sanqing.bean;

/**
 * 投票类
 */
public class Vote {
    private int voteID;        //投票ID
    private String voteName;    //投票名称
    private int channelID;    //频道ID

    public int getVoteID() {
        return voteID;
    }

    public void setVoteID(int voteID) {
        this.voteID = voteID;
    }

    public String getVoteName() {
        return voteName;
    }

    public void setVoteName(String voteName) {
        this.voteName = voteName;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }
}
