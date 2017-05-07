package com.sanqing.struts.form;

import org.apache.struts.action.ActionForm;


public class BasDictForm extends ActionForm {
    private Long dictId;
    private String dictType;
    private String dictItem;
    private String dictValue;
    private String dictIsEditable;

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictItem() {
        return dictItem;
    }

    public void setDictItem(String dictItem) {
        this.dictItem = dictItem;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictIsEditable() {
        return dictIsEditable;
    }

    public void setDictIsEditable(String dictIsEditable) {
        this.dictIsEditable = dictIsEditable;
    }

}