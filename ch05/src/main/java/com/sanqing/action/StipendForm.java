package com.sanqing.action;

import com.sanqing.po.Stipend;
import com.sanqing.tool.DateUtil;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;

public class StipendForm extends ActionForm {

    public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
        return null;
    }

    /**
     * identifier field
     */
    private Long id;

    /**
     * nullable persistent field
     */
    private String name;

    /**
     * nullable persistent field
     */
    private Float basic;

    /**
     * nullable persistent field
     */
    private Float eat;

    /**
     * nullable persistent field
     */
    private Float house;

    /**
     * nullable persistent field
     */
    private String granttime;

    /**
     * nullable persistent field
     */
    private Float duty;

    /**
     * nullable persistent field
     */
    private Float scot;

    /**
     * nullable persistent field
     */
    private Float punishment;

    /**
     * nullable persistent field
     */
    private Float other;

    /**
     * nullable persistent field
     */
    private Float totalize;

    /**
     * full constructor
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        this.name = null;
        this.basic = null;
        this.eat = null;
        this.house = null;
        this.granttime = null;
        this.duty = null;
        this.scot = null;
        this.punishment = null;
        this.other = null;
        this.totalize = null;
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

    public Stipend populate() {
        Stipend s = new Stipend();
        s.setBasic(this.getBasic());
        s.setDuty(this.getDuty());
        s.setEat(this.getEat());
        s.setHouse(this.getHouse());
        s.setId(this.getId());
        s.setGranttime(DateUtil.parseToDate(this.getGranttime(), DateUtil.yyyyMMdd));
        s.setName(this.getName());
        s.setOther(this.getOther());
        s.setPunishment(this.getPunishment());
        s.setScot(this.getScot());
        s.setTotalize(this.getTotalize());
        return s;
    }

    /**
     * @return Returns the granttime.
     */
    public String getGranttime() {
        return granttime;
    }

    /**
     * @param granttime The granttime to set.
     */
    public void setGranttime(String granttime) {
        this.granttime = granttime;
    }
}
