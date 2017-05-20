package com.dao;

import java.io.Serializable;
import java.sql.Date;

public class Anpai implements Serializable {

    private static final long serialVersionUID = 1L;
    private String hao;
    private Date rqi;

    public void setHao(String hao) {
        this.hao = hao;
    }

    public String getHao() {
        return hao;
    }

    public void setRqi(Date rqi) {
        this.rqi = rqi;
    }

    public Date getRqi() {
        return rqi;
    }


}