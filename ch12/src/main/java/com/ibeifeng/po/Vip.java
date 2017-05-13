package com.ibeifeng.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Vip entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Vip implements java.io.Serializable {

    // Fields

    private Integer vipId;
    private Date joinTime;
    private String name;
    private Integer age;
    private String profession;
    private Set consumes = new HashSet(0);

    // Constructors

    /**
     * default constructor
     */
    public Vip() {
    }

    /**
     * full constructor
     */
    public Vip(Date joinTime, String name, Integer age, String profession,
               Set consumes) {
        this.joinTime = joinTime;
        this.name = name;
        this.age = age;
        this.profession = profession;
        this.consumes = consumes;
    }

    // Property accessors

    public Integer getVipId() {
        return this.vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public Date getJoinTime() {
        return this.joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfession() {
        return this.profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Set getConsumes() {
        return this.consumes;
    }

    public void setConsumes(Set consumes) {
        this.consumes = consumes;
    }

}