package com.sanqing.struts.form;


public class OrdersLineForm {
    private String prodName;
    private Integer oddCount;
    private String oddUnit;
    private Double oddPrice;
    private Double prodTotal;

    public Double getProdTotal() {
        return prodTotal;
    }

    public void setProdTotal(Double prodTotal) {
        this.prodTotal = prodTotal;
    }

    public Integer getOddCount() {
        return oddCount;
    }

    public void setOddCount(Integer oddCount) {
        this.oddCount = oddCount;
    }

    public String getOddUnit() {
        return oddUnit;
    }

    public void setOddUnit(String oddUnit) {
        this.oddUnit = oddUnit;
    }

    public Double getOddPrice() {
        return oddPrice;
    }

    public void setOddPrice(Double oddPrice) {
        this.oddPrice = oddPrice;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
}
