package com.txr.spbdatamybatis.repository.entity;

/**
 * Created by xinrui.tian on 2019/3/18.
 */
public class IssuerInfo {

    private String id;
    private String issuerCode;
    private String issuerName;
    private String swSector;
    private String swSubSector;
    private String province;
    private String city;
    private Double registerCapital;
    private String modifyDate;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getSwSector() {
        return swSector;
    }

    public void setSwSector(String swSector) {
        this.swSector = swSector;
    }

    public String getSwSubSector() {
        return swSubSector;
    }

    public void setSwSubSector(String swSubSector) {
        this.swSubSector = swSubSector;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getRegisterCapital() {
        return registerCapital;
    }

    public void setRegisterCapital(Double registerCapital) {
        this.registerCapital = registerCapital;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "IssuerInfo{" +
                "id='" + id + '\'' +
                ", issuerCode='" + issuerCode + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", swSector='" + swSector + '\'' +
                ", swSubSector='" + swSubSector + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", registerCapital=" + registerCapital +
                ", modifyDate='" + modifyDate + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
