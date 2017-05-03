package com.sanqing.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sanqing.po.Commodity;
import com.sanqing.po.CommodityClass;
import com.sanqing.service.CommodityClassService;
import com.sanqing.service.CommodityService;
import com.sanqing.util.FileToByte;

import java.io.File;
import java.util.Date;
import java.util.List;

public class CommodityAddAction extends ActionSupport {
    private CommodityClassService commodityClassService;// 业务逻辑层
    private List<CommodityClass> commodityClasses;// 商品种类列表
    private CommodityService commodityService;//商品业务逻辑组件
    private CommodityClass commodityClass;    //商品种类
    private Integer commodityClassID;        //商品种类编号
    private String commodityName;            //商品名称
    private String manufacturer;            //生产厂家
    private String commodityDepict;            //商品描述
    private Double commodityPrice;            //商品价格
    private Double fcPrice;                    //帆成网价格
    private Integer commodityAmount;        //商品总数量
    private File uploadImage;                    //上传图片文件
    private String uploadImageContentType;        //上传图片文件类型
    private String uploadImageFileName;            //上传图片文件名

    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    public CommodityClass getCommodityClass() {
        return commodityClass;
    }

    public void setCommodityClass(CommodityClass commodityClass) {
        this.commodityClass = commodityClass;
    }

    public Integer getCommodityClassID() {
        return commodityClassID;
    }

    public void setCommodityClassID(Integer commodityClassID) {
        this.commodityClassID = commodityClassID;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCommodityDepict() {
        return commodityDepict;
    }

    public void setCommodityDepict(String commodityDepict) {
        this.commodityDepict = commodityDepict;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Double getFcPrice() {
        return fcPrice;
    }

    public void setFcPrice(Double fcPrice) {
        this.fcPrice = fcPrice;
    }

    public Integer getCommodityAmount() {
        return commodityAmount;
    }

    public void setCommodityAmount(Integer commodityAmount) {
        this.commodityAmount = commodityAmount;
    }

    public File getUploadImage() {
        return uploadImage;
    }

    public void setUploadImage(File uploadImage) {
        this.uploadImage = uploadImage;
    }

    public String getUploadImageContentType() {
        return uploadImageContentType;
    }

    public void setUploadImageContentType(String uploadImageContentType) {
        this.uploadImageContentType = uploadImageContentType;
    }

    public String getUploadImageFileName() {
        return uploadImageFileName;
    }

    public void setUploadImageFileName(String uploadImageFileName) {
        this.uploadImageFileName = uploadImageFileName;
    }

    public CommodityClassService getCommodityClassService() {
        return commodityClassService;
    }

    public void setCommodityClassService(
            CommodityClassService commodityClassService) {
        this.commodityClassService = commodityClassService;
    }

    public List<CommodityClass> getCommodityClasses() {
        return commodityClasses;
    }

    public void setCommodityClasses(List<CommodityClass> commodityClasses) {
        this.commodityClasses = commodityClasses;
    }

    public String addBefore() {// 新增商品之前首先调用该方法
        commodityClasses = commodityClassService.findAllCommodityClass();// 查询所有的商品种类
        return "success";
    }

    public String execute() throws Exception {
        Commodity commodity = new Commodity();        //实例化一个商品信息对象
        commodity.setCommodityClass
                (new CommodityClass(commodityClassID));//设置商品分类
        commodity.setCommodityDepict(commodityDepict);//设置商品描述
        commodity.setCommodityLeaveNum(commodityAmount);//设置商品剩余数量
        commodity.setCommodityAmount(commodityAmount);//设置商品总数量
        commodity.setCommodityPrice(commodityPrice);//设置商品价格
        commodity.setFcPrice(fcPrice);//设置帆成网价格
        commodity.setManufacturer(manufacturer);//设置生成厂家
        commodity.setCommodityName(commodityName);//设置商品名称
        commodity.setRegTime(new Date());//设置商品上架时间
        File file = getUploadImage();
        if (file != null && file.exists()) {
            commodity.setImage(FileToByte.getBytesFromFile(file));
        }
        commodityService.addCommodity(commodity);//保存商品
        return SUCCESS;
    }
}
