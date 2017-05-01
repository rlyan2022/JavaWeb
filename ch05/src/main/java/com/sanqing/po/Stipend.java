package com.sanqing.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 薪金
 */
public class Stipend implements Serializable {
    private Long id;        //薪金信息编号
    private String name;    //员工姓名
    private Float basic;    //基本薪金
    private Float eat;        //饭补
    private Float house;    //房补
    private Date granttime;    // 工资发放时间
    private Float duty;        //全勤奖
    private Float scot;        //赋税
    private Float punishment;//罚款
    private Float other;    //额外补助
    private Float totalize;    //总计

    public Stipend(String name, Float basic, Float eat, Float house, Date granttime, Float duty, Float scot, Float punishment, Float other, Float totalize) {
        this.name = name;
        this.basic = basic;
        this.eat = eat;
        this.house = house;
        this.granttime = granttime;
        this.duty = duty;
        this.scot = scot;
        this.punishment = punishment;
        this.other = other;
        this.totalize = totalize;
    }

    /**
     * default constructor
     */
    public Stipend() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBasic() {
        return this.basic;
    }

    public void setBasic(Float basic) {
        this.basic = basic;
    }

    public Float getEat() {
        return this.eat;
    }

    public void setEat(Float eat) {
        this.eat = eat;
    }

    public Float getHouse() {
        return this.house;
    }

    public void setHouse(Float house) {
        this.house = house;
    }

    /**
     * @return Returns the granttime.
     */
    public Date getGranttime() {
        return granttime;
    }

    /**
     * @param granttime The granttime to set.
     */
    public void setGranttime(Date granttime) {
        this.granttime = granttime;
    }

    public Float getDuty() {
        return this.duty;
    }

    public void setDuty(Float duty) {
        this.duty = duty;
    }

    public Float getScot() {
        return this.scot;
    }

    public void setScot(Float scot) {
        this.scot = scot;
    }

    public Float getPunishment() {
        return this.punishment;
    }

    public void setPunishment(Float punishment) {
        this.punishment = punishment;
    }

    public Float getOther() {
        return this.other;
    }

    public void setOther(Float other) {
        this.other = other;
    }

    public Float getTotalize() {
        return this.totalize;
    }

    public void setTotalize(Float totalize) {
        this.totalize = totalize;
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        toStr.append("[Stipend] = [\n");
        toStr.append("    id = " + this.id + ";\n");
        toStr.append("    name = " + this.name + ";\n");
        toStr.append("    basic = " + this.basic + ";\n");
        toStr.append("    eat = " + this.eat + ";\n");
        toStr.append("    house = " + this.house + ";\n");
        toStr.append("    granttime = " + this.granttime + ";\n");
        toStr.append("    duty = " + this.duty + ";\n");
        toStr.append("    scot = " + this.scot + ";\n");
        toStr.append("    punishment = " + this.punishment + ";\n");
        toStr.append("    other = " + this.other + ";\n");
        toStr.append("    totalize = " + this.totalize + ";\n");
        toStr.append("    ];\n");
        return toStr.toString();
    }

}
