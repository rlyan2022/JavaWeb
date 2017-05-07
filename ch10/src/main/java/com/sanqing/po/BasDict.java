package com.sanqing.po;

/**
 * BasDict entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class BasDict implements java.io.Serializable {
    private Long dictId;                        //字典编号
    private String dictType;                    //字典类别
    private String dictItem;                    //字典条目
    private String dictValue;                    //字典数据
    private String dictIsEditable;                //是否可以编辑

    // Constructors

    /**
     * default constructor
     */
    public BasDict() {
    }

    /**
     * full constructor
     */
    public BasDict(String dictType, String dictItem, String dictValue,
                   String dictIsEditable) {
        this.dictType = dictType;
        this.dictItem = dictItem;
        this.dictValue = dictValue;
        this.dictIsEditable = dictIsEditable;
    }

    // Property accessors

    public Long getDictId() {
        return this.dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return this.dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictItem() {
        return this.dictItem;
    }

    public void setDictItem(String dictItem) {
        this.dictItem = dictItem;
    }

    public String getDictValue() {
        return this.dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictIsEditable() {
        return this.dictIsEditable;
    }

    public void setDictIsEditable(String dictIsEditable) {
        this.dictIsEditable = dictIsEditable;
    }

}