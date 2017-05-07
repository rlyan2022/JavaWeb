package com.sanqing.po;

/**
 * SalPlan entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class SalPlan implements java.io.Serializable {
    private Long plaId;                                //计划编号
    private SalChance salChance;                    //销售机会信息
    private String plaDate;                            //计划日期
    private String plaTodo;                            //计划实施说明
    private String plaResult;                        //计划结果

    // Constructors

    /**
     * default constructor
     */
    public SalPlan() {
    }

    /**
     * minimal constructor
     */
    public SalPlan(SalChance salChance, String plaDate, String plaTodo) {
        this.salChance = salChance;
        this.plaDate = plaDate;
        this.plaTodo = plaTodo;
    }

    /**
     * full constructor
     */
    public SalPlan(SalChance salChance, String plaDate, String plaTodo,
                   String plaResult) {
        this.salChance = salChance;
        this.plaDate = plaDate;
        this.plaTodo = plaTodo;
        this.plaResult = plaResult;
    }

    // Property accessors

    public Long getPlaId() {
        return this.plaId;
    }

    public void setPlaId(Long plaId) {
        this.plaId = plaId;
    }

    public SalChance getSalChance() {
        return this.salChance;
    }

    public void setSalChance(SalChance salChance) {
        this.salChance = salChance;
    }

    public String getPlaDate() {
        return this.plaDate;
    }

    public void setPlaDate(String plaDate) {
        this.plaDate = plaDate;
    }

    public String getPlaTodo() {
        return this.plaTodo;
    }

    public void setPlaTodo(String plaTodo) {
        this.plaTodo = plaTodo;
    }

    public String getPlaResult() {
        return this.plaResult;
    }

    public void setPlaResult(String plaResult) {
        this.plaResult = plaResult;
    }

}