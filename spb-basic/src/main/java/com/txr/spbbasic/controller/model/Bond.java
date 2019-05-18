package com.txr.spbbasic.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by xinrui.tian on 2018/9/25.
 */
@ApiModel(value = "债券信息")
public class Bond implements Serializable{

    @ApiModelProperty(value = "债券key", position = 1)
    private String bondKey;

    @ApiModelProperty(value = "债券名称", position = 2)
    private String bondName;

    @ApiModelProperty(value = "发行人", position = 3)
    private String issuerName;

    @ApiModelProperty(value = "债券类型", position = 4)
    private String bondType;

    @ApiModelProperty(value = "发行价格", position = 5)
    private Long issuePrice;

    @ApiModelProperty(value = "票面利率", position = 6)
    private Double couponRate;

    @ApiModelProperty(value = "期限", position = 7)
    private String maturityDate;

    public Bond() {
    }

    public Bond(String bondKey, String bondName, String issuerName, String bondType, Long issuePrice, Double couponRate, String maturityDate) {
        this.bondKey = bondKey;
        this.bondName = bondName;
        this.issuerName = issuerName;
        this.bondType = bondType;
        this.issuePrice = issuePrice;
        this.couponRate = couponRate;
        this.maturityDate = maturityDate;
    }

    public String getBondKey() {
        return bondKey;
    }

    public void setBondKey(String bondKey) {
        this.bondKey = bondKey;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getBondType() {
        return bondType;
    }

    public void setBondType(String bondType) {
        this.bondType = bondType;
    }

    public Long getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(Long issuePrice) {
        this.issuePrice = issuePrice;
    }

    public Double getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(Double couponRate) {
        this.couponRate = couponRate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    @Override
    public String toString() {
        return "Bond{" +
                "bondKey='" + bondKey + '\'' +
                ", bondName='" + bondName + '\'' +
                ", issuerName='" + issuerName + '\'' +
                ", bondType='" + bondType + '\'' +
                ", issuePrice=" + issuePrice +
                ", couponRate=" + couponRate +
                ", maturityDate='" + maturityDate + '\'' +
                '}';
    }
}
