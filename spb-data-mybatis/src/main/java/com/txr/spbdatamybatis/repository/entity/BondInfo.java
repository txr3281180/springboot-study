package com.txr.spbdatamybatis.repository.entity;

/**
 * Created by xinrui.tian on 2019/3/18.
 */
public class BondInfo {

    private String id;
    private String bondKey;
    private String shortName;
    private String fullName;
    private String issuerCode;
    private String bondType;
    private String bondSubtype;
    private String maturityTerm;
    private String termUnit;
    private String maturityDate;
    private String issueStartDate;
    private String issueEndDate;
    private String couponRate;
    private String modifyDate;
    private String createDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public String getBondSubtype() {
        return bondSubtype;
    }

    public void setBondSubtype(String bondSubtype) {
        this.bondSubtype = bondSubtype;
    }

    public String getMaturityTerm() {
        return maturityTerm;
    }

    public void setMaturityTerm(String maturityTerm) {
        this.maturityTerm = maturityTerm;
    }

    public String getTermUnit() {
        return termUnit;
    }

    public void setTermUnit(String termUnit) {
        this.termUnit = termUnit;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getIssueStartDate() {
        return issueStartDate;
    }

    public void setIssueStartDate(String issueStartDate) {
        this.issueStartDate = issueStartDate;
    }

    public String getIssueEndDate() {
        return issueEndDate;
    }

    public void setIssueEndDate(String issueEndDate) {
        this.issueEndDate = issueEndDate;
    }

    public String getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(String couponRate) {
        this.couponRate = couponRate;
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
        return "BondInfo{" +
                "id='" + id + '\'' +
                ", bondKey='" + bondKey + '\'' +
                ", shortName='" + shortName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", issuerCode='" + issuerCode + '\'' +
                ", bondType='" + bondType + '\'' +
                ", bondSubtype='" + bondSubtype + '\'' +
                ", maturityTerm='" + maturityTerm + '\'' +
                ", termUnit='" + termUnit + '\'' +
                ", maturityDate='" + maturityDate + '\'' +
                ", issueStartDate='" + issueStartDate + '\'' +
                ", issueEndDate='" + issueEndDate + '\'' +
                ", couponRate='" + couponRate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
