

package com.dao;

import java.io.Serializable;

public class Sched implements Serializable {
    // JavaBean
    // 用于储存航班信息
    private static final long serialVersionUID = 1L;
    private String hao;
    private String rqi;
    private String qifei;
    private String mudi;
    private int jiage;
    private int piaosu;

    public void setHao(String hao) {
        this.hao = hao;
    }

    public String getHao() {
        return hao;
    }

    public void setRqi(String rqi) {
        this.rqi = rqi;
    }

    public String getRqi() {
        return rqi;
    }

    public void setQifei(String qifei) {
        this.qifei = qifei;
    }

    public String getQifei() {
        return qifei;
    }

    public void setMudi(String mudi) {
        this.mudi = mudi;
    }

    public String getMudi() {
        return mudi;
    }

    public void setJiage(int jiage) {
        this.jiage = jiage;
    }

    public int getJiage() {
        return jiage;
    }

    public void setPiaosu(int piaosu) {
        this.piaosu = piaosu;
    }

    public int getPiaosu() {
        return piaosu;
    }


}
